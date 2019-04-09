package Thmod.Cards.DeriveCards.EasterEgg;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Cards.DeriveCards.AbstractDeriveCards;

public class RealHeartBreak extends AbstractDeriveCards {
    public static final String ID = "RealHeartBreak";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;

    public RealHeartBreak() {
        super("RealHeartBreak", RealHeartBreak.NAME,  -2, RealHeartBreak.DESCRIPTION, CardType.POWER, CardRarity.SPECIAL, CardTarget.NONE);
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
    }

    public AbstractCard makeCopy() {
        return new RealHeartBreak();
    }

    public void upgrade() {
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("RealHeartBreak");
        NAME = RealHeartBreak.cardStrings.NAME;
        DESCRIPTION = RealHeartBreak.cardStrings.DESCRIPTION;
    }
}
