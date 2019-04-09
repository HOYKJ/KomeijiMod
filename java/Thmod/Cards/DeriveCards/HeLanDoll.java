package Thmod.Cards.DeriveCards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class HeLanDoll extends AbstractDeriveCards {
    public static final String ID = "HeLanDoll";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;

    public HeLanDoll() {
        super("HeLanDoll", HeLanDoll.NAME,  -2, HeLanDoll.DESCRIPTION, CardType.SKILL, CardRarity.SPECIAL, CardTarget.NONE);
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
    }

    public AbstractCard makeCopy() {
        return new HeLanDoll();
    }

    public void upgrade() {
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("HeLanDoll");
        NAME = HeLanDoll.cardStrings.NAME;
        DESCRIPTION = HeLanDoll.cardStrings.DESCRIPTION;
    }
}
