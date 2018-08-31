package Thmod.Cards.DeriveCards.EasterEgg;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Cards.DeriveCards.AbstractDeriveCards;

public class Innocent extends AbstractDeriveCards {
    public static final String ID = "Innocent";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;

    public Innocent() {
        super("Innocent", Innocent.NAME,  -2, Innocent.DESCRIPTION, CardType.POWER, CardRarity.SPECIAL, CardTarget.NONE);
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
    }

    public AbstractCard makeCopy() {
        return new Innocent();
    }

    public void upgrade() {
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("Innocent");
        NAME = Innocent.cardStrings.NAME;
        DESCRIPTION = Innocent.cardStrings.DESCRIPTION;
    }
}
