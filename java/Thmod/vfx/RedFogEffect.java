package Thmod.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class RedFogEffect extends AbstractGameEffect
{
    private static final float HEAL_AMOUNT = 0.3F;
    private static final float DUR = 3.0F;
    private Color screenColor = AbstractDungeon.fadeColor.cpy();

    public RedFogEffect()
    {
        this.startingDuration = 3.0F;
        this.duration = this.startingDuration;

        this.screenColor.a = 0.0F;
    }

    public void update()
    {
        this.duration -= Gdx.graphics.getDeltaTime();
        updateBlackScreenColor();
        if (this.duration < this.startingDuration / 2.0F)
        {
            this.isDone = true;
        }
    }


    private void updateBlackScreenColor()
    {
        if (this.duration > this.startingDuration - 0.5F) {
            this.screenColor.a = Interpolation.fade.apply(1.0F, 0.0F, (this.duration - (this.startingDuration - 0.5F)) * 2.0F);
        } else if (this.duration < 1.0F) {
            this.screenColor.a = Interpolation.fade.apply(0.0F, 1.0F, this.duration);
        } else {
            this.screenColor.a = 1.0F;
        }
    }

    public void render(SpriteBatch sb)
    {
        sb.setColor(this.screenColor);
        sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0.0F, 0.0F, Settings.WIDTH, Settings.HEIGHT);
    }
}
