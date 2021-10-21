package Thmod.Power.Weather;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import Thmod.vfx.weather.FuuuEffect;
import Thmod.vfx.weather.KawaGiriBack;
import Thmod.vfx.weather.KiriSameEffect;

public class KiriSame extends AbstractPower {
    public static final String POWER_ID = "KiriSame";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("KiriSame");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
//    private float[][] x = new float[18][36];
//    private float[][] y = new float[18][36];
//    private float[][] vX = new float[18][36];
//    private float[][] vY = new float[18][36];
//    private float[][] scale = new float[18][36];
//    private float[][] scaleY = new float[18][36];
//    private float[][] rotation = new float[18][36];
//    private Color[][] rain = new Color[18][36];
//    private Color kiri = Color.GRAY.cpy();
//    private Texture img2;
//    private TextureAtlas.AtlasRegion img3 = ImageMaster.BORDER_GLOW_2;
    public static KiriSameEffect[][] effects = new KiriSameEffect[6][36];
    private KawaGiriBack back;

    public KiriSame(AbstractCreature owner) {
        this.name = NAME;
        this.ID = "KiriSame";
        this.owner = owner;
        this.amount = 5;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/Weather/KiriSame.png");
        this.type = PowerType.BUFF;
//        if (img2 == null) {
//            img2 = ImageMaster.loadImage("images/vfx/horizontal_line.png");
//        }
        for (int i = 0; i < effects.length; i++) {
            for (int i1 = 0; i1 < effects[i].length; i1++) {
                //initializeData(i, i1);
                effects[i][i1].initializeData(i);
                AbstractDungeon.effectsQueue.add(effects[i][i1]);
            }
        }
        //this.kiri.a = 0.0F;

        this.back = new KawaGiriBack();
        this.back.initializeData();
        AbstractDungeon.effectList.add(this.back);
    }

    public float atDamageGive(float damage, DamageInfo.DamageType type)
    {
        if (type == DamageInfo.DamageType.NORMAL) {
            return (damage * 1.25F);
        }
        else
            return damage;
    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        super.onAttack(info, damageAmount, target);
        if (info.type == DamageInfo.DamageType.NORMAL) {
            this.amount -= 1;
        }
    }

    public void atEndOfRound() {
        if (this.amount <= 1)
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "KiriSame"));
        else
            this.amount -= 1;
    }

//    private void initializeData(int i, int i1){
//        this.x[i][i1] =(MathUtils.random(0.0F, 1.0F) * Settings.WIDTH);
//        this.y[i][i1] = (MathUtils.random(900.0F, 1250.0F) * Settings.scale + 350.0F * (i));
//        this.vY[i][i1] = (MathUtils.random(-500.0F, -400.0F) * Settings.scale);
//
//        this.rotation[i][i1] = (MathUtils.random(75.0F,85.0F));
//        this.scale[i][i1] = MathUtils.random(0.25F, 0.5F);
//        this.scale[i][i1] *= Settings.scale;
//        this.scaleY[i][i1] = (MathUtils.random(0.15F, 0.35F) * Settings.scale);
//
//        this.vX[i][i1] = (this.vY[i][i1] * (float) Math.tan(Math.toRadians(90 - this.rotation[i][i1])));
//
//        this.rain[i][i1] = Color.SKY.cpy();
//        this.rain[i][i1].r += MathUtils.random(-0.01F, 0.01F);
//        this.rain[i][i1].g += MathUtils.random(-0.01F, 0.01F);
//        this.rain[i][i1].b += MathUtils.random(-0.01F, 0.01F);
//        this.rain[i][i1].a -= MathUtils.random(0.1F, 0.2F);
//    }
//
//    @Override
//    public void renderIcons(SpriteBatch sb, float x, float y, Color c) {
//        super.renderIcons(sb, x, y, c);
//        for (int i = 0; i < 18; i++) {
//            for (int i1 = 0; i1 < 36; i1++) {
//                if(this.y[i][i1] <= (AbstractDungeon.floorY - MathUtils.random(50.0F, 150.0F))){
//                    initializeData(i, i1);
//                }
//                else {
//                    this.x[i][i1] += this.vX[i][i1] * Gdx.graphics.getDeltaTime();
//                    this.y[i][i1] += this.vY[i][i1] * Gdx.graphics.getDeltaTime();
//
//                    this.rain[i][i1].a -= Gdx.graphics.getDeltaTime() / 4;
//                    if(this.rain[i][i1].a < 0.0F)
//                        this.rain[i][i1].a = 0.0F;
//                }
//
//                sb.setColor(this.rain[i][i1]);
//                sb.draw(this.img2, this.x[i][i1], this.y[i][i1], 128.0F, 128.0F, 256.0F, 256.0F,
//                        this.scale[i][i1], this.scaleY[i][i1], this.rotation[i][i1],
//                        0, 0, 256, 256, false, false);
//            }
//        }
//
//        if (this.kiri.a < Color.GRAY.a){
//            this.kiri.a += Gdx.graphics.getDeltaTime();
//        }
//        else if(this.kiri.a > Color.GRAY.a){
//            this.kiri.a = Color.GRAY.a;
//        }
//        sb.setColor(this.kiri);
//        sb.draw(this.img3, 0F, 0F, Settings.WIDTH, Settings.HEIGHT);
//    }

    @Override
    public void onRemove() {
        super.onRemove();
        for (KiriSameEffect[] effect : effects) {
            for (KiriSameEffect anEffect : effect) {
                anEffect.remove();
            }
        }
        this.back.remove();
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0];
    }
}
