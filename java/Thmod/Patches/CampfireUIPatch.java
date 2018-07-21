package Thmod.Patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.CampfireUI;
import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import Thmod.Cards.AbstractSweepCards;
import Thmod.ThMod;
import Thmod.ui.SweepOption;
import basemod.ReflectionHacks;

@SpirePatch(cls="com.megacrit.cardcrawl.rooms.CampfireUI", method="initializeButtons")
public class CampfireUIPatch {
    public static void Postfix(Object meObj)
    {
        CampfireUI campfire = (CampfireUI)meObj;
        try
        {
            ArrayList campfireButtons = (ArrayList) ReflectionHacks.getPrivate(campfire, CampfireUI.class, "buttons");
            String cardid;

            int height = 450;
            if (campfireButtons.size() > 2) {
                height = 180;
            }

            SweepOption button = new SweepOption(true);

            label0:
            for (Iterator localIterator = AbstractDungeon.player.masterDeck.group.iterator(); localIterator.hasNext();) {
                AbstractCard c = (AbstractCard)localIterator.next();
//                for (Iterator Iterator = ThMod.campids.iterator(); Iterator.hasNext(); ) {
//                    cardid = (String) Iterator.next();
                    if (c instanceof AbstractSweepCards) {
                        campfireButtons.add(button);
                        ((AbstractCampfireOption) campfireButtons.get(campfireButtons.size() - 1)).setPosition(950.0F * Settings.scale, height * Settings.scale);
                        break label0;
                    }
                }
//            }




        }
        catch (java.lang.IllegalArgumentException e)
        {
            e.printStackTrace();
        }
    }
}
