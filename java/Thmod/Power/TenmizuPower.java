package Thmod.Power;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;


public class TenmizuPower extends AbstractPower {
    public static final String POWER_ID = "TenmizuPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("TenmizuPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static AbstractPlayer p = AbstractDungeon.player;

    public TenmizuPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = "TenmizuPower";
        this.owner = owner;
        this.amount = amount;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/TenmizuPower.png");
        this.type = PowerType.BUFF;
    }

    public void atEndOfTurn(boolean isPlayer) {
        if((isPlayer) && (this.amount >= 3)){
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, this));
            AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(this.owner,this.owner,new IntangiblePlayerPower(this.owner,3),3));
        }
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0];
    }
}
