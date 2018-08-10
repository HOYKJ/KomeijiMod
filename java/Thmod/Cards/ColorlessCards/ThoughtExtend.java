package Thmod.Cards.ColorlessCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Power.ThoughtExtendPower;

public class ThoughtExtend extends AbstractColorlessCards {
    public static final String ID = "ThoughtExtend";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 2;

    public ThoughtExtend() {
        super("ThoughtExtend", RidiculousThoughts.NAME,  2, RidiculousThoughts.DESCRIPTION, CardType.POWER, CardRarity.RARE, CardTarget.NONE);
        this.baseMagicNumber = 5;
        this.magicNumber = baseMagicNumber;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,new ThoughtExtendPower(p)));
    }

    public AbstractCard makeCopy() {
        return new ThoughtExtend();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.upgradeBaseCost(1);
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("ThoughtExtend");
        NAME = ThoughtExtend.cardStrings.NAME;
        DESCRIPTION = ThoughtExtend.cardStrings.DESCRIPTION;
    }
}
