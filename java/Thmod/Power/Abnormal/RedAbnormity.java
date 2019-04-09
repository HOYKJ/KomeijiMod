package Thmod.Power.Abnormal;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class RedAbnormity extends AbstractPower {
    public static final String POWER_ID = "RedAbnormity";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("RedAbnormity");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public RedAbnormity(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = "RedAbnormity";
        this.owner = owner;
        this.amount = amount;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/Abnormal/RedAbnormity.png");
        this.type = PowerType.DEBUFF;
    }

    public float atDamageReceive(float damage, DamageInfo.DamageType type)
    {
        if (type == DamageInfo.DamageType.NORMAL)
        {
            int oth = 0;
            float crease = 0.12f;
            boolean ga = true;
            boolean ba = true;
            for(AbstractPower p:this.owner.powers){
                if((p instanceof GreenAbnormity) && (ga)){
                    oth += 1;
                    ga = false;
                }
                if((p instanceof BlueAbnormity) && (ba)){
                    oth += 1;
                    ba = false;
                }
            }
            return (damage * (1.25F + oth * crease));
        }
        return damage;
    }

    public void atEndOfRound() {
        if (this.amount <= 1)
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "RedAbnormity"));
        else
            this.amount -= 1;
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0];
    }
}
