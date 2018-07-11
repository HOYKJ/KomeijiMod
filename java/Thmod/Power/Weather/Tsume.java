package Thmod.Power.Weather;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class Tsume extends AbstractPower {
    public static final String POWER_ID = "Tsume";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Tsume");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public Tsume(AbstractCreature owner) {
        this.name = NAME;
        this.ID = "Tsume";
        this.owner = owner;
        this.amount = 5;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/Weather/Tsume.png");
        this.type = PowerType.BUFF;
    }

    public int onLoseHp(int damageAmount) {
        AbstractDungeon.player.addBlock(3);
        return damageAmount;
    }

    public void atEndOfRound() {
        if (this.amount == 1)
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "Tsume"));
        else
            this.amount -= 1;
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0];
    }
}
