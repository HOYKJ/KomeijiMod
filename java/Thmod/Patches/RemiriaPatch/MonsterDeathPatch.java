package Thmod.Patches.RemiriaPatch;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Power.remiria.MillenniumVampirePower;

public class MonsterDeathPatch {
    @SpirePatch(
            clz= AbstractMonster.class,
            method="die",
            paramtypez = {
                    boolean.class
            }
    )
    public static class diePatch {
        @SpireInsertPatch(rloc=2)
        public static void Insert(AbstractMonster _inst, boolean triggerRelics){
            if(AbstractDungeon.player.hasPower(MillenniumVampirePower.POWER_ID)){
                ((MillenniumVampirePower)AbstractDungeon.player.getPower(MillenniumVampirePower.POWER_ID)).onMonsterDeath(_inst);
            }
        }
    }
}
