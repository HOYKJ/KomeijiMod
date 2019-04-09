package Thmod.Patches.MonsterSpeaking;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.exordium.Hexaghost;

import Thmod.ThMod;

@SpirePatch(clz=Hexaghost.class, method="usePreBattleAction")
public class HexaghostSpeak {
    public static void Postfix(Hexaghost _inst){
        if(ThMod.Hexaghost)
            AbstractDungeon.actionManager.addToBottom(new TalkAction(_inst, Dialogs.TEXT[1], 1.0F, 2.0F));
    }
}
