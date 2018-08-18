package Thmod.Patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.MonsterGroup;

import Thmod.Monsters.Remiria;
import Thmod.Monsters.Shikieiki;

@SpirePatch(cls="com.megacrit.cardcrawl.helpers.MonsterHelper", method="getEncounter")
public class MonsterEncounterPatches {
    public static MonsterGroup Postfix(MonsterGroup __result, String key)
    {
        String str;
        if (key.equals("Remiria"))
            return new MonsterGroup(new AbstractMonster[] { new Remiria(-110.0F,-10.0F) });
        if (key.equals("Shikieiki"))
            return new MonsterGroup(new AbstractMonster[] { new Shikieiki(-110.0F,-10.0F) });
        return __result;
    }
}
