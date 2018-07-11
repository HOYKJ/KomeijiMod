package Thmod.Power;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class FuubiPower extends AbstractPower {
    public static final String POWER_ID = "FuubiPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("FuubiPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private AbstractPlayer p = AbstractDungeon.player;

    public FuubiPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = "FuubiPower";
        this.owner = owner;
        this.amount = amount;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/FuubiPower.png");
        this.type = PowerType.BUFF;
    }

    public void stackPower(int stackAmount)
    {
        if (this.amount == 0) {
            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, "FuubiPower"));
        }
    }

    public void atEndOfTurn(boolean isPlayer) {
        if(isPlayer)
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p,p,"FuubiPower"));
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0];
    }
}
