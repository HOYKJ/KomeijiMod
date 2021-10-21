package Thmod.vfx.weather;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class KousaDustEffect extends AbstractGameEffect
{
    private TextureAtlas.AtlasRegion img;
    private boolean flipY = MathUtils.randomBoolean();
    private boolean flipX = MathUtils.randomBoolean();
    private float aV;
    private float vY;
    private float vX;
    private float y;
    private float x;

    public KousaDustEffect()
    {
        this.duration = MathUtils.random(3.0F, 4.0F);
        this.startingDuration = this.duration;
        switch (MathUtils.random(2))
        {
            case 0:
                this.img = ImageMaster.SMOKE_1;
                break;
            case 1:
                this.img = ImageMaster.SMOKE_2;
                break;
            default:
                this.img = ImageMaster.SMOKE_3;
        }
        this.x = (-600.0F * Settings.scale - this.img.packedWidth / 2.0F);
        this.y = MathUtils.random(1.0F, 20.0F);
        this.y *= this.y;
        this.y *= this.y * Settings.scale;
        this.y -= this.img.packedHeight / 2.0F;
        this.vX = (MathUtils.random(400.0F, 1200.0F) * Settings.scale);
        this.vY = (MathUtils.random(-20.0F, 20.0F) * Settings.scale);
        this.aV = MathUtils.random(-50.0F, 50.0F);

        float tmp = MathUtils.random(0.2F, 0.3F);
        this.color = new Color();
        this.color.g = (tmp + MathUtils.random(0.2F, 0.3F));
        this.color.r = (this.color.g + MathUtils.random(0.15F, 0.2F));
        this.color.b = tmp;
        this.scale = (MathUtils.random(6.0F, 8.0F) * Settings.scale);
    }

    public void update()
    {
        this.x += this.vX * Gdx.graphics.getDeltaTime();
        this.y += this.vY * Gdx.graphics.getDeltaTime();
        this.rotation += this.aV * Gdx.graphics.getDeltaTime();
        if (this.startingDuration - this.duration < 1.0F) {
            this.color.a = Interpolation.fade.apply(0.4F, 0.4F, (this.startingDuration - this.duration) / 1.0F);
        } else if (this.duration < 1.0F) {
            this.color.a = Interpolation.fade.apply(0.4F, 0.0F, 1.0F - this.duration / 1.0F);
        }
        this.duration -= Gdx.graphics.getDeltaTime();
        this.scale += Gdx.graphics.getDeltaTime() / 3.0F;
        if (this.duration < 0.0F) {
            this.isDone = true;
        }
    }

    public void render(SpriteBatch sb, float srcX, float srcY)
    {
        sb.setColor(this.color);
        if ((this.flipX) && (!this.img.isFlipX())) {
            this.img.flip(true, false);
        } else if ((!this.flipX) && (this.img.isFlipX())) {
            this.img.flip(true, false);
        }
        if ((this.flipY) && (!this.img.isFlipY())) {
            this.img.flip(false, true);
        } else if ((!this.flipY) && (this.img.isFlipY())) {
            this.img.flip(false, true);
        }
        sb.draw(this.img, this.x, this.y + srcY, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale, this.rotation);
    }

    public void dispose() {}

    public void render(SpriteBatch sb) {
        this.render(sb, 0.0F, -50.0F * Settings.scale + Settings.HEIGHT - 1300.0F * Settings.scale);
    }
}
