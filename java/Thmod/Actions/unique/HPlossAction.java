package Thmod.Actions.unique;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class HPlossAction extends AbstractGameAction{
    private AbstractCreature target;
    private int HPloss;

    public HPlossAction(AbstractCreature target,int HPloss){
        this.target = target;
        this.HPloss = HPloss;
    }

    public void update(){
        this.target.currentHealth -= this.HPloss;
        this.target.healthBarUpdatedEvent();
        this.isDone = true;
    }
}
