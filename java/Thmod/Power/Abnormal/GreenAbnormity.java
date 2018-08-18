package Thmod.Power.Abnormal;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class GreenAbnormity extends AbstractPower {
    public static final String POWER_ID = "GreenAbnormity";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("GreenAbnormity");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public GreenAbnormity(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = "GreenAbnormity";
        this.owner = owner;
        this.amount = amount;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/Abnormal/GreenAbnormity.png");
        this.type = PowerType.DEBUFF;
    }

    public int onAttacked(DamageInfo info, int damageAmount)
    {
        int roll = MathUtils.random(6);
        return (damageAmount + roll);
    }

    public void atEndOfRound() {
        if (this.amount <= 1)
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "GreenAbnormity"));
        else
            this.amount -= 1;
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0];
    }
}
