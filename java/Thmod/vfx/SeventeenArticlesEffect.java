package Thmod.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.HemokinesisParticle;

public class SeventeenArticlesEffect extends AbstractGameEffect
{
    private float x;
    private float y;
    private float tX;
    private float tY;

    public SeventeenArticlesEffect(float sX, float sY, float tX, float tY)
    {
        this.x = sX;
        this.y = sY;
        this.tX = tX;
        this.tY = tY;

        this.scale = 0.12F;
        this.duration = 0.5F;
    }

    public void update() {
        AbstractDungeon.effectsQueue.add(new HemokinesisParticle(this.x +
                MathUtils.random(60.0F, -60.0F) * Settings.scale, this.y +
                MathUtils.random(60.0F, -60.0F) * Settings.scale, this.tX, this.tY, AbstractDungeon.player.flipHorizontal));

        this.isDone = true;
    }

    public void render(SpriteBatch sb) {}

    public void dispose() {}
}
