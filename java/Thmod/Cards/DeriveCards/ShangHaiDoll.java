package Thmod.Cards.DeriveCards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;


public class ShangHaiDoll extends AbstractDeriveCards {
    public static final String ID = "ShangHaiDoll";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;

    public ShangHaiDoll() {
        super("ShangHaiDoll", ShangHaiDoll.NAME,  -2, ShangHaiDoll.DESCRIPTION, CardType.SKILL, CardRarity.SPECIAL, CardTarget.NONE);
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
    }

    public AbstractCard makeCopy() {
        return new ShangHaiDoll();
    }

    public void upgrade() {
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("ShangHaiDoll");
        NAME = ShangHaiDoll.cardStrings.NAME;
        DESCRIPTION = ShangHaiDoll.cardStrings.DESCRIPTION;
    }
}
