package Thmod.Power.Weather;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class Fuuu extends AbstractPower {
    public static final String POWER_ID = "Fuuu";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Fuuu");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public Fuuu(AbstractCreature owner) {
        this.name = NAME;
        this.ID = "Fuuu";
        this.owner = owner;
        this.amount = 2;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/Weather/Fuuu.png");
        this.type = PowerType.BUFF;
    }

    public void atStartOfTurnPostDraw() {
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, 1));
        flash();
    }

    public void atEndOfRound() {
        if (this.amount <= 1)
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "Fuuu"));
        else
            this.amount -= 1;
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0];
    }
}
