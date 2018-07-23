package Thmod.Power;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class StrengthDownPower extends AbstractPower {
    public static final String POWER_ID = "StrengthDownPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("StrengthDownPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public StrengthDownPower(AbstractCreature owner, int newAmount)
    {
        this.name = NAME;
        this.ID = "StrengthDownPower";
        this.owner = owner;
        this.amount = newAmount;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/StrengthDownPower.png");
        this.type = PowerType.BUFF;
    }

    public void updateDescription()
    {
        this.description = this.amount + DESCRIPTIONS[0] + "10" + DESCRIPTIONS[1];
    }

    public int onPlayerGainedBlock(int blockAmount)
    {
        return 0;
    }

    public void atEndOfTurn(boolean isPlayer) {
        int Artnum = 0;
        if (isPlayer) {
            if (this.amount == 1) {
                if (this.owner.hasPower("Artifact")) {
                    Artnum = this.owner.getPower("Artifact").amount;
                    AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, "Artifact"));
                }
                flash();
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, -10), -10));
                AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "StrengthEndPower"));
                if(Artnum > 0){
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new ArtifactPower(this.owner, Artnum), Artnum));
                }
            } else
                this.amount -= 1;
        }
    }
}
