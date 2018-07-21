package Thmod.Power.Weather;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class Soyuki extends AbstractPower {
    public static final String POWER_ID = "Soyuki";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Soyuki");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public Soyuki(AbstractCreature owner) {
        this.name = NAME;
        this.ID = "Soyuki";
        this.owner = owner;
        this.amount = 5;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/Weather/Soyuki.png");
        this.type = PowerType.BUFF;
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if(!(card.upgraded)) {
            card.upgrade();
            flash();
            this.amount -= 1;
        }
    }

    public void atEndOfRound() {
        if (this.amount <= 1)
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "Soyuki"));
        else
            this.amount -= 1;
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0];
    }
}
