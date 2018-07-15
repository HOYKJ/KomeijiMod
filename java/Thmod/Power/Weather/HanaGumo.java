package Thmod.Power.Weather;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;

import Thmod.Power.PointPower;

public class HanaGumo extends AbstractPower {
    public static final String POWER_ID = "HanaGumo";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("HanaGumo");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private AbstractPlayer p = AbstractDungeon.player;

    public HanaGumo(AbstractCreature owner) {
        this.name = NAME;
        this.ID = "HanaGumo";
        this.owner = owner;
        this.amount = 2;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/Weather/HanaGumo.png");
        this.type = PowerType.BUFF;
    }

    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if((power.ID == "DashPower")&&(target == p)) {
            AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(p,p,"DashPower",1));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new IntangiblePlayerPower(p, 1), 1));
            flash();
        }
    }

    public void atEndOfRound() {
        if (this.amount == 1)
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "HanaGumo"));
        else
            this.amount -= 1;
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0];
    }
}
