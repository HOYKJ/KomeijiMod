package Thmod.Power.remiria;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.WeakPower;

import Thmod.Actions.unique.ChooseAction;
import Thmod.Cards.ScarletCard.Absorbed;
import Thmod.Cards.ScarletCard.rareCards.FitfulNightmare;

public class FitfulNightmarePower extends AbstractPower {
    public static final String POWER_ID = "FitfulNightmarePower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("FitfulNightmarePower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private boolean goodTurn;
    private boolean canEnd;

    public FitfulNightmarePower(AbstractCreature owner, boolean canEnd) {
        this.name = NAME;
        this.ID = "FitfulNightmarePower";
        this.owner = owner;
        this.amount = -1;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/remiria/FitfulNightmarePower.png");
        this.type = PowerType.BUFF;
        this.goodTurn = false;
        this.canEnd = canEnd;
    }

    @Override
    public void atStartOfTurnPostDraw() {
        super.atStartOfTurnPostDraw();
        if(this.canEnd) {
            final ChooseAction choice = new ChooseAction(new FitfulNightmare(), null, Absorbed.EXTENDED_DESCRIPTION[2], false, 1);
            choice.add(FitfulNightmare.NAME, DESCRIPTIONS[1], () -> {
            });
            choice.add(FitfulNightmare.NAME, DESCRIPTIONS[2], () -> {
                AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, this));
                if (this.owner.hasPower(ScarletLordPower.POWER_ID)) {
                    if (this.owner.getPower(ScarletLordPower.POWER_ID).amount > 1) {
                        this.owner.getPower(ScarletLordPower.POWER_ID).amount -= 1;
                    } else {
                        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, ScarletLordPower.POWER_ID));
                    }
                }
            });
            AbstractDungeon.actionManager.addToBottom(choice);
        }

        if(this.goodTurn){
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new ScarletLordPower(this.owner, 1), 1));
        }
        else {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new WeakPower(this.owner, 1, false), 1));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new FrailPower(this.owner, 1, false), 1));
        }

        this.goodTurn = !this.goodTurn;
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0];
    }
}
