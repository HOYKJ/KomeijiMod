package Thmod.Patches.MonsterSpeaking;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.beyond.TimeEater;

import Thmod.ThMod;

@SpirePatch(clz=TimeEater.class, method="usePreBattleAction")
public class TimeEaterSpeak {
    public static void Postfix(TimeEater _inst){
        if(ThMod.TimeEater) {
            AbstractDungeon.actionManager.addToBottom(new TalkAction(_inst, Dialogs.TEXT[5], 1.0F, 2.0F));
        }
    }
}
