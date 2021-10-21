package Thmod.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.esotericsoftware.spine.Skeleton;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

import java.lang.reflect.Field;

import basemod.helpers.SuperclassFinder;

public class SunderSoulEffect extends AbstractGameEffect
{
    private AbstractMonster target;
    private AbstractPlayer source;
    private float addX;

    public SunderSoulEffect(AbstractPlayer source, AbstractMonster target, float time)
    {
        this(source, target, time, Color.WHITE.cpy());
    }

    public SunderSoulEffect(AbstractPlayer source, AbstractMonster target, float time, Color color)
    {
        this.duration = time;
        this.startingDuration = time;
        this.source = source;
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
            this.addX = this.target.hb.width / 2  * (this.startingDuration - this.duration)/ this.startingDuration;
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
        Field skeleton2 = null;

        try {
            skeleton = SuperclassFinder.getSuperclassField(this.target.getClass(), "skeleton");
            skeleton2 = SuperclassFinder.getSuperclassField(this.source.getClass(), "skeleton");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        assert skeleton != null;
        assert skeleton2 != null;
        skeleton.setAccessible(true);
        skeleton2.setAccessible(true);

        try {
            if(skeleton.get(this.target) != null) {
                ((Skeleton) skeleton.get(this.target)).updateWorldTransform();
                ((Skeleton) skeleton.get(this.target)).setPosition(this.target.drawX + this.target.animX + this.addX, this.target.drawY + this.target.animY + AbstractDungeon.sceneOffsetY);
                ((Skeleton) skeleton.get(this.target)).setColor(this.color);
                sb.end();
                CardCrawlGame.psb.begin();
                AbstractCreature.sr.draw(CardCrawlGame.psb, ((Skeleton) skeleton.get(this.target)));
                CardCrawlGame.psb.end();
                sb.begin();
            }
            else
            {
                Field img = null;

                try {
                    img = SuperclassFinder.getSuperclassField(this.target.getClass(), "img");
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }

                if(img != null) {
                    img.setAccessible(true);
                    sb.setColor(Color.WHITE);
                    sb.draw(((Texture) img.get(this.target)), this.target.drawX - ((Texture) img.get(this.target)).getWidth() * Settings.scale / 2.0F + this.target.animX + this.addX,
                            this.target.drawY, ((Texture) img.get(this.target)).getWidth() * Settings.scale, ((Texture) img.get(this.target)).getHeight() * Settings.scale,
                            0, 0, ((Texture) img.get(this.target)).getWidth(), ((Texture) img.get(this.target)).getHeight(), this.target.flipHorizontal, this.target.flipVertical);
                }
            }

            if(skeleton2.get(this.source) != null) {
                ((Skeleton) skeleton2.get(this.source)).updateWorldTransform();
                ((Skeleton) skeleton2.get(this.source)).setPosition(this.source.drawX + this.source.animX - this.addX, this.source.drawY + this.source.animY + AbstractDungeon.sceneOffsetY);
                ((Skeleton) skeleton2.get(this.source)).setColor(this.color);
                sb.end();
                CardCrawlGame.psb.begin();
                AbstractCreature.sr.draw(CardCrawlGame.psb, ((Skeleton) skeleton2.get(this.source)));
                CardCrawlGame.psb.end();
                sb.begin();
            }
            else
            {
                Field img = null;

                try {
                    img = SuperclassFinder.getSuperclassField(this.source.getClass(), "img");
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }

                assert img != null;
                img.setAccessible(true);
                sb.setColor(Color.WHITE);
                sb.draw(((Texture)img.get(this.source)), this.source.drawX - ((Texture)img.get(this.source)).getWidth() * Settings.scale / 2.0F + this.source.animX + this.addX,
                        this.source.drawY, ((Texture)img.get(this.source)).getWidth() * Settings.scale, ((Texture)img.get(this.source)).getHeight() * Settings.scale,
                        0, 0, ((Texture)img.get(this.source)).getWidth(), ((Texture)img.get(this.source)).getHeight(), this.source.flipHorizontal, this.source.flipVertical);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void dispose() {}
}
