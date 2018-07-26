package Thmod.Power;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import Thmod.Actions.unique.TurnEndAction;

public class HibernatePower extends AbstractPower {
    public static final String POWER_ID = "HibernatePower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("HibernatePower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private int healnum;
    private AbstractPlayer p = AbstractDungeon.player;

    public HibernatePower(AbstractCreature owner, int Amount) {
        this.name = NAME;
        this.ID = "HibernatePower";
        this.owner = owner;
        this.amount = 3;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/HibernatePower.png");
        this.type = PowerType.BUFF;
        this.healnum = Amount;
    }

    public void atStartOfTurnPostDraw() {
        AbstractDungeon.actionManager.addToBottom(new TurnEndAction());
    }

    public void atEndOfTurn(boolean isPlayer) {
        if(isPlayer){
            if(this.amount > 1){
                p.heal(this.healnum);
                this.amount -= 1;
            }
            else if(this.amount == 1)
                AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner,this.owner,"HibernatePower"));
        }
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0];
    }
}
