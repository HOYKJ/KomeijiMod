package Thmod.Power.Weather;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class Nagi extends AbstractPower {
    public static final String POWER_ID = "Nagi";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Nagi");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public Nagi(AbstractCreature owner) {
        this.name = NAME;
        this.ID = "Nagi";
        this.owner = owner;
        this.amount = 5;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/Weather/Nagi.png");
        this.type = PowerType.BUFF;
    }

    public void atEndOfTurn(boolean isPlayer) {
        if(isPlayer){
            AbstractDungeon.player.heal(1);
            flash();
        }
    }

    public void atEndOfRound() {
        if (this.amount <= 1)
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "Nagi"));
        else
            this.amount -= 1;
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0];
    }
}
