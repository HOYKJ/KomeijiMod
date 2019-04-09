package Thmod.vfx.action;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDrawPileEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

import java.util.ArrayList;

import Thmod.Utils;
import Thmod.vfx.LatterEffect;

public class ChooseAndObtainEffect extends AbstractGameEffect {
    private CardGroup groupToChoose, getGroup, getGroup2;
    private String text;
    private int amount;

    public ChooseAndObtainEffect(CardGroup chooseGroup, CardGroup getGroup, String text) {
        this(chooseGroup, getGroup, null, text);
    }

    public ChooseAndObtainEffect(CardGroup chooseGroup, CardGroup getGroup, CardGroup getGroup2, String text) {
        this.groupToChoose = chooseGroup;
        this.getGroup = getGroup;
        this.getGroup2 = getGroup2;
        this.text = text;
        this.amount = 1;
        this.duration = 0.5f;
    }

    public void update() {
        if (this.duration == 0.5f) {
            if (AbstractDungeon.cardRewardScreen.codexCard != null) {
                final AbstractCard c = AbstractDungeon.cardRewardScreen.codexCard.makeStatEquivalentCopy();
                if(this.getGroup == AbstractDungeon.player.drawPile){
                    AbstractDungeon.effectsQueue.add(new ShowCardAndAddToDrawPileEffect(c, true, false));
                }
                else if(this.getGroup == AbstractDungeon.player.hand){
                    AbstractDungeon.effectsQueue.add(new ShowCardAndAddToHandEffect(c));
                }
                else if(this.getGroup == AbstractDungeon.player.discardPile){
                    AbstractDungeon.effectsQueue.add(new ShowCardAndAddToDiscardEffect(c));
                }
                else if(this.getGroup == AbstractDungeon.player.masterDeck){
                    AbstractDungeon.effectsQueue.add(new ShowCardAndObtainEffect(c, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
                }
                else {
                    this.getGroup.addToTop(c);
                }
                //this.getGroup.addToTop(c);
                if(this.getGroup2 != null){
                    if(this.getGroup2 == AbstractDungeon.player.drawPile){
                        AbstractDungeon.effectsQueue.add(new ShowCardAndAddToDrawPileEffect(c, true, false));
                    }
                    else if(this.getGroup2 == AbstractDungeon.player.hand){
                        AbstractDungeon.effectsQueue.add(new ShowCardAndAddToHandEffect(c));
                    }
                    else if(this.getGroup2 == AbstractDungeon.player.discardPile){
                        AbstractDungeon.effectsQueue.add(new ShowCardAndAddToDiscardEffect(c));
                    }
                    else if(this.getGroup2 == AbstractDungeon.player.masterDeck){
                        AbstractDungeon.effectsQueue.add(new ShowCardAndObtainEffect(c, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
                    }
                    else {
                        this.getGroup2.addToTop(c);
                    }
                }
                AbstractDungeon.cardRewardScreen.codexCard = null;
                this.isDone = true;
            }
        }
        if (this.amount > 0) {
            --this.amount;
            ArrayList<AbstractCard> chooseGroup;
            if(this.groupToChoose.group.size() >= 5) {
                chooseGroup = getRandomCards(5);
            }
            else {
                chooseGroup = getRandomCards(this.groupToChoose.group.size());
            }
            Utils.openCardRewardsScreen(chooseGroup, false, this.text);
        }
    }

    private ArrayList<AbstractCard> getRandomCards(final int amount) {
        final ArrayList<AbstractCard> cards = new ArrayList<>();
        while (cards.size() < amount) {
            final AbstractCard card = getRandomCard();
            if (!cards.contains(card)) {
                cards.add(card);
            }
        }
        return cards;
    }

    private AbstractCard getRandomCard() {
        return allCardsToChoose().getRandomCard(true);
    }

    private CardGroup allCardsToChoose() {
        return this.groupToChoose;
    }

    @Override
    public void render(SpriteBatch spriteBatch) {

    }

    @Override
    public void dispose() {

    }
}
