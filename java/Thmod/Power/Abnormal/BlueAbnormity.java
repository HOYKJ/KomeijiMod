package Thmod.Power.Abnormal;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class BlueAbnormity extends AbstractPower {
    public static final String POWER_ID = "BlueAbnormity";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("BlueAbnormity");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private float damageGive;

    public BlueAbnormity(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = "BlueAbnormity";
        this.owner = owner;
        this.amount = amount;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/Abnormal/BlueAbnormity.png");
        this.type = PowerType.DEBUFF;
    }

    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        this.damageGive = damage;
        return damage;
    }

    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        if(target != this.owner) {
            int oth = 0;
            float crease = 0.2f;
            boolean ga = true;
            boolean ra = true;
            for(AbstractPower p:this.owner.powers){
                if((p instanceof GreenAbnormity) && (ga)){
                    oth += 1;
                    ga = false;
                }
                if((p instanceof RedAbnormity) && (ra)){
                    oth += 1;
                    ra = false;
                }
            }
            AbstractDungeon.actionManager.addToTop(new DamageAction(info.owner, new DamageInfo(this.owner, (int) (this.damageGive * (0.5 + oth * crease)), DamageInfo.DamageType.HP_LOSS)));
        }
    }

    public void atEndOfRound() {
        if (this.amount <= 1)
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, this));
        else
            this.amount -= 1;
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0];
    }
}
