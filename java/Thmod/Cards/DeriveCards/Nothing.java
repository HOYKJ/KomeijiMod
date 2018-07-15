package Thmod.Cards.DeriveCards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Nothing extends AbstractDeriveCards {
    public static final String ID = "Nothing";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;

    public Nothing() {
        super("Nothing", Nothing.NAME,  0, Nothing.DESCRIPTION, CardType.POWER, CardRarity.SPECIAL, CardTarget.NONE);
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
    }

    public AbstractCard makeCopy() {
        return new Nothing();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("Nothing");
        NAME = Nothing.cardStrings.NAME;
        DESCRIPTION = Nothing.cardStrings.DESCRIPTION;
    }
}
