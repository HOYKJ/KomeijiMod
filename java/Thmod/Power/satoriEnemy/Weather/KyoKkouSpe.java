package Thmod.Power.satoriEnemy.Weather;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.unique.CalculatedGambleAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import Thmod.Power.PointPower;

public class KyoKkouSpe extends AbstractWeatherPowers {
    public static final String POWER_ID = "KyoKkouSpe";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("KyoKkou");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private int counter;
    private int counter1;
    private int roll;
    private AbstractPlayer p = AbstractDungeon.player;
    private int StrgengthCounter;
    private int DexterityCounter;
    private boolean damaged;
    private TextureAtlas.AtlasRegion img3 = ImageMaster.BORDER_GLOW_2;
    private float cV;
    private float cV2;
    private float cV3;
    private Color kyokkou;
    private Color kyokkou2;
    private Color kyokkou3;
    private Color targetColor;
    private Color targetColor2;
    private Color targetColor3;
    private boolean changed;
    private boolean changed2;
    private boolean changed3;
    private boolean bigger;
    private boolean bigger2;
    private boolean bigger3;
    private float[] changeC = new float[4];
    private float[] changeC2 = new float[4];
    private float[] changeC3 = new float[4];
    private int lastColor;
    private int lastColor2;
    private int lastColor3;
    private boolean first = true;

    public KyoKkouSpe(AbstractCreature owner) {
        this.name = NAME;
        this.ID = "KyoKkouSpe";
        this.owner = owner;
        this.amount = 5;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/Weather/KyoKkou.png");
        this.type = PowerType.BUFF;
        this.counter = 0;
        this.counter1 = 1;
        this.StrgengthCounter = 0;
        this.DexterityCounter = 0;
        this.damaged = true;
        this.roll = MathUtils.random(19);
        this.kyokkou = Color.PURPLE.cpy();
        this.kyokkou2 = Color.PURPLE.cpy();
        this.kyokkou3 = Color.PURPLE.cpy();
        this.kyokkou.a = 0.0F;
        this.kyokkou2.a = 0.0F;
        this.kyokkou3.a = 0.0F;
        randomColor();
        randomColor2();
        randomColor3();
    }

    private void randomColor(){
        switch (MathUtils.random(6)){
            case 0:
                if(this.lastColor == 0){
                    randomColor();
                }
                else {
                    this.targetColor = Color.SCARLET.cpy();
                    this.lastColor = 0;
                }
                break;
            case 1:
                if(this.lastColor == 1){
                    randomColor();
                }
                else {
                    this.targetColor = Color.ORANGE.cpy();
                    this.lastColor = 1;
                }
                break;
            case 2:
                if(this.lastColor == 2){
                    randomColor();
                }
                else {
                    this.targetColor = Color.YELLOW.cpy();
                    this.lastColor = 2;
                }
                break;
            case 3:
                if(this.lastColor == 3){
                    randomColor();
                }
                else {
                    this.targetColor = Color.GREEN.cpy();
                    this.lastColor = 3;
                }
                break;
            case 4:
                if(this.lastColor == 4){
                    randomColor();
                }
                else {
                    this.targetColor = Color.SKY.cpy();
                    this.lastColor = 4;
                }
                break;
            case 5:
                if(this.lastColor == 5){
                    randomColor();
                }
                else {
                    this.targetColor = Color.BLUE.cpy();
                    this.lastColor = 5;
                }
                break;
            default:
                if(this.lastColor == 6){
                    randomColor();
                }
                else {
                    this.targetColor = Color.PURPLE.cpy();
                    this.lastColor = 6;
                }
                break;
        }
        this.cV = MathUtils.random(4.0F, 6.0F);
        this.changed = true;
    }

    private void randomColor2(){
        switch (MathUtils.random(6)){
            case 0:
                if(this.lastColor2 == 0){
                    randomColor2();
                }
                else {
                    this.targetColor2 = Color.SCARLET.cpy();
                    this.lastColor2 = 0;
                }
                break;
            case 1:
                if(this.lastColor2 == 1){
                    randomColor2();
                }
                else {
                    this.targetColor2 = Color.ORANGE.cpy();
                    this.lastColor2 = 1;
                }
                break;
            case 2:
                if(this.lastColor2 == 2){
                    randomColor2();
                }
                else {
                    this.targetColor2 = Color.YELLOW.cpy();
                    this.lastColor2 = 2;
                }
                break;
            case 3:
                if(this.lastColor2 == 3){
                    randomColor2();
                }
                else {
                    this.targetColor2 = Color.GREEN.cpy();
                    this.lastColor2 = 3;
                }
                break;
            case 4:
                if(this.lastColor2 == 4){
                    randomColor2();
                }
                else {
                    this.targetColor2 = Color.SKY.cpy();
                    this.lastColor2 = 4;
                }
                break;
            case 5:
                if(this.lastColor2 == 5){
                    randomColor2();
                }
                else {
                    this.targetColor2 = Color.BLUE.cpy();
                    this.lastColor2 = 5;
                }
                break;
            default:
                if(this.lastColor2 == 6){
                    randomColor2();
                }
                else {
                    this.targetColor2 = Color.PURPLE.cpy();
                    this.lastColor2 = 6;
                }
                break;
        }
        this.cV2 = MathUtils.random(6.0F, 8.0F);
        this.changed2 = true;
    }

    private void randomColor3(){
        switch (MathUtils.random(6)){
            case 0:
                if(this.lastColor3 == 0){
                    randomColor3();
                }
                else {
                    this.targetColor3 = Color.SCARLET.cpy();
                    this.lastColor3 = 0;
                }
                break;
            case 1:
                if(this.lastColor3 == 1){
                    randomColor3();
                }
                else {
                    this.targetColor3 = Color.ORANGE.cpy();
                    this.lastColor3 = 1;
                }
                break;
            case 2:
                if(this.lastColor3 == 2){
                    randomColor3();
                }
                else {
                    this.targetColor3 = Color.YELLOW.cpy();
                    this.lastColor3 = 2;
                }
                break;
            case 3:
                if(this.lastColor3 == 3){
                    randomColor3();
                }
                else {
                    this.targetColor3 = Color.GREEN.cpy();
                    this.lastColor3 = 3;
                }
                break;
            case 4:
                if(this.lastColor3 == 4){
                    randomColor3();
                }
                else {
                    this.targetColor3 = Color.SKY.cpy();
                    this.lastColor3 = 4;
                }
                break;
            case 5:
                if(this.lastColor3 == 5){
                    randomColor3();
                }
                else {
                    this.targetColor3 = Color.BLUE.cpy();
                    this.lastColor3 = 5;
                }
                break;
            default:
                if(this.lastColor3 == 6){
                    randomColor3();
                }
                else {
                    this.targetColor3 = Color.PURPLE.cpy();
                    this.lastColor3 = 6;
                }
                break;
        }
        this.cV3 = MathUtils.random(8.0F, 10.0F);
        this.changed3 = true;
    }

    @Override
    public void renderIcons(SpriteBatch sb, float x, float y, Color c) {
        super.renderIcons(sb, x, y, c);

        if(this.first){
            this.kyokkou.a += Gdx.graphics.getDeltaTime() / 4;
            this.kyokkou2.a += Gdx.graphics.getDeltaTime() / 4;
            this.kyokkou3.a += Gdx.graphics.getDeltaTime() / 4;

            sb.setColor(this.kyokkou);
            sb.draw(this.img3, 0F, 0F, Settings.WIDTH, Settings.HEIGHT);
            sb.setColor(this.kyokkou2);
            sb.draw(this.img3, -100.0F, -100.0F, Settings.WIDTH + 200.0F, Settings.HEIGHT + 200.0F);
            sb.setColor(this.kyokkou3);
            sb.draw(this.img3, -150.0F, -150.0F, Settings.WIDTH + 300.0F, Settings.HEIGHT + 300.0F);
            if(this.kyokkou.a >= 0.8F){
                this.first = false;
            }
        }
        else {
            if (this.changed) {
                this.bigger = this.kyokkou.r < this.targetColor.r;
                this.changeC[0] = (this.kyokkou.r - this.targetColor.r);
                this.changeC[1] = (this.kyokkou.g - this.targetColor.g);
                this.changeC[2] = (this.kyokkou.b - this.targetColor.b);
                this.changeC[3] = (this.kyokkou.a - this.targetColor.a);
                this.changed = false;
            }
            if (((this.bigger) && (this.kyokkou.r >= this.targetColor.r)) || ((!this.bigger) && (this.kyokkou.r <= this.targetColor.r))) {
                randomColor();
            } else {
                this.kyokkou.r -= this.changeC[0] * (Gdx.graphics.getDeltaTime() / this.cV);
                this.kyokkou.g -= this.changeC[1] * (Gdx.graphics.getDeltaTime() / this.cV);
                this.kyokkou.b -= this.changeC[2] * (Gdx.graphics.getDeltaTime() / this.cV);
                this.kyokkou.a -= this.changeC[3] * (Gdx.graphics.getDeltaTime() / this.cV);
            }

            sb.setColor(this.kyokkou);
            sb.draw(this.img3, 0F, 0F, Settings.WIDTH, Settings.HEIGHT);

            if (this.changed2) {
                this.bigger2 = this.kyokkou2.r < this.targetColor2.r;
                this.changeC2[0] = (this.kyokkou2.r - this.targetColor2.r);
                this.changeC2[1] = (this.kyokkou2.g - this.targetColor2.g);
                this.changeC2[2] = (this.kyokkou2.b - this.targetColor2.b);
                this.changeC2[3] = (this.kyokkou2.a - this.targetColor2.a);
                this.changed2 = false;
            }
            if (((this.bigger2) && (this.kyokkou2.r >= this.targetColor2.r)) || ((!this.bigger2) && (this.kyokkou2.r <= this.targetColor2.r))) {
                randomColor2();
            } else {
                this.kyokkou2.r -= this.changeC2[0] * (Gdx.graphics.getDeltaTime() / this.cV2);
                this.kyokkou2.g -= this.changeC2[1] * (Gdx.graphics.getDeltaTime() / this.cV2);
                this.kyokkou2.b -= this.changeC2[2] * (Gdx.graphics.getDeltaTime() / this.cV2);
                this.kyokkou2.a -= this.changeC2[3] * (Gdx.graphics.getDeltaTime() / this.cV2);
            }

            sb.setColor(this.kyokkou2);
            sb.draw(this.img3, -100.0F, -100.0F, Settings.WIDTH + 200.0F, Settings.HEIGHT + 200.0F);

            if (this.changed3) {
                this.bigger3 = this.kyokkou3.r < this.targetColor3.r;
                this.changeC3[0] = (this.kyokkou3.r - this.targetColor3.r);
                this.changeC3[1] = (this.kyokkou3.g - this.targetColor3.g);
                this.changeC3[2] = (this.kyokkou3.b - this.targetColor3.b);
                this.changeC3[3] = (this.kyokkou3.a - this.targetColor3.a);
                this.changed3 = false;
            }
            if (((this.bigger3) && (this.kyokkou3.r >= this.targetColor3.r)) || ((!this.bigger3) && (this.kyokkou3.r <= this.targetColor3.r))) {
                randomColor3();
            } else {
                this.kyokkou3.r -= this.changeC3[0] * (Gdx.graphics.getDeltaTime() / this.cV3);
                this.kyokkou3.g -= this.changeC3[1] * (Gdx.graphics.getDeltaTime() / this.cV3);
                this.kyokkou3.b -= this.changeC3[2] * (Gdx.graphics.getDeltaTime() / this.cV3);
                this.kyokkou3.a -= this.changeC3[3] * (Gdx.graphics.getDeltaTime() / this.cV3);
            }

            sb.setColor(this.kyokkou3);
            sb.draw(this.img3, -150.0F, -150.0F, Settings.WIDTH + 300.0F, Settings.HEIGHT + 300.0F);
        }
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0];
    }
}
