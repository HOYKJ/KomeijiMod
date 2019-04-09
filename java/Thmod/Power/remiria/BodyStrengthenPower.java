package Thmod.Power.remiria;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class BodyStrengthenPower extends AbstractPower {
    public static final String POWER_ID = "BodyStrengthenPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("BodyStrengthenPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public BodyStrengthenPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = "BodyStrengthenPower";
        this.owner = owner;
        this.amount = amount;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/remiria/BodyStrengthenPower.png");
        this.type = PowerType.BUFF;
    }

    @Override
    public float atDamageReceive(float damage, DamageInfo.DamageType damageType) {
        if((damageType == DamageInfo.DamageType.THORNS) || (damageType == DamageInfo.DamageType.HP_LOSS)){
            return 0;
        }
        return super.atDamageReceive(damage, damageType);
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if((info.type == DamageInfo.DamageType.THORNS) || (info.type == DamageInfo.DamageType.HP_LOSS)){
            return 0;
        }
        return super.onAttacked(info, damageAmount);
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        super.atEndOfTurn(isPlayer);
        if(this.amount > 1){
            this.amount -= 1;
        }
        else {
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, this));
        }
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
