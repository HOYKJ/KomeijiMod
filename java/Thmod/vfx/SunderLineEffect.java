package Thmod.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class SunderLineEffect extends AbstractGameEffect
{
    float x;
    float y;
    private TextureAtlas.AtlasRegion img;
    private TextureAtlas.AtlasRegion[] imgs = new TextureAtlas.AtlasRegion[3];

    public SunderLineEffect(float x, float y, float rotation, Color color)
    {
        if (imgs[0] == null)
        {
            imgs[0] = ImageMaster.vfxAtlas.findRegion("env/fire1");
            imgs[1] = ImageMaster.vfxAtlas.findRegion("env/fire2");
            imgs[2] = ImageMaster.vfxAtlas.findRegion("env/fire3");
        }
        this.img = imgs[MathUtils.random(imgs.length - 1)];
        this.x = x;
        this.y = y;
        this.startingDuration = 1.0F;
        this.duration = this.startingDuration;

        this.rotation = rotation;

        this.scale = (Settings.scale * MathUtils.random(1.0F, 3.0F));
        this.color = color;
        this.renderBehind = false;
    }

    public void update()
    {
        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration > this.startingDuration / 3.0F * 2) {
            this.color.a = (this.startingDuration - this.duration) / this.startingDuration * 3 * 0.8f;
        } else {
            this.color.a = this.duration / this.startingDuration * 3 / 2 * 0.8f;
        }
        if (this.duration < 0.0F) {
            this.isDone = true;
        }
    }

    public void render(SpriteBatch sb)
    {
        sb.setColor(this.color);
        sb.setBlendFunction(770, 1);
        sb.draw(this.img, this.x - this.img.packedWidth / 2.0F, this.y - this.img.packedHeight / 2.0F, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F,
                this.img.packedWidth, this.img.packedHeight, this.scale * Settings.scale, this.scale * Settings.scale, this.rotation);

        sb.setBlendFunction(770, 771);
    }

    public void dispose()
    {
    }
}
