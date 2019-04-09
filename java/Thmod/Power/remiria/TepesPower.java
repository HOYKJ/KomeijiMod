package Thmod.Power.remiria;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class TepesPower extends AbstractPower {
    public static final String POWER_ID = "TepesPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("TepesPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private boolean first;

    public TepesPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = "TepesPower";
        this.owner = owner;
        this.amount = amount;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/remiria/TepesPower.png");
        this.type = PowerType.DEBUFF;
        this.first = true;
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        super.atEndOfTurn(isPlayer);
        if(this.first){
            this.first = false;
        }
        else {
            if(this.amount > 1){
                this.amount -= 1;
            }
            else {
                AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, this));
            }
        }
    }

    @Override
    public int onHeal(int healAmount) {
        return 0;
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0];
    }
}
