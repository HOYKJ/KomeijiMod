package Thmod.Power;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import Thmod.Cards.AbstractSweepCards;
import Thmod.Cards.ElementCards.AbstractElementSweepCards;

public class ThoughtExtendPower extends AbstractPower {
    public static final String POWER_ID = "ThoughtExtendPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("ThoughtExtendPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private AbstractPlayer p = AbstractDungeon.player;

    public ThoughtExtendPower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = "ThoughtExtendPower";
        this.owner = owner;
        this.amount = 5;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/ThoughtExtendPower.png");
        this.type = PowerType.BUFF;
    }

    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        if((card instanceof AbstractSweepCards) || (card instanceof AbstractElementSweepCards)){
            if(this.amount > 1)
                this.amount -= 1;
            else {
                this.amount = 5;
                AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, 1));
                AbstractDungeon.actionManager.addToTop(new GainEnergyAction(1));
            }
        }
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
