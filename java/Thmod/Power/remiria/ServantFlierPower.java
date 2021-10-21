package Thmod.Power.remiria;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class ServantFlierPower extends AbstractPower {
    public static final String POWER_ID = "ServantFlierPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("ServantFlierPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public ServantFlierPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = "ServantFlierPower";
        this.owner = owner;
        this.amount = amount;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/remiria/ServantFlierPower.png");
        this.type = PowerType.BUFF;
    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        super.onAttack(info, damageAmount, target);
//        if((info.owner != this.owner) || (info.type != DamageInfo.DamageType.HP_LOSS)) {
//
//            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target, this.owner, new BloodBruisePower(target, 2), 2));
//
//            if (this.amount > 1) {
//                this.amount -= 1;
//            } else {
//                AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, this));
//            }
//        }
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, 1), 1));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new LoseStrengthPower(this.owner, 1), 1));
        if (this.amount > 1) {
            this.amount -= 1;
        } else {
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, this));
        }
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0];
    }
}
