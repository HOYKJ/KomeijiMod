package Thmod.Actions.Remiria;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;

import Thmod.Power.BoundariesPower;

public class RemoveRandomBuffAction extends AbstractGameAction
{
    private AbstractCreature c;

    public RemoveRandomBuffAction(AbstractCreature c)
    {
        this.c = c;
        this.duration = 0.5F;
    }

    public void update()
    {
        ArrayList<AbstractPower> powers = new ArrayList<>();
        for (AbstractPower p : this.c.powers) {
            if ((p.type == AbstractPower.PowerType.BUFF) && !(p instanceof BoundariesPower)) {
                powers.add(p);
            }
        }
        if(powers.size() > 0) {
            AbstractPower p = powers.get(AbstractDungeon.cardRandomRng.random(0, powers.size() - 1));
            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.c, this.c, p));
        }
        this.isDone = true;
    }
}
