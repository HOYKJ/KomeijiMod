package Thmod.Actions.common;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

public class NeedleMountainAttack extends AbstractGameAction
{
    private DamageInfo info;
    private int numTimes;

    public NeedleMountainAttack(AbstractCreature target, AbstractCreature source, int numTimes, AttackEffect attackEffect)
    {
        this.info = new DamageInfo(source, 1, DamageInfo.DamageType.NORMAL);
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
            AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, this.attackEffect));
            //this.info.applyPowers(this.info.owner, this.target);
            this.target.damage(this.info);

            AbstractMonster monster = AbstractDungeon.getMonsters().getRandomMonster(true);

            if ((this.numTimes > 1) && (!(AbstractDungeon.getMonsters().areMonstersBasicallyDead()))) {
                this.numTimes -= 1;
                AbstractDungeon.actionManager.addToTop(new NeedleMountainAttack(monster, this.info.owner, this.numTimes,this.attackEffect));
            }

            int roll = MathUtils.random(2);
            switch (roll) {
                case 0:
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster, this.info.owner, new WeakPower(monster, 1, false), 1));
                    break;
                case 1:
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster, this.info.owner, new VulnerablePower(monster, 1, false), 1));
                    break;
                case 2:
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster, this.info.owner, new StrengthPower(monster, -1), -1));
                    break;
                default:
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster, this.info.owner, new WeakPower(monster, 1, false), 1));
            }
        }

        this.isDone = true;
    }
}
