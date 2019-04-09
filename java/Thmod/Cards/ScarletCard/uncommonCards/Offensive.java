package Thmod.Cards.ScarletCard.uncommonCards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import Thmod.Actions.Remiria.RemoveDebuffsActionChange;
import Thmod.Cards.ScarletCard.AbstractRemiriaCards;
import Thmod.Characters.RemiriaScarlet;
import Thmod.Power.remiria.BloodBruisePower;
import Thmod.Power.remiria.ScarletLordPower;
import basemod.helpers.TooltipInfo;

public class Offensive extends AbstractRemiriaCards {
    public static final String ID = "Offensive";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;

    public Offensive() {
        this(false);
    }

    public Offensive(boolean isPlus) {
        super("Offensive", Offensive.NAME,  1, Offensive.DESCRIPTION, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY, isPlus);
        this.baseDamage = 6;
        this.addTips();
        this.attackType = AttackType.LIGHT;
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        AbstractPlayer p = AbstractDungeon.player;
        for(AbstractPower power : p.powers){
            if(!(power instanceof StrengthPower) && (power.type == AbstractPower.PowerType.BUFF)){
                if(power.amount > 0){
                    this.damage += power.amount;
                }
                else {
                    this.damage += 1;
                }
            }
        }
        this.isDamageModified = true;
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
        AbstractPlayer p = AbstractDungeon.player;
        for(AbstractPower power : p.powers){
            if(!(power instanceof StrengthPower) && (power.type == AbstractPower.PowerType.BUFF)){
                if(power.amount > 0){
                    this.damage += power.amount;
                }
                else {
                    this.damage += 1;
                }
            }
        }
        if(this.isPlus){
            for(AbstractPower power : mo.powers){
                if(power.type == AbstractPower.PowerType.DEBUFF){
                    if(power.amount > 0){
                        this.damage += power.amount;
                    }
                    else {
                        this.damage += 1;
                    }
                }
            }
        }
        this.isDamageModified = true;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToTop(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        super.use(p, m);
    }

    public AbstractCard makeCopy() {
        if(AbstractDungeon.player != null){
            if((AbstractDungeon.player instanceof RemiriaScarlet) && (AbstractDungeon.player.hasPower(ScarletLordPower.POWER_ID))){
                return new Offensive(true);
            }
        }
        return new Offensive();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.upgradeDamage(4);
        }
    }

    @Override
    public void addTips() {
        this.tips.clear();
        this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("Offensive");
        NAME = Offensive.cardStrings.NAME;
        DESCRIPTION = Offensive.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = Offensive.cardStrings.EXTENDED_DESCRIPTION;
    }
}
