package Thmod.vfx.animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class ColorFlameParticelEffect extends AbstractGameEffect {
    private float x;
    private float y;
    private float vY;
    private float alpha;
    private float delay;
    private TextureAtlas.AtlasRegion img;
    public static boolean renderGreen = false;

    public ColorFlameParticelEffect(float x, float y, Color color, boolean behind, float delay){
        this(x, y, color, 1, behind, delay);
    }

    public ColorFlameParticelEffect(float x, float y, Color color, float scale, boolean behind, float delay) {
        this(x, y, color, 1, behind, delay, MathUtils.random(1.0F, 10.0F));
    }

    public ColorFlameParticelEffect(float x, float y, Color color, float scale, boolean behind, float delay, float vY) {
        this.delay = delay;
        this.duration = MathUtils.random(1.5F, 2.5F);
        this.startingDuration = this.duration;
        this.img = this.getImg();
        this.x = x - (float)(this.img.packedWidth / 2) + MathUtils.random(-1.0F, 1.0F) * Settings.scale;
        this.y = y - (float)(this.img.packedHeight / 2);
        this.scale = Settings.scale * MathUtils.random(2.0F, 3.0F) * scale;
        this.vY = vY;
        this.vY *= this.vY * Settings.scale;
        this.rotation = MathUtils.random(-20.0F, 20.0F);

        this.color = color.cpy();
        this.color.r += MathUtils.random(-0.1F, 0.1F);
        this.color.g += MathUtils.random(-0.1F, 0.1F);
        this.color.b += MathUtils.random(-0.1F, 0.1F);
        if(this.color.r > 1){
            this.color.r = 1;
        }
        else if(this.color.r < 0){
            this.color.r = 0;
        }
        if(this.color.g > 1){
            this.color.g = 1;
        }
        else if(this.color.g < 0){
            this.color.g = 0;
        }
        if(this.color.b > 1){
            this.color.b = 1;
        }
        else if(this.color.b < 0){
            this.color.b = 0;
        }

        this.renderBehind = behind;
        this.alpha = this.color.a;
        this.color.a = 0.01F;
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
        if(this.delay < 0) {
            this.duration -= Gdx.graphics.getDeltaTime();

            this.color.a = Interpolation.fade.apply(0.0F, 0.75F * this.alpha, this.duration / this.startingDuration);
            this.y += this.vY * Gdx.graphics.getDeltaTime();
        }
        else {
            this.delay -= Gdx.graphics.getDeltaTime();
        }

        if (this.duration < 0.0F) {
            this.isDone = true;
        }
    }

    public void render(SpriteBatch sb) {
        if(this.delay < 0) {
            sb.setBlendFunction(770, 1);
            sb.setColor(this.color);
            sb.draw(this.img, this.x, this.y, (float) this.img.packedWidth / 2.0F, (float) this.img.packedHeight / 2.0F, (float) this.img.packedWidth, (float) this.img.packedHeight, this.scale, this.scale, this.rotation);
            sb.setBlendFunction(770, 771);
        }
    }

    public void dispose() {
    }
}
