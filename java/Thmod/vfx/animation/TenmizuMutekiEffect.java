package Thmod.vfx.animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class TenmizuMutekiEffect extends AbstractGameEffect
{
    private int frame;
    private float animTimer = 0.5F;
    private Color targetColor;
    private AbstractCreature target;

    public TenmizuMutekiEffect(AbstractCreature target)
    {
        this.frame = 0;
        this.scale = (MathUtils.random(2.5F, 3.0F) * Settings.scale) * scale;
        this.rotation = 0.0F;
        this.scale *= Settings.scale;
        this.target = target;
        this.target.tint.color = new Color(1.0F, 0.52F, 0.31F, 1.0F);
        this.targetColor = new Color(1.0F, 0.52F, 0.31F, 1.0F);
    }

    public void update()
    {
        this.animTimer -= Gdx.graphics.getDeltaTime();
        if (this.animTimer < 0.0F)
        {
            this.animTimer += 0.5F;
            this.frame += 1;

            switch (this.frame){
                case 2:
                    this.targetColor = new Color(0.9F, 1.0F, 0.3F, 1.0F);
                    break;
                case 3:
                    this.targetColor = new Color(0.39F, 1.0F, 0.3F, 1.0F);
                    break;
                case 4:
                    this.targetColor = new Color(0.3F, 1.0F, 1.0F, 1.0F);
                    break;
                case 5:
                    this.targetColor = new Color(0.22F, 0.22F, 0.6F, 1.0F);
                    break;
            }

            if (this.frame > 5)
            {
                this.frame = 5;
                this.isDone = true;
            }
        }

        this.target.tint.color.lerp(this.targetColor, 2F * Gdx.graphics.getDeltaTime());
    }

    public void render(SpriteBatch sb)
    {
    }

    public void dispose() {}
}
