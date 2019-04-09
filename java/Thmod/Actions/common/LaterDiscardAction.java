package Thmod.Actions.common;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

public class LaterDiscardAction extends AbstractGameAction {
    private AbstractCard speCard;
    private CardGroup group;

    public LaterDiscardAction(AbstractCard speCard) {
        this.speCard = speCard;
        this.actionType = ActionType.DISCARD;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            ArrayList<AbstractCard> cards = new ArrayList<>();
            if (this.group == null) {
                this.group = AbstractDungeon.player.hand;
            }

            for(AbstractCard card : this.group.group){
                if(card == this.speCard){
                }
                else {
                    cards.add(card);
                }
            }

            if(cards.size() > 0){
                for(AbstractCard card : cards) {
                    this.group.moveToDiscardPile(card);
                    GameActionManager.incrementDiscard(false);
                    card.triggerOnManualDiscard();
                }
            }
        }

        this.tickDuration();
    }
}
