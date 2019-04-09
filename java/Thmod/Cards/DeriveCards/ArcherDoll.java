package Thmod.Cards.DeriveCards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ArcherDoll extends AbstractDeriveCards {
    public static final String ID = "ArcherDoll";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;

    public ArcherDoll() {
        super("ArcherDoll", ArcherDoll.NAME,  -2, ArcherDoll.DESCRIPTION, CardType.SKILL, CardRarity.SPECIAL, CardTarget.NONE);
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
    }

    public AbstractCard makeCopy() {
        return new ArcherDoll();
    }

    public void upgrade() {
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("ArcherDoll");
        NAME = ArcherDoll.cardStrings.NAME;
        DESCRIPTION = ArcherDoll.cardStrings.DESCRIPTION;
    }
}
