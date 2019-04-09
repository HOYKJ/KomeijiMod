package Thmod.Cards.DeriveCards.EasterEgg;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Cards.DeriveCards.AbstractDeriveCards;

public class CardSetForOne extends AbstractDeriveCards {
    public static final String ID = "CardSetForOne";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;

    public CardSetForOne() {
        super("CardSetForOne", CardSetForOne.NAME,  -2, CardSetForOne.DESCRIPTION, CardType.POWER, CardRarity.SPECIAL, CardTarget.NONE);
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
    }

    public AbstractCard makeCopy() {
        return new CardSetForOne();
    }

    public void upgrade() {
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("CardSetForOne");
        NAME = CardSetForOne.cardStrings.NAME;
        DESCRIPTION = CardSetForOne.cardStrings.DESCRIPTION;
    }
}
