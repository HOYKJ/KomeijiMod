package Thmod.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;

public class JyouchiEffect  extends AbstractGameEffect
{
    private static TextureAtlas.AtlasRegion top;
    private boolean impactHook = false;
    private float x;
    private float y;
    private float sY;
    private float dY;
    private static final float DUR = 1.0F;
    private boolean playedSfx = false;

    public JyouchiEffect(float x, float y, Color c)
    {
        if (top == null)
        {
            top = ImageMaster.vfxAtlas.findRegion("combat/biteTop");
        }
        this.x = (x - top.packedWidth / 2);
//        this.sY = (y - top.packedHeight / 2 + 150.0F * Settings.scale);
//        this.dY = (y - 0.0F * Settings.scale);
//        this.y = this.sY;

        this.sY = (y - top.packedHeight / 2 - 90.0F * Settings.scale);
        this.dY = (y - 160.0F * Settings.scale);
        this.y = this.sY;

        this.duration = 1.0F;
        this.startingDuration = 1.0F;
        this.color = c;
        this.scale = (0.5F * Settings.scale);
    }

    public JyouchiEffect(float x, float y)
    {
        this(x, y, new Color(0.7F, 0.9F, 1.0F, 0.0F));
    }

    public void update()
    {
        if (!this.impactHook)
        {
            this.impactHook = true;
            AbstractDungeon.effectsQueue.add(new BorderFlashEffect(this.color));
                CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.HIGH, ScreenShake.ShakeDur.MED, true);
        }

        this.duration -= Gdx.graphics.getDeltaTime();
        if ((this.duration < this.startingDuration - 0.3F) && (!this.playedSfx))
        {
            this.playedSfx = true;
            CardCrawlGame.sound.play("EVENT_VAMP_BITE", 0.05F);
        }
        if (this.duration > this.startingDuration / 2.0F)
        {
            this.color.a = Interpolation.fade.apply(1.0F, 0.0F, (this.duration - 0.5F) * 2.0F);
//            this.y = Interpolation.bounceIn.apply(this.dY, this.sY, (this.duration - 0.5F) * 2.0F);
        }
        else
        {
            this.color.a = Interpolation.fade.apply(0.0F, 1.0F, this.duration * 2.0F);
//            this.y = Interpolation.fade.apply(this.sY, this.dY, this.duration * 2.0F);
//            this.y = Interpolation.fade.apply(this.sY, this.dY, this.duration * 2.0F);
        }
        this.y = Interpolation.bounceIn.apply(this.dY, this.sY, (this.duration - 0.5F) * 2.0F);
        this.scale = (Interpolation.pow3In.apply(1.0F, 0.5F, (this.duration) / (this.startingDuration)) * Settings.scale);
        if (this.duration < 0.0F) {
            this.isDone = true;
        }
    }

    public void render(SpriteBatch sb)
    {
        sb.setBlendFunction(770, 1);
        sb.setColor(this.color);
        sb.draw(top, this.x, this.y, top.packedWidth / 2.0F, top.packedHeight / 2.0F, top.packedWidth, top.packedHeight, this.scale * 1.6F, this.scale * 1.6F, 0.0F);

        sb.setBlendFunction(770, 771);
    }

    public void dispose(){}
}
