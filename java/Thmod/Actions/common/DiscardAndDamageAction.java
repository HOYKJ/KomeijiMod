package Thmod.Actions.common;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

import java.util.Iterator;

import Thmod.Cards.SpellCards.HagoromoToki;

public class DiscardAndDamageAction extends AbstractGameAction
{
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("DiscardAndDamageAction");
    public static final String[] TEXT = uiStrings.TEXT;

    public DiscardAndDamageAction(AbstractCreature source)
    {
        setValues(AbstractDungeon.player, source);
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
    }

    public void update()
    {
        if (this.duration == 0.5F) {
            AbstractDungeon.handCardSelectScreen.open(TEXT[0], 1, false, true);

            AbstractDungeon.actionManager.addToBottom(new WaitAction(0.25F));
            tickDuration();
            return;
        }

        if (!(AbstractDungeon.handCardSelectScreen.wereCardsRetrieved))
        {
            if (!(AbstractDungeon.handCardSelectScreen.selectedCards.group.isEmpty())) {
                for (Iterator localIterator = AbstractDungeon.handCardSelectScreen.selectedCards.group.iterator(); localIterator.hasNext(); ) {
                    AbstractCard c = (AbstractCard)localIterator.next();
                    AbstractDungeon.player.hand.moveToDiscardPile(c);
                    GameActionManager.incrementDiscard(false);
                    c.triggerOnManualDiscard();
                }
                HagoromoToki.discarded = true;
            }

            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
        }

        tickDuration();
    }
}
