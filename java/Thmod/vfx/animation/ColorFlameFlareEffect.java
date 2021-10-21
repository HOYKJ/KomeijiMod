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

public class ColorFlameFlareEffect extends AbstractGameEffect {
    private float x;
    private float y;
    private float alpha;
    public static TextureAtlas.AtlasRegion[] imgs = new TextureAtlas.AtlasRegion[2];
    private TextureAtlas.AtlasRegion img;
    public static boolean renderGreen = false;

    public ColorFlameFlareEffect(float x, float y, Color color, boolean behind, float time){
        this(x, y, color, 1, behind, time);
    }

    public ColorFlameFlareEffect(float x, float y, Color color, float scale, boolean behind, float time) {
        if (imgs[0] == null) {
            imgs[0] = ImageMaster.vfxAtlas.findRegion("env/lightFlare1");
            imgs[1] = ImageMaster.vfxAtlas.findRegion("env/lightFlare2");
        }

        this.duration = 1.5f + time;
        this.startingDuration = this.duration;
        this.img = imgs[MathUtils.random(imgs.length - 1)];
        this.x = x - (float)(this.img.packedWidth / 2);
        this.y = y - (float)(this.img.packedHeight / 2);
        this.scale = Settings.scale * MathUtils.random(1.0F, 2.0F) * scale;
        this.rotation = MathUtils.random(360.0F);

        //this.color = new Color(MathUtils.random(0.1F, 0.3F), MathUtils.random(0.5F, 0.9F), MathUtils.random(0.1F, 0.3F), 0.01F);
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

    public void update() {
        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration < 0.0F) {
            this.isDone = true;
        }

        if (this.startingDuration - this.duration < 1.0F) {
            this.color.a = Interpolation.fade.apply(0.2F * this.alpha, 0.0F, this.duration / this.startingDuration);
        } else {
            this.color.a = Interpolation.fade.apply(0.0F, 0.2F * this.alpha, this.duration / this.startingDuration);
        }

    }

    public void render(SpriteBatch sb) {
        sb.setBlendFunction(770, 1);
        sb.setColor(this.color);
        sb.draw(this.img, this.x, this.y, (float)this.img.packedWidth / 2.0F, (float)this.img.packedHeight / 2.0F, (float)this.img.packedWidth, (float)this.img.packedHeight, this.scale, this.scale, this.rotation);
        sb.setBlendFunction(770, 771);
    }

    public void dispose() {
    }
}
