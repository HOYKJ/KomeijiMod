package Thmod.Cards.DeriveCards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;


public class SpearDoll extends AbstractDeriveCards {
    public static final String ID = "SpearDoll";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;

    public SpearDoll() {
        super("SpearDoll", SpearDoll.NAME,  -2, SpearDoll.DESCRIPTION, CardType.SKILL, CardRarity.SPECIAL, CardTarget.NONE);
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
    }

    public AbstractCard makeCopy() {
        return new SpearDoll();
    }

    public void upgrade() {
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("SpearDoll");
        NAME = SpearDoll.cardStrings.NAME;
        DESCRIPTION = SpearDoll.cardStrings.DESCRIPTION;
    }
}
