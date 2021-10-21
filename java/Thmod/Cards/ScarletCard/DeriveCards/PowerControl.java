package Thmod.Cards.ScarletCard.DeriveCards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Cards.DeriveCards.AbstractDeriveCards;

public class PowerControl extends AbstractRemiriaDeriveCards {
    public static final String ID = "PowerControl";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = -2;

    public PowerControl() {
        super("PowerControl", PowerControl.NAME,  -2, PowerControl.DESCRIPTION, CardType.SKILL, CardRarity.SPECIAL, CardTarget.NONE, false);
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
    }

    @Override
    public void addTips() {
    }

    public AbstractCard makeCopy() {
        return new PowerControl();
    }

    public void upgrade() {
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("PowerControl");
        NAME = PowerControl.cardStrings.NAME;
        DESCRIPTION = PowerControl.cardStrings.DESCRIPTION;
    }
}
