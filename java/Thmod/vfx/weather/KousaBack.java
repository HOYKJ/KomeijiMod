package Thmod.vfx.weather;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class KousaBack extends AbstractGameEffect {
    private Color kiri;
    private TextureAtlas.AtlasRegion img3 = ImageMaster.BORDER_GLOW_2;
    private boolean end;

    public KousaBack(){
        this.end = false;
    }

    public void initializeData(){
        float tmp = 0.3f;
        this.kiri = new Color();
        this.kiri.g = (tmp + MathUtils.random(0.3F));
        this.kiri.r = (this.kiri.g + MathUtils.random(0.2F));
        this.kiri.b = tmp;
        this.kiri.a = 0.0F;
        this.end = false;
    }

    public void update()
    {
        if(this.end){
            if(this.kiri.a > 0){
                this.kiri.a -= Gdx.graphics.getDeltaTime();
            }
            else {
                this.isDone = true;
            }
        }
        else {
            if (this.kiri.a < 0.8f) {
                this.kiri.a += Gdx.graphics.getDeltaTime();
            } else if (this.kiri.a > 0.8f) {
                this.kiri.a = 0.8f;
            }
        }
    }

    public void render(SpriteBatch sb)
    {
        sb.setColor(this.kiri);
        sb.draw(this.img3, 0F, 0F, Settings.WIDTH, Settings.HEIGHT);
    }

    public void remove(){
        this.end = true;
    }

    public void dispose() {}
}
