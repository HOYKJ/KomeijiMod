package Thmod.Cards.UncommonCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

import Thmod.Cards.AbstractKomeijiCards;
import Thmod.Power.StrengthEndPower;

public class MissingPower extends AbstractKomeijiCards {
    public static final String ID = "MissingPower";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 1;
    private static final int STR_AMOUNT = 6;

    public MissingPower() {
        super("MissingPower", MissingPower.NAME,  1, MissingPower.DESCRIPTION, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = 6;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, this.magicNumber), this.magicNumber));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthEndPower(p, this.magicNumber), this.magicNumber));
    }

    public AbstractCard makeCopy() {
        return new MissingPower();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            upgradeName();
            upgradeMagicNumber(2);
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("MissingPower");
        NAME = MissingPower.cardStrings.NAME;
        DESCRIPTION = MissingPower.cardStrings.DESCRIPTION;
    }
}
