package Thmod.Cards.DeriveCards.EasterEgg;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Cards.DeriveCards.AbstractDeriveCards;

public class RakuenSaibancyou extends AbstractDeriveCards {
    public static final String ID = "RakuenSaibancyou";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;

    public RakuenSaibancyou() {
        super("RakuenSaibancyou", RakuenSaibancyou.NAME,  -2, RakuenSaibancyou.DESCRIPTION, CardType.POWER, CardRarity.SPECIAL, CardTarget.NONE);
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
    }

    public AbstractCard makeCopy() {
        return new RakuenSaibancyou();
    }

    public void upgrade() {
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("RakuenSaibancyou");
        NAME = RakuenSaibancyou.cardStrings.NAME;
        DESCRIPTION = RakuenSaibancyou.cardStrings.DESCRIPTION;
    }
}
