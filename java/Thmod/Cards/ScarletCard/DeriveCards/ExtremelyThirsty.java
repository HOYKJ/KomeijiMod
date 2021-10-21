package Thmod.Cards.ScarletCard.DeriveCards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Cards.DeriveCards.AbstractDeriveCards;

public class ExtremelyThirsty extends AbstractRemiriaDeriveCards {
    public static final String ID = "ExtremelyThirsty";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = -2;

    public ExtremelyThirsty() {
        super("ExtremelyThirsty", ExtremelyThirsty.NAME,  -2, ExtremelyThirsty.DESCRIPTION, CardType.SKILL, CardRarity.SPECIAL, CardTarget.NONE, false);
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
    }

    @Override
    public void addTips() {
    }

    public AbstractCard makeCopy() {
        return new ExtremelyThirsty();
    }

    public void upgrade() {
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("ExtremelyThirsty");
        NAME = ExtremelyThirsty.cardStrings.NAME;
        DESCRIPTION = ExtremelyThirsty.cardStrings.DESCRIPTION;
    }
}
