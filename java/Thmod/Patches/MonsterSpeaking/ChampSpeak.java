package Thmod.Patches.MonsterSpeaking;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.city.Champ;

import Thmod.ThMod;

@SpirePatch(clz=Champ.class, method="usePreBattleAction")
public class ChampSpeak {
    public static void Postfix(Champ _inst){
        if(ThMod.Champ) {
            AbstractDungeon.actionManager.addToBottom(new TalkAction(_inst, Dialogs.TEXT[7], 1.0F, 2.0F));
        }
    }
}
