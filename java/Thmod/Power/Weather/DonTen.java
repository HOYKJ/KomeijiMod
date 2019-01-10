package Thmod.Power.Weather;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import Thmod.Power.PointPower;

public class DonTen extends AbstractPower {
    public static final String POWER_ID = "DonTen";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("DonTen");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private int counter;
    private AbstractPlayer p = AbstractDungeon.player;

    public DonTen(AbstractCreature owner) {
        this.name = NAME;
        this.ID = "DonTen";
        this.owner = owner;
        this.amount = 5;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/Weather/DonTen.png");
        this.type = PowerType.BUFF;
        this.counter = 0;
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if((card.type == AbstractCard.CardType.ATTACK)&&(this.counter != 5))
            this.counter += 1;
        if(this.counter == 5){
            if(p.hasPower("PointPower")) {
                if (p.getPower("PointPower").amount < 5) {
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new PointPower(p, 1), 1));
                    flash();
                    this.counter = 0;
                }
            }
            else{
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new PointPower(p, 1), 1));
                flash();
                this.counter = 0;
            }
        }
        if(card.cost >= 2) {
            card.cost -= 1;
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "DonTen"));
        }
    }

    public void atEndOfRound() {
        if (this.amount <= 1)
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "DonTen"));
        else
            this.amount -= 1;
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0];
    }
}
