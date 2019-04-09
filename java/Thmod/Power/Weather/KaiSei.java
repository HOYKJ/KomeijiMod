package Thmod.Power.Weather;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class KaiSei extends AbstractPower {
    public static final String POWER_ID = "KaiSei";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("KaiSei");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private int counter;

    public KaiSei(AbstractCreature owner) {
        this.name = NAME;
        this.ID = "KaiSei";
        this.owner = owner;
        this.amount = 5;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/Weather/KaiSei.png");
        this.type = PowerType.BUFF;
        this.counter = 0;
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if(card.cost > 1)
            this.counter += 1;
        if(this.counter == 4){
            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(this.owner, 1));
            flash();
            this.counter = 0;
        }
    }

    public void atEndOfRound() {
        if (this.amount <= 1)
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "KaiSei"));
        else
            this.amount -= 1;
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0];
    }
}
