package Thmod.Patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.CampfireUI;
import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;

import java.util.ArrayList;
import java.util.Iterator;

import Thmod.Cards.AbstractSweepCards;
import Thmod.Cards.ElementCards.AbstractElementSweepCards;
import Thmod.Relics.GoodDreamPillow;
import Thmod.ui.GoodDreamOption;
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
            int wide = 950;
            if (campfireButtons.size() > 3) {
                height = 180;
            }
            else if(campfireButtons.size() == 3) {
                wide = 1110;
            }

            SweepOption button = new SweepOption(true);

            label0:
            for (Iterator<AbstractCard> localIterator = AbstractDungeon.player.masterDeck.group.iterator(); localIterator.hasNext();) {
                AbstractCard c = localIterator.next();
//                for (Iterator Iterator = ThMod.campids.iterator(); Iterator.hasNext(); ) {
//                    cardid = (String) Iterator.next();
                    if ((c instanceof AbstractSweepCards) || (c instanceof AbstractElementSweepCards)) {
                        campfireButtons.add(button);
                        if(campfireButtons.size() == 4){
                            ((AbstractCampfireOption) campfireButtons.get(campfireButtons.size() - 2)).setPosition(800.0F * Settings.scale, height * Settings.scale);
                        }
                        ((AbstractCampfireOption) campfireButtons.get(campfireButtons.size() - 1)).setPosition(wide * Settings.scale, height * Settings.scale);
                        break label0;
                    }
                }
//            }

            height = 450;
            wide = 950;
            if (campfireButtons.size() == 4) {
                height = 180;
            }
            else if(campfireButtons.size() == 3) {
                wide = 1110;
            }
            else if(campfireButtons.size() == 5){
                height = 180;
                wide = 1110;
            }

            GoodDreamOption button1 = new GoodDreamOption(true);

//            if(AbstractDungeon.player.hasRelic(""))
            if(AbstractDungeon.player.hasRelic(GoodDreamPillow.ID)) {
                campfireButtons.add(button1);
                if (campfireButtons.size() == 4) {
                    ((AbstractCampfireOption) campfireButtons.get(campfireButtons.size() - 2)).setPosition(800.0F * Settings.scale, height * Settings.scale);
                } else if (campfireButtons.size() == 6) {
                    ((AbstractCampfireOption) campfireButtons.get(campfireButtons.size() - 2)).setPosition(800.0F * Settings.scale, height * Settings.scale);
                }
                ((AbstractCampfireOption) campfireButtons.get(campfireButtons.size() - 1)).setPosition(wide * Settings.scale, height * Settings.scale);
            }

        }
        catch (java.lang.IllegalArgumentException e)
        {
            e.printStackTrace();
        }
    }
}
