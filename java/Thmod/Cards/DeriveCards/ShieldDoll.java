package Thmod.Cards.DeriveCards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ShieldDoll extends AbstractDeriveCards {
    public static final String ID = "ShieldDoll";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;

    public ShieldDoll() {
        super("ShieldDoll", ShieldDoll.NAME,  -2, ShieldDoll.DESCRIPTION, CardType.SKILL, CardRarity.SPECIAL, CardTarget.NONE);
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
    }

    public AbstractCard makeCopy() {
        return new ShieldDoll();
    }

    public void upgrade() {
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("ShieldDoll");
        NAME = ShieldDoll.cardStrings.NAME;
        DESCRIPTION = ShieldDoll.cardStrings.DESCRIPTION;
    }
}
