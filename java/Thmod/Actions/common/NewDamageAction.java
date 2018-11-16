package Thmod.Actions.common;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.combat.BlockImpactLineEffect;
import com.megacrit.cardcrawl.vfx.combat.BlockedNumberEffect;
import com.megacrit.cardcrawl.vfx.combat.BlockedWordEffect;
import com.megacrit.cardcrawl.vfx.combat.DeckPoofEffect;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import com.megacrit.cardcrawl.vfx.combat.HbBlockBrokenEffect;
import com.megacrit.cardcrawl.vfx.combat.StrikeEffect;

import java.util.Iterator;

import Thmod.vfx.NewStrikeEffect;

import static com.megacrit.cardcrawl.core.AbstractCreature.TEXT;

public class NewDamageAction extends AbstractGameAction {
    private DamageInfo info;
    private int goldAmount;
    private static final float DURATION = 0.1F;
    private static final float POST_ATTACK_WAIT_DUR = 0.1F;
    private boolean skipWait;
    private boolean muteSfx;

    private float BLOCK_ICON_X = -14.0F * Settings.scale;
    private float BLOCK_ICON_Y = -14.0F * Settings.scale;

    public NewDamageAction(AbstractCreature target, DamageInfo info, AttackEffect effect) {
        this.goldAmount = 0;
        this.skipWait = false;
        this.muteSfx = false;
        this.info = info;
        this.setValues(target, info);
        this.actionType = ActionType.DAMAGE;
        this.attackEffect = effect;
        this.duration = 0.0F;
    }

    public NewDamageAction(AbstractCreature target, DamageInfo info, int stealGoldAmount) {
        this(target, info, AttackEffect.SLASH_DIAGONAL);
        this.goldAmount = stealGoldAmount;
    }

    public NewDamageAction(AbstractCreature target, DamageInfo info) {
        this(target, info, AttackEffect.NONE);
    }

    public NewDamageAction(AbstractCreature target, DamageInfo info, boolean superFast) {
        this(target, info, AttackEffect.NONE);
        this.skipWait = superFast;
    }

    public NewDamageAction(AbstractCreature target, DamageInfo info, AttackEffect effect, boolean superFast) {
        this(target, info, effect);
        this.skipWait = superFast;
    }

    public NewDamageAction(AbstractCreature target, DamageInfo info, AttackEffect effect, boolean superFast, boolean muteSfx) {
        this(target, info, effect, superFast);
        this.muteSfx = muteSfx;
    }

    public void update() {
        if (this.shouldCancelAction() && this.info.type != DamageInfo.DamageType.THORNS) {
            this.isDone = true;
        } else {
            if (this.duration == 0.1F) {
                if (this.info.type != DamageInfo.DamageType.THORNS && (this.info.owner.isDying || this.info.owner.halfDead)) {
                    this.isDone = true;
                    return;
                }

                AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, this.attackEffect, this.muteSfx));
            }

            this.tickDuration();
            if (this.isDone) {
                if (this.attackEffect == AttackEffect.POISON) {
                    this.target.tint.color = Color.CHARTREUSE.cpy();
                    this.target.tint.changeColor(Color.WHITE.cpy());
                } else if (this.attackEffect == AttackEffect.FIRE) {
                    this.target.tint.color = Color.RED.cpy();
                    this.target.tint.changeColor(Color.WHITE.cpy());
                }

                if(this.target instanceof AbstractMonster){
                    AbstractMonster m = (AbstractMonster)this.target;
                    damage(this.info,m);
                }
                if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                    AbstractDungeon.actionManager.clearPostCombatActions();
                }

                if (!this.skipWait && !Settings.FAST_MODE) {
                    AbstractDungeon.actionManager.addToTop(new WaitAction(0.1F));
                }
            }

        }
    }

    private void damage(DamageInfo info, AbstractMonster m) {
        if (info.output > 0 && m.hasPower("IntangiblePlayer")) {
            info.output = 1;
        }

        int damageAmount = info.output;
        if (!m.isDying && !m.isEscaping) {
            if (damageAmount < 0) {
                damageAmount = 0;
            }

            boolean hadBlock = true;
            if (m.currentBlock == 0) {
                hadBlock = false;
            }

            boolean weakenedToZero = damageAmount == 0;
            damageAmount = decrementBlock(info, damageAmount,m);
            Iterator var5;
            AbstractRelic r;
            if (info.owner instanceof AbstractPlayer) {
                var5 = AbstractDungeon.player.relics.iterator();

                while(var5.hasNext()) {
                    r = (AbstractRelic)var5.next();
                    r.onAttack(info, damageAmount, m);
                }
            }

            AbstractPower p;
            if (info.owner != null) {
                var5 = info.owner.powers.iterator();

                while(var5.hasNext()) {
                    p = (AbstractPower)var5.next();
                    p.onAttack(info, damageAmount, m);
                }
            }

            for(var5 = m.powers.iterator(); var5.hasNext(); damageAmount = p.onAttacked(info, damageAmount)) {
                p = (AbstractPower)var5.next();
            }

            for(var5 = AbstractDungeon.player.relics.iterator(); var5.hasNext(); damageAmount = r.onAttackedMonster(info, damageAmount)) {
                r = (AbstractRelic)var5.next();
            }

            if (damageAmount > 0) {
                if (info.owner != m) {
                    m.useStaggerAnimation();
                }

                if (damageAmount >= 99 && !CardCrawlGame.overkill) {
                    CardCrawlGame.overkill = true;
                }

                m.currentHealth -= damageAmount;
                AbstractDungeon.effectList.add(new NewStrikeEffect(m, m.hb.cX, m.hb.cY, damageAmount));
                if (m.currentHealth < 0) {
                    m.currentHealth = 0;
                }

                m.healthBarUpdatedEvent();
            } else if (weakenedToZero && m.currentBlock == 0) {
                if (hadBlock) {
                    AbstractDungeon.effectList.add(new BlockedWordEffect(m, m.hb.cX, m.hb.cY, AbstractMonster.TEXT[30]));
                } else {
                    AbstractDungeon.effectList.add(new NewStrikeEffect(m, m.hb.cX, m.hb.cY, 0));
                }
            } else if (Settings.SHOW_DMG_BLOCK) {
                AbstractDungeon.effectList.add(new BlockedWordEffect(m, m.hb.cX, m.hb.cY, AbstractMonster.TEXT[30]));
            }

            if (m.currentHealth <= 0) {
                m.die();
                if (AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
                    AbstractDungeon.actionManager.cleanCardQueue();
                    AbstractDungeon.effectList.add(new DeckPoofEffect(64.0F * Settings.scale, 64.0F * Settings.scale, true));
                    AbstractDungeon.effectList.add(new DeckPoofEffect((float)Settings.WIDTH - 64.0F * Settings.scale, 64.0F * Settings.scale, false));
                    AbstractDungeon.overlayMenu.hideCombatPanels();
                }

                if (m.currentBlock > 0) {
                    m.loseBlock();
                    AbstractDungeon.effectList.add(new HbBlockBrokenEffect(m.hb.cX - m.hb.width / 2.0F + BLOCK_ICON_X, m.hb.cY - m.hb.height / 2.0F + BLOCK_ICON_Y));
                }
            }

        }
    }

    private int decrementBlock(DamageInfo info, int damageAmount, AbstractMonster m) {
        if (info.type != DamageInfo.DamageType.HP_LOSS && m.currentBlock > 0) {
            CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, false);
            if (damageAmount > m.currentBlock) {
                damageAmount -= m.currentBlock;
                if (Settings.SHOW_DMG_BLOCK) {
                    AbstractDungeon.effectList.add(new BlockedNumberEffect(m.hb.cX, m.hb.cY + m.hb.height / 2.0F, Integer.toString(m.currentBlock)));
                }

                m.loseBlock();
                brokeBlock(m);
            } else if (damageAmount == m.currentBlock) {
                damageAmount = 0;
                m.loseBlock();
                brokeBlock(m);
                AbstractDungeon.effectList.add(new BlockedWordEffect(m, m.hb.cX, m.hb.cY, TEXT[1]));
            } else {
                CardCrawlGame.sound.play("BLOCK_ATTACK");
                m.loseBlock(damageAmount);

                for(int i = 0; i < 18; ++i) {
                    AbstractDungeon.effectList.add(new BlockImpactLineEffect(m.hb.cX, m.hb.cY));
                }

                if (Settings.SHOW_DMG_BLOCK) {
                    AbstractDungeon.effectList.add(new BlockedNumberEffect(m.hb.cX, m.hb.cY + m.hb.height / 2.0F, Integer.toString(damageAmount)));
                }

                damageAmount = 0;
            }
        }

        return damageAmount;
    }

    private void brokeBlock(AbstractMonster m) {
        if (m instanceof AbstractMonster) {
            Iterator var1 = AbstractDungeon.player.relics.iterator();

            while(var1.hasNext()) {
                AbstractRelic r = (AbstractRelic)var1.next();
                r.onBlockBroken(m);
            }
        }

        AbstractDungeon.effectList.add(new HbBlockBrokenEffect(m.hb.cX - m.hb.width / 2.0F + BLOCK_ICON_X, m.hb.cY - m.hb.height / 2.0F + BLOCK_ICON_Y));
        CardCrawlGame.sound.play("BLOCK_BREAK");
    }
}
