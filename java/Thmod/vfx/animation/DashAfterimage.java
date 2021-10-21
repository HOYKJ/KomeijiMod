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

import basemod.helpers.SuperclassFinder;

public class DashAfterimage extends AbstractGameEffect
{
    private AbstractCreature target;
    private float x, y;

    public DashAfterimage(AbstractCreature target, float time, float x, float y)
    {
        this(target, time, Color.WHITE.cpy(), x, y);
    }

    public DashAfterimage(AbstractCreature target, float time, Color color, float x, float y)
    {
        this.duration = time;
        this.startingDuration = time;
        this.target = target;
        this.renderBehind = true;
        this.color = color;
        this.x = x;
        this.y = y;

        this.color.r += 0.6;
        this.color.g += 0.6;
        this.color.b += 0.6;
        if(this.color.r > 1){
            this.color.r = 1;
        }
        if(this.color.g > 1){
            this.color.g = 1;
        }
        if(this.color.b > 1){
            this.color.b = 1;
        }
    }

    public void update()
    {
        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration > 0)
        {
            this.color.a = this.duration / this.startingDuration * 0.8f;
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
            skeleton = SuperclassFinder.getSuperclassField(this.target.getClass(),"skeleton");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        assert skeleton != null;
        skeleton.setAccessible(true);

        try {
            if(skeleton.get(this.target) != null) {
                ((Skeleton) skeleton.get(this.target)).updateWorldTransform();
                ((Skeleton) skeleton.get(this.target)).setPosition(this.x, this.y);
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
