package Thmod.Cards.RareCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

import Thmod.Cards.AbstractKomeijiCards;
import Thmod.Power.YuumeiPower;

public class YuumeiNoKurin extends AbstractKomeijiCards {
    public static final String ID = "YuumeiNoKurin";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;
    private static final int COST = 1;

    public YuumeiNoKurin() {
        super("YuumeiNoKurin", YuumeiNoKurin.NAME,  1, YuumeiNoKurin.DESCRIPTION, CardType.POWER, CardRarity.RARE, CardTarget.NONE);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,new YuumeiPower(p,this.magicNumber),this.magicNumber));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,new WeakPower(p,1,false),1));
    }

    public AbstractCard makeCopy() {
        return new YuumeiNoKurin();
    }

    public void upgrade() {
        if (this.upgraded) {
            this.name = EXTENDED_DESCRIPTION[0] + "+" + (this.timesUpgraded);
        } else {
            this.name = EXTENDED_DESCRIPTION[0];
        }
        this.initializeTitle();
        this.upgradeMagicNumber(1);
        this.upgradeBaseCost(this.cost + 1);
        this.timesUpgraded += 1;
        this.upgraded = true;
    }

    public boolean canUpgrade()
    {
        return true;
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("YuumeiNoKurin");
        NAME = YuumeiNoKurin.cardStrings.NAME;
        DESCRIPTION = YuumeiNoKurin.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = YuumeiNoKurin.cardStrings.EXTENDED_DESCRIPTION;
    }
}
