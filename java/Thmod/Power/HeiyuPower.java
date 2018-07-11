package Thmod.Power;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class HeiyuPower extends AbstractPower {
    public static final String POWER_ID = "HeiyuPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("HeiyuPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public HeiyuPower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = "HeiyuPower";
        this.owner = owner;
        this.amount = 5;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/HeiyuPower.png");
        this.type = PowerType.BUFF;
    }

//    public int onLoseHp(int damageAmount)
//    {
//        if((damageAmount > 0))
//            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "HeiyuPower"));
//        return damageAmount;
//    }

    public void atEndOfTurn(boolean isPlayer){
        if(isPlayer)
            AbstractDungeon.player.heal(2);
    }

    public void atEndOfRound() {
        if (this.amount == 1)
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "HeiyuPower"));
        else
            this.amount -= 1;
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0];
    }
}
