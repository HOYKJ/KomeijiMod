package Thmod.vfx.animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class ColorWaterDropEffect extends AbstractGameEffect
{
    private float x;
    private float y;
    private int frame;
    private float animTimer = 0.1F;
    private Color targetColor;
    private float alpha;
    private float sca;

    public ColorWaterDropEffect(float x, float y, float scale)
    {
        this.x = x;
        this.y = y;
        this.frame = 0;
        this.scale = (MathUtils.random(2.5F, 3.0F) * Settings.scale) * scale;
        this.rotation = 0.0F;
        this.scale *= Settings.scale;
        this.alpha = 0.0F;
        this.color = new Color(1.0F, 0.52F, 0.31F, this.alpha);
        this.targetColor = new Color(1.0F, 0.52F, 0.31F, this.alpha);
        this.sca = scale;
    }

    public void update()
    {
        this.alpha = MathHelper.fadeLerpSnap(this.alpha, 1.0F);
        this.color.a = this.alpha;
        this.targetColor.a = this.alpha;
        this.animTimer -= Gdx.graphics.getDeltaTime();
        if (this.animTimer < 0.0F)
        {
            this.animTimer += 0.1F;
            this.frame += 1;

            switch (this.frame){
                case 2:
                    this.targetColor = new Color(0.9F, 1.0F, 0.3F, this.alpha);
                    break;
                case 3:
                    this.targetColor = new Color(0.39F, 1.0F, 0.3F, this.alpha);
                    break;
                case 4:
                    this.targetColor = new Color(0.3F, 1.0F, 1.0F, this.alpha);
                    break;
//                case 4:
//                    this.targetColor = new Color(0.38F, 0.3F, 1.0F, this.alpha);
//                    break;
                case 5:
                    this.targetColor = new Color(0.22F, 0.22F, 0.6F, this.alpha);
                    break;
            }

            if (this.frame == 3) {
                for (int i = 0; i < 3; i++) {
                    AbstractDungeon.effectsQueue.add(new ColorWaterSplashEffect(this.x, this.y, this.sca));
                }
            }
            if (this.frame > 5)
            {
                this.frame = 5;
                this.isDone = true;
            }
        }

        this.color.lerp(this.targetColor, 10F * Gdx.graphics.getDeltaTime());
    }

    public void render(SpriteBatch sb)
    {
        sb.setColor(this.color);
        switch (this.frame)
        {
            case 0:
                sb.draw(ImageMaster.WATER_DROP_VFX[0], this.x - 32.0F, this.y - 32.0F + 40.0F * Settings.scale, 32.0F, 32.0F, 64.0F, 64.0F, this.scale, this.scale, this.rotation, 0, 0, 64, 64, false, false);

                break;
            case 1:
                sb.draw(ImageMaster.WATER_DROP_VFX[1], this.x - 32.0F, this.y - 32.0F + 20.0F * Settings.scale, 32.0F, 32.0F, 64.0F, 64.0F, this.scale, this.scale, this.rotation, 0, 0, 64, 64, false, false);

                break;
            case 2:
                sb.draw(ImageMaster.WATER_DROP_VFX[2], this.x - 32.0F, this.y - 32.0F + 10.0F * Settings.scale, 32.0F, 32.0F, 64.0F, 64.0F, this.scale, this.scale, this.rotation, 0, 0, 64, 64, false, false);

                break;
            case 3:
                sb.draw(ImageMaster.WATER_DROP_VFX[3], this.x - 32.0F, this.y - 32.0F, 32.0F, 32.0F, 64.0F, 64.0F, this.scale, this.scale, this.rotation, 0, 0, 64, 64, false, false);

                break;
            case 4:
                sb.draw(ImageMaster.WATER_DROP_VFX[4], this.x - 32.0F, this.y - 32.0F, 32.0F, 32.0F, 64.0F, 64.0F, this.scale, this.scale, this.rotation, 0, 0, 64, 64, false, false);

                break;
            case 5:
                sb.draw(ImageMaster.WATER_DROP_VFX[5], this.x - 32.0F, this.y - 32.0F, 32.0F, 32.0F, 64.0F, 64.0F, this.scale, this.scale, this.rotation, 0, 0, 64, 64, false, false);

                break;
        }
    }

    public void dispose() {}
}
