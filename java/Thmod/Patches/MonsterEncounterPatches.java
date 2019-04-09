package Thmod.Patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.MonsterGroup;

import Thmod.Monsters.Flandre;
import Thmod.Monsters.Remiria;
import Thmod.Monsters.Satori;
import Thmod.Monsters.Shikieiki;
import Thmod.Monsters.Yukari;

@SpirePatch(cls="com.megacrit.cardcrawl.helpers.MonsterHelper", method="getEncounter")
public class MonsterEncounterPatches {
    public static MonsterGroup Postfix(MonsterGroup __result, String key)
    {
        String str;
        if (key.equals("Remiria"))
            return new MonsterGroup(new AbstractMonster[] { new Remiria(-100.0F,290.0F) });
        if (key.equals("Shikieiki"))
            return new MonsterGroup(new AbstractMonster[] { new Shikieiki(-70.0F,150.0F) });
        if (key.equals("Yukari"))
            return new MonsterGroup(new AbstractMonster[] { new Yukari(150.0F,-10.0F) });
        if (key.equals("Flandre"))
            return new MonsterGroup(new AbstractMonster[] { new Flandre(-100.0F,0.0F) });
        if (key.equals("Satori"))
            return new MonsterGroup(new AbstractMonster[] { new Satori(-900.0F,0.0F) });
        return __result;
    }
}
