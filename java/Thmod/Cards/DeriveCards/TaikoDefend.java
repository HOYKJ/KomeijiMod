package Thmod.Cards.DeriveCards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class TaikoDefend extends AbstractDeriveCards {
    public static final String ID = "TaikoDefend";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 0;
    private static final int BLOCK_AMT = 5;

    public TaikoDefend() {
        super("TaikoDefend", TaikoDefend.NAME,  0, TaikoDefend.DESCRIPTION, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF);
        this.baseBlock = 5;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToTop(new GainBlockAction(p, p, this.block));
    }

    public AbstractCard makeCopy() {
        return new TaikoDefend();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.upgradeBlock(3);
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("TaikoDefend");
        NAME = TaikoDefend.cardStrings.NAME;
        DESCRIPTION = TaikoDefend.cardStrings.DESCRIPTION;
    }
}
