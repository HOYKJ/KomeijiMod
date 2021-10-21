package Thmod.vfx.animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class ColorWaterSplashEffect extends AbstractGameEffect
{
    private TextureAtlas.AtlasRegion img;
    private float x;
    private float y;
    private float vX;
    private float vY;
    private float floor;
    private float alpha;
    private Color targetColor;
    private float animTimer = 0.1F;
    private boolean frame;

    public ColorWaterSplashEffect(float x, float y, float scale)
    {
        this.img = ImageMaster.DECK_GLOW_1;
        this.duration = MathUtils.random(0.5F, 1.0F);
        this.x = (x - this.img.packedWidth / 2 + MathUtils.random(-10.0F, 10.0F) * Settings.scale);
        this.y = (y - this.img.packedHeight / 2 - 40.0F * Settings.scale);
        this.alpha = 0.0F;
        this.color = new Color(0.9F, 1.0F, 0.3F, this.alpha);
        this.targetColor = new Color(0.39F, 1.0F, 0.3F, this.alpha);
        this.scale = (MathUtils.random(1.5F, 3.5F) * Settings.scale) * scale;
        this.vX = (MathUtils.random(-120.0F, 120.0F) * Settings.scale);
        this.vY = (MathUtils.random(150.0F, 300.0F) * Settings.scale);
        this.floor = (y - 40.0F * Settings.scale);
        this.frame = true;
    }

    public void update()
    {
        this.vY -= 1000.0F * Settings.scale * Gdx.graphics.getDeltaTime();
        this.x += this.vX * Gdx.graphics.getDeltaTime();
        this.y += this.vY * Gdx.graphics.getDeltaTime();
        Vector2 test = new Vector2(this.vX, this.vY);
        this.rotation = (test.angle() + 45.0F);

        this.scale -= Gdx.graphics.getDeltaTime() / 2.0F;
        if ((this.y < this.floor) && (this.vY < 0.0F) &&
                (this.duration > 0.2F)) {
            this.duration = 0.2F;
        }
        if (this.duration < 0.2F) {
            this.alpha = Interpolation.fade.apply(0.0F, 1.0F, this.duration * 5.0F);
        } else {
            this.alpha = 1.0F;
        }

        this.color.a = this.alpha;
        this.targetColor.a = this.alpha;
        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration < 0.0F) {
            this.isDone = true;
        }

        this.animTimer -= Gdx.graphics.getDeltaTime();
        if (this.animTimer < 0.0F) {
            this.animTimer += 0.1F;
            if(this.frame){
                this.frame = false;
                this.targetColor = new Color(0.3F, 1.0F, 1.0F, this.alpha);
            }
            else {
                this.targetColor = new Color(0.22F, 0.22F, 0.6F, this.alpha);
            }
        }

        this.color.lerp(this.targetColor, 10F * Gdx.graphics.getDeltaTime());
    }

    public void render(SpriteBatch sb)
    {
        sb.setColor(this.color);
        sb.draw(this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale * 0.54F, this.rotation);
    }

    public void dispose() {}
}
