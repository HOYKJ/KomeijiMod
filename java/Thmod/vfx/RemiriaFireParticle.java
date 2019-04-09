package Thmod.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class RemiriaFireParticle extends AbstractGameEffect {
    private float x;
    private float y;
    private float vY;
    private TextureAtlas.AtlasRegion img;

    public RemiriaFireParticle(float x, float y) {
        this.duration = MathUtils.random(0.5F, 1.0F);
        this.startingDuration = this.duration;
        this.img = this.getImg();
        this.x = x - (float)(this.img.packedWidth / 2);
        this.y = y - (float)(this.img.packedHeight / 2);
        this.scale = Settings.scale * MathUtils.random(0.75F, 1F);
        this.vY = MathUtils.random(1.0F, 10.0F) * Settings.scale;
        this.vY *= this.vY;
        this.rotation = MathUtils.random(-20.0F, 20.0F);
        this.color =  new Color(MathUtils.random(0.8F, 1.0F), MathUtils.random(0.1F, 0.4F), MathUtils.random(0.0F, 0.3F), 1F);
    }

    private TextureAtlas.AtlasRegion getImg() {
        switch(MathUtils.random(0, 2)) {
            case 0:
                return ImageMaster.TORCH_FIRE_1;
            case 1:
                return ImageMaster.TORCH_FIRE_2;
            default:
                return ImageMaster.TORCH_FIRE_3;
        }
    }

    public void update() {
        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration < 0.0F) {
            this.isDone = true;
        }

        this.color.a = Interpolation.fade.apply(0.0F, 1.0F, this.duration / this.startingDuration);
        this.y += this.vY * Gdx.graphics.getDeltaTime();
    }

    public void render(SpriteBatch sb) {
        sb.setColor(this.color);
        sb.draw(this.img, this.x, this.y, (float)this.img.packedWidth, (float)this.img.packedHeight, (float)this.img.packedWidth, (float)this.img.packedHeight, this.scale, this.scale, this.rotation);
    }

    public void dispose() {
    }
}
