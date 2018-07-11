package Thmod.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class BorderVeryLongFlashEffect extends AbstractGameEffect
{
    private TextureAtlas.AtlasRegion img = ImageMaster.BORDER_GLOW_2;
    private static final float DUR = 2.0F;
    private float startAlpha;

    public BorderVeryLongFlashEffect(Color color)
    {
        this.duration = 4F;
        this.startAlpha = color.a;
        this.color = color.cpy();
        this.color.a = 0F;
    }

    public void update() {
        if (4F - this.duration < 0.20000000298023224F)
            this.color.a = Interpolation.fade.apply(0F, this.startAlpha, (4F - this.duration) * 10.0F);
        else {
            this.color.a = Interpolation.pow2Out.apply(0F, this.startAlpha, this.duration / 4F);
        }

        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration < 0F)
            this.isDone = true;
    }

    public void render(SpriteBatch sb)
    {
        sb.setBlendFunction(770, 1);
        sb.setColor(this.color);
        sb.draw(this.img, 0F, 0F, Settings.WIDTH, Settings.HEIGHT);
        sb.setBlendFunction(770, 771);
    }
}
