package Thmod.Power.Weather;

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
import basemod.DevConsole;

public class KyoKkou extends AbstractPower {
    public static final String POWER_ID = "KyoKkou";
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

    public KyoKkou(AbstractCreature owner) {
        this.name = NAME;
        this.ID = "KyoKkou";
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

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if(this.roll == 0) {
            if (card.cost > 1)
                this.counter += 1;
            if (this.counter == 4) {
                AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, 1));
                flash();
                this.counter = 0;
            }
        }
        if(this.roll == 2) {
            if((card.type == AbstractCard.CardType.ATTACK)&&(this.counter != 5))
                this.counter += 1;
            if(this.counter == 5){
                if(p.hasPower("PointPower")) {
                    if (p.getPower("PointPower").amount < 5) {
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new PointPower(p, 1), 1));
                        flash();
                        this.counter = 0;
                    }
                }
                else{
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new PointPower(p, 1), 1));
                    flash();
                    this.counter = 0;
                }
            }
            if(card.cost >= 2) {
                card.cost -= 1;
                AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "DonTen"));
            }
        }
        if(this.roll == 3) {
            if (card.cost > 1)
                this.counter += 1;
            if (this.counter == 3) {
                AbstractDungeon.actionManager.addToTop(new GainEnergyAction(1));
                flash();
                this.amount -= 1;
                this.counter = 0;
            }
        }
        if(this.roll == 9) {
            if (!(card.upgraded)) {
                card.upgrade();
                flash();
                this.amount -= 1;
            }
        }
        if(this.roll == 11) {
            AbstractDungeon.actionManager.addToBottom(new CalculatedGambleAction(false));
            flash();
        }
    }

    public void atStartOfTurnPostDraw() {
        if(this.roll == 4) {
            this.counter += 1;
            if (this.counter == 1) {
                AbstractDungeon.actionManager.addToTop(new GainEnergyAction(1));
                flash();
                this.counter = 0;
            }
        }
        if(this.roll == 10) {
            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, 1));
            flash();
        }
        if(this.roll == 12) {
            int roll1 = MathUtils.random(5);
            if (roll >= 2) {
                this.StrgengthCounter = roll1 - 2;
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, this.StrgengthCounter), this.StrgengthCounter));
                flash();
            } else {
                this.DexterityCounter = roll1;
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DexterityPower(p, this.DexterityCounter), this.DexterityCounter));
                flash();
            }
        }
    }

    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if(this.roll == 5) {
            if ((power.ID.equals("DashPower")) && (target == p)) {
                AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(p,p,"DashPower",1));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new IntangiblePlayerPower(p, 1), 1));
                flash();
            }
        }
    }

    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        if(this.roll == 6) {
            if(info.type != DamageInfo.DamageType.HP_LOSS) {
                AbstractDungeon.player.heal((int) (damageAmount * 0.15));
                flash();
            }
        }
    }

    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        if (this.roll == 1) {
            if (type == DamageInfo.DamageType.NORMAL) {
                this.amount -= 1;
                return (damage * 1.25F);
            }
            else
                return damage;
        }
        else if (this.roll == 13) {
            return ((int) (damage * 1.5F));
        }
        else if (this.roll == 16) {
            if ((type == DamageInfo.DamageType.NORMAL) && (this.damaged)) {
                this.damaged = false;
                return (damage * 1.33F);
            }
            else
                return damage;
        }
        else
            return damage;
    }

    public float atDamageReceive(float damage, DamageInfo.DamageType damageType){
        if (this.roll == 13) {
            return ((int) (damage * 1.5F));
        }
        else
            return damage;
    }

    public int onLoseHp(int damageAmount) {
        if (this.roll == 7) {
            if (p.hasPower("PointPower")) {
                if (p.getPower("PointPower").amount >= 1) {
                    AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(p,p,"PointPower",1));
                    flash();
                    return 1;
                } else
                    return damageAmount;
            } else
                return damageAmount;
        }
        else if (this.roll == 18) {
            AbstractDungeon.player.addBlock(3);
            return damageAmount;
        }
        else
            return damageAmount;
    }

    public float modifyBlock(float blockAmount) {
        if (this.roll == 8) {
            int roll = MathUtils.random(5);
            flash();
            return (blockAmount + roll - 2);
        }
        return blockAmount;
    }

    public void atEndOfTurn(boolean isPlayer) {
        if(isPlayer){
            if (this.roll == 14) {
                AbstractDungeon.player.heal(1);
                flash();
            }
            if (this.roll == 17) {
                for (int i = (AbstractDungeon.getCurrRoom().monsters.monsters.size() - 1); i >= 0; i--) {
                    AbstractMonster target = AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
                    if ((!(target.isDying)) && (target.currentHealth > 0) && (!(target.isEscaping))) {
                        flash();
                        AbstractDungeon.actionManager.addToTop(new DamageAction(target, new DamageInfo(AbstractDungeon.player, this.counter1, DamageInfo.DamageType.HP_LOSS), AbstractGameAction.AttackEffect.FIRE, true));
                    }
                }
                this.counter1 += 1;
            }
        }
    }

    public void atEndOfRound() {
        if (this.roll == 12) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, -this.StrgengthCounter), -this.StrgengthCounter));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DexterityPower(p, -this.DexterityCounter), -this.DexterityCounter));
            flash();
        }
        if (this.roll == 15) {
            for (int i = (AbstractDungeon.getCurrRoom().monsters.monsters.size() - 1); i >= 0; i--) {
                AbstractMonster target = AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
                if ((!(target.isDying)) && (target.currentHealth > 0) && (!(target.isEscaping))) {
                    if (AbstractMonster.Intent.valueOf(target.intent.name()) == AbstractMonster.Intent.SLEEP) {
                        target.die();
                        flash();
                    }
                }
            }
        }
        if (this.amount <= 1)
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "KyoKkou"));
        else
            this.amount -= 1;
        if (this.roll == 16) {
            this.damaged = true;
        }
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
