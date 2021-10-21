package Thmod.vfx.weather;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class FuuuEffect extends AbstractGameEffect {
    private float x;
    private float y;
    private float vX;
    private float vY;
    private float scale;
    private float scaleY;
    private float rotation;
    private Color rain;
    private Texture img2;
    private boolean end;

    public FuuuEffect(){
        this.img2 = ImageMaster.loadImage("images/vfx/horizontal_line.png");
        this.end = false;
    }

    public void getInfo(float x, float y, float vX, float vY, float scale, float scaleY, float rotation, Color rain){
        this.x = x;
        this.y = y;
        this.vX = vX;
        this.vY = vY;
        this.scale = scale;
        this.scaleY = scaleY;
        this.rotation = rotation;
        this.rain = rain;
    }

    public void initializeData(int i){
        this.x =(MathUtils.random(0.0F, 1.0F) * Settings.WIDTH);
        this.y = (MathUtils.random(900.0F, 1250.0F) * Settings.scale + 350.0F * (i));
        this.vY = (MathUtils.random(-500.0F, -400.0F) * Settings.scale);

        this.rotation = (MathUtils.random(65.0F,75.0F));
        this.scale = MathUtils.random(0.25F, 0.5F);
        this.scale *= Settings.scale;
        this.scaleY = (MathUtils.random(0.15F, 0.35F) * Settings.scale);
        this.renderBehind = (this.scale + this.scaleY) < (0.65f * Settings.scale);

        this.vX = (this.vY * (float) Math.tan(Math.toRadians(90 - this.rotation)));

        this.rain = Color.SKY.cpy();
        this.rain.r += MathUtils.random(-0.01F, 0.01F);
        this.rain.g += MathUtils.random(-0.01F, 0.01F);
        this.rain.b += MathUtils.random(-0.01F, 0.01F);
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
        }
    }

    public void render(SpriteBatch sb)
    {
        sb.setColor(this.rain);
        sb.draw(this.img2, this.x, this.y, 128.0F, 128.0F, 256.0F, 256.0F, this.scale, this.scaleY, this.rotation,
                0, 0, 256, 256, false, false);
    }

    public void remove(){
        this.end = true;
    }

    public void dispose() {}
}
