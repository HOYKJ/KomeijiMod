package Thmod.vfx.weather;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

import Thmod.ThMod;

import static com.megacrit.cardcrawl.helpers.ImageMaster.vfxAtlas;

public class KawaGiriEffect extends AbstractGameEffect {
    private Color noumu;
    private float x;
    private float y;
    private float aV;
    private float scale;
    private float rotation;
    private float cV;
    private float targetAlpha;
    private boolean flipX;
    private boolean flipY;
    private boolean fadeOut;
    private TextureAtlas.AtlasRegion img;
    private boolean end;

    public KawaGiriEffect(){
        this.end = false;
    }

    public void getInfo(Color noumu, float x, float y, float aV, float scale, float rotation, float cV, boolean flipX, boolean flipY, TextureAtlas.AtlasRegion img){
        this.noumu = noumu;
        this.x = x;
        this.y = y;
        this.aV = aV;
        this.scale = scale;
        this.rotation = rotation;
        this.cV = cV;
        this.flipX = flipX;
        this.flipY = flipY;
        this.img = img;
        this.renderBehind = true;

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

        this.duration = 100f;
    }

    public void initializeData(){
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

        this.x = (MathUtils.random(-100.0F * Settings.scale, Settings.WIDTH + 100.0F * Settings.scale) - this.img.packedWidth / 2.0F);
        this.y = (MathUtils.random(-100.0F * Settings.scale, 120.0F * Settings.scale) - this.img.packedHeight / 2.0F);

        this.cV = MathUtils.random(4.0F, 8.0F);
        this.aV = (MathUtils.random(-40.0F, -30.0F) * Settings.scale);

        this.rotation = MathUtils.random(0.0F, 360.0F);
        this.scale = (MathUtils.random(8.0F, 15.0F) * Settings.scale);

        this.noumu = Color.GRAY.cpy();
        this.noumu.r += MathUtils.random(-0.01F, 0.01F);
        this.noumu.g += MathUtils.random(-0.01F, 0.01F);
        this.noumu.b += MathUtils.random(-0.01F, 0.01F);
        this.noumu.a = 0F;
        this.targetAlpha = MathUtils.random(0.8F, 0.9F);

        this.flipX = MathUtils.randomBoolean();
        this.flipY = MathUtils.randomBoolean();
        this.fadeOut = true;

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

        this.end = false;
    }

    public void update()
    {
//        if (this.noumu.a < 0.0F && !this.fadeOut) {
//            if(this.end){
//                this.isDone = true;
//                return;
//            }
//            this.initializeData();
//        } else if (this.noumu.a >= this.targetAlpha) {
//            this.noumu.a = this.targetAlpha;
//            this.fadeOut = false;
//            this.noumu.a -= Gdx.graphics.getDeltaTime() / this.cV;
//        } else {
//            if (this.fadeOut) {
//                this.noumu.a += Gdx.graphics.getDeltaTime() / this.cV * 2;
//            } else {
//                this.noumu.a -= Gdx.graphics.getDeltaTime() / this.cV;
//            }
//        }
//        this.rotation += this.aV * Gdx.graphics.getDeltaTime();

        this.noumu.a -= Gdx.graphics.getDeltaTime() / this.cV;
        this.rotation += this.aV * Gdx.graphics.getDeltaTime();

        if (this.noumu.a <= 0.0F){
            this.isDone = true;
        }

        this.duration -= Gdx.graphics.getDeltaTime();
        if(this.duration <= 0){
            this.duration = 100f;
        }
    }

    public void render(SpriteBatch sb)
    {
        //this.noumu.a = 2;
        sb.setColor(this.noumu);
        sb.draw(this.img, this.x, this.y,
                this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight,
                this.scale, this.scale, this.rotation);
    }

    public void remove(){
        this.end = true;
    }

    public void dispose() {}
}
