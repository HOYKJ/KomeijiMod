package Thmod.Power.Weather;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import basemod.DevConsole;

public class TenkiYume extends AbstractPower {
    public static final String POWER_ID = "TenkiYume";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("TenkiYume");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public TenkiYume(AbstractCreature owner) {
        this.name = NAME;
        this.ID = "TenkiYume";
        this.owner = owner;
        this.amount = 3;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/Weather/TenkiYume.png");
        this.type = PowerType.BUFF;
    }

    public float modifyBlock(float blockAmount) {
        int roll = MathUtils.random(5);
        flash();
        DevConsole.logger.info("Tenkiyume" + blockAmount);
        return (blockAmount + roll - 2);
    }


    public void atEndOfRound() {
        if (this.amount <= 1)
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "TenkiYume"));
        else
            this.amount -= 1;
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0];
    }
}
