package Thmod.Actions.Remiria;

import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

import Thmod.Actions.common.RandomAttackAction;
import Thmod.Power.remiria.BloodBruisePower;
import Thmod.vfx.SeventeenArticlesEffect;

public class SeventeenArticlesAction extends AbstractGameAction
{
    private DamageInfo info;
    private int numTimes;
    private boolean isPlus, effect;

    public SeventeenArticlesAction(AbstractCreature target, DamageInfo info, int numTimes, AbstractGameAction.AttackEffect attackEffect, boolean isPlus)
    {
        this.info = info;
        this.target = target;
        this.actionType = AbstractGameAction.ActionType.DAMAGE;
        this.attackEffect = attackEffect;
        this.duration = 0.25F;
        this.numTimes = numTimes;
        this.isPlus = isPlus;
        this.effect = true;
    }

    public void update()
    {
        if (this.target == null) {
            this.isDone = true;
            return;
        }

        if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
            AbstractDungeon.actionManager.clearPostCombatActions();
            this.isDone = true;
            return;
        }

        this.duration -= Gdx.graphics.getDeltaTime();

        if((this.effect) && (this.target.currentHealth > 0)){
            AbstractDungeon.effectsQueue.add(new SeventeenArticlesEffect(this.info.owner.hb.cX, this.info.owner.hb.cY, this.target.hb.cX, this.target.hb.cY));
            this.effect = false;
        }

        if(this.duration <= 0) {
            if (this.target.currentHealth > 0) {
                if (this.isPlus) {
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.target, this.info.owner, new BloodBruisePower(this.target, 1), 1));
                }
                AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, this.attackEffect));
                this.info.applyPowers(this.info.owner, this.target);
                this.target.damage(this.info);

                if ((this.numTimes > 1) && (!(AbstractDungeon.getMonsters().areMonstersBasicallyDead()))) {
                    this.numTimes -= 1;
                    AbstractDungeon.actionManager.addToTop(new SeventeenArticlesAction(AbstractDungeon.getMonsters().getRandomMonster(true), this.info, this.numTimes,
                            this.attackEffect, this.isPlus));
                }

                AbstractDungeon.actionManager.addToTop(new WaitAction(0.05F));
            }
            this.isDone = true;
        }
    }
}
