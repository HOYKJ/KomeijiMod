package Thmod.Power.remiria;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class BloodCoagulationPower extends AbstractPower {
    public static final String POWER_ID = "BloodCoagulationPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("BloodCoagulationPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public BloodCoagulationPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = "BloodCoagulationPower";
        this.owner = owner;
        this.amount = amount;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/remiria/BloodCoagulationPower.png");
        this.type = PowerType.BUFF;
    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        super.onApplyPower(power, target, source);
        if(power instanceof BloodBruisePower){
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this.owner, this.owner, power.amount * this.amount));
        }
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
