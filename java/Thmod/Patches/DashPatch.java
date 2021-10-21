package Thmod.Patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

import Thmod.Cards.ScarletCard.AbstractRemiriaFate;
import Thmod.Power.DashPower;
import Thmod.ThMod;

public class DashPatch {

    @SpirePatch(
            clz = AbstractCreature.class,
            method = "useStaggerAnimation"
    )
    public static class useStaggerAnimation {
        @SpirePrefixPatch
        public static SpireReturn prefix(AbstractCreature _inst) {
            if(_inst.hasPower(DashPower.POWER_ID)){
                return SpireReturn.Return(null);
            }
            return SpireReturn.Continue();
        }
    }
}
