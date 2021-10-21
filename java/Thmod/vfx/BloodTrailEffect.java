package Thmod.vfx;

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

import java.util.ArrayList;

import static java.lang.Math.PI;

public class BloodTrailEffect extends AbstractGameEffect
{
    private float x;
    private float y;
    private float vX;
    private float vY;
    private float vXnum;
    private float fX;
    private float fY;
    private double ro;
    private double vR;
    private float sca;
    private float hbR;
    private float waveDst;
    private float baseAlpha;
    private float trailTimer = 0.0F;
    private boolean change;
    private TextureAtlas.AtlasRegion img;
    private Color setColor;
    private ArrayList<BloodWakeEffect> prevPositions = new ArrayList<>();
    private Vector2 starter;

    public BloodTrailEffect(Color setColor, Hitbox hb)
    {
        this.startingDuration = MathUtils.random(6.0F, 14.0F);
        this.duration = this.startingDuration;
        this.setColor = setColor;

        this.img = ImageMaster.STRIKE_BLUR;

        this.vX = (MathUtils.random(60.0F, 90.0F) * Settings.scale);
        //this.aX = (MathUtils.random(-5.0F, 5.0F) * Settings.scale);
        //this.vR = MathUtils.random(-2.0F, 2.0F);
        this.ro = MathUtils.random(0.0f, (float)(2 * PI));
        this.vR = MathUtils.random(-0.1F, 0.1F);
        this.waveDst = (this.vX * MathUtils.random(0.03F, 0.04F));
        //this.scale = (Settings.scale * this.vX / 60.0F);
//        if (MathUtils.randomBoolean()) {
//            this.vX = (-this.vX);
//        }
        this.vY = (MathUtils.random(-36.0F, 36.0F) * Settings.scale);
        this.color = setColor.cpy();
        this.baseAlpha = 0.25F;
        this.color.a = 0.0F;

        this.hbR = Math.max(hb.height/2, hb.width/2) + 50.0F;
        this.starter = new Vector2(hb.cX, hb.cY);

//        this.x = (MathUtils.random(this.starter.x - this.hbR, this.starter.x + this.hbR) - this.img.packedWidth / 2.0F);
//        this.y = (MathUtils.random(this.starter.y - this.hbR, this.starter.y + this.hbR) - this.img.packedHeight / 2.0F);
        this.fX = (MathUtils.random(this.starter.x - this.hbR + 50.0F, this.starter.x + this.hbR - 50.0F) - this.img.packedWidth / 2.0F);
        this.fY = (MathUtils.random(this.starter.y - this.hbR + 50.0F, this.starter.y + this.hbR - 50.0F) - this.img.packedHeight / 2.0F);
        this.change = true;
        this.vXnum = this.vX;
    }

    public void update()
    {
        double ro2;
        this.trailTimer -= Gdx.graphics.getDeltaTime();
        if (this.trailTimer < 0.0F)
        {
            this.trailTimer = 0.04F;
            BloodWakeEffect tmp = new BloodWakeEffect(this.setColor, new Vector2(this.fX, this.fY), this.prevPositions.size() + 1, this.prevPositions.size(),
                    this.sca, this.renderBehind, this.color);
            this.prevPositions.add(tmp);
            AbstractDungeon.effectsQueue.add(tmp);
            if (this.prevPositions.size() > 100) {
                this.prevPositions.get(0).powerRemove();
                this.prevPositions.remove(0);
            }
        }
        this.duration -= Gdx.graphics.getDeltaTime();

        //this.x += this.vX * Gdx.graphics.getDeltaTime();
        //this.vX += this.aX * Gdx.graphics.getDeltaTime();
//        if ((!this.prevPositions.isEmpty()) && (
//                ((this.prevPositions.get(0)).x < 0.0F) || ((this.prevPositions.get(0)).x > Settings.WIDTH)
//                || ((this.prevPositions.get(0)).y < 0.0F) || ((this.prevPositions.get(0)).y > Settings.HEIGHT))) {
//            AbstractDungeon.effectList.add(new JyouchiReiEffect(this.setColor, this.hb));
//            this.isDone = true;
//        }
        //this.y += this.vY * Gdx.graphics.getDeltaTime();
        //this.y += MathUtils.sin(this.duration * this.waveDst) * this.waveDst / 4.0F * Gdx.graphics.getDeltaTime() * 60.0F;

        //this.ro = PI/4;
        changeRotation();
        this.ro += this.vR * Gdx.graphics.getDeltaTime();
        if(this.ro >= 2 * PI){
            this.ro -= 2 * PI;
        }
        ro2 = this.ro + PI/2;
        if(ro2 >= 2 * PI){
            ro2 -= 2 * PI;
        }
//        this.fX = (float) (Math.cos(this.ro) * (this.x - this.starter.x) + Math.cos(ro2) * (this.y - this.starter.y)) + this.starter.x;
//        this.fY = (float) (Math.sin(this.ro) * (this.x - this.starter.x) + Math.sin(ro2) * (this.y - this.starter.y)) + this.starter.y;
        this.fX += (float) (Math.cos(this.ro) * (this.vX * Gdx.graphics.getDeltaTime()) +
                Math.cos(ro2) * (MathUtils.sin(this.duration * this.waveDst) * this.waveDst / 4.0F * Gdx.graphics.getDeltaTime() * 60.0F));
        this.fY += (float) (Math.sin(this.ro) * (this.vX * Gdx.graphics.getDeltaTime()) +
                Math.sin(ro2) * (MathUtils.sin(this.duration * this.waveDst) * this.waveDst / 4.0F * Gdx.graphics.getDeltaTime() * 60.0F));

        if (this.duration < 0.0F) {
            this.duration = this.startingDuration;
        }
        if (this.duration > this.startingDuration / 2.0F)
        {
            float tmp = this.duration - this.startingDuration / 2.0F;
            this.color.a = (Interpolation.fade.apply(0.8F, 1.0F, this.startingDuration / 2.0F - tmp) * this.baseAlpha);
        }
        else
        {
            this.color.a = (Interpolation.fade.apply(0.8F, 1.0F, this.duration / (this.startingDuration / 2.0F)) * this.baseAlpha);
        }

        for (int i = this.prevPositions.size() - 1; i > 0; i--)
        {
            this.prevPositions.get(i).changeState(this.setColor, this.prevPositions.size(), i, this.sca, this.color.cpy());
        }
    }

    public void render(SpriteBatch sb)
    {
        //sb.setBlendFunction(770, 1);
//        Color tmpColor = this.setColor.cpy();
//        tmpColor.a = this.color.a;
//        for (int i = this.prevPositions.size() - 1; i > 0; i--)
//        {
//            tmpColor.a *= 0.95F;
//            sb.setColor(tmpColor);
//            sb.draw(this.img, (this.prevPositions.get(i)).x, (this.prevPositions.get(i)).y,
//                    this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight,
//                    this.scale * (i + 5) / this.prevPositions.size() * this.sca / 2.5F, this.scale * (i + 5) / this.prevPositions.size() * this.sca / 2.5F, this.rotation);
//            sb.draw(this.img, (this.prevPositions.get(i)).x, (this.prevPositions.get(i)).y,
//                    this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight,
//                    this.scale * (i + 5) / this.prevPositions.size() * this.sca / 2.5F, this.scale * (i + 5) / this.prevPositions.size() * this.sca / 2.5F, this.rotation);
//        }
        sb.setColor(this.color);
        sb.draw(this.img, this.fX, this.fY, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale *
                this.sca, this.scale *
                this.sca, this.rotation);
        sb.draw(this.img, this.fX, this.fY, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale *
                this.sca, this.scale *
                this.sca, this.rotation);

        //sb.setBlendFunction(770, 771);
    }

    private void changeRotation(){
        double range;
        double xDev = this.fX - this.starter.x, yDev = this.fY - this.starter.y;
        range = Math.sqrt((xDev) * (xDev) + (yDev) * (yDev));
        if(range >= this.hbR){
            if(this.change) {
                this.change = false;
                this.renderBehind = !this.renderBehind;
                if ((xDev == range) || (xDev == -range)) {
                    this.ro += PI;
                    if (this.ro > 2 * PI) {
                        this.ro -= 2 * PI;
                    }
                    return;
                }
                if ((yDev == range) || (yDev == -range)) {
                    this.ro += PI;
                    if (this.ro > 2 * PI) {
                        this.ro -= 2 * PI;
                    }
                    return;
                }

                double s = yDev / range, c = xDev / range;
                double v = Math.asin(s), v1 = Math.acos(c);
                double roC;
                if ((v1 > PI / 2) && (v > 0)) {
                    roC = v1;
                } else if ((v1 > PI / 2) && (v < 0)) {
                    roC = 2 * PI - v1;
                } else if ((v1 < PI / 2) && (v < 0)) {
                    roC = 2 * PI - v1;
                } else {
                    roC = v1;
                }

                this.ro = PI - this.ro + 2 * roC;
                while (this.ro > 2 * PI) {
                    this.ro -= 2 * PI;
                }

            }
        }
        else {
            this.change = true;
        }

        if(range > this.hbR + 20){
            if ((xDev == range) || (xDev == -range)) {
                this.ro += PI;
                if (this.ro > 2 * PI) {
                    this.ro -= 2 * PI;
                }
                return;
            }
            if ((yDev == range) || (yDev == -range)) {
                this.ro += PI;
                if (this.ro > 2 * PI) {
                    this.ro -= 2 * PI;
                }
                return;
            }

            double s = yDev / range, c = xDev / range;
            double v = Math.asin(s), v1 = Math.acos(c);
            double roC;
            if ((v1 > PI / 2) && (v > 0)) {
                roC = v1;
            } else if ((v1 > PI / 2) && (v < 0)) {
                roC = 2 * PI - v1;
            } else if ((v1 < PI / 2) && (v < 0)) {
                roC = 2 * PI - v1;
            } else {
                roC = v1;
            }

            this.ro = PI + roC;
            while (this.ro > 2 * PI) {
                this.ro -= 2 * PI;
            }
        }

        if(this.renderBehind){
            this.sca = Interpolation.fade.apply(2F, 3F, (float) range / this.hbR);
        }
        else {
            this.sca = Interpolation.fade.apply(4F, 3F, (float) range / this.hbR);
        }

        this.vX = Interpolation.fade.apply(this.vXnum * 1.25f, this.vXnum * 0.75f, (float) range / this.hbR);
    }

    public void powerRemove(){
        for (BloodWakeEffect e : this.prevPositions){
            e.powerRemove();
        }
        this.isDone = true;
    }

    public void dispose() {}
}
