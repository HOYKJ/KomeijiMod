package Thmod.Patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import Thmod.Cards.VictoryCards.AbstractVictoryCards;

@SpirePatch(cls="com.megacrit.cardcrawl.characters.AbstractPlayer", method="onVictory")
public class CardVictoryPatch {
    @SpireInsertPatch(rloc=1)
    public static void Insert(AbstractPlayer _inst)
    {
        for(AbstractCard c:AbstractDungeon.player.masterDeck.group){
            if(c instanceof AbstractVictoryCards){
                ((AbstractVictoryCards) c).onVictory();
            }
        }
    }
}
