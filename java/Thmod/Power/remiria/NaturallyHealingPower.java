package Thmod.Power.remiria;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class NaturallyHealingPower extends AbstractPower {
    public static final String POWER_ID = "NaturallyHealingPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("NaturallyHealingPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public NaturallyHealingPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = "NaturallyHealingPower";
        this.owner = owner;
        this.amount = amount;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/remiria/NaturallyHealingPower.png");
        this.type = PowerType.BUFF;
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        super.atEndOfTurn(isPlayer);
        this.trigger();
    }

    public void trigger(){
        if(this.owner.hasPower(BloodBruisePower.POWER_ID)){
            for(int i = 0; i < this.amount; i ++) {
                AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this.owner, this.owner, this.owner.getPower(BloodBruisePower.POWER_ID).amount));
            }
        }
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
