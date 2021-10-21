package Thmod.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class YomeiCutEffect extends AbstractGameEffect
{
    private TextureAtlas.AtlasRegion img;
    private float stallTimer;
    private float fadeOutTimer = 0.4F;
    private float fadeInTimer = 0.05F;
    private AbstractCreature target;
    private float width;
    private float vX;
    private float y;
    private float x;

    public YomeiCutEffect(AbstractCreature target)
    {
        this.img = ImageMaster.vfxAtlas.findRegion("combat/cleave");
        this.color = new Color(1.0F, 1.0F, 1.0F, 0.0F);
        this.x = target.hb.cX;
        this.y = target.hb.cY;
        this.vX = (100.0F * Settings.scale);
        this.stallTimer = MathUtils.random(0.0F, 0.2F);
        this.scale = (1.2F * Settings.scale);
        this.rotation = 40;
        this.target = target;
        this.width = (float) Math.sqrt(Math.pow(this.target.hb.width, 2) + Math.pow(this.target.hb.height, 2)) + 40;
    }

    public void update()
    {
        if (this.stallTimer > 0.0F)
        {
            this.stallTimer -= Gdx.graphics.getDeltaTime();
            return;
        }
//        this.x -= this.vX * Gdx.graphics.getDeltaTime();
//        this.y -= this.vX * Gdx.graphics.getDeltaTime();
        //this.rotation += MathUtils.random(-0.5F, 0.5F);
        this.scale += 0.005F * Settings.scale;
        if (this.fadeInTimer != 0.0F)
        {
            this.fadeInTimer -= Gdx.graphics.getDeltaTime();
            if (this.fadeInTimer < 0.0F) {
                this.fadeInTimer = 0.0F;
            }
            this.color.a = Interpolation.fade.apply(1.0F, 0.0F, this.fadeInTimer / 0.05F);
        }
        else if (this.fadeOutTimer != 0.0F)
        {
            this.fadeOutTimer -= Gdx.graphics.getDeltaTime();
            if (this.fadeOutTimer < 0.0F) {
                this.fadeOutTimer = 0.0F;
            }
            this.color.a = Interpolation.pow2.apply(0.0F, 1.0F, this.fadeOutTimer / 0.4F);
        }
        else
        {
            this.isDone = true;
        }
    }

    public void render(SpriteBatch sb)
    {
        sb.setColor(this.color);
        sb.draw(this.img, this.x - this.width / 2, this.y - this.img.packedHeight / 2.0F, this.width / 2, this.img.packedHeight / 2.0F, this.width, this.img.packedHeight, this.scale, this.scale, this.rotation);

        sb.setBlendFunction(770, 1);
        sb.draw(this.img, this.x - this.width / 2, this.y - this.img.packedHeight / 2.0F, this.width / 2, this.img.packedHeight / 2.0F, this.width, this.img.packedHeight, this.scale, this.scale, this.rotation);

        sb.setBlendFunction(770, 771);
    }

    public void dispose() {}
}
