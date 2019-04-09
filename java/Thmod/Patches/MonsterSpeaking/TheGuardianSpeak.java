package Thmod.Patches.MonsterSpeaking;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.exordium.TheGuardian;

import Thmod.ThMod;

@SpirePatch(clz=TheGuardian.class, method="usePreBattleAction")
public class TheGuardianSpeak {
    public static void Postfix(TheGuardian _inst){
        if(ThMod.TheGuardian) {
            AbstractDungeon.actionManager.addToBottom(new TalkAction(_inst, Dialogs.TEXT[3], 1.0F, 2.0F));
        }
    }
}
