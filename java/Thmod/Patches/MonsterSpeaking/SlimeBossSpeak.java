package Thmod.Patches.MonsterSpeaking;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.exordium.SlimeBoss;

import Thmod.ThMod;

@SpirePatch(clz=SlimeBoss.class, method="usePreBattleAction")
public class SlimeBossSpeak {
    public static void Postfix(SlimeBoss _inst){
        if(ThMod.SlimeBoss)
            AbstractDungeon.actionManager.addToBottom(new TalkAction(_inst, Dialogs.TEXT[2], 1.0F, 2.0F));
    }
}
