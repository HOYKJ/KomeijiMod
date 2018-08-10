package Thmod.Cards.Curses;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Cards.DeriveCards.DemonsFork;
import basemod.abstracts.CustomCard;

public class Fetter extends CustomCard
{
    public static final String ID = "Fetter";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = -2;

    public Fetter()
    {
        super("Fetter", Fetter.NAME,"images/cards/curse/Fetter.png", -2, Fetter.DESCRIPTION,CardType.CURSE, CardColor.CURSE, CardRarity.CURSE, CardTarget.NONE);
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
    }

    public void triggerOnEndOfTurnForPlayingCard()
    {
    }

    public AbstractCard makeCopy()
    {
        return new Fetter();
    }

    public void upgrade() {}

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("Fetter");
        NAME = Fetter.cardStrings.NAME;
        DESCRIPTION = Fetter.cardStrings.DESCRIPTION;
    }
}
