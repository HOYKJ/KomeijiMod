package Thmod.Actions.unique;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

import Thmod.Cards.ItemCards.FusyokuKusuri;

import static Thmod.ThMod.masterSpellCard;
import static Thmod.ThMod.masterSpellCardFor2;
import static Thmod.ThMod.masterSpellCardFor3;
import static Thmod.ThMod.masterSpellCardFor5;

public class DeleteSpellAction extends AbstractGameAction {
    private CardGroup choices;
    private String message;

    public DeleteSpellAction(final String message){
        this.choices = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        this.message = message;
        this.duration = Settings.ACTION_DUR_FASTER;
        for(AbstractCard card : masterSpellCard.group) {
            this.choices.addToTop(card);
        }
        for(AbstractCard card : masterSpellCardFor2.group) {
            this.choices.addToTop(card);
        }
        for(AbstractCard card : masterSpellCardFor3.group) {
            this.choices.addToTop(card);
        }
        for(AbstractCard card : masterSpellCardFor5.group) {
            this.choices.addToTop(card);
        }
    }

    @Override
    public void update() {
        if (this.choices.size() <= 1) {
            this.tickDuration();
            this.isDone = true;
            return;
        }
        if (this.duration != Settings.ACTION_DUR_FASTER) {
            if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                AbstractCard card = AbstractDungeon.gridSelectScreen.selectedCards.get(0);
                if(masterSpellCard.contains(card)){
                    masterSpellCard.removeCard(card);
                }
                if(masterSpellCardFor2.contains(card)){
                    masterSpellCardFor2.removeCard(card);
                }
                if(masterSpellCardFor3.contains(card)){
                    masterSpellCardFor3.removeCard(card);
                }
                if(masterSpellCardFor5.contains(card)){
                    masterSpellCardFor5.removeCard(card);
                }
                if(this.choices.size() <= 8){
                    AbstractCard deleteCard = null;
                    for(AbstractCard card1 : masterSpellCard.group){
                        if(card1 instanceof FusyokuKusuri){
                            deleteCard = card1;
                        }
                    }
                    if(deleteCard != null){
                        masterSpellCard.removeCard(deleteCard);
                    }
                }
                AbstractDungeon.gridSelectScreen.selectedCards.clear();
            }
            this.tickDuration();
            return;
        }
        if (this.choices.size() > 1) {
            AbstractDungeon.gridSelectScreen.open(this.choices, 1, true, this.message);
            this.tickDuration();
        }
    }
}
