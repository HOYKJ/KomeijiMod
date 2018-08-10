package Thmod.Cards.DeriveCards.EasterEgg;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Cards.DeriveCards.AbstractDeriveCards;

public class ScarletsBlessing extends AbstractDeriveCards {
    public static final String ID = "ScarletsBlessing";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;

    public ScarletsBlessing() {
        super("ScarletsBlessing", ScarletsBlessing.NAME,  -2, ScarletsBlessing.DESCRIPTION, CardType.POWER, CardRarity.SPECIAL, CardTarget.NONE);
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
    }

    public AbstractCard makeCopy() {
        return new THsWorld();
    }

    public void upgrade() {
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("ScarletsBlessing");
        NAME = ScarletsBlessing.cardStrings.NAME;
        DESCRIPTION = ScarletsBlessing.cardStrings.DESCRIPTION;
    }
}
