package Thmod.Power;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class SoulPower extends AbstractPower {
    public static final String POWER_ID = "SoulPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("SoulPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private int num;

    public SoulPower(AbstractCreature owner,int amount,int num) {
        this.name = NAME;
        this.ID = "SoulPower";
        this.owner = owner;
        this.amount = amount;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/SoulPower.png");
        this.type = PowerType.BUFF;
        this.num = num;
    }

    public void atEndOfTurn(boolean isPlayer){
        if(isPlayer)
            AbstractDungeon.player.heal(this.num);
    }

    public void atEndOfRound() {
        if (this.amount == 1)
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "SoulPower"));
        else
            this.amount -= 1;
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0] + this.num + DESCRIPTIONS[1];
    }
}
