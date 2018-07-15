package Thmod.Actions.unique;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.Iterator;

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
        for (Iterator localIterator = this.c.powers.iterator(); localIterator.hasNext(); ) { AbstractPower p = (AbstractPower)localIterator.next();
            if (p.type == AbstractPower.PowerType.BUFF)
                AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.c, this.c, p.ID));

        }

        this.isDone = true;
    }
}
