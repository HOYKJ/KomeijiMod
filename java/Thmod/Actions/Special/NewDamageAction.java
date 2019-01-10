package Thmod.Actions.Special;

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
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
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
    private boolean fire;

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

    public NewDamageAction(AbstractCreature target, DamageInfo info, AttackEffect effect, boolean superFast, boolean muteSfx, boolean fire) {
        this(target, info, effect, superFast);
        this.muteSfx = muteSfx;
        this.fire = fire;
    }

    public void update() {
        if (this.shouldCancelAction() && this.info.type != DamageInfo.DamageType.THORNS) {
            this.isDone = true;
        } else {
            if (this.duration == 0.0F) {
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

//                if(this.fire)
                    this.target.damage(this.info);
//                else {
//                    AbstractGameEffect removeEffect = null;
//                    for (AbstractGameEffect effect : AbstractDungeon.effectList) {
//                        if (effect instanceof StrikeEffect) {
//                            removeEffect = effect;
//                        }
//                    }
//                    if(!(removeEffect == null)){
//                        AbstractDungeon.effectList.remove(removeEffect);
//                        damage(this.info, (AbstractMonster) this.target);
//                    }
//                }

//                AbstractDungeon.effectList.clear();
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

            damageAmount = decrementBlock(info, damageAmount,m);

            if (damageAmount > 0) {
                AbstractDungeon.effectList.add(new NewStrikeEffect(m, m.hb.cX, m.hb.cY, damageAmount));
            }
        }
    }

    private int decrementBlock(DamageInfo info, int damageAmount, AbstractMonster m) {
        if (info.type != DamageInfo.DamageType.HP_LOSS && m.currentBlock > 0) {
            if (damageAmount > m.currentBlock) {
                damageAmount -= m.currentBlock;
            } else if (damageAmount == m.currentBlock) {
                damageAmount = 0;
            } else {
                damageAmount = 0;
            }
        }

        return damageAmount;
    }
}
