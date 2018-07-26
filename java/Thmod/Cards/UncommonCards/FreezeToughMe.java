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

import static Thmod.ThMod.AllzhsOpen;

public class FreezeToughMe extends AbstractKomeijiCards {
    public static final String ID = "FreezeToughMe";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 2;
    private static final int BLOCK_AMT = 15;

    public FreezeToughMe() {
        super("FreezeToughMe", FreezeToughMe.NAME,  2, FreezeToughMe.DESCRIPTION, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseBlock = 15;
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToTop(new GainBlockAction(p, p, this.block));
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p,p,new FreezePower(p,this.magicNumber)));
    }

    public AbstractCard makeCopy() {
        return new FreezeToughMe();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            if(AllzhsOpen)
                this.name = "回忆「急冻大气」";
            else
                this.name = "回忆「Freeze Atmosphere」";
            this.initializeTitle();
            this.upgradeMagicNumber(2);
            this.upgradeBlock(5);
            this.timesUpgraded += 1;
            this.upgraded = true;
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("FreezeToughMe");
        NAME = FreezeToughMe.cardStrings.NAME;
        DESCRIPTION = FreezeToughMe.cardStrings.DESCRIPTION;
    }
}
