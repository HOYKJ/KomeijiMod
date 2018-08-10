package Thmod.Cards.DeriveCards.EasterEgg;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Cards.DeriveCards.AbstractDeriveCards;

public class SpontaneousDetonation extends AbstractDeriveCards {
    public static final String ID = "SpontaneousDetonation";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;

    public SpontaneousDetonation() {
        super("SpontaneousDetonation", SpontaneousDetonation.NAME,  -2, SpontaneousDetonation.DESCRIPTION, CardType.POWER, CardRarity.SPECIAL, CardTarget.NONE);
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
    }

    public AbstractCard makeCopy() {
        return new SpontaneousDetonation();
    }

    public void upgrade() {
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("SpontaneousDetonation");
        NAME = SpontaneousDetonation.cardStrings.NAME;
        DESCRIPTION = SpontaneousDetonation.cardStrings.DESCRIPTION;
    }
}
