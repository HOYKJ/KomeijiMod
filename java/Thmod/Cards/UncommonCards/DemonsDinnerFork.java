package Thmod.Cards.UncommonCards;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.List;

import Thmod.Cards.AbstractKomeijiCards;
import Thmod.Cards.DeriveCards.AbstractDeriveCards;
import Thmod.Cards.DeriveCards.DemonsFork;
import basemod.helpers.TooltipInfo;

public class DemonsDinnerFork extends AbstractKomeijiCards {
    public static final String ID = "DemonsDinnerFork";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;
    private static final int COST = 1;

    public DemonsDinnerFork() {
        super("DemonsDinnerFork", DemonsDinnerFork.NAME,  1, DemonsDinnerFork.DESCRIPTION, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY, CardSet_k.REMIRIA);
        this.baseMagicNumber = 6;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
//        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p,p,new DemonsForkAccumulate(p,this.upgraded)));
        AbstractDeriveCards c = new DemonsFork(1);
        if(this.upgraded)
            c.upgrade();
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c, 3));
    }

    @Override
    public List<TooltipInfo> getCustomTooltips()
    {
        List<TooltipInfo> tips = new ArrayList<>();
        tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
        return tips;
    }

    public AbstractCard makeCopy() {
        return new DemonsDinnerFork();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.upgradeMagicNumber(2);
            this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("DemonsDinnerFork");
        NAME = DemonsDinnerFork.cardStrings.NAME;
        DESCRIPTION = DemonsDinnerFork.cardStrings.DESCRIPTION;
        UPGRADE_DESCRIPTION = DemonsDinnerFork.cardStrings.UPGRADE_DESCRIPTION;
        EXTENDED_DESCRIPTION = DemonsDinnerFork.cardStrings.EXTENDED_DESCRIPTION;
    }
}
