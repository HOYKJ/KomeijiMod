package Thmod.vfx.animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class PoisonSmokeEffect extends AbstractGameEffect {
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
    private TextureAtlas.AtlasRegion img;
    private boolean flipX;
    private boolean flipY;

    public PoisonSmokeEffect(){
//        this.color.r = MathUtils.random(140.0F, 150.0F);
//        this.color.g = MathUtils.random(170.0F, 180.0F);
//        this.color.b = MathUtils.random(0.0F, 10.0F);
//        this.color.a = MathUtils.random(0.8F, 0.9F);
        this.color = new Color(MathUtils.random(100.0F, 110.0F) / 255, MathUtils.random(210.0F, 220.0F) / 255, MathUtils.random(10.0F, 20.0F) / 255, MathUtils.random(0.8F, 0.9F));
        this.x = Settings.WIDTH / 2 + MathUtils.random(-300.0F, 300.0F);
        this.y = MathUtils.random(50.0F, 100.0F) * Settings.scale;
        this.xV = MathUtils.random(-300.0F, 300.0F) * Settings.scale;
        this.xA = MathUtils.random(1.0F, 4.0F) * Settings.scale * this.xV > 0? -1: 1;
        this.scale = (MathUtils.random(8.0F, 15.0F) * Settings.scale);
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
        this.x += this.xV * Gdx.graphics.getDeltaTime();
        this.xV += this.xA * Gdx.graphics.getDeltaTime();

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
