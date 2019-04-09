package Thmod.Patches;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;

import java.util.ArrayList;

import Thmod.Characters.RemiriaScarlet;
import Thmod.Power.remiria.BloodBruisePower;
import Thmod.Relics.CrystalOfMemory;

@SpirePatch(cls="com.megacrit.cardcrawl.core.AbstractCreature", method="renderPowerTips")
public class NoPowerPatch {
    @SpireInsertPatch(rloc=8, localvars={"tips"})
    public static void Insert(AbstractCreature _inst, SpriteBatch sb, ArrayList<PowerTip> tips)
    {
        if(AbstractDungeon.player.hasRelic("KoishisEye")){
            boolean noChange = false;
            if(AbstractDungeon.player.hasRelic(CrystalOfMemory.ID)){
                if (((CrystalOfMemory) AbstractDungeon.player.getRelic(CrystalOfMemory.ID)).number[0] == 2){
                    noChange = true;
                }
            }
            if(!noChange) {
                tips.clear();
            }
        }
        if(_inst instanceof RemiriaScarlet){
            tips.add(new PowerTip(BloodBruisePower.DESCRIPTIONS[1], BloodBruisePower.DESCRIPTIONS[2]));
            if(_inst.hasPower(BloodBruisePower.POWER_ID)) {
                int num = Math.min((int) ((_inst.getPower(BloodBruisePower.POWER_ID)).amount * 1.5f), 30);
                tips.add(new PowerTip(BloodBruisePower.DESCRIPTIONS[1], BloodBruisePower.DESCRIPTIONS[3]
                        + num + BloodBruisePower.DESCRIPTIONS[4]));
            }
        }
    }
}
