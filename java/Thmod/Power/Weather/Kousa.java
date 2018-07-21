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

public class Kousa extends AbstractPower {
    public static final String POWER_ID = "Kousa";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Kousa");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private boolean damaged;

    public Kousa(AbstractCreature owner) {
        this.name = NAME;
        this.ID = "KaiSei";
        this.owner = owner;
        this.amount = 5;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/Weather/Kousa.png");
        this.type = PowerType.BUFF;
        this.damaged = true;
    }

    public float atDamageGive(float damage, DamageInfo.DamageType type)
    {
        if ((type == DamageInfo.DamageType.NORMAL)&&(this.damaged)) {
            flash();
            this.damaged = false;
            return (damage * 1.33F);
        }
        else
            return damage;
    }

    public void atEndOfRound() {
        if (this.amount <= 1)
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "Kousa"));
        else
            this.amount -= 1;
        this.damaged = true;
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0];
    }
}
