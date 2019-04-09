package Thmod.Patches.MonsterSpeaking;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.city.TheCollector;

import Thmod.ThMod;

@SpirePatch(clz=TheCollector.class, method="usePreBattleAction")
public class TheCollectorSpeak {
    public static void Postfix(TheCollector _inst){
        if(ThMod.TheCollector) {
            AbstractDungeon.actionManager.addToBottom(new TalkAction(_inst, Dialogs.TEXT[4], 1.0F, 2.0F));
        }
    }
}
