package Thmod.Power.remiria;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import Thmod.Actions.unique.ChooseAction;
import Thmod.Cards.ScarletCard.Absorbed;
import Thmod.Cards.ScarletCard.rareCards.FitfulNightmare;

public class FitfulNightmarePower extends AbstractPower {
    public static final String POWER_ID = "FitfulNightmarePower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("FitfulNightmarePower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private boolean ask;

    public FitfulNightmarePower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = "FitfulNightmarePower";
        this.owner = owner;
        this.amount = -1;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/remiria/FitfulNightmarePower.png");
        this.type = PowerType.BUFF;
        this.ask = false;
    }

    @Override
    public void atStartOfTurnPostDraw() {
        super.atStartOfTurnPostDraw();
        if(this.ask) {
            final ChooseAction choice = new ChooseAction(new FitfulNightmare(), null, Absorbed.EXTENDED_DESCRIPTION[2], false, 1);
            choice.add(FitfulNightmare.NAME, DESCRIPTIONS[1], () -> {
                AbstractCard card = AbstractDungeon.returnRandomCurse().makeCopy();
                AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAction(card, 1));
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
            this.ask = false;
        }
        else {
            this.ask = true;
        }
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0];
    }
}
