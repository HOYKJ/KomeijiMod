package Thmod.Cards.UncommonCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Cards.AbstractKomeijiCards;
import Thmod.Power.FreezePower;

public class FreezeTouchMe extends AbstractKomeijiCards {
    public static final String ID = "FreezeTouchMe";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;
    private static final int COST = 2;
    private static final int BLOCK_AMT = 15;

    public FreezeTouchMe() {
        super("FreezeTouchMe", FreezeTouchMe.NAME,  2, FreezeTouchMe.DESCRIPTION, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF, CardSet_k.CIRNO);
        this.baseBlock = 12;
        this.baseMagicNumber = 5;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToTop(new GainBlockAction(p, p, this.block));
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p,p,new FreezePower(p,this.magicNumber),this.magicNumber));
    }

    public AbstractCard makeCopy() {
        return new FreezeTouchMe();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.name = EXTENDED_DESCRIPTION[0];
            this.initializeTitle();
            this.upgradeMagicNumber(3);
            this.upgradeBlock(4);
            this.timesUpgraded += 1;
            this.upgraded = true;
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("FreezeTouchMe");
        NAME = FreezeTouchMe.cardStrings.NAME;
        DESCRIPTION = FreezeTouchMe.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = FreezeTouchMe.cardStrings.EXTENDED_DESCRIPTION;
    }
}
