package Thmod.Power.Weather;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
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
import Thmod.vfx.weather.NouMuEffect;

public class NouMu extends AbstractPower {
    public static final String POWER_ID = "NouMu";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("NouMu");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    //private Color kiri = Color.GRAY.cpy();
    private Color[][] noumu = new Color[4][8];
    private float[][] x = new float[4][8];
    private float[][] y = new float[4][8];
    private float[][] aV = new float[4][8];
    private float[][] scale = new float[4][8];
    private float[][] rotation = new float[4][8];
    private float[][] cV = new float[4][8];
    private float[][] targetAlpha = new float[4][8];
    private boolean[][] flipX = new boolean[4][8];
    private boolean[][] flipY = new boolean[4][8];
    private boolean[][] fadeOut = new boolean[4][8];
    private TextureAtlas.AtlasRegion[][] img2 =new  TextureAtlas.AtlasRegion[4][8];
    //private TextureAtlas.AtlasRegion img3 = ImageMaster.BORDER_GLOW_2;
    private KawaGiriBack back;
    public static NouMuEffect[][] effects = new NouMuEffect[4][8];

    public NouMu(AbstractCreature owner) {
        this.name = NAME;
        this.ID = "NouMu";
        this.owner = owner;
        this.amount = 5;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/Weather/NouMu.png");
        this.type = PowerType.BUFF;
        //this.kiri.a = KawaGiri
        for (int i = 0; i < effects.length; i++) {
            for (int i1 = 0; i1 < effects[i].length; i1++) {
                initializeData(i, i1);
            }
        }

        this.back = new KawaGiriBack();
        this.back.initializeData();
        AbstractDungeon.effectList.add(this.back);
    }

    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        if(info.type != DamageInfo.DamageType.HP_LOSS) {
            AbstractDungeon.player.heal((int) (damageAmount * 0.15));
            flash();
        }
    }

    public void atEndOfRound() {
        if (this.amount <= 1)
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "NouMu"));
        else
            this.amount -= 1;
    }

    private void initializeData(int i, int i1){
        switch (MathUtils.random(2))
        {
            case 0:
                this.img2[i][i1] = ImageMaster.SMOKE_1;
                break;
            case 1:
                this.img2[i][i1] = ImageMaster.SMOKE_2;
                break;
            default:
                this.img2[i][i1] = ImageMaster.SMOKE_3;
        }

        switch (i){
            case 0:
                this.x[i][i1] = (MathUtils.random(-100.0F * Settings.scale, Settings.WIDTH + 100.0F * Settings.scale) - this.img2[i][i1].packedWidth / 2.0F);
                this.y[i][i1] = (MathUtils.random(Settings.HEIGHT -100.0F * Settings.scale, Settings.HEIGHT + 100.0F * Settings.scale) - this.img2[i][i1].packedHeight / 2.0F);
                break;
            case 1:
                this.x[i][i1] = (MathUtils.random(-100.0F * Settings.scale, Settings.WIDTH + 100.0F * Settings.scale) - this.img2[i][i1].packedWidth / 2.0F);
                this.y[i][i1] = (MathUtils.random(-100.0F * Settings.scale, 100.0F * Settings.scale) - this.img2[i][i1].packedHeight / 2.0F);
                break;
            case 2:
                this.x[i][i1] = (MathUtils.random(-100.0F * Settings.scale, 100.0F * Settings.scale) - this.img2[i][i1].packedWidth / 2.0F);
                this.y[i][i1] = (MathUtils.random(-100.0F * Settings.scale, Settings.HEIGHT + 100.0F * Settings.scale) - this.img2[i][i1].packedHeight / 2.0F);
                break;
            case 3:
                this.x[i][i1] = (MathUtils.random(Settings.WIDTH  - 100.0F * Settings.scale, Settings.WIDTH  + 100.0F * Settings.scale) - this.img2[i][i1].packedWidth / 2.0F);
                this.y[i][i1] = (MathUtils.random(-100.0F * Settings.scale, Settings.HEIGHT + 100.0F * Settings.scale) - this.img2[i][i1].packedHeight / 2.0F);
                break;
        }

        this.cV[i][i1] = MathUtils.random(4.0F, 8.0F);
        this.aV[i][i1] = (MathUtils.random(-40.0F, -30.0F) * Settings.scale);

        this.rotation[i][i1] = MathUtils.random(0.0F, 360.0F);
        this.scale[i][i1] = (MathUtils.random(8.0F, 15.0F) * Settings.scale);

        this.noumu[i][i1] = Color.GRAY.cpy();
        this.noumu[i][i1].r += MathUtils.random(-0.01F, 0.01F);
        this.noumu[i][i1].g += MathUtils.random(-0.01F, 0.01F);
        this.noumu[i][i1].b += MathUtils.random(-0.01F, 0.01F);
        this.noumu[i][i1].a = 0.0F;
        this.targetAlpha[i][i1] = MathUtils.random(0.8F, 0.9F);

        this.flipX[i][i1] = MathUtils.randomBoolean();
        this.flipY[i][i1] = MathUtils.randomBoolean();
        this.fadeOut[i][i1] = true;
    }

    @Override
    public void renderIcons(SpriteBatch sb, float x, float y, Color c) {
        super.renderIcons(sb, x, y, c);

        for (int i = 0; i < effects.length; i++) {
            for (int i1 = 0; i1 < effects[i].length; i1++) {
                if(this.noumu[i][i1].a <= 0.0F && !this.fadeOut[i][i1]){
                    initializeData(i, i1);
                }
                else if(this.noumu[i][i1].a >= this.targetAlpha[i][i1]){
                    this.fadeOut[i][i1] = false;
                    this.noumu[i][i1].a -= Gdx.graphics.getDeltaTime() / this.cV[i][i1];
                }
                else {
                    if(this.fadeOut[i][i1]){
                        this.noumu[i][i1].a += Gdx.graphics.getDeltaTime() / this.cV[i][i1] * 2;
                    }
                    else {
                        this.noumu[i][i1].a -= Gdx.graphics.getDeltaTime() / this.cV[i][i1];
                    }
                }
                this.rotation[i][i1] += this.aV[i][i1] * Gdx.graphics.getDeltaTime();

                if ((this.flipX[i][i1]) && (!this.img2[i][i1].isFlipX())) {
                    this.img2[i][i1].flip(true, false);
                } else if ((!this.flipX[i][i1]) && (this.img2[i][i1].isFlipX())) {
                    this.img2[i][i1].flip(true, false);
                }
                if ((this.flipY[i][i1]) && (!this.img2[i][i1].isFlipY())) {
                    this.img2[i][i1].flip(false, true);
                } else if ((!this.flipY[i][i1]) && (this.img2[i][i1].isFlipY())) {
                    this.img2[i][i1].flip(false, true);
                }

                sb.setColor(this.noumu[i][i1]);
                sb.draw(this.img2[i][i1], this.x[i][i1], this.y[i][i1],
                        this.img2[i][i1].packedWidth / 2.0F, this.img2[i][i1].packedHeight / 2.0F, this.img2[i][i1].packedWidth, this.img2[i][i1].packedHeight,
                        this.scale[i][i1], this.scale[i][i1], this.rotation[i][i1]);
            }
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
        for (int i = 0; i < effects.length; i++) {
            for (int i1 = 0; i1 < effects[i].length; i1++) {
                effects[i][i1].getInfo(this.noumu[i][i1], this.x[i][i1], this.y[i][i1], this.aV[i][i1], this.scale[i][i1], this.rotation[i][i1], this.cV[i][i1],
                        this.flipX[i][i1], this.flipY[i][i1], this.img2[i][i1], i);
                AbstractDungeon.effectList.add(effects[i][i1]);
            }
        }
        this.back.remove();
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0];
    }
}
