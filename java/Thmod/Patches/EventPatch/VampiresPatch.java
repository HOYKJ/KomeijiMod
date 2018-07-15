package Thmod.Patches.EventPatch;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.Iterator;

@SpirePatch(cls="com.megacrit.cardcrawl.events.thecity.Vampires", method="replaceAttacks")
public class VampiresPatch
{
    @SpireInsertPatch(rloc=8)
    public static void Insert(Object __obj_instance)
    {
        for (Iterator<AbstractCard> i = AbstractDungeon.player.masterDeck.group.iterator(); i.hasNext();)
        {
            AbstractCard e = i.next();
            if (e.cardID.equals("Strike_Komeiji")) {
                i.remove();
            }
        }
    }
}
