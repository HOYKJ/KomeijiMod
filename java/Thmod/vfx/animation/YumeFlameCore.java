package Thmod.vfx.animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

import static java.lang.Math.PI;

public class YumeFlameCore extends AbstractGameEffect {
    private float x;
    private float y;
    private Vector2 starter;
    private float r;
    private float scaleS;
    private float theta, divisor;
    private float startTimer, timer, triggerT;
    private boolean reverse;
    private Color color2;

    public YumeFlameCore(Hitbox hb) {
        this.duration = MathUtils.random(8.0F, 16.0F);
        this.startingDuration = this.duration;
        this.starter = new Vector2(hb.cX, hb.cY + MathUtils.random(-30.0F, 30.0F));
        this.r = Math.max(hb.height/2, hb.width/2);
        this.r += MathUtils.random(0.0F, 160.0F);
        this.reverse = MathUtils.randomBoolean();
        this.color = Color.WHITE.cpy();
        this.color2 = Color.NAVY.cpy();
        this.scaleS = Settings.scale * MathUtils.random(1.0F, 2.0F);
        this.scale = scaleS;
        this.divisor = MathUtils.random(1.5F, 16.0F);
        this.triggerT = 0;

        this.startTimer = MathUtils.random(6.0F, 10.0F);
        this.timer = MathUtils.random(0F, this.startTimer);

        this.renderBehind = this.timer < this.startTimer / 2;

        setSize();
    }

    public void update() {
        this.duration -= Gdx.graphics.getDeltaTime();
        this.timer += this.reverse? -Gdx.graphics.getDeltaTime(): Gdx.graphics.getDeltaTime();
        this.triggerT -= Gdx.graphics.getDeltaTime();

        setSize();

        if (this.duration < 0.0F) {
            this.isDone = true;
        }

        if(this.duration < this.startingDuration / 4){
            this.color.a = this.duration / this.startingDuration * 4;
            this.color2.a = this.duration / this.startingDuration * 4;
        }

        if(this.triggerT <= 0) {
            this.triggerT = 0.02F;
            float t = MathUtils.random(0.5F, 1.5F);
            AbstractDungeon.effectsQueue.add(new ColorFlameFlareEffect(this.x, this.y, this.color, this.scale, this.renderBehind, t));
            AbstractDungeon.effectsQueue.add(new ColorFlameParticelEffect(this.x, this.y, this.color2, this.scale, this.renderBehind, t));
        }
    }

    private void setSize(){
        if((this.renderBehind) && (((!this.reverse) && (this.timer >= this.startTimer / 2)) || ((this.reverse) && (this.timer <= 0)))){
            this.renderBehind = false;
        }
        else if((!this.renderBehind) && (((!this.reverse) && (this.timer >= this.startTimer)) || ((this.reverse) && (this.timer <= this.startTimer / 2)))){
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

    public void render(SpriteBatch sb) {
    }

    public void dispose() {
    }
}
