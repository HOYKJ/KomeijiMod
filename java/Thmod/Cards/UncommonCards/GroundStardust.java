package Thmod.Cards.UncommonCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
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
import Thmod.Cards.AbstractSweepCards;
import Thmod.Cards.DeriveCards.Stardust;
import Thmod.Power.StardustAccumulate;

public class GroundStardust extends AbstractSweepCards {
    public static final String ID = "GroundStardust";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;
    private static final int COST = 1;

    public GroundStardust() {
        super("GroundStardust", GroundStardust.NAME,  1, GroundStardust.DESCRIPTION, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        this.exhaust = true;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p,p,new StardustAccumulate(p)));
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new Stardust(0), false));
    }

    @Override
    public ArrayList<AbstractSweepCards> getOpposite() {
        final ArrayList<AbstractSweepCards> opposite = new ArrayList<>();
        opposite.add(new MajikuruSanhai());
        return opposite;
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m){
        super.canUse(p,m);
        if (p.hasPower("StardustAccumulate")) {
            AbstractDungeon.actionManager.addToTop(new PlayerTalkAction(p,EXTENDED_DESCRIPTION[0]));
            return false;
        }
        return true;
    }

    public AbstractCard makeCopy() {
        return new GroundStardust();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("GroundStardust");
        NAME = GroundStardust.cardStrings.NAME;
        DESCRIPTION = GroundStardust.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = GroundStardust.cardStrings.EXTENDED_DESCRIPTION;
    }
}
