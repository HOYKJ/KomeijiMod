package Thmod.Actions.common;

import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;

import java.util.ArrayList;

public class LatterAction extends AbstractGameAction {
    private Runnable action;

    public LatterAction(final Runnable action, float lateTime){
        this.action = action;
        this.duration = lateTime;
    }

    public void update(){
        this.duration -= Gdx.graphics.getDeltaTime();
        if(this.duration <= 0.0F){
            this.action.run();
            this.isDone = true;
        }
    }
}
