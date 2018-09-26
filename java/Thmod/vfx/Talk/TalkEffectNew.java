package Thmod.vfx.Talk;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.ui.DialogWord;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.SpeechTextEffect;

import Thmod.ThMod;

public class TalkEffectNew extends AbstractGameEffect {
    private static final int RAW_W = 512;
    private float shadow_offset;
    private static final float SHADOW_OFFSET;
    private float x;
    private float y;
    private float scale_x;
    private float scale_y;
    private float wavy_y;
    private float wavyHelper;
    private static final float WAVY_SPEED;
    private static final float WAVY_DISTANCE;
    private static final float SCALE_TIME = 0.3F;
    private float scaleTimer;
    private static final float ADJUST_X;
    private static final float ADJUST_Y;
    private boolean facingRight;
    private static final float DEFAULT_DURATION = 2.0F;
    private static final float FADE_TIME = 0.3F;
    private boolean LclickStart;
    private boolean Lclick;
    private boolean RclickStart;
    private boolean Rclick;
    private Texture ImgLeft;
    private Texture ImgRight;
    private Texture ContinueLeft;
    private Texture ContinueRight;
    private Texture ImgYes;
    private Texture ImgNo;
    private boolean canChoose;

    public TalkEffectNew(float x, float y, String msg, boolean isPlayer, boolean canChoose) {
        this.shadow_offset = 0.0F;
        this.scaleTimer = 0.3F;
        float effect_x = -170.0F * Settings.scale;
        if (isPlayer) {
            effect_x = 170.0F * Settings.scale;
        }
        this.canChoose = canChoose;

        AbstractDungeon.effectsQueue.add(new TextEffectNew(x + effect_x, y + 124.0F * Settings.scale, msg, DialogWord.AppearEffect.BUMP_IN));
        if (isPlayer) {
            this.x = x + ADJUST_X;
        } else {
            this.x = x - ADJUST_X;
        }

        this.y = y + ADJUST_Y;
        this.scaleTimer = 0.3F;
        this.color = new Color(0.8F, 0.9F, 0.9F, 0.0F);
        this.duration = 0.3F;
        this.facingRight = !isPlayer;

        this.Lclick = false;
        this.LclickStart = false;
        this.Rclick = false;
        this.RclickStart = false;
        this.ImgLeft = ImageMaster.loadImage("images/ui/LClick.png");
        this.ImgRight = ImageMaster.loadImage("images/ui/RClick.png");
        if(ThMod.AllengOpen){
            this.ContinueLeft = ImageMaster.loadImage("images/ui/ContinueLeft.png");
            this.ContinueRight = ImageMaster.loadImage("images/ui/ContinueRight.png");
            this.ImgYes = ImageMaster.loadImage("images/ui/Yes.png");
            this.ImgNo = ImageMaster.loadImage("images/ui/No.png");
        }
        else {
            this.ContinueLeft = ImageMaster.loadImage("images/ui/ContinueZNLeft.png");
            this.ContinueRight = ImageMaster.loadImage("images/ui/ContinueZNRight.png");
            this.ImgYes = ImageMaster.loadImage("images/ui/YesZN.png");
            this.ImgNo = ImageMaster.loadImage("images/ui/NoZN.png");
        }

    }

    public void update() {
        this.updateScale();
        this.wavyHelper += Gdx.graphics.getDeltaTime() * WAVY_SPEED;
        this.wavy_y = MathUtils.sin(this.wavyHelper) * WAVY_DISTANCE;

        if(InputHelper.justClickedLeft){
            this.LclickStart = true;
        }
        if((this.LclickStart) && (InputHelper.justReleasedClickLeft)){
            this.Lclick = true;
        }
        if(InputHelper.justClickedRight){
            this.RclickStart = true;
        }
        if((this.RclickStart) && (InputHelper.justReleasedClickRight)){
            this.Rclick = true;
        }

        if((this.Lclick) || (this.Rclick)) {
            this.duration -= Gdx.graphics.getDeltaTime();
        }

        if ((this.Lclick) && (this.duration == 0)) {
            this.isDone = true;
            this.Lclick = false;
        }
        if ((this.Rclick) && (this.duration == 0)) {
            this.isDone = true;
            this.Rclick = false;
        }

        if (!((this.Lclick) || (this.Rclick))) {
            this.color.a = MathUtils.lerp(this.color.a, 1.0F, Gdx.graphics.getDeltaTime() * 12.0F);
        } else {
            this.color.a = MathUtils.lerp(this.color.a, 0.0F, Gdx.graphics.getDeltaTime() * 12.0F);
        }

        this.shadow_offset = MathUtils.lerp(this.shadow_offset, SHADOW_OFFSET, Gdx.graphics.getDeltaTime() * 4.0F);
    }

    private void updateScale() {
        this.scaleTimer -= Gdx.graphics.getDeltaTime();
        if (this.scaleTimer < 0.0F) {
            this.scaleTimer = 0.0F;
        }

        this.scale_x = Interpolation.circleIn.apply(Settings.scale, Settings.scale * 0.5F, this.scaleTimer / 0.3F);
        this.scale_y = Interpolation.swingIn.apply(Settings.scale, Settings.scale * 0.8F, this.scaleTimer / 0.3F);
    }

    public void render(SpriteBatch sb) {
        sb.setColor(new Color(0.0F, 0.0F, 0.0F, this.color.a / 4.0F));
        sb.draw(ImageMaster.SPEECH_BUBBLE_IMG, this.x - 256.0F + this.shadow_offset, this.y - 256.0F + this.wavy_y - this.shadow_offset, 256.0F, 256.0F, 512.0F, 512.0F, this.scale_x, this.scale_y, this.rotation, 0, 0, 512, 512, this.facingRight, false);
        sb.setColor(this.color);
        sb.draw(ImageMaster.SPEECH_BUBBLE_IMG, this.x - 256.0F, this.y - 256.0F + this.wavy_y, 256.0F, 256.0F, 512.0F, 512.0F, this.scale_x, this.scale_y, this.rotation, 0, 0, 512, 512, this.facingRight, false);
        sb.draw(this.ImgLeft,this.x - 140F,this.y - 100F + this.wavy_y,64F,32F,128F,64F,this.scale_x, this.scale_y, this.rotation, 0, 0, 128, 64, false, false);
        sb.draw(this.ImgRight,this.x - 10F,this.y - 100F + this.wavy_y,64F,32F,128F,64F,this.scale_x, this.scale_y, this.rotation, 0, 0, 128, 64, false, false);
        if(this.canChoose){
            sb.draw(this.ImgYes,this.x - 140F,this.y - 140F + this.wavy_y,64F,32F,128F,64F,this.scale_x, this.scale_y, this.rotation, 0, 0, 128, 64, false, false);
            sb.draw(this.ImgNo,this.x - 10F,this.y - 140F + this.wavy_y,64F,32F,128F,64F,this.scale_x, this.scale_y, this.rotation, 0, 0, 128, 64, false, false);
        }
        else {
            sb.draw(this.ContinueLeft, this.x - 140F, this.y - 140F + this.wavy_y, 64F, 32F, 128F, 64F, this.scale_x, this.scale_y, this.rotation, 0, 0, 128, 64, false, false);
            sb.draw(this.ContinueRight, this.x - 10F, this.y - 140F + this.wavy_y, 64F, 32F, 128F, 64F, this.scale_x, this.scale_y, this.rotation, 0, 0, 128, 64, false, false);
        }
    }

    static {
        SHADOW_OFFSET = 16.0F * Settings.scale;
        WAVY_SPEED = 6.0F * Settings.scale;
        WAVY_DISTANCE = 2.0F * Settings.scale;
        ADJUST_X = 170.0F * Settings.scale;
        ADJUST_Y = 116.0F * Settings.scale;
    }
}
