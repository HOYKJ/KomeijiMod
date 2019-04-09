package Thmod.Patches.MonsterSpeaking;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.city.BronzeAutomaton;

import Thmod.ThMod;

@SpirePatch(clz=BronzeAutomaton.class, method="usePreBattleAction")
public class BronzeAutomatonSpeak {
    public static void Postfix(BronzeAutomaton _inst){
        if(ThMod.BronzeAutomaton) {
            AbstractDungeon.actionManager.addToBottom(new TalkAction(_inst, Dialogs.TEXT[6], 1.0F, 2.0F));
        }
    }
}
