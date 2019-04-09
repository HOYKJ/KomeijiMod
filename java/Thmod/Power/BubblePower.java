package Thmod.Power;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.common.SetMoveAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class BubblePower extends AbstractPower {
    public static final String POWER_ID = "BubblePower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("BubblePower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private int strNum;
    private byte moveByte;
    private AbstractMonster.Intent moveIntent;

    public BubblePower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = "BubblePower";
        this.owner = owner;
        this.amount = amount;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/BubblePower.png");
        this.type = PowerType.DEBUFF;
        moveIntent = null;
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        if(this.amount >= 4){
            this.type = AbstractPower.PowerType.BUFF;
            if (this.owner instanceof AbstractMonster) {
                AbstractMonster m = (AbstractMonster)owner;

                this.moveByte = Byte.valueOf(m.nextMove).byteValue();
                this.moveIntent = AbstractMonster.Intent.valueOf(m.intent.name());

                byte a = 127;
                m.setMove(a, AbstractMonster.Intent.STUN);
                m.createIntent();
                AbstractDungeon.actionManager.addToBottom(new SetMoveAction(m, a, AbstractMonster.Intent.STUN));
            }
        }
    }

    public void atEndOfTurn(boolean isPlayer) {
        if(this.strNum != this.amount) {
            if(this.strNum < this.amount) {
                int reducenum = (this.amount - this.strNum);
                AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, -reducenum), -reducenum));
            }
            else if(this.strNum > this.amount) {
                int addnum = (this.strNum - this.amount);
                AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner,addnum),addnum));
            }
            this.strNum = this.amount;
        }
    }

//    public void atStartOfTurn() {
//        if(this.amount <= 1) {
//            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, this));
//        }
//        else {
//            this.amount -= 1;
//        }
//
//    }

    public void atEndOfRound() {
        if(this.amount >= 4) {
            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));

//            if (this.owner instanceof AbstractMonster) {
//                AbstractMonster m = (AbstractMonster) this.owner;
//
//                m.setMove(this.moveByte, this.moveIntent);
//                m.createIntent();
//                AbstractDungeon.actionManager.addToBottom(new SetMoveAction(m, this.moveByte, this.moveIntent));
//                m.updatePowers();
//            }
//            this.moveIntent = null;
        }
        else if(this.amount <= 1) {
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, this));
        }
        else {
            this.amount -= 1;
        }
    }

    @Override
    public void onRemove() {
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, this.amount), this.amount));
        if(this.moveIntent != null){
            if (this.owner instanceof AbstractMonster) {
                AbstractMonster m = (AbstractMonster) this.owner;

                m.setMove(this.moveByte, this.moveIntent);
                m.createIntent();
                AbstractDungeon.actionManager.addToBottom(new SetMoveAction(m, this.moveByte, this.moveIntent));
                m.updatePowers();
            }
            this.moveIntent = null;
        }
        super.onRemove();
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
