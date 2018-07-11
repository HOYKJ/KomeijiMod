package Thmod.Cards.UncommonCards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.Iterator;

import Thmod.Actions.unique.FromDeckToHandAction;
import Thmod.Cards.AbstractKomeijiCards;

public class KimairaNoYoku extends AbstractKomeijiCards {
    public static final String ID = "KimairaNoYoku";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;
    private static final int COST = 0;

    public KimairaNoYoku() {
        super("KimairaNoYoku", KimairaNoYoku.NAME,  0, KimairaNoYoku.DESCRIPTION, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(new FromDeckToHandAction(1));
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m)
    {
        boolean canUse = super.canUse(p, m);
        if (!(canUse)) {
            return false;
        }

        boolean hasCard = false;
        for (Iterator localIterator = p.drawPile.group.iterator(); localIterator.hasNext(); ) { AbstractCard c = (AbstractCard)localIterator.next();
            hasCard = true;
        }

        if (!(hasCard)) {
            this.cantUseMessage = EXTENDED_DESCRIPTION[0];
            canUse = false;
        }

        return canUse;
    }

    public AbstractCard makeCopy() {
        return new KimairaNoYoku();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.isInnate = true;
            this.rawDescription = UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("KimairaNoYoku");
        NAME = KimairaNoYoku.cardStrings.NAME;
        DESCRIPTION = KimairaNoYoku.cardStrings.DESCRIPTION;
        UPGRADE_DESCRIPTION = KimairaNoYoku.cardStrings.UPGRADE_DESCRIPTION;
        EXTENDED_DESCRIPTION = KimairaNoYoku.cardStrings.EXTENDED_DESCRIPTION;
    }
}
