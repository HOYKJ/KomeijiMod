package Thmod.Patches.RelicPatch;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.saveAndContinue.SaveFile;

import Thmod.Characters.RemiriaScarlet;
import Thmod.Relics.AbstractThLaterRelic;

public class LaterEnterPatch {
    @SpirePatch(clz= AbstractDungeon.class, method="nextRoomTransition", paramtypez = {SaveFile.class})
    public static class BGMPatch
    {
        public static void Postfix(AbstractDungeon _inst, SaveFile saveFile)
        {
            if (AbstractDungeon.getCurrRoom() != null) {
                for (AbstractRelic r : AbstractDungeon.player.relics) {
                    if(r instanceof AbstractThLaterRelic){
                        ((AbstractThLaterRelic) r).enteredRoom(AbstractDungeon.getCurrRoom());
                    }
                }
                if((AbstractDungeon.player != null) && (AbstractDungeon.player instanceof RemiriaScarlet)){
                    ((RemiriaScarlet) AbstractDungeon.player).enterRoom();
                }
            }
        }
    }
}
