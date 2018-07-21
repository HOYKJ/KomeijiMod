package Thmod.Cards;

import com.megacrit.cardcrawl.actions.defect.IncreaseMaxOrbAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

public class SeekerWire extends AbstractSweepCards {
    public static final String ID = "SeekerWire";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 1;

    public SeekerWire() {
        super("SeekerWire", SeekerWire.NAME,  1, SeekerWire.DESCRIPTION, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE);
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.isEthereal = true;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new IncreaseMaxOrbAction(this.magicNumber));
    }

    @Override
    public ArrayList<AbstractSweepCards> getOpposite() {
        final ArrayList<AbstractSweepCards> opposite = new ArrayList<>();
        opposite.add(new NingyouShinki());
        opposite.add(new NingyouSP());
        return opposite;
    }

    public AbstractCard makeCopy() {
        return new SeekerWire();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("SeekerWire");
        NAME = SeekerWire.cardStrings.NAME;
        DESCRIPTION = SeekerWire.cardStrings.DESCRIPTION;
    }
}
