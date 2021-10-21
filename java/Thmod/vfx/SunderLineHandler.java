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

import Thmod.ThMod;

public class SunderLineHandler extends AbstractGameEffect
{
    private AbstractCreature source, target;
    private float x1;
    private float y, y1;
    private int counter;
    private float addX, addY;

    public SunderLineHandler(AbstractCreature source, AbstractCreature target)
    {
        this.source = source;
        this.target = target;
        this.counter = 20;
        this.duration = 0.05f;
        this.addX = 0;
        this.addY = 0;
        this.y = 0;
    }

    public void update()
    {
        float totalX = this.target.hb.cX - this.source.hb.cX, totalY = this.target.hb.cY - this.source.hb.cY;
        float lastY = this.source.hb.cY;
        if(this.y != 0) {
             lastY = this.y;
        }
        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration < 0.0F) {
            if(this.counter > 0){
                this.counter --;
                this.addX += totalX / 20;
                this.addY += totalY / 20;
                float x = this.source.hb.cX + this.addX;
                this.y = (float) (Math.pow(this.addX / totalX, 2) - Math.pow(this.addX / totalX, 3)) * totalX + this.source.hb.cY + addY;
                this.rotation = (float) Math.toDegrees(Math.atan((this.y - lastY) / totalX * 20)) + 90;
                if(lastY == this.source.hb.cY){
                    this.rotation = 90;
                }
                AbstractDungeon.effectsQueue.add(new SunderLineEffect(x + MathUtils.random(-30.0F, 30.0F), this.y + 60 + MathUtils.random(-10.0F, 10.0F), this.rotation, Color.NAVY.cpy()));
                AbstractDungeon.effectsQueue.add(new SunderLineEffect(x + MathUtils.random(-30.0F, 30.0F), this.y + 60 + MathUtils.random(-10.0F, 10.0F), this.rotation, Color.NAVY.cpy()));
                AbstractDungeon.effectsQueue.add(new SunderLineEffect(x + MathUtils.random(-30.0F, 30.0F), this.y + 60 + MathUtils.random(-10.0F, 10.0F), this.rotation, Color.NAVY.cpy()));
                AbstractDungeon.effectsQueue.add(new SunderLineEffect(x + MathUtils.random(-30.0F, 30.0F), this.y + 60 + MathUtils.random(-10.0F, 10.0F), this.rotation, Color.NAVY.cpy()));
                AbstractDungeon.effectsQueue.add(new SunderLineEffect(x + MathUtils.random(-30.0F, 30.0F), this.y + 60 + MathUtils.random(-10.0F, 10.0F), this.rotation, new Color(100f / 255f, 200f / 255f, 255f / 255f,1f)));
                AbstractDungeon.effectsQueue.add(new SunderLineEffect(x + MathUtils.random(-30.0F, 30.0F), this.y + 60 + MathUtils.random(-10.0F, 10.0F), this.rotation, new Color(100f / 255f, 200f / 255f, 255f / 255f,1f)));

                float nX = this.source.hb.cX + totalX * 1.05f - this.addX, nY = this.source.hb.cY + totalY - addY - 20;
                AbstractDungeon.effectsQueue.add(new SunderLineEffect(nX + MathUtils.random(-30.0F, 30.0F), nY + MathUtils.random(-10.0F, 10.0F), -90, Color.RED.cpy()));
                AbstractDungeon.effectsQueue.add(new SunderLineEffect(nX + MathUtils.random(-30.0F, 30.0F), nY + MathUtils.random(-10.0F, 10.0F), -90, Color.RED.cpy()));
                AbstractDungeon.effectsQueue.add(new SunderLineEffect(nX + MathUtils.random(-30.0F, 30.0F), nY + MathUtils.random(-10.0F, 10.0F), -90, Color.RED.cpy()));
                AbstractDungeon.effectsQueue.add(new SunderLineEffect(nX + MathUtils.random(-30.0F, 30.0F), nY + MathUtils.random(-10.0F, 10.0F), -90, Color.RED.cpy()));
                AbstractDungeon.effectsQueue.add(new SunderLineEffect(nX + MathUtils.random(-30.0F, 30.0F), nY + MathUtils.random(-10.0F, 10.0F), -90, new Color(255f / 255f, 100f / 255f, 100f / 255f,1f)));
                AbstractDungeon.effectsQueue.add(new SunderLineEffect(nX + MathUtils.random(-30.0F, 30.0F), nY + MathUtils.random(-10.0F, 10.0F), -90, new Color(255f / 255f, 100f / 255f, 100f / 255f,1f)));
            }
            else {
                this.isDone = true;
            }
        }
    }

    public void render(SpriteBatch sb)
    {
    }

    public void dispose()
    {
    }
}
