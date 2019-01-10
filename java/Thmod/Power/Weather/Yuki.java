package Thmod.Power.Weather;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
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

public class Yuki extends AbstractPower {
    public static final String POWER_ID = "Yuki";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Yuki");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private AbstractPlayer p = AbstractDungeon.player;
    private float[][] x = new float[18][36];
    private float[][] y = new float[18][36];
    private float[][] vX = new float[18][36];
    private float[][] vY = new float[18][36];
    private float[][] scale = new float[18][36];
    private float[][] rotation = new float[18][36];
    private Color[][] rain = new Color[18][36];
    private TextureAtlas.AtlasRegion img2;

    public Yuki(AbstractCreature owner) {
        this.name = NAME;
        this.ID = "Yuki";
        this.owner = owner;
        this.amount = 5;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/Weather/Yuki.png");
        this.type = PowerType.BUFF;
        this.img2 = ImageMaster.vfxAtlas.findRegion("env/dustCloud");
        for (int i = 0; i < 18; i++) {
            for (int i1 = 0; i1 < 36; i1++) {
                initializeData(i, i1);
            }
        }
    }

    public int onLoseHp(int damageAmount) {
        if (p.hasPower("PointPower")) {
            if (p.getPower("PointPower").amount >= 1) {
                AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(p,p,"PointPower",1));
                flash();
                return 1;
            }
            else
                return damageAmount;
        }
        else
            return damageAmount;
    }

    public void atEndOfRound() {
        if (this.amount <= 1)
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "Yuki"));
        else
            this.amount -= 1;
    }

    private void initializeData(int i, int i1){
        this.x[i][i1] =(MathUtils.random(0.0F, 1.0F) * Settings.WIDTH);
        this.y[i][i1] = (MathUtils.random(900.0F, 1250.0F) * Settings.scale + 350.0F * (i));
        this.vY[i][i1] = (MathUtils.random(-300.0F, -200.0F) * Settings.scale);

        this.rotation[i][i1] = (MathUtils.random(0.0F,360.0F));
        this.scale[i][i1] = MathUtils.random(0.25F, 0.5F);
        this.scale[i][i1] *= Settings.scale;

        this.vX[i][i1] = (MathUtils.random(-100.0F, 20.0F) * Settings.scale);

        this.rain[i][i1] = Color.WHITE.cpy();
        this.rain[i][i1].a -= MathUtils.random(0.1F, 0.2F);
    }

    @Override
    public void renderIcons(SpriteBatch sb, float x, float y, Color c) {
        super.renderIcons(sb, x, y, c);
        for (int i = 0; i < 18; i++) {
            for (int i1 = 0; i1 < 36; i1++) {
                if(this.y[i][i1] <= (AbstractDungeon.floorY - MathUtils.random(50.0F, 150.0F))){
                    initializeData(i, i1);
                }
                else {
                    this.x[i][i1] += this.vX[i][i1] * Gdx.graphics.getDeltaTime();
                    this.y[i][i1] += this.vY[i][i1] * Gdx.graphics.getDeltaTime();

                    this.rain[i][i1].a -= Gdx.graphics.getDeltaTime() / 8;
                    if(this.rain[i][i1].a < 0.0F)
                        this.rain[i][i1].a = 0.0F;
                    this.rotation[i][i1] += (MathUtils.random(0.0F,360.0F) * Gdx.graphics.getDeltaTime());
                }

                sb.setColor(this.rain[i][i1]);
                sb.draw(this.img2, this.x[i][i1], this.y[i][i1], this.img2.packedWidth / 2.0F, this.img2.packedHeight / 2.0F, this.img2.packedWidth, this.img2.packedHeight, this.scale[i][i1], this.scale[i][i1], this.rotation[i][i1]);
            }
        }
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0];
    }
}
