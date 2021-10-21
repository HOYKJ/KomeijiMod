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

public class YukiEffect extends AbstractGameEffect {
    private float x;
    private float y;
    private float vX;
    private float vY;
    private float scale;
    private float rotation;
    private Color rain;
    private TextureAtlas.AtlasRegion img2;
    private boolean end;

    public YukiEffect(){
        this.img2 = ImageMaster.vfxAtlas.findRegion("env/dustCloud");
        this.end = false;
    }

    public void initializeData(int i){
        this.x =(MathUtils.random(0.0F, 1.0F) * Settings.WIDTH);
        this.y = (MathUtils.random(900.0F, 1250.0F) * Settings.scale + 350.0F * (i));
        this.vY = (MathUtils.random(-300.0F, -200.0F) * Settings.scale);

        this.rotation = (MathUtils.random(0.0F,360.0F));
        this.scale = MathUtils.random(0.25F, 0.5F);
        this.renderBehind = this.scale < 0.4F;
        this.scale *= Settings.scale;

        this.vX = (MathUtils.random(-100.0F, 20.0F) * Settings.scale);

        this.rain = Color.WHITE.cpy();
        this.rain.a -= MathUtils.random(0.1F, 0.2F);

        this.end = false;
    }

    public void update()
    {
        if(this.y <= (AbstractDungeon.floorY - MathUtils.random(50.0F, 150.0F))){
            if(this.end){
                this.isDone = true;
                return;
            }
            this.initializeData(1);
        }
        else {
            this.x += this.vX * Gdx.graphics.getDeltaTime();
            this.y += this.vY * Gdx.graphics.getDeltaTime();

            this.rain.a -= Gdx.graphics.getDeltaTime() / 4;
            if(this.rain.a < 0.0F) {
                if(this.end){
                    this.isDone = true;
                    return;
                }
                this.rain.a = 0.0F;
            }
            this.rotation += (MathUtils.random(0.0F,360.0F) * Gdx.graphics.getDeltaTime());
        }
    }

    public void render(SpriteBatch sb)
    {
        sb.setColor(this.rain);
        sb.draw(this.img2, this.x, this.y, this.img2.packedWidth / 2.0F, this.img2.packedHeight / 2.0F, this.img2.packedWidth, this.img2.packedHeight,
                this.scale, this.scale, this.rotation);
    }

    public void remove(){
        this.end = true;
    }

    public void dispose() {}
}
