package Thmod.Patches.EventPatch;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.neow.NeowEvent;
import com.megacrit.cardcrawl.neow.NeowReward;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import Thmod.Characters.KomeijiSatori;
import Thmod.Neow.ShikigamiReward;
import Thmod.ThMod;

public class NeowEventPatch {

    @SpirePatch(cls="com.megacrit.cardcrawl.neow.NeowEvent", method="blessing")
    public static class blessingPatch1
    {
        @SpireInsertPatch(rloc=12, localvars={"rewards"})
        public static void Insert(NeowEvent _inst, ArrayList<NeowReward> rewards)
        {
            if (((AbstractDungeon.player instanceof KomeijiSatori)) && (ThMod.defeatYukari)) {
                rewards.add(new ShikigamiReward());
            }
        }
    }

    @SpirePatch(cls="com.megacrit.cardcrawl.neow.NeowEvent", method="blessing")
    public static class blessingPatch
    {
        @SpireInsertPatch(rloc=19, localvars={"rewards"})
        public static void Insert(NeowEvent _inst, ArrayList<NeowReward> rewards)
        {
            if (((AbstractDungeon.player instanceof KomeijiSatori)) && (ThMod.defeatYukari)) {
                _inst.roomEventText.addDialogOption((rewards.get(4)).optionLabel);
            }
        }
    }

    @SpirePatch(cls="com.megacrit.cardcrawl.neow.NeowEvent", method="buttonEffect")
    public static class buttEffectPatch
    {
        @SpireInsertPatch(rloc=62, localvars={"rewards"})
        public static void Insert(NeowEvent _inst, int buttonPressed, ArrayList<NeowReward> rewards) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
            if (buttonPressed < 4) {
                return;
            }
            rewards.get(buttonPressed).activate();
            Method m = NeowEvent.class.getDeclaredMethod("talk", String.class);
            m.setAccessible(true);
            m.invoke(_inst, "???!");
            ThMod.defeatYukari = false;
            ThMod.SavePointPower();
        }
    }
}
