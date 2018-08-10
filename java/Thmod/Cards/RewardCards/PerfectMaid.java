package Thmod.Cards.RewardCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

import Thmod.Cards.AbstractSweepCards;
import Thmod.Power.CounterAttackPower;

public class PerfectMaid extends AbstractSweepCards {
    public static final String ID = "PerfectMaid";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 2;

    public PerfectMaid() {
        super("PerfectMaid", PerfectMaid.NAME,  2, PerfectMaid.DESCRIPTION, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF);
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new CounterAttackPower(p)));
    }

    @Override
    public ArrayList<AbstractSweepCards> getOpposite() {
        final ArrayList<AbstractSweepCards> opposite = new ArrayList<>();
        opposite.add(new VanishingEverything());
        return opposite;
    }

    public AbstractCard makeCopy() {
        return new PerfectMaid();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("PerfectMaid");
        NAME = PerfectMaid.cardStrings.NAME;
        DESCRIPTION = PerfectMaid.cardStrings.DESCRIPTION;
    }
}
