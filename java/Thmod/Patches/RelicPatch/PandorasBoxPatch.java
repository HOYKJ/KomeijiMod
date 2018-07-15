package Thmod.Patches.RelicPatch;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.PandorasBox;

import java.lang.reflect.Field;
import java.util.Iterator;

@SpirePatch(cls="com.megacrit.cardcrawl.relics.PandorasBox", method="onEquip")
public class PandorasBoxPatch
{
    @SpireInsertPatch(rloc=12)
    public static void Insert(Object __obj_instance)
    {
        try
        {
            PandorasBox box;
            Field count;
            Iterator<AbstractCard> i;
            box = (PandorasBox)__obj_instance;
            count = box.getClass().getDeclaredField("count");
            count.setAccessible(true);
            for (i = AbstractDungeon.player.masterDeck.group.iterator(); i.hasNext();)
            {
                AbstractCard e = (AbstractCard)i.next();
                if ((e.cardID.equals("Strike_Komeiji")) || (e.cardID.equals("Defend_Komeiji")))
                {
                    i.remove();
                    count.set(box, Integer.valueOf(((Integer)count.get(box)).intValue() + 1));
                }
            }
        }
        catch (NoSuchFieldException|SecurityException|IllegalArgumentException|IllegalAccessException e)
        {

            e.printStackTrace();
        }
    }
}
