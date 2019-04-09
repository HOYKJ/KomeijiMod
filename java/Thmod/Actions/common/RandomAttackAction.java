package Thmod.Actions.common;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

public class RandomAttackAction extends AbstractGameAction
{
    private DamageInfo info;
    private int numTimes;

    public RandomAttackAction(AbstractCreature target, DamageInfo info, int numTimes, AbstractGameAction.AttackEffect attackEffect)
    {
        this.info = info;
        this.target = target;
        this.actionType = AbstractGameAction.ActionType.DAMAGE;
        this.attackEffect = attackEffect;
        this.duration = 0.01F;
        this.numTimes = numTimes;
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

        if (this.target.currentHealth > 0) {
            this.target.damageFlash = true;
            this.target.damageFlashFrames = 4;
            AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, this.attackEffect));
            this.info.applyPowers(this.info.owner, this.target);
            this.target.damage(this.info);

            if ((this.numTimes > 1) && (!(AbstractDungeon.getMonsters().areMonstersBasicallyDead()))) {
                this.numTimes -= 1;
                AbstractDungeon.actionManager.addToTop(new RandomAttackAction(AbstractDungeon.getMonsters().getRandomMonster(true), this.info, this.numTimes,this.attackEffect));
            }

            AbstractDungeon.actionManager.addToTop(new WaitAction(0.2F));
        }

        this.isDone = true;
    }
}

