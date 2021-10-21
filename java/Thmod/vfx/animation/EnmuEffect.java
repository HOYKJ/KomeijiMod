package Thmod.vfx.animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class EnmuEffect extends AbstractGameEffect {
    private float x;
    private float y;
    private float xV;
    private float xA;
    private float scale;
    private float rotation;
    private float rV;
    private float rA;
    private float aV;
    private float aA;
    private float ro;
    private TextureAtlas.AtlasRegion img;
    private boolean flipX;
    private boolean flipY;

    public EnmuEffect(float x, float y){
        float tmp = MathUtils.random(220.0F, 255.0F) / 255;
        this.ro = MathUtils.random(0.0F, 2.0F) * (float) PI;
        this.color = new Color(tmp, tmp, tmp, MathUtils.random(0.8F, 0.9F));
        this.x = x + MathUtils.random(-30.0F, 30.0F) * Settings.scale;
        this.y = y + MathUtils.random(-30.0F, 30.0F) * Settings.scale;
        this.xV = MathUtils.random(0.0F, 100.0F) * Settings.scale;
        this.xA = MathUtils.random(1.0F, 4.0F) * Settings.scale;
        this.scale = (MathUtils.random(2.0F, 4.0F) * Settings.scale);
        this.rotation = MathUtils.random(0.0F, 360.0F);
        this.rV = MathUtils.random(-30.0F, 30.0F);
        this.rA = MathUtils.random(1.0F, 4.0F) * this.rV > 0? -1: 1;

        this.aV = MathUtils.random(0.1F, 0.3F);
        this.aA = MathUtils.random(0.005F, 0.01F);

        this.flipX = MathUtils.randomBoolean();
        this.flipY = MathUtils.randomBoolean();
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
        this.renderBehind = false;
    }

    public void update()
    {
        if(this.color.a <= 0.0F){
            this.isDone = true;
            return;
        }
        this.x += this.xV * Gdx.graphics.getDeltaTime() * cos(this.ro);
        this.y += this.xV * Gdx.graphics.getDeltaTime() * sin(this.ro);
        if(xV > 0) {
            this.xV -= this.xA * Gdx.graphics.getDeltaTime();
        }

        this.rotation += this.rV * Gdx.graphics.getDeltaTime();
        this.rV += this.rA * Gdx.graphics.getDeltaTime();

        this.color.a -= this.aV * Gdx.graphics.getDeltaTime();
        this.aV -= this.aA * Gdx.graphics.getDeltaTime();
        if(this.aV < 0.02F) {
            this.aV = 0.02F;
        }
    }

    public void render(SpriteBatch sb)
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
        sb.draw(this.img, this.x, this.y,
                this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight,
                this.scale, this.scale, this.rotation);
    }

    public void dispose() {}
}
