package Thmod.Patches;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;

import java.util.ArrayList;

@SpirePatch(cls="com.megacrit.cardcrawl.core.AbstractCreature", method="renderPowerTips")
public class NoPowerPatch {
    @SpireInsertPatch(rloc=8, localvars={"tips"})
    public static void Insert(AbstractCreature _inst, SpriteBatch sb, ArrayList<PowerTip> tips)
    {
        if(AbstractDungeon.player.hasRelic("KoishisEye")){
                tips.clear();
        }
    }
}
