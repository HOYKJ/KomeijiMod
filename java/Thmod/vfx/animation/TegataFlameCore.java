package Thmod.vfx.animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

import static java.lang.Math.PI;

public class TegataFlameCore extends AbstractGameEffect {
    private float x;
    private float y;
    private Vector2 starter;
    private float r;
    private float scaleS;
    private float theta, divisor;
    private float startTimer, timer, triggerT;
    private Color color2;

    public TegataFlameCore(Hitbox hb, float num) {
        this.starter = new Vector2(hb.cX, hb.cY);
        this.r = Math.max(hb.height/2, hb.width/2);
        this.r += 40F;
        this.color = Color.WHITE.cpy();
        this.color2 = Color.PINK.cpy();
        this.scaleS = 1.2F;
        this.scale = scaleS;
        this.divisor = 16.0F;
        this.triggerT = 0;

        this.startTimer = 12.0F;
        this.timer = 0.01F + startTimer / 6 * num;

        this.renderBehind = this.timer < this.startTimer / 2;

        setSize();
    }

    public void update() {
        this.timer += Gdx.graphics.getDeltaTime();
        this.triggerT -= Gdx.graphics.getDeltaTime();

        setSize();

        if(this.triggerT <= 0) {
            this.triggerT = 0.05F;
            float t = 0.5F;
            AbstractDungeon.effectsQueue.add(new ColorFlameFlareEffect(this.x, this.y, this.color, this.scale, this.renderBehind, t));
            AbstractDungeon.effectsQueue.add(new ColorFlameParticelEffect(this.x, this.y, this.color2, this.scale, this.renderBehind, t, MathUtils.random(6.0F, 10.0F)));
        }
    }

    private void setSize(){
        if((this.renderBehind) && (this.timer >= this.startTimer / 2)){
            this.renderBehind = false;
        }
        else if((!this.renderBehind) && (this.timer >= this.startTimer)){
            this.renderBehind = true;
        }

        if(this.timer >= this.startTimer){
            this.timer -= this.startTimer;
        }
        else if(this.timer <= 0){
            this.timer += this.startTimer;
        }

        this.theta = (float) ((PI * 2) * (this.timer / this.startTimer));
        this.scale = this.scaleS - MathUtils.sin(this.theta) * (float) Math.sqrt(this.divisor) * this.scaleS / 10;
        this.x = this.starter.x + MathUtils.cos(this.theta) * this.r;
        this.y = this.starter.y + (float) Math.sqrt((Math.pow(this.r, 2) - Math.pow(this.x - this.starter.x, 2)) / this.divisor) * (this.renderBehind? 1: -1);
    }

    public void end(){
        for(int i = 0; i < 6; i ++){
            AbstractDungeon.effectsQueue.add(new CustomPetalEffect(this.x, this.y, 0.4F - MathUtils.sin(this.theta) * 0.1F, Color.PINK.cpy(), this.renderBehind));
        }
        this.isDone = true;
    }

    public void remove(){
        this.isDone = true;
    }

    public void render(SpriteBatch sb) {
    }

    public void dispose() {
    }
}
