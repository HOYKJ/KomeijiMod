package Thmod.vfx.weather;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class NagiEffect extends AbstractGameEffect {
    private Color nagi;
    private Texture img2;
    private boolean lighten;
    private float aV;
    private boolean end;

    public NagiEffect(){
        this.img2 = ImageMaster.loadImage("images/vfx/spotlight.jpg");
        this.end = false;
    }

    public void initializeData(){
        this.lighten = true;
        this.nagi = new Color(1.0F, 1.0F, 0.8F, 0F);
        this.aV = MathUtils.random(0.15F, 0.25F);

        this.end = false;
    }

    public void update()
    {
        if(this.end){
            if(this.nagi.a <= 0){
                this.isDone = true;
            }
        }
        if(this.lighten){
            this.nagi.a += this.aV * Gdx.graphics.getDeltaTime();
            if(this.nagi.a >= 0.5f){
                this.lighten = false;
            }
        }
        else {
            this.nagi.a -= this.aV * Gdx.graphics.getDeltaTime();
            if(this.nagi.a <= 0){
                //this.lighten = true;
                this.initializeData();
            }
        }
    }

    public void render(SpriteBatch sb)
    {
        sb.setColor(this.nagi);
        sb.setBlendFunction(770, 1);
        sb.draw(this.img2, AbstractDungeon.player.drawX - AbstractDungeon.player.hb_w / 2 - 50, AbstractDungeon.floorY,
                AbstractDungeon.player.hb_w + 100, Settings.HEIGHT - AbstractDungeon.floorY);
        sb.setBlendFunction(770, 771);
    }

    public void remove(){
        this.end = true;
    }

    public void dispose() {}
}
