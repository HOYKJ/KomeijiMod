package Thmod.Patches.EventPatch;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.GenericEventDialog;
import com.megacrit.cardcrawl.events.beyond.SpireHeart;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.map.MapRoomNode;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoomBoss;
import Thmod.ThMod;

@SpirePatch(cls="com.megacrit.cardcrawl.events.beyond.SpireHeart", method="buttonEffect")
public class SpireHeartPatch {
    @SpirePostfixPatch
    public static void Postfix(SpireHeart _obj_instance, int buttonPressed) {
        final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("SpireHeartPatch");
        final String[] TEXT = uiStrings.TEXT;
        if ((ThMod.blessingOfDetermination == 3) && (ThMod.blessingOfRemission == 2) && (ThMod.blessingOfTime == 3)) {
            if (ThMod.HeartScreen == 0) {
                _obj_instance.roomEventText.updateBodyText(TEXT[0]);
                _obj_instance.roomEventText.addDialogOption(TEXT[1]);
                ThMod.HeartScreen = 1;
            }
            else {
                switch (buttonPressed) {
                    case 1:
                        AbstractDungeon.effectList.clear();
                        _obj_instance.roomEventText.updateBodyText("");
                        AbstractDungeon.bossKey = "Yukari";
                        CardCrawlGame.music.fadeOutBGM();
                        CardCrawlGame.music.fadeOutTempBGM();
                        MapRoomNode node = new MapRoomNode(-1, 15);
                        node.room = new MonsterRoomBoss();
                        AbstractDungeon.nextRoom = node;
                        AbstractDungeon.closeCurrentScreen();
                        AbstractDungeon.nextRoomTransitionStart();

                        ThMod.HeartScreen = 0;
                        break;

                    default:
                        if(ThMod.HeartScreen == 1){
                            ThMod.HeartScreen += 1;
                            _obj_instance.roomEventText.clearRemainingOptions();
                        }
                        else {
                            ThMod.HeartScreen = 0;
                            _obj_instance.roomEventText.clearRemainingOptions();
                        }
                }
            }
        }
    }
}
