package Thmod.Patches.RemiriaPatch;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.events.city.Vampires;

import Thmod.Characters.RemiriaScarlet;
import Thmod.Events.Remiria.RoomOfTimeSpe;
import Thmod.Events.Remiria.VampiresRemi;
import Thmod.ThMod;

public class VampiresPatch {
    @SpirePatch(
            clz= AbstractEvent.class,
            method="onEnterRoom"
    )
    public static class onEnterRoom {
        @SpireInsertPatch(rloc=0)
        public static void Insert(AbstractEvent _inst){
            if(_inst instanceof Vampires) {
                if ((AbstractDungeon.player != null) && (AbstractDungeon.player instanceof RemiriaScarlet)) {
                    if ((AbstractDungeon.player != null) && (AbstractDungeon.player instanceof RemiriaScarlet)) {
                        ThMod.logger.info("Replace Event");
                        AbstractDungeon.getCurrRoom().event = new VampiresRemi();
                        AbstractDungeon.getCurrRoom().event.onEnterRoom();
                    }
                }
            }
        }
    }
}
