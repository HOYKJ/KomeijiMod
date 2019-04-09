package Thmod.Power;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class HenyouMirumePower extends AbstractPower {
    public static final String POWER_ID = "HenyouMirumePower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("HenyouMirumePower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private int moreDamage;

    public HenyouMirumePower(AbstractCreature owner,int amount) {
        this.name = NAME;
        this.ID = "HenyouMirumePower";
        this.owner = owner;
        if (amount > 0)
            this.amount = amount;
        else
            this.amount = -1;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/HenyouMirumePower.png");
        this.type = AbstractPower.PowerType.BUFF;
        this.moreDamage = 12;
    }

    public float atDamageGive(float damage, DamageInfo.DamageType type)
    {
        if (this.amount > 0) {
            //AbstractDungeon.actionManager.addToTop(new ReducePowerAction(this.owner, this.owner, "HenyouMirumePower", 1));
            return (damage + this.moreDamage);
        }
        else if(this.amount == 0)
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner,this.owner,this));
        else if(this.amount == -1)
            return (damage + this.moreDamage);
        return damage;
    }

    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        if (this.amount > 0)
            AbstractDungeon.actionManager.addToTop(new ReducePowerAction(this.owner, this.owner, this, 1));
    }

    public void atEndOfTurn(boolean isPlayer) {
        if((isPlayer))
            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner,this.owner,this));
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0] + this.moreDamage + DESCRIPTIONS[1];
    }
}
