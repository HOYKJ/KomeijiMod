package Thmod.Cards.RareCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Cards.AbstractKomeijiCards;
import Thmod.Power.YuumeiPower;

public class YuumeiNoKurin extends AbstractKomeijiCards {
    public static final String ID = "YuumeiNoKurin";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 2;

    public YuumeiNoKurin() {
        super("YuumeiNoKurin", YuumeiNoKurin.NAME,  2, YuumeiNoKurin.DESCRIPTION, CardType.SKILL, CardRarity.RARE, CardTarget.NONE);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,new YuumeiPower(p,this.magicNumber)));
    }

    public AbstractCard makeCopy() {
        return new MusouMyousyu();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.name = "回忆「幽明求闻持聪明之法」";
            this.initializeTitle();
            this.upgradeMagicNumber(1);
            this.upgradeBaseCost(3);
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("YuumeiNoKurin");
        NAME = YuumeiNoKurin.cardStrings.NAME;
        DESCRIPTION = YuumeiNoKurin.cardStrings.DESCRIPTION;
    }
}
