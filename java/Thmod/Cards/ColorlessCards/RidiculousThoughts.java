package Thmod.Cards.ColorlessCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Power.RidiculousPower;

public class RidiculousThoughts extends AbstractColorlessCards {
    public static final String ID = "RidiculousThoughts";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 2;

    public RidiculousThoughts() {
        super("RidiculousThoughts", RidiculousThoughts.NAME,  2, RidiculousThoughts.DESCRIPTION, CardType.POWER, CardRarity.RARE, CardTarget.NONE);
        this.baseMagicNumber = 1;
        this.magicNumber = baseMagicNumber;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,new RidiculousPower(p,this.magicNumber),this.magicNumber));
    }

    public AbstractCard makeCopy() {
        return new RidiculousThoughts();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.upgradeBaseCost(1);
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("RidiculousThoughts");
        NAME = RidiculousThoughts.cardStrings.NAME;
        DESCRIPTION = RidiculousThoughts.cardStrings.DESCRIPTION;
    }
}
