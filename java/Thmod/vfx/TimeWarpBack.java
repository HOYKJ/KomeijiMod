package Thmod.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class TimeWarpBack extends AbstractGameEffect
{
    private float x;
    private float y;
    private static TextureAtlas.AtlasRegion img = null;

    public TimeWarpBack()
    {
        if (img == null) {
            img = AbstractPower.atlas.findRegion("128/time");
        }
        this.startingDuration = 2.0F;
        this.duration = this.startingDuration;
        this.scale = (Settings.scale * 3.0F);
        this.x = (Settings.WIDTH * 0.5F - img.packedWidth / 2.0F);
        this.y = (img.packedHeight / 2.0F);
        this.color = Color.DARK_GRAY.cpy();
    }

    public void update()
    {
        if(this.duration == 2.0F){
            CardCrawlGame.sound.play("POWER_TIME_WARP", 0.05F);
        }
        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration < 0.0F) {
            this.isDone = true;
        }
        if (this.duration < 1.0F) {
            this.color.a = Interpolation.fade.apply(0.0F, 1.0F, this.duration);
        } else {
            this.y = Interpolation.swingIn.apply(Settings.HEIGHT * 0.7F - img.packedHeight / 2.0F, -img.packedHeight / 2.0F, this.duration - 1.0F);
        }
    }

    public void render(SpriteBatch sb)
    {
        sb.setColor(this.color);
        sb.draw(img, this.x, this.y, img.packedWidth / 2.0F, img.packedHeight / 2.0F, img.packedWidth, img.packedHeight, this.scale, this.scale, this.duration * -360.0F);
    }

    public void dispose() {}
}
