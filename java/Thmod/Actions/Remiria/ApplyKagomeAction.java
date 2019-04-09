package Thmod.Actions.Remiria;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.ShowCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import Thmod.Power.remiria.KagomePower;
import Thmod.ThMod;

public class ApplyKagomeAction extends AbstractGameAction
{
    private AbstractCreature owner;
    private float startingDuration;
    private AbstractCard card1 = null, card2 = null, card3 = null;

    public ApplyKagomeAction(AbstractCreature owner)
    {
        this.owner = owner;
        this.duration = Settings.ACTION_DUR_LONG;
        this.startingDuration = Settings.ACTION_DUR_LONG;
        this.actionType = AbstractGameAction.ActionType.WAIT;
    }

    public void update()
    {
        if ((AbstractDungeon.player.drawPile.isEmpty()) && (AbstractDungeon.player.discardPile.isEmpty()))
        {
            this.isDone = true;
            return;
        }
        if (this.duration == this.startingDuration)
        {
            getCard(1);
            getCard(2);
            getCard(3);
        }
        tickDuration();
        if ((this.isDone) && (this.card1 != null))
        {
            ThMod.logger.info("---------------TEST----------------");
            AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(this.owner, this.owner, new KagomePower(this.owner, this.card1, this.card2, this.card3)));
            AbstractDungeon.actionManager.addToTop(new ShowCardAction(this.card3));
            AbstractDungeon.actionManager.addToTop(new ShowCardAction(this.card2));
            AbstractDungeon.actionManager.addToTop(new ShowCardAction(this.card1));
        }
    }

    private void getCard(int size){
        AbstractCard.CardRarity cardRarity;
        switch (size){
            case 1:
                cardRarity = AbstractCard.CardRarity.COMMON;
                break;
            case 2:
                cardRarity = AbstractCard.CardRarity.UNCOMMON;
                break;
            default:
                cardRarity = AbstractCard.CardRarity.RARE;
                break;
        }
        AbstractCard card;
        if (AbstractDungeon.player.drawPile.size() < size)
        {
            card = AbstractDungeon.player.discardPile.getRandomCard(true, cardRarity);
            if (card == null) {
                card = AbstractDungeon.player.discardPile.getRandomCard(true);
            }
            AbstractDungeon.player.discardPile.removeCard(card);
        }
        else
        {
            card = AbstractDungeon.player.drawPile.getRandomCard(true, cardRarity);
            if (card == null) {
                card = AbstractDungeon.player.drawPile.getRandomCard(true);
            }
            AbstractDungeon.player.drawPile.removeCard(card);
        }
        AbstractDungeon.player.limbo.addToBottom(card);
        card.setAngle(0.0F);
        card.targetDrawScale = 0.75F;
        card.target_x = (Settings.WIDTH / 2.0F + (-250f + size * 250f) * Settings.scale);
        card.target_y = (Settings.HEIGHT / 2.0F);
        card.lighten(false);
        card.unfadeOut();
        card.unhover();
        card.untip();
        card.stopGlowing();
        switch (size){
            case 1:
                this.card1 = card;
                break;
            case 2:
                this.card2 = card;
                break;
            default:
                this.card3 = card;
                break;
        }
    }
}
