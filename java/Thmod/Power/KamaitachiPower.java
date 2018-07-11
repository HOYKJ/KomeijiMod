package Thmod.Power;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
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

public class KamaitachiPower extends AbstractPower {
    public static final String POWER_ID = "KamaitachiPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("KamaitachiPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private AbstractPlayer p = AbstractDungeon.player;

    public KamaitachiPower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = "KamaitachiPower";
        this.owner = owner;
        this.amount = -1;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/KamaitachiPower.png");
        this.type = PowerType.BUFF;
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if(card.type == AbstractCard.CardType.ATTACK)
            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, 1));
    }

    public void atEndOfTurn(boolean isPlayer) {
        if(isPlayer)
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p,p,"KamaitachiPower"));
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0];
    }
}
