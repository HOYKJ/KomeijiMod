package Thmod.Power;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import Thmod.Cards.ElementCards.AbstractElementCards;

public class ForestBlazePower extends AbstractPower {
    public static final String POWER_ID = "ForestBlazePower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("ForestBlazePower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private AbstractPlayer p = AbstractDungeon.player;

    public ForestBlazePower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = "ForestBlazePower";
        this.owner = owner;
        this.amount = -1;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/ForestBlazePower.png");
        this.type = PowerType.BUFF;
    }

    public void onUseCard(AbstractCard card, UseCardAction action)
    {
        if (card instanceof AbstractElementCards) {
            flash();
            action.exhaustCard = true;
        }
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0];
    }
}
