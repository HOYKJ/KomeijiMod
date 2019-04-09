package Thmod.Cards.DeriveCards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class PengLaiDoll extends AbstractDeriveCards {
    public static final String ID = "PengLaiDoll";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;

    public PengLaiDoll() {
        super("PengLaiDoll", PengLaiDoll.NAME,  -2, PengLaiDoll.DESCRIPTION, CardType.SKILL, CardRarity.SPECIAL, CardTarget.NONE);
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
    }

    public AbstractCard makeCopy() {
        return new PengLaiDoll();
    }

    public void upgrade() {
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("PengLaiDoll");
        NAME = PengLaiDoll.cardStrings.NAME;
        DESCRIPTION = PengLaiDoll.cardStrings.DESCRIPTION;
    }
}
