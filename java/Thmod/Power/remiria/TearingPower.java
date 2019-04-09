package Thmod.Power.remiria;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class TearingPower extends AbstractPower {
    public static final String POWER_ID = "TearingPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("TearingPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public TearingPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = "TearingPower";
        this.owner = owner;
        this.amount = amount;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/remiria/TearingPower.png");
        this.type = PowerType.DEBUFF;
    }

    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        super.onAttack(info, damageAmount, target);
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new BloodBruisePower(this.owner, this.amount), this.amount));
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
