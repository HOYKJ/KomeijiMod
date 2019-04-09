package Thmod.Cards.ElementCards.UncommonCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;
import java.util.Iterator;

import Thmod.Actions.unique.PlayerTalkAction;
import Thmod.Cards.DeriveCards.AbstractDeriveCards;
import Thmod.Cards.DeriveCards.Thrasher;
import Thmod.Cards.ElementCards.AbstractElementSweepCards;
import Thmod.Power.StardustAccumulate;
import Thmod.Power.ThrasherAccumulate;

public class FallThrasher extends AbstractElementSweepCards {
    public static final String ID = "FallThrasher";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 2;

    public FallThrasher() {
        super("FallThrasher", FallThrasher.NAME,  2, FallThrasher.DESCRIPTION, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.NONE,ElementType.Metal);
        this.baseMagicNumber = 12;
        this.magicNumber = baseMagicNumber;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        super.use(p,m);
        if(!(p.hasPower("ThrasherAccumulate"))) {
            AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p, p, new ThrasherAccumulate(p)));
            AbstractDeriveCards c = new Thrasher(1);
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c, false));
        }
        else{
            AbstractDungeon.actionManager.addToTop(new GainEnergyAction(1));
            AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p, p, new ThrasherAccumulate(p), 1));
        }
    }

    @Override
    public ArrayList<AbstractElementSweepCards> getOpposite() {
        final ArrayList<AbstractElementSweepCards> opposite = new ArrayList<>();
        opposite.add(new AutumnEdge());
        opposite.add(new AutumnBlades());
        return opposite;
    }

    public AbstractCard makeCopy() {
        return new FallThrasher();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("FallThrasher");
        NAME = FallThrasher.cardStrings.NAME;
        DESCRIPTION = FallThrasher.cardStrings.DESCRIPTION;
    }
}
