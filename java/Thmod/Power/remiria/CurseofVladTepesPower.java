package Thmod.Power.remiria;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class CurseofVladTepesPower extends AbstractPower {
    public static final String POWER_ID = "CurseofVladTepesPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("CurseofVladTepesPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public CurseofVladTepesPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = "CurseofVladTepesPower";
        this.owner = owner;
        this.amount = amount;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/remiria/CurseofVladTepesPower.png");
        this.type = PowerType.BUFF;
    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        super.onAttack(info, damageAmount, target);

        if ((damageAmount > 0) && (target != this.owner))
        {
            flash();
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target, this.owner, new BloodBruisePower(target, this.amount), this.amount));
        }
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
