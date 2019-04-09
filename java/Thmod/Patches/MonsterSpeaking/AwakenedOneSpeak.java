package Thmod.Patches.MonsterSpeaking;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.beyond.AwakenedOne;

import Thmod.ThMod;

@SpirePatch(clz=AwakenedOne.class, method="usePreBattleAction")
public class AwakenedOneSpeak {
    public static void Postfix(AwakenedOne _inst){
        if(ThMod.AwakenedOne)
            AbstractDungeon.actionManager.addToBottom(new TalkAction(_inst, Dialogs.TEXT[0], 1.0F, 2.0F));
    }
}
