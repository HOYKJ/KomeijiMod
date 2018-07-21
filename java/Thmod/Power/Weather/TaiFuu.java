package Thmod.Power.Weather;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class TaiFuu extends AbstractPower {
    public static final String POWER_ID = "TaiFuu";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("TaiFuu");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public TaiFuu(AbstractCreature owner) {
        this.name = NAME;
        this.ID = "TaiFuu";
        this.owner = owner;
        this.amount = 3;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/Weather/TaiFuu.png");
        this.type = PowerType.BUFF;
    }

    public float atDamageGive(float damage, DamageInfo.DamageType damageType)
    {
        return ((int)(damage * 1.5F));
    }

    public float atDamageReceive(float damage, DamageInfo.DamageType damageType)
    {
        return ((int)(damage * 1.5F));
    }

    public void atEndOfRound() {
        if (this.amount <= 1)
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "TaiFuu"));
        else
            this.amount -= 1;
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0];
    }
}
