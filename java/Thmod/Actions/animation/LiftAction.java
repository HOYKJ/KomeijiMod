package Thmod.Actions.animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.ShakeScreenAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;

import java.lang.reflect.Field;

public class LiftAction extends AbstractGameAction
{
    private boolean called = false;

    public LiftAction(AbstractCreature target)
    {
        setValues(target, null, 0);
        this.duration = 2.0f;
        target.animX = 0.0F;
        target.animY = 0.0F;
    }

    public void update()
    {
        this.duration -= Gdx.graphics.getDeltaTime();

        if(this.duration <= 0){
            this.target.animY = 0;
            this.isDone = true;
            this.target.useShakeAnimation(2);
            CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.HIGH, ScreenShake.ShakeDur.XLONG, false);
        }
        else if(this.duration <= 1) {
            if (!this.called) {
                AbstractDungeon.effectList.add(new WeightyImpactEffect(this.target.hb.cX, this.target.hb.cY, Color.ORANGE));
                this.called = true;
            }
            this.target.animY = Interpolation.fade.apply(Settings.HEIGHT, 0, 1.0F - this.duration);
        }
        else{
            this.target.animY = Interpolation.fade.apply(0, Settings.HEIGHT, 2.0F - this.duration);
        }
    }
}
