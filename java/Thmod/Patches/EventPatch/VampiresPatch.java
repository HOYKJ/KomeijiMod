package Thmod.Patches.EventPatch;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.Iterator;

@SpirePatch(cls="com.megacrit.cardcrawl.events.city.Vampires", method="replaceAttacks")
public class VampiresPatch
{
    @SpireInsertPatch(rloc=8)
    public static void Insert(Object __obj_instance)
    {
        AbstractDungeon.player.masterDeck.group.removeIf(e -> e.cardID.equals("Strike_Komeiji"));
    }
}
