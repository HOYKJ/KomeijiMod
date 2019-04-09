package Thmod.Patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;

import Thmod.Power.JyouchiPower;

@SpirePatch(
        clz=AbstractPlayer.class,
        method= "damage"
)
public class ReduceDamagePatch {
    @SpireInsertPatch(rloc=0)
    public static void Insert(AbstractPlayer _inst, DamageInfo info){
        if(_inst.hasPower(JyouchiPower.POWER_ID)) {
            if (info.output > 3) {
                info.output -= 3;
            }
            else {
                info.output = 0;
            }
        }
    }
}
