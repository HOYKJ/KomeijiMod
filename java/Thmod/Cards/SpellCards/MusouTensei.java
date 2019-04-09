package Thmod.Cards.SpellCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

import Thmod.Power.MusouTenseiPower;

public class MusouTensei extends AbstractSpellCards {
    public static final String ID = "MusouTensei";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private int pointcost;

    public MusouTensei() {
        super("MusouTensei", MusouTensei.NAME,  3, MusouTensei.DESCRIPTION, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ALL_ENEMY);
        this.baseDamage = 153;
        this.baseMagicNumber = 7;
        this.magicNumber = this.baseMagicNumber;
        this.isMultiDamage = true;
        this.pointcost = 5;
    }

    public void calculateCardDamage(AbstractMonster mo){
        super.calculateCardDamage(mo);
        AbstractPlayer p = AbstractDungeon.player;
        if (p.hasPower(StrengthPower.POWER_ID)) {
            if (p.getPower(StrengthPower.POWER_ID).amount > 0) {
                this.damage += (p.getPower("Strength").amount * (this.magicNumber - 1));
                this.isDamageModified = true;
            }
        }
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        AbstractPlayer p = AbstractDungeon.player;
        if (p.hasPower(StrengthPower.POWER_ID)) {
            if (p.getPower(StrengthPower.POWER_ID).amount > 0) {
                this.damage += (p.getPower("Strength").amount * (this.magicNumber - 1));
                this.isDamageModified = true;
            }
        }
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        if (p.hasPower("PointPower")) {
            if (p.getPower("PointPower").amount >= this.pointcost) {
                AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p, p, new MusouTenseiPower(p,this.damage)));
                AbstractDungeon.actionManager.addToTop(new ReducePowerAction(p, p, "PointPower",this.pointcost));
            }
        }
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m){
        super.canUse(p,m);
        if (p.hasPower("PointPower")) {
            if (p.getPower("PointPower").amount >= this.pointcost) {
                if(!p.hasPower(MusouTenseiPower.POWER_ID)) {
                    return true;
                }
            }
        }
        if(!p.hasPower(MusouTenseiPower.POWER_ID)) {
            this.cantUseMessage = AbstractSpellCards.EXTENDED_DESCRIPTION[4];
        }
        else{
            this.cantUseMessage = null;
        }
        return false;
    }

    public AbstractCard makeCopy() {
        return new MusouTensei();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("MusouTensei");
        NAME = MusouTensei.cardStrings.NAME;
        DESCRIPTION = MusouTensei.cardStrings.DESCRIPTION;
    }
}
