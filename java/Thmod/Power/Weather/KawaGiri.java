package Thmod.Power.Weather;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import Thmod.vfx.weather.FuuuEffect;
import Thmod.vfx.weather.KawaGiriBack;
import Thmod.vfx.weather.KawaGiriEffect;

public class KawaGiri extends AbstractPower {
    public static final String POWER_ID = "KawaGiri";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("KawaGiri");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private int StrgengthCounter;
    private int DexterityCounter;
    //private Color kiri = Color.GRAY.cpy();
    private Color[] noumu = new Color[8];
    private float[] x = new float[8];
    private float[] y = new float[8];
    private float[] aV = new float[8];
    private float[] scale = new float[8];
    private float[] rotation = new float[8];
    private float[] cV = new float[8];
    private float[] targetAlpha = new float[8];
    private boolean[] flipX = new boolean[8];
    private boolean[] flipY = new boolean[8];
    private boolean[] fadeOut = new boolean[8];
    private TextureAtlas.AtlasRegion[] img2 = new  TextureAtlas.AtlasRegion[8];
    //private TextureAtlas.AtlasRegion img3 = ImageMaster.BORDER_GLOW_2;
    public static KawaGiriEffect[] effects = new KawaGiriEffect[8];
    private KawaGiriBack back;

    public KawaGiri(AbstractCreature owner) {
        this.name = NAME;
        this.ID = "KawaGiri";
        this.owner = owner;
        this.amount = 3;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/Weather/KawaGiri.png");
        this.type = PowerType.BUFF;
        this.StrgengthCounter = 0;
        this.DexterityCounter = 0;
        //this.kiri.a = 0.0F;
        for(int i = 0; i < effects.length; i ++){
            initializeData(i);
        }
//        for (KawaGiriEffect effect : effects) {
//            effect.initializeData();
//            AbstractDungeon.effectList.add(effect);
//        }
        this.back = new KawaGiriBack();
        this.back.initializeData();
        AbstractDungeon.effectList.add(this.back);
    }

    public void atStartOfTurnPostDraw() {
        int roll = MathUtils.random(3) + 1;
        if(roll > 2){
            this.StrgengthCounter = roll - 2;
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, this.StrgengthCounter), this.StrgengthCounter));
            flash();
        }
        else {
            this.DexterityCounter = roll;
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new DexterityPower(this.owner, this.DexterityCounter), this.DexterityCounter));
            flash();
        }
    }

    public void atEndOfRound() {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, -this.StrgengthCounter), -this.StrgengthCounter));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new DexterityPower(this.owner, -this.DexterityCounter), -this.DexterityCounter));
        flash();
        if (this.amount <= 1)
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, this));
        else
            this.amount -= 1;
    }

    private void initializeData(int i){
        switch (MathUtils.random(2))
        {
            case 0:
                this.img2[i] = ImageMaster.SMOKE_1;
                break;
            case 1:
                this.img2[i] = ImageMaster.SMOKE_2;
                break;
            default:
                this.img2[i] = ImageMaster.SMOKE_3;
        }

        this.x[i] = (MathUtils.random(-100.0F * Settings.scale, Settings.WIDTH + 100.0F * Settings.scale) - this.img2[i].packedWidth / 2.0F);
        this.y[i] = (MathUtils.random(-100.0F * Settings.scale, 120.0F * Settings.scale) - this.img2[i].packedHeight / 2.0F);

        this.cV[i] = MathUtils.random(4.0F, 8.0F);
        this.aV[i] = (MathUtils.random(-40.0F, -30.0F) * Settings.scale);

        this.rotation[i] = MathUtils.random(0.0F, 360.0F);
        this.scale[i] = (MathUtils.random(8.0F, 15.0F) * Settings.scale);

        this.noumu[i] = Color.GRAY.cpy();
        this.noumu[i].r += MathUtils.random(-0.01F, 0.01F);
        this.noumu[i].g += MathUtils.random(-0.01F, 0.01F);
        this.noumu[i].b += MathUtils.random(-0.01F, 0.01F);
        this.noumu[i].a = 0.0F;
        this.targetAlpha[i] = MathUtils.random(0.8F, 0.9F);

        this.flipX[i] = MathUtils.randomBoolean();
        this.flipY[i] = MathUtils.randomBoolean();
        this.fadeOut[i] = true;
    }

    @Override
    public void renderIcons(SpriteBatch sb, float x, float y, Color c) {
        super.renderIcons(sb, x, y, c);

        for (int i = 0; i < effects.length; i++) {
            if (this.noumu[i].a <= 0.0F && !this.fadeOut[i]) {
                initializeData(i);
            } else if (this.noumu[i].a >= this.targetAlpha[i]) {
                this.fadeOut[i] = false;
                this.noumu[i].a -= Gdx.graphics.getDeltaTime() / this.cV[i];
            } else {
                if (this.fadeOut[i]) {
                    this.noumu[i].a += Gdx.graphics.getDeltaTime() / this.cV[i] * 2;
                } else {
                    this.noumu[i].a -= Gdx.graphics.getDeltaTime() / this.cV[i];
                }
            }
            this.rotation[i] += this.aV[i] * Gdx.graphics.getDeltaTime();

            if ((this.flipX[i]) && (!this.img2[i].isFlipX())) {
                this.img2[i].flip(true, false);
            } else if ((!this.flipX[i]) && (this.img2[i].isFlipX())) {
                this.img2[i].flip(true, false);
            }
            if ((this.flipY[i]) && (!this.img2[i].isFlipY())) {
                this.img2[i].flip(false, true);
            } else if ((!this.flipY[i]) && (this.img2[i].isFlipY())) {
                this.img2[i].flip(false, true);
            }

            sb.setColor(this.noumu[i]);
            sb.draw(this.img2[i], this.x[i], this.y[i],
                    this.img2[i].packedWidth / 2.0F, this.img2[i].packedHeight / 2.0F, this.img2[i].packedWidth, this.img2[i].packedHeight,
                    this.scale[i], this.scale[i], this.rotation[i]);
        }

//        if (this.kiri.a < Color.GRAY.a) {
//            this.kiri.a += Gdx.graphics.getDeltaTime();
//        } else if (this.kiri.a > Color.GRAY.a) {
//            this.kiri.a = Color.GRAY.a;
//        }
//        sb.setColor(this.kiri);
//        sb.draw(this.img3, 0F, 0F, Settings.WIDTH, Settings.HEIGHT);
    }

    @Override
    public void onRemove() {
        super.onRemove();
        //ThMod.logger.info("num is" + this.tmp + ", " + this.tmp2);
        for (int i = 0; i < effects.length; i ++) {
            effects[i].getInfo(this.noumu[i], this.x[i], this.y[i], this.aV[i], this.scale[i], this.rotation[i], this.cV[i], this.flipX[i], this.flipY[i], this.img2[i]);
            AbstractDungeon.effectList.add(effects[i]);
        }
        this.back.remove();
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0];
    }
}
