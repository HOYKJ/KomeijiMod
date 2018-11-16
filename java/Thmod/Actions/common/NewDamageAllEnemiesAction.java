package Thmod.Actions.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
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

import java.util.Iterator;

import Thmod.vfx.NewStrikeEffect;
import basemod.DevConsole;

import static com.megacrit.cardcrawl.core.AbstractCreature.TEXT;

public class NewDamageAllEnemiesAction extends AbstractGameAction
{
    public int[] damage;
    private int num;
    private boolean firstFrame = true;

    private float BLOCK_ICON_X = -14.0F * Settings.scale;
    private float BLOCK_ICON_Y = -14.0F * Settings.scale;

    public NewDamageAllEnemiesAction(AbstractCreature source, int[] amount, DamageInfo.DamageType type, AbstractGameAction.AttackEffect effect, int num)
    {
        setValues(null, source, amount[0]);
        this.damage = amount;
        this.actionType = AbstractGameAction.ActionType.DAMAGE;
        this.damageType = type;
        this.attackEffect = effect;

        this.duration = 0.03F;
        this.num = num;
        DevConsole.logger.info("num2 " + num);
    }

    public void update()
    {
        boolean playedMusic;
        if (this.firstFrame)
        {
            playedMusic = false;
            int temp = AbstractDungeon.getCurrRoom().monsters.monsters.size();
            for (int i = 0; i < temp; i++) {
                if ((!(AbstractDungeon.getCurrRoom().monsters.monsters.get(i)).isDying) &&
                        ((AbstractDungeon.getCurrRoom().monsters.monsters.get(i)).currentHealth > 0) &&
                        (!(AbstractDungeon.getCurrRoom().monsters.monsters.get(i)).isEscaping)) {
                    if (playedMusic)
                    {
                        AbstractDungeon.effectList.add(new FlashAtkImgEffect(

                                (AbstractDungeon.getCurrRoom().monsters.monsters.get(i)).hb.cX,
                                (AbstractDungeon.getCurrRoom().monsters.monsters.get(i)).hb.cY, this.attackEffect, true));
                    }
                    else
                    {
                        playedMusic = true;
                        AbstractDungeon.effectList.add(new FlashAtkImgEffect(

                                (AbstractDungeon.getCurrRoom().monsters.monsters.get(i)).hb.cX,
                                (AbstractDungeon.getCurrRoom().monsters.monsters.get(i)).hb.cY, this.attackEffect));
                    }
                }
            }
            this.firstFrame = false;
        }
        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration <= 0)
        {
            for (AbstractPower p : AbstractDungeon.player.powers) {
                p.onDamageAllEnemies(this.damage);
            }
            int temp = AbstractDungeon.getCurrRoom().monsters.monsters.size();
            for (int i = 0; i < temp; i++) {
                if (!(AbstractDungeon.getCurrRoom().monsters.monsters.get(i)).isDeadOrEscaped())
                {
                    if (this.attackEffect == AbstractGameAction.AttackEffect.POISON)
                    {
                        (AbstractDungeon.getCurrRoom().monsters.monsters.get(i)).tint.color = Color.CHARTREUSE.cpy();
                        (AbstractDungeon.getCurrRoom().monsters.monsters.get(i)).tint.changeColor(Color.WHITE.cpy());
                    }
                    else if (this.attackEffect == AbstractGameAction.AttackEffect.FIRE)
                    {
                        (AbstractDungeon.getCurrRoom().monsters.monsters.get(i)).tint.color = Color.RED.cpy();
                        (AbstractDungeon.getCurrRoom().monsters.monsters.get(i)).tint.changeColor(Color.WHITE.cpy());
                    }
//                    (AbstractDungeon.getCurrRoom().monsters.monsters.get(i)).damage(new DamageInfo(this.source, this.damage[i], this.damageType));
                    if(damage[i] >= 1) {
                        damage(new DamageInfo(this.source, 1, this.damageType), AbstractDungeon.getCurrRoom().monsters.monsters.get(i));
                        damage[i] --;
                    }
                }
            }
            if(this.num >= 1){
                this.duration = 0.03F;
                this.num --;
                this.firstFrame = true;
            }
            else {
                this.isDone = true;
            }
            if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                AbstractDungeon.actionManager.clearPostCombatActions();
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
