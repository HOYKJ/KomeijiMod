package Thmod.Actions.unique;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.Iterator;

import Thmod.Cards.AbstractSweepCards;
import Thmod.Cards.ElementCards.AbstractElementSweepCards;
import Thmod.ThMod;

public class GetSweepbleCards {
    public GetSweepbleCards(){

    }

    public static CardGroup getSweepbleCards () {
        String cardid;
        CardGroup retVal = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        for (Iterator localIterator = AbstractDungeon.player.masterDeck.group.iterator(); localIterator.hasNext();) {
            AbstractCard c = (AbstractCard)localIterator.next();
//            for (Iterator Iterator = ThMod.campids.iterator(); Iterator.hasNext(); ) {
//                cardid = (String) Iterator.next();
//                if (c.cardID.equals(cardid))
//                    retVal.group.add(c);
//            }
            if(c instanceof AbstractSweepCards)
                retVal.group.add(c);
            if(c instanceof AbstractElementSweepCards)
                retVal.group.add(c);
        }
        return retVal;
    }
}
