package Thmod.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.RoomTintEffect;

import Thmod.ThMod;

public class TheWorld extends AbstractGameEffect
{
    private float x;
    private float y;
    private int count;
    private float stakeTimer = 0F;
    private boolean stop;

    public TheWorld(float x, float y,boolean stop)
    {
        this.x = x;
        this.y = y;
        this.count = 13;
        this.stop = stop;
    }

    public void update()
    {
        this.stakeTimer -= Gdx.graphics.getDeltaTime();
        if (this.stakeTimer < 0F) {
            if (this.count == 13) {
                int roll = (MathUtils.random(99) + 1);
                if(ThMod.SoundOpen){
                    if(this.stop) {
                        if ((roll == 1)) {
                            CardCrawlGame.sound.play("world_ot");
                            UnlockTracker.unlockCard("TheWorld");
                        } else
                            CardCrawlGame.sound.play("world");
                    }
                }
                //CardCrawlGame.sound.playA("ATTACK_HEAVY", -0.5F);

                AbstractDungeon.effectsQueue.add(new RoomTintEffect(Color.DARK_GRAY.cpy(), 0.8F,4F,true));
                AbstractDungeon.effectsQueue.add(new BorderVeryLongFlashEffect(new Color(1061109759)));
            }

            //AbstractDungeon.effectsQueue.add(new CollectorStakeEffect(this.x + MathUtils.random(-50.0F, 50.0F) * Settings.scale, this.y + MathUtils.random(-60.0F, 60.0F) * Settings.scale));
            this.stakeTimer = 0.3F;
            this.count -= 1;
            if (this.count == 0)
                this.isDone = true;
        }
    }

    public void render(SpriteBatch sb)
    {
    }

    public void dispose(){}
}
