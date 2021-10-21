package Thmod.Power.Weather;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import Thmod.ThMod;
import Thmod.vfx.weather.FuuuEffect;

public class Fuuu extends AbstractPower {
    public static final String POWER_ID = "Fuuu";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Fuuu");
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
//    private Texture img2;
    public static FuuuEffect[][] effects = new FuuuEffect[6][36];

    public Fuuu(AbstractCreature owner) {
        this.name = NAME;
        this.ID = "Fuuu";
        this.owner = owner;
        this.amount = 2;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/Weather/Fuuu.png");
        this.type = PowerType.BUFF;
//        if (img2 == null) {
//            img2 = ImageMaster.loadImage("images/vfx/horizontal_line.png");
//        }
        for (int i = 0; i < effects.length; i++) {
            for (int i1 = 0; i1 < effects[i].length; i1++) {
                //initializeData(i, i1);
//                this.effects[i][i1] = new FuuuEffect(i);
                effects[i][i1].initializeData(i);
                AbstractDungeon.effectsQueue.add(effects[i][i1]);
            }
        }
    }

    public void atStartOfTurnPostDraw() {
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, 1));
        flash();
    }

//    @Override
//    public void update(int slot) {
//        super.update(slot);
//        if (this.tmp < 18) {
//            if (this.tmp2 < 12) {
//                //initializeData(i, i1);
//                this.effects[this.tmp][this.tmp2] = new FuuuEffect();
//                this.effects[this.tmp][this.tmp2].getInfo(x[this.tmp][this.tmp2], y[this.tmp][this.tmp2], vX[this.tmp][this.tmp2], vY[this.tmp][this.tmp2],
//                        scale[this.tmp][this.tmp2], scaleY[this.tmp][this.tmp2], rotation[this.tmp][this.tmp2], rain[this.tmp][this.tmp2]);
//                AbstractDungeon.effectsQueue.add(this.effects[this.tmp][this.tmp2]);
//                this.effects[this.tmp][this.tmp2 + 1] = new FuuuEffect();
//                this.effects[this.tmp][this.tmp2 + 1].getInfo(x[this.tmp][this.tmp2 + 1], y[this.tmp][this.tmp2 + 1], vX[this.tmp][this.tmp2 + 1], vY[this.tmp][this.tmp2 + 1],
//                        scale[this.tmp][this.tmp2 + 1], scaleY[this.tmp][this.tmp2 + 1], rotation[this.tmp][this.tmp2 + 1], rain[this.tmp][this.tmp2 + 1]);
//                AbstractDungeon.effectsQueue.add(this.effects[this.tmp][this.tmp2]);
//                this.effects[this.tmp][this.tmp2 + 2] = new FuuuEffect();
//                this.effects[this.tmp][this.tmp2].getInfo(x[this.tmp][this.tmp2], y[this.tmp][this.tmp2], vX[this.tmp][this.tmp2], vY[this.tmp][this.tmp2],
//                        scale[this.tmp][this.tmp2], scaleY[this.tmp][this.tmp2], rotation[this.tmp][this.tmp2], rain[this.tmp][this.tmp2]);
//                AbstractDungeon.effectsQueue.add(this.effects[this.tmp][this.tmp2]);
//                this.tmp2 += 3;
//            }
//            if(this.tmp2 == 36) {
//                this.tmp2 = 0;
//                this.tmp++;
//            }
//        }
//    }

    public void atEndOfRound() {
        if (this.amount <= 1)
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, this));
        else
            this.amount -= 1;
    }

//    private void initializeData(int i, int i1){
//        this.x[i][i1] =(MathUtils.random(0.0F, 1.0F) * Settings.WIDTH);
//        this.y[i][i1] = (MathUtils.random(900.0F, 1250.0F) * Settings.scale + 350.0F * (i));
//        this.vY[i][i1] = (MathUtils.random(-500.0F, -400.0F) * Settings.scale);
//
//        this.rotation[i][i1] = (MathUtils.random(65.0F,75.0F));
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
//        for (int i = this.tmp; i < 18; i++) {
//            for (int i1 = this.tmp2; i1 < 36; i1++) {
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
//    }

    @Override
    public void onRemove() {
        super.onRemove();
        //ThMod.logger.info("num is" + this.tmp + ", " + this.tmp2);
        for (FuuuEffect[] effect : effects) {
            for (FuuuEffect anEffect : effect) {
                anEffect.remove();
            }
        }
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0];
    }
}
