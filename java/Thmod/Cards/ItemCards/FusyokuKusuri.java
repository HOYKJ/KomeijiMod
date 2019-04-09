package Thmod.Cards.ItemCards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Actions.unique.DeleteSpellAction;

public class FusyokuKusuri extends AbstractItemCards {
    public static final String ID = "FusyokuKusuri";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;

    public FusyokuKusuri() {
        super("FusyokuKusuri", FusyokuKusuri.NAME,  0, FusyokuKusuri.DESCRIPTION, CardType.SKILL, CardRarity.SPECIAL, CardTarget.NONE);
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new DeleteSpellAction(EXTENDED_DESCRIPTION[0]));
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m){
        return true;
    }

    public AbstractCard makeCopy() {
        return new FusyokuKusuri();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("FusyokuKusuri");
        NAME = FusyokuKusuri.cardStrings.NAME;
        DESCRIPTION = FusyokuKusuri.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = FusyokuKusuri.cardStrings.EXTENDED_DESCRIPTION;
    }
}
