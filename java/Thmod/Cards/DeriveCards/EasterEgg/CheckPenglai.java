package Thmod.Cards.DeriveCards.EasterEgg;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Cards.DeriveCards.AbstractDeriveCards;

public class CheckPenglai extends AbstractDeriveCards {
    public static final String ID = "CheckPenglai";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = -2;

    public CheckPenglai() {
        super("CheckPenglai", CheckPenglai.NAME,  -2, CheckPenglai.DESCRIPTION, CardType.SKILL, CardRarity.SPECIAL, CardTarget.NONE);
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
    }

    public AbstractCard makeCopy() {
        return new CheckPenglai();
    }

    public void upgrade() {
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("CheckPenglai");
        NAME = CheckPenglai.cardStrings.NAME;
        DESCRIPTION = CheckPenglai.cardStrings.DESCRIPTION;
    }
}
