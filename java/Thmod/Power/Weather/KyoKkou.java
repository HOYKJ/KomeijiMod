package Thmod.Power.Weather;

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
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import Thmod.Power.PointPower;

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
            if ((power.ID == "DashPower") && (target == p)) {
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
        else if (this.roll == 7) {
            AbstractDungeon.player.addBlock(3);
            return damageAmount;
        }
        else
            return damageAmount;
    }

    public int onPlayerGainedBlock(int blockAmount) {
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

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0];
    }
}
