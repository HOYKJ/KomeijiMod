package Thmod.Actions.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;

public class DashAction extends AbstractGameAction {
    private AbstractCreature target;
    private boolean isPlayer;
    private float animationTimer = 0.4F;

    public DashAction(AbstractCreature target,boolean isPlayer){
        this.target = target;
        this.isPlayer = isPlayer;
    }

    @Override
    public void update() {
        this.animationTimer -= Gdx.graphics.getDeltaTime();
        float targetPos = 90.0F * Settings.scale;
        if (this.isPlayer) {
            targetPos = -targetPos;
        }
        if (this.animationTimer > 0.5F)
        {
            target.animX = Interpolation.exp5In.apply(0.0F, targetPos, (1.0F - this.animationTimer / 1.0F) * 2.0F);
        }
        else if (this.animationTimer < 0.0F)
        {
            this.animationTimer = 0.0F;
            target.animX = 0.0F;
            this.isDone = true;
        }
        else
        {
            target.animX = Interpolation.fade.apply(0.0F, targetPos, this.animationTimer / 1.0F * 2.0F);
        }
    }
}
