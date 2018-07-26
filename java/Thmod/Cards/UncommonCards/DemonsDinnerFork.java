package Thmod.Cards.UncommonCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Cards.AbstractKomeijiCards;
import Thmod.Cards.DeriveCards.AbstractDeriveCards;
import Thmod.Cards.DeriveCards.DemonsFork;
import Thmod.Power.DemonsForkAccumulate;

public class DemonsDinnerFork extends AbstractKomeijiCards {
    public static final String ID = "DemonsDinnerFork";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 1;

    public DemonsDinnerFork() {
        super("DemonsDinnerFork", DemonsDinnerFork.NAME,  1, DemonsDinnerFork.DESCRIPTION, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        this.exhaust = true;
        this.baseMagicNumber = 6;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
//        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p,p,new DemonsForkAccumulate(p,this.upgraded)));
        AbstractDeriveCards c = new DemonsFork(1);
        if(this.upgraded)
            c.upgrade();
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c, false));
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c, false));
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c, false));
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m){
        if (p.hasPower("DemonsForkAccumulate")) {
            return false;
        }
        return true;
    }

    public AbstractCard makeCopy() {
        return new DemonsDinnerFork();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.upgradeMagicNumber(2);
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("DemonsDinnerFork");
        NAME = DemonsDinnerFork.cardStrings.NAME;
        DESCRIPTION = DemonsDinnerFork.cardStrings.DESCRIPTION;
    }
}
