package Thmod.Actions.unique;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

import Thmod.Cards.AbstractSweepCards;
import Thmod.Cards.ElementCards.AbstractElementSweepCards;
import Thmod.Relics.SpellCardsRule;
import Thmod.Utils;

public class AssociationAction extends AbstractGameAction {
    private ArrayList<AbstractCard> cardGroup = new ArrayList<>();
    private AbstractCard selectCard = null;
    private AbstractCard removeCard;

    public AssociationAction(AbstractCard card){
        this.amount = 1;
        this.duration = 0.5f;
        if(card instanceof AbstractSweepCards){
            this.cardGroup.addAll(((AbstractSweepCards) card).getOpposite());
        }
        else if(card instanceof AbstractElementSweepCards){
            this.cardGroup.addAll(((AbstractElementSweepCards) card).getOpposite());
        }
        this.removeCard = card;
    }

    public void update() {
        if (this.duration == 0.5f) {
            if (AbstractDungeon.cardRewardScreen.codexCard != null) {
                this.selectCard = AbstractDungeon.cardRewardScreen.codexCard.makeStatEquivalentCopy();
                AbstractDungeon.cardRewardScreen.codexCard = null;
            }
            if (this.amount > 0) {
                --this.amount;
                Utils.openCardRewardsScreen(this.cardGroup, true, 5);
                return;
            }
            SpellCardsRule.clicked = false;
            this.cardSelect();
        }
        this.tickDuration();
    }

    private void cardSelect() {
        if(this.selectCard != null){
            AbstractDungeon.player.hand.removeCard(this.removeCard);
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(this.selectCard, false));
            this.selectCard = null;
        }
    }
}
