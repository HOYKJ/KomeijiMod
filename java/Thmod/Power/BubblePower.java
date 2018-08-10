package Thmod.Power;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class BubblePower extends AbstractPower {
    public static final String POWER_ID = "BubblePower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("BubblePower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private int strNum;

    public BubblePower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = "NingyouPower";
        this.owner = owner;
        this.amount = amount;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/BubblePower.png");
        this.type = PowerType.DEBUFF;
    }

    public void atEndOfTurn(boolean isPlayer) {
        if(this.amount <= 1)
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner,this.owner,this));
        else
            this.amount -= 1;
    }

    public void atStartOfTurn() {
        if(this.strNum != this.amount) {
            if(this.strNum < this.amount) {
                int reducenum = (this.amount - this.strNum);
                AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, -reducenum), -reducenum));
            }
            else if(this.strNum > this.amount) {
                int addnum = (this.strNum - this.amount);
                AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner,addnum),addnum));
            }
            this.strNum = this.amount;
        }
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
