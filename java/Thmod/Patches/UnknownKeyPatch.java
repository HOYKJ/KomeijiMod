package Thmod.Patches;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;

import Thmod.ThMod;

@SpirePatch(cls="com.megacrit.cardcrawl.core.AbstractCreature", method="renderPowerTips")
public class UnknownKeyPatch {
    @SpireInsertPatch(rloc=1, localvars={"tips"})
    public static void Insert(AbstractCreature _inst, SpriteBatch sb, ArrayList<PowerTip> tips)
    {
        for (AbstractPower p : _inst.powers) {
            boolean alreadyExists = false;
            if (ThMod.unknownKey.containsKey(p.name))
            {
                for (PowerTip t : tips) {
                    if (t.header.toLowerCase().equals(p.name))
                    {
                        alreadyExists = true;
                        break;
                    }
                }
                if (!alreadyExists) {
                    tips.add(new PowerTip(p.name, ThMod.unknownKey.get(p.name)));
                }
            }
        }
    }
}
