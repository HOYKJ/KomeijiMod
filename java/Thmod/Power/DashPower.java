package Thmod.Power;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class DashPower extends AbstractPower {
    public static final String POWER_ID = "DashPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("DashPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private AbstractPlayer p = AbstractDungeon.player;

    public DashPower(AbstractCreature owner,int amount) {
        this.name = NAME;
        this.ID = "DashPower";
        this.owner = owner;
        this.amount = amount;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/DashPower.png");
        this.type = PowerType.BUFF;
    }

    public void updateDescription()
    {
        if (this.amount <= 1)
            this.description = DESCRIPTIONS[0];
        else
            this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
    }

    public int onLoseHp(int damageAmount) {
        AbstractDungeon.actionManager.addToTop(new ReducePowerAction(this.owner, this.owner, this.ID, 1));
        if (!(p.hasPower("PointPower"))) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new PointPower(p, 1), 1));
        }
        else if (p.getPower("PointPower").amount < 5) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new PointPower(p, 1), 1));
        }
        return 0;
    }
}
