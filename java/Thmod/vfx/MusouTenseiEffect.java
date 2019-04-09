package Thmod.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

import basemod.DevConsole;

import static java.lang.Math.PI;

public class MusouTenseiEffect extends AbstractGameEffect {
    private float x;
    private float y;
    private double timer;
    private double timerNum;
    private double vTime;
    private double aTime;
    private double vTimeMax;
    private boolean vTimeUp;
    private float outDuration;
    private float outDurationNum;
    private float ro;
    private float vR;
    private float aR;
    private float sca;
    private float hbR;
    private float hbRnum;
    private Vector2 starter;
    private TextureAtlas.AtlasRegion img;
    private TextureAtlas.AtlasRegion img2;
    private TextureAtlas.AtlasRegion img22;
    private TextureAtlas.AtlasRegion img3;
    private Color setColor;
    private Color highColor;
    private Color highColor2;
    private Color highColor3;
    private boolean active;
    private boolean front;
    private boolean startToAct;
    private float alpha;
    public float rotation2;
    private float vR2;
    private float aR2;
    private float vR2Max;
    private boolean vR2Up;
    private double vXR;
    private float vRR;

    private float activeTime;
    private float activeDuration;
    private float activeDurationNum;

    public MusouTenseiEffect(Hitbox hb, int num){
        TextureAtlas thvfxAtlas = new TextureAtlas(Gdx.files.internal("images/vfx/thvfx.atlas"));
        this.startingDuration = 6.0F;
        this.duration = startingDuration;
        this.outDurationNum = 0.4f;
        this.outDuration = this.outDurationNum;

        //this.img = ImageMaster.POWER_UP_2;
        this.img = thvfxAtlas.findRegion("yinyang");
        this.img2 = ImageMaster.STRIKE_BLUR;
        this.img22 = ImageMaster.POWER_UP_2;
        this.img3 = ImageMaster.WHITE_RING;

        this.hbR = Math.max(hb.height/2, hb.width/2);
        this.hbRnum = this.hbR;
        this.starter = new Vector2(hb.cX - 15, hb.cY);
        this.setColor = Color.WHITE.cpy();
        this.highColor = Color.WHITE.cpy();
        this.highColor2 = Color.SKY.cpy();
        this.highColor3 = Color.BLUE.cpy();

        this.x = this.starter.x;
        this.y = this.starter.y - (float) Math.sqrt(this.hbR / 16 - Math.pow(this.x - this.starter.x,2) / 16);
        //this.y = this.starter.y;
        this.active = false;
        this.vR2 = 0.0f;
        this.aR2 = 120.0f;
        this.vR2Max = 360;
        this.vR2Up = true;

        this.timer = 2.8;
        this.timerNum = this.timer;
        this.timer = this.timer / 7 * num;
        this.vTime = 0;
        this.aTime = 2;
        this.vTimeMax = 3;
        this.vTimeUp = true;
        this.vXR = 0;
        this.ro = 16;
        this.vR = 8;
        this.aR = 0.5f;
        this.sca = 1.1f;
        this.startToAct = false;
        this.vRR = 10;

        if(this.timer < this.timerNum / 2){
            this.front = false;
            this.renderBehind = true;
        }
        else {
            this.front = true;
        }

        this.activeTime = 6;
        this.activeDurationNum = 1f;
        this.activeDuration = this.activeDurationNum;
    }

    public void update()
    {
        this.duration -= Gdx.graphics.getDeltaTime();

        if(this.outDuration > 0){
            this.outDuration -= Gdx.graphics.getDeltaTime();
        }
        else {
            this.outDuration = 0;
            if(this.activeTime > 0) {
                this.timer -= this.vTime * Gdx.graphics.getDeltaTime();
            }

            if(this.vTimeUp){
                if(this.vTime >= this.vTimeMax){
                    this.vTimeUp = false;
                }
                else {
                    this.vTime += this.aTime * Gdx.graphics.getDeltaTime();
                }
            }
            else {
                if(this.vTime <= -this.vTimeMax){
                    this.vTimeUp = true;
                }
                else {
                    this.vTime -= this.aTime * Gdx.graphics.getDeltaTime();
                }
            }
        }

        if(this.ro > 1) {
            if (this.startToAct) {
                if(this.vR < 0.5){
                    this.vR = 0.5f;
                }
                this.ro -= this.vR * Gdx.graphics.getDeltaTime();
                this.vR -= this.aR * Gdx.graphics.getDeltaTime();
            }
        }
        else {
            this.ro = 1;
        }

        if((this.hbR - this.hbRnum) < 50) {
            if (this.startToAct) {
                this.hbR += this.vRR * Gdx.graphics.getDeltaTime();
            }
        }
        else {
            this.hbR = this.hbRnum + 50;
        }

        changeSize();

        if(this.outDuration > 0){
            if (front) {
                this.y = this.starter.y - (float) Math.sqrt(Math.pow(this.hbR, 2) / this.ro - Math.pow((this.starter.x + (float) (Math.cos(this.vXR) * (this.hbR + 0.1))) - this.starter.x, 2) / this.ro);
            } else {
                this.y = this.starter.y + (float) Math.sqrt(Math.pow(this.hbR, 2) / this.ro - Math.pow((this.starter.x + (float) (Math.cos(this.vXR) * (this.hbR + 0.1))) - this.starter.x, 2) / this.ro);
            }
            if (Math.abs(this.x - this.starter.x) >= this.hbR) {
                this.y = this.starter.y;
            }
        }
        else {
            if (front) {
                this.y = this.starter.y - (float) Math.sqrt(Math.pow(this.hbR, 2) / this.ro - Math.pow(this.x - this.starter.x, 2) / this.ro);
            } else {
                this.y = this.starter.y + (float) Math.sqrt(Math.pow(this.hbR, 2) / this.ro - Math.pow(this.x - this.starter.x, 2) / this.ro);
            }
            if (Math.abs((float) (Math.cos(this.vXR) * (this.hbR + 0.1))) >= this.hbR) {
                this.y = this.starter.y;
            }
        }

        if(this.vR2Up){
            if(this.vR2 >= this.vR2Max){
                this.vR2Up = false;
            }
            else {
                this.vR2 += this.aR2 * Gdx.graphics.getDeltaTime();
            }
        }
        else {
            if(this.vR2 <= -this.vR2Max){
                this.vR2Up = true;
            }
            else {
                this.vR2 -= this.aR2 * Gdx.graphics.getDeltaTime();
            }
        }
        this.rotation2 += this.vR2 * Gdx.graphics.getDeltaTime();

        if (this.duration < 0.0F) {
            if(this.activeTime > 0) {
                this.duration = this.startingDuration;
            }
        }
        if(active) {
            if(this.duration >= 0) {
                if (this.duration > this.startingDuration / 2.0F) {
                    float tmp = this.duration - this.startingDuration / 2.0F;
                    this.alpha = (Interpolation.fade.apply(0.0F, 1.0F, (this.startingDuration / 2.0F - tmp) / (this.startingDuration / 2.0F)));
                } else {
                    this.alpha = (Interpolation.fade.apply(0.0F, 1.0F, this.duration / (this.startingDuration / 2.0F)));
                }
            }
        }

        if(this.ro == 1){
            if(this.activeTime >= 0) {
                this.activeTime -= Gdx.graphics.getDeltaTime();
            }
            else {
                if(this.duration <= 0) {
                    this.activeDuration -= Gdx.graphics.getDeltaTime();

                    if(this.activeDuration > this.activeDurationNum / 2) {
                        this.sca = 0.75f * (this.activeDurationNum - this.activeDuration) / (this.activeDurationNum / 2);
                        this.alpha = (this.activeDurationNum - this.activeDuration) / (this.activeDurationNum / 2);
                    }
                    else if(this.activeDuration >= 0) {
                        this.alpha = this.activeDuration / this.activeDurationNum / 2;
                    }
//                    else {
//                        this.isDone = true;
//                    }
                    //this.alpha = 1;
                    //DevConsole.logger.info("alpha:" + this.alpha);
                }
            }
        }
    }

    public void render(SpriteBatch sb)
    {
        sb.setBlendFunction(770, 1);

        if(active) {
            this.highColor3.a = 0.13f * this.alpha;
            sb.setColor(this.highColor3);
            sb.draw(this.img3, this.x, this.y, this.img22.packedWidth / 2.0F, this.img22.packedHeight / 2.0F, this.img22.packedWidth, this.img22.packedHeight,
                    this.scale * 4.2F * this.sca, this.scale * 4.2F * this.sca, this.rotation);
            this.highColor3.a = 0.05f * this.alpha;
            sb.setColor(this.highColor3);
            sb.draw(this.img3, this.x, this.y, this.img22.packedWidth / 2.0F, this.img22.packedHeight / 2.0F, this.img22.packedWidth, this.img22.packedHeight,
                    this.scale * 4.5F * this.sca, this.scale * 4.5F * this.sca, this.rotation);
            sb.draw(this.img3, this.x, this.y, this.img22.packedWidth / 2.0F, this.img22.packedHeight / 2.0F, this.img22.packedWidth, this.img22.packedHeight,
                    this.scale * 3.9F * this.sca, this.scale * 3.9F * this.sca, this.rotation);

            this.highColor3.a = this.alpha;
            sb.setColor(this.highColor3);
            sb.draw(this.img2, this.x, this.y, this.img22.packedWidth / 2.0F, this.img22.packedHeight / 2.0F, this.img22.packedWidth, this.img22.packedHeight,
                    this.scale * 16.0F * this.sca, this.scale * 16.0F * this.sca, this.rotation);

            this.highColor2.a = this.alpha;
            sb.setColor(this.highColor2);
            sb.draw(this.img2, this.x, this.y, this.img22.packedWidth / 2.0F, this.img22.packedHeight / 2.0F, this.img22.packedWidth, this.img22.packedHeight,
                    this.scale * 7.0F * this.sca, this.scale * 7.0F * this.sca, this.rotation);
        }

        if((this.activeTime > 0) || (this.duration > 0)) {
            sb.setBlendFunction(770, 771);
            sb.setColor(this.setColor);
            sb.draw(this.img, this.x - 35, this.y - 35, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight,
                    this.scale * 0.6F * this.sca, this.scale * 0.6F * this.sca, this.rotation2);
            sb.setBlendFunction(770, 1);


            if (active) {
                this.highColor2.a = this.alpha;
                sb.setColor(this.highColor2);
                sb.draw(this.img, this.x - 35, this.y - 35, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight,
                        this.scale * 0.6F * this.sca, this.scale * 0.6F * this.sca, this.rotation2);
                this.highColor.a = this.alpha;
                sb.setColor(this.highColor);
                sb.draw(this.img, this.x - 35, this.y - 35, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight,
                        this.scale * 0.6F * this.sca, this.scale * 0.6F * this.sca, this.rotation2);
            }
        }

        sb.setBlendFunction(770, 771);
    }

    public void setActive(){
        this.active = true;
    }

    private void changeSize(){
        if(this.vTime >= 0) {
            if ((this.timer < this.timerNum / 2) && (this.front)) {
                this.front = false;
                this.renderBehind = true;
                //this.timer += this.timerNum;
                this.sca = 1;
            }
            if ((this.timer <= 0) && (!this.front)) {
                this.front = true;
                this.renderBehind = false;
                this.timer += this.timerNum;
                this.sca = 1;
            }
        }
        else {
            if ((this.timer > this.timerNum / 2) && (!this.front)) {
                this.front = true;
                this.renderBehind = false;
                this.sca = 1;
            }
            if ((this.timer >= this.timerNum) && (this.front)) {
                this.front = false;
                this.renderBehind = true;
                this.timer -= this.timerNum;
                this.sca = 1;
            }
        }

        this.vXR = ((PI * 2) * (this.timer / this.timerNum));
        this.x = this.starter.x + ((float) (Math.cos(this.vXR) * (this.hbR + 0.1)) * (this.outDurationNum - this.outDuration) / this.outDurationNum);
        this.sca = 1 - (float) (Math.sin(this.vXR) * (0.1 * ((this.ro - 1) / (15))) * (this.outDurationNum - this.outDuration) / this.outDurationNum);
    }

    public void setStart(){
        this.startToAct = true;
    }

    public void printDura(){
        DevConsole.logger.info("dura:" + this.duration);
    }

    public void dispose() {}
}
