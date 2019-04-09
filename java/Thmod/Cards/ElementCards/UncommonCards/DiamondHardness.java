package Thmod.Cards.ElementCards.UncommonCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

import Thmod.Cards.ElementCards.AbstractElementSweepCards;
import Thmod.Power.HardnessPower;

public class DiamondHardness extends AbstractElementSweepCards {
    public static final String ID = "DiamondHardness";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 2;

    public DiamondHardness() {
        super("DiamondHardness", DiamondHardness.NAME,  2, DiamondHardness.DESCRIPTION, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE,ElementType.Earth);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        super.use(p,m);
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,new HardnessPower(p,this.magicNumber),this.magicNumber));
    }

    @Override
    public ArrayList<AbstractElementSweepCards> getOpposite() {
        final ArrayList<AbstractElementSweepCards> opposite = new ArrayList<>();
        opposite.add(new MidautumnSpear(0));
        opposite.add(new EmeraldCity());
        return opposite;
    }

    public AbstractCard makeCopy() {
        return new DiamondHardness();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("DiamondHardness");
        NAME = DiamondHardness.cardStrings.NAME;
        DESCRIPTION = DiamondHardness.cardStrings.DESCRIPTION;
    }
}
