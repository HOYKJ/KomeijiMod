package Thmod.Actions.Remiria;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;

import java.util.ArrayList;

import Thmod.Cards.ScarletCard.AbstractRemiriaCards;
import Thmod.Cards.ScarletCard.AbstractRemiriaFate;
import Thmod.ThMod;

public class PlusCardAction extends AbstractGameAction {
    private ArrayList<AbstractRemiriaCards> cards = new ArrayList<>();

    public PlusCardAction(AbstractRemiriaCards cards){
        this.duration = 0.2F;
        this.cards.clear();
        this.cards.add(cards);
    }

    public PlusCardAction(CardGroup cardGroup, AbstractCard creater){
        this(cardGroup, false, creater);
    }

    public PlusCardAction(CardGroup cardGroup, int num, AbstractCard creater){
        this.cards.clear();
        this.duration = 0.2F;
//        while (num > 0){
//            boolean canPlus = false;
//            for(AbstractCard c : cardGroup.group){
//                if((c instanceof AbstractRemiriaCards) && (!((AbstractRemiriaCards) c).isPlus) && (c != creater)){
//                    if(!this.cards.contains(c)) {
//                        canPlus = true;
//                        AbstractCard card;
//                        do {
//                            card = cardGroup.getRandomCard(true);
//                        } while (!(card instanceof AbstractRemiriaCards) || (this.cards.contains(c) || (((AbstractRemiriaCards) card).isPlus) || (card == creater)));
//                        this.cards.add((AbstractRemiriaCards) card);
//                        ThMod.logger.info("plus card: " + card.cardID);
//                        break;
//                    }
//                }
//            }
//            if(!canPlus){
//                break;
//            }
//            num --;
//        }

        CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        for(AbstractCard c : cardGroup.group){
            if((c instanceof AbstractRemiriaCards) && (!((AbstractRemiriaCards) c).isPlus) && (c != creater)){
                tmp.addToTop(c);
            }
        }
        while ((tmp.group.size() > 0) && (num > 0)) {
            AbstractCard card = tmp.getRandomCard(true);
            this.cards.add((AbstractRemiriaCards) card);
            tmp.removeCard(card);
            num --;
        }
    }

    public PlusCardAction(CardGroup cardGroup, boolean all, AbstractCard creater){
        this.cards.clear();
        this.duration = 0.2F;
        if(all) {
            for (AbstractCard c : cardGroup.group) {
                if((c instanceof AbstractRemiriaCards) && (!((AbstractRemiriaCards) c).isPlus) && (c != creater)){
                    this.cards.add((AbstractRemiriaCards) c);
                }
            }
        }
        else {
            CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            for(AbstractCard c : cardGroup.group){
                if((c instanceof AbstractRemiriaCards) && (!((AbstractRemiriaCards) c).isPlus) && (c != creater)){
                    tmp.addToTop(c);
                    ThMod.logger.info("have card: " + c.cardID);
                }
            }
            if(tmp.group.size() > 0){
                AbstractCard card = tmp.getRandomCard(true);
                this.cards.add((AbstractRemiriaCards) card);
            }
            for(AbstractRemiriaCards card : this.cards){
                card.plusCard();
                ThMod.logger.info("plus card: " + card.cardID);
            }
            this.cards.clear();
        }
    }

    public PlusCardAction(CardGroup cardGroup, boolean all, AbstractCard creater, boolean fate) {
        this.cards.clear();
        this.duration = 0.2F;

        for (AbstractCard c : cardGroup.group) {
            if ((c instanceof AbstractRemiriaCards) && (!((AbstractRemiriaCards) c).isPlus) && (c != creater) && (c instanceof AbstractRemiriaFate)) {
                this.cards.add((AbstractRemiriaCards) c);
            }
        }
    }

    public void update(){
        if (this.duration == 0.2F){
            for(AbstractRemiriaCards card : this.cards){
                card.plusCard();
                ThMod.logger.info("plus card: " + card.cardID);
            }
        }
        tickDuration();
    }
}
