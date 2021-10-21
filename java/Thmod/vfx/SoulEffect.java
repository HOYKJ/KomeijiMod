package Thmod.vfx;

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

public class SoulEffect extends AbstractGameEffect
{
    private AbstractMonster target;
    private float addX, addY;

    public SoulEffect(AbstractMonster target, float time)
    {
        this(target, time, Color.WHITE.cpy());
    }

    public SoulEffect(AbstractMonster target, float time, Color color)
    {
        this.duration = time;
        this.startingDuration = time;
        this.target = target;
        this.renderBehind = true;
        this.color = color;
    }

    public void update()
    {
        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration > 0)
        {
            this.color.a = this.duration / this.startingDuration * 0.8f;
            this.addX = this.target.hb.width / 4  * (this.startingDuration - this.duration)/ this.startingDuration;
            this.addY = this.target.hb.height / 4  * (this.startingDuration - this.duration)/ this.startingDuration;
        }
        else
        {
            this.isDone = true;
        }
    }

    public void render(SpriteBatch sb)
    {
        sb.setColor(this.color);
        Field skeleton = null;

        try {
            skeleton = this.target.getClass().getSuperclass().getSuperclass().getDeclaredField("skeleton");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        assert skeleton != null;
        skeleton.setAccessible(true);

        try {
            if(skeleton.get(this.target) != null) {
                ((Skeleton) skeleton.get(this.target)).updateWorldTransform();
                ((Skeleton) skeleton.get(this.target)).setPosition(this.target.drawX + this.target.animX + this.addX, this.target.drawY + this.target.animY + AbstractDungeon.sceneOffsetY + this.addY);
                ((Skeleton) skeleton.get(this.target)).setColor(this.color);
                sb.end();
                CardCrawlGame.psb.begin();
                AbstractCreature.sr.draw(CardCrawlGame.psb, ((Skeleton) skeleton.get(this.target)));
                CardCrawlGame.psb.end();
                sb.begin();
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void dispose() {}
}
