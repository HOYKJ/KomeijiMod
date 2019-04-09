package Thmod.Actions.unique;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.Iterator;

import Thmod.Power.BoundariesPower;

public class RemoveBuffsAction extends AbstractGameAction
{
    private AbstractCreature c;

    public RemoveBuffsAction(AbstractCreature c)
    {
        this.c = c;
        this.duration = 0.5F;
    }

    public void update()
    {
        for (AbstractPower p : this.c.powers) {
            if ((p.type == AbstractPower.PowerType.BUFF) && !(p instanceof BoundariesPower)) {
                AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.c, this.c, p));
            }
        }
        this.isDone = true;
    }
}
