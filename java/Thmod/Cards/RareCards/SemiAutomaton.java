package Thmod.Cards.RareCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Cards.AbstractKomeijiCards;
import Thmod.Power.AutomatonPower;

public class SemiAutomaton extends AbstractKomeijiCards {
    public static final String ID = "SemiAutomaton";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION;
    private static final int COST = 3;

    public SemiAutomaton() {
        super("SemiAutomaton", SemiAutomaton.NAME,  3, SemiAutomaton.DESCRIPTION, CardType.POWER, CardRarity.RARE, CardTarget.NONE);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new AutomatonPower(p, this.magicNumber), this.magicNumber));
    }

    public AbstractCard makeCopy() {
        return new SemiAutomaton();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.upgradeBaseCost(2);
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("SemiAutomaton");
        NAME = SemiAutomaton.cardStrings.NAME;
        DESCRIPTION = SemiAutomaton.cardStrings.DESCRIPTION;
        UPGRADE_DESCRIPTION = SemiAutomaton.cardStrings.UPGRADE_DESCRIPTION;
    }
}
