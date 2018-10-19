package Thmod.Actions.common;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.util.Iterator;

import basemod.DevConsole;

public class DrawSpeCardAction extends AbstractGameAction {
    private AbstractCard cardToDraw;

    public DrawSpeCardAction(AbstractCard cardToDraw){
        this.cardToDraw = cardToDraw;
    }

    public void update() {
        AbstractPlayer p = AbstractDungeon.player;
        boolean hasCard = false;
        if(p.hand.size() < 10){
            if (!p.drawPile.isEmpty()) {
                AbstractCard c = cardToDraw;
                for(AbstractCard card:p.drawPile.group){
                    if(card.cardID.equals(c.cardID)) {
                        hasCard = true;
                        break;
                    }
                }

                if(hasCard) {
                    CardCrawlGame.sound.playAV("CARD_DRAW_8", -0.12F, 0.25F);

                    c.current_x = CardGroup.DRAW_PILE_X;
                    c.current_y = CardGroup.DRAW_PILE_Y;
                    c.setAngle(0.0F, true);
                    c.lighten(false);
                    c.drawScale = 0.12F;
                    c.targetDrawScale = 0.75F;
                    if (p.hasPower("Confusion") && c.cost >= 0) {
                        int newCost = AbstractDungeon.cardRandomRng.random(3);
                        if (c.cost != newCost) {
                            c.cost = newCost;
                            c.costForTurn = c.cost;
                            c.isCostModified = true;
                        }
                    }

                    c.triggerWhenDrawn();
                    p.hand.addToHand(c);
                    for(AbstractCard card:p.drawPile.group){
                        if(card.cardID.equals(c.cardID)) {
                            p.drawPile.group.remove(c);
                            break;
                        }
                    }
                    if (p.hasPower("Corruption") && c.type == AbstractCard.CardType.SKILL) {
                        c.setCostForTurn(-9);
                    }
                    c.applyPowers();

                    Iterator var6 = p.relics.iterator();

                    while (var6.hasNext()) {
                        AbstractRelic r = (AbstractRelic) var6.next();
                        r.onCardDraw(c);
                    }
                    AbstractDungeon.player.hand.refreshHandLayout();

                    p.onCardDrawOrDiscard();
                }
            }
        }
        this.isDone = true;
    }
}
