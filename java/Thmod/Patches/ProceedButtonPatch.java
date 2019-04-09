package Thmod.Patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.EventRoom;
import com.megacrit.cardcrawl.rooms.TreasureRoomBoss;
import com.megacrit.cardcrawl.ui.buttons.ProceedButton;

import java.lang.reflect.Field;

import Thmod.Events.GateofTheMinistry;
import Thmod.Events.Remiria.RoomOfDemonRemi;
import Thmod.Events.RoomOfDemon;
import basemod.DevConsole;

@SpirePatch(cls="com.megacrit.cardcrawl.ui.buttons.ProceedButton", method="update")
public class ProceedButtonPatch {
    @SpireInsertPatch(rloc=0)
    public static void Insert(ProceedButton meObj)
    {
        Field isHidden = null;
        Field hb = null;
        Field current_x = null;
        try {
            isHidden = meObj.getClass().getDeclaredField("isHidden");
            hb = meObj.getClass().getDeclaredField("hb");
            current_x = meObj.getClass().getDeclaredField("current_x");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        assert isHidden != null;
        isHidden.setAccessible(true);
        assert hb != null;
        hb.setAccessible(true);
        assert current_x != null;
        current_x.setAccessible(true);
        try {
            if (!isHidden.getBoolean(meObj))
            {
                if (current_x.getFloat(meObj) - (1670.0F * Settings.scale) < (25.0F * Settings.scale)) {
                    ((Hitbox)hb.get(meObj)).update();
                }
                if ((((Hitbox)hb.get(meObj)).hovered) && (InputHelper.justClickedLeft))
                {
                    CardCrawlGame.sound.play("UI_CLICK_1");
                    ((Hitbox)hb.get(meObj)).clickStarted = true;
                }
                if ((((Hitbox)hb.get(meObj)).clicked) || (CInputActionSet.proceed.isJustPressed()))
                {
                    AbstractRoom currentRoom = AbstractDungeon.getCurrRoom();
                    if ((AbstractDungeon.screen == AbstractDungeon.CurrentScreen.COMBAT_REWARD) &&
                            (!(AbstractDungeon.getCurrRoom() instanceof TreasureRoomBoss)))
                    {
                        if ((currentRoom instanceof EventRoom)){
                            if ((currentRoom.event instanceof RoomOfDemon) || (currentRoom.event instanceof GateofTheMinistry) || (currentRoom.event instanceof RoomOfDemonRemi)){
                                AbstractDungeon.closeCurrentScreen();
                                AbstractDungeon.dungeonMapScreen.open(false);
                                AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.COMBAT_REWARD;
                                //meObj.hide();
                                ((Hitbox)hb.get(meObj)).clicked = false;
                                return;
                            }
                        }
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
