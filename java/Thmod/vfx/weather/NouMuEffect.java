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

public class NouMuEffect extends AbstractGameEffect {
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
    private TextureAtlas.AtlasRegion img2;
    private int i;
    private boolean end;

    public NouMuEffect(){
        this.end = false;
    }

    public void getInfo(Color noumu, float x, float y, float aV, float scale, float rotation, float cV, boolean flipX, boolean flipY, TextureAtlas.AtlasRegion img2, int i){
        this.noumu = noumu;
        this.x = x;
        this.y = y;
        this.aV = aV;
        this.scale = scale;
        this.rotation = rotation;
        this.cV = cV;
        this.flipX = flipX;
        this.flipY = flipY;
        this.img2 = img2;
        this.i = i;
        this.renderBehind = true;

        if ((this.flipX) && (!this.img2.isFlipX())) {
            this.img2.flip(true, false);
        } else if ((!this.flipX) && (this.img2.isFlipX())) {
            this.img2.flip(true, false);
        }
        if ((this.flipY) && (!this.img2.isFlipY())) {
            this.img2.flip(false, true);
        } else if ((!this.flipY) && (this.img2.isFlipY())) {
            this.img2.flip(false, true);
        }
    }

    public void initializeData(int i){
        this.i = i;
        switch (MathUtils.random(2))
        {
            case 0:
                this.img2 = ImageMaster.SMOKE_1;
                break;
            case 1:
                this.img2 = ImageMaster.SMOKE_2;
                break;
            default:
                this.img2 = ImageMaster.SMOKE_3;
        }

        switch (i){
            case 0:
                this.x = (MathUtils.random(-100.0F * Settings.scale, Settings.WIDTH + 100.0F * Settings.scale) - this.img2.packedWidth / 2.0F);
                this.y = (MathUtils.random(Settings.HEIGHT -100.0F * Settings.scale, Settings.HEIGHT + 100.0F * Settings.scale) - this.img2.packedHeight / 2.0F);
                break;
            case 1:
                this.x = (MathUtils.random(-100.0F * Settings.scale, Settings.WIDTH + 100.0F * Settings.scale) - this.img2.packedWidth / 2.0F);
                this.y = (MathUtils.random(-100.0F * Settings.scale, 100.0F * Settings.scale) - this.img2.packedHeight / 2.0F);
                break;
            case 2:
                this.x = (MathUtils.random(-100.0F * Settings.scale, 100.0F * Settings.scale) - this.img2.packedWidth / 2.0F);
                this.y = (MathUtils.random(-100.0F * Settings.scale, Settings.HEIGHT + 100.0F * Settings.scale) - this.img2.packedHeight / 2.0F);
                break;
            case 3:
                this.x = (MathUtils.random(Settings.WIDTH  - 100.0F * Settings.scale, Settings.WIDTH  + 100.0F * Settings.scale) - this.img2.packedWidth / 2.0F);
                this.y = (MathUtils.random(-100.0F * Settings.scale, Settings.HEIGHT + 100.0F * Settings.scale) - this.img2.packedHeight / 2.0F);
                break;
        }

        this.cV = MathUtils.random(4.0F, 8.0F);
        this.aV = (MathUtils.random(-40.0F, -30.0F) * Settings.scale);

        this.rotation = MathUtils.random(0.0F, 360.0F);
        this.scale = (MathUtils.random(8.0F, 15.0F) * Settings.scale);

        this.noumu = Color.GRAY.cpy();
        this.noumu.r += MathUtils.random(-0.01F, 0.01F);
        this.noumu.g += MathUtils.random(-0.01F, 0.01F);
        this.noumu.b += MathUtils.random(-0.01F, 0.01F);
        this.noumu.a = 0.0F;
        this.targetAlpha = MathUtils.random(0.8F, 0.9F);

        this.flipX = MathUtils.randomBoolean();
        this.flipY = MathUtils.randomBoolean();
        this.fadeOut = true;

        this.end = false;
    }

    public void update()
    {
//        if(this.noumu.a <= 0.0F && !this.fadeOut){
//            if(this.end){
//                this.isDone = true;
//                return;
//            }
//            initializeData(this.i);
//        }
//        else if(this.noumu.a >= this.targetAlpha){
//            this.fadeOut = false;
//            this.noumu.a -= Gdx.graphics.getDeltaTime() / this.cV;
//        }
//        else {
//            if(this.fadeOut){
//                this.noumu.a += Gdx.graphics.getDeltaTime() / this.cV * 2;
//            }
//            else {
//                this.noumu.a -= Gdx.graphics.getDeltaTime() / this.cV;
//            }
//        }
//        this.rotation += this.aV * Gdx.graphics.getDeltaTime();

        this.noumu.a -= Gdx.graphics.getDeltaTime() / this.cV;
        this.rotation += this.aV * Gdx.graphics.getDeltaTime();

        if (this.noumu.a <= 0.0F){
            this.isDone = true;
        }
    }

    public void render(SpriteBatch sb)
    {
        sb.setColor(this.noumu);
        sb.draw(this.img2, this.x, this.y,
                this.img2.packedWidth / 2.0F, this.img2.packedHeight / 2.0F, this.img2.packedWidth, this.img2.packedHeight,
                this.scale, this.scale, this.rotation);
    }

    public void remove(){
        this.end = true;
    }

    public void dispose() {}
}
