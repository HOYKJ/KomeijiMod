package Thmod.vfx.animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.esotericsoftware.spine.Skeleton;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

import java.lang.reflect.Field;

public class DashEffect extends AbstractGameEffect
{
    private AbstractCreature target;
    private float targetX, startX, timer, timerNum;

    public DashEffect(AbstractCreature target, float time, boolean isFlip)
    {
        this.duration = time;
        this.startingDuration = time;
        this.target = target;
        this.targetX = isFlip? 100:-100;
        this.startX = target.animX;
        this.timerNum = time / 20;
        this.timer = this.timerNum;
    }

    public void update()
    {
        this.duration -= Gdx.graphics.getDeltaTime();
        this.timer -= Gdx.graphics.getDeltaTime();

        if (this.duration > startingDuration / 2)
        {
            this.target.animX = this.startX + this.targetX * (this.startingDuration - this.duration) / (this.startingDuration / 2);
        }
        else if(this.duration > 0){
            this.target.animX = this.startX + this.targetX * this.duration / (this.startingDuration / 2);
        }
        else
        {
            this.isDone = true;
        }

        if(this.timer <= 0){
            this.timer = this.timerNum;
            AbstractDungeon.effectsQueue.add(new DashAfterimage(this.target, 0.8f, Color.NAVY.cpy(), this.target.drawX + this.target.animX,
                    this.target.drawY + this.target.animY + AbstractDungeon.sceneOffsetY));
        }
    }

    public void render(SpriteBatch sb)
    {
    }

    public void dispose() {}
}
