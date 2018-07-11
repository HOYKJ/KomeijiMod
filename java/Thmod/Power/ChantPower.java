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

public class ChantPower extends AbstractPower {
    public static final String POWER_ID = "ChantPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("ChantPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private AbstractPlayer p = AbstractDungeon.player;
    private int damage;
    private int turn;

    public ChantPower(AbstractCreature owner, int damage,int turn) {
        this.name = NAME;
        this.ID = "ChantPower";
        this.owner = owner;
        this.amount = -1;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/ChantPower.png");
        this.type = PowerType.BUFF;
        this.damage = damage;
        this.turn = turn;
    }

    public void atStartOfTurn() {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new WumiGaWareruPower(p, this.turn,this.damage), this.turn));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,new IntangiblePlayerPower(p,2)));
        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p,p,"ChantPower"));
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0];
    }
}
