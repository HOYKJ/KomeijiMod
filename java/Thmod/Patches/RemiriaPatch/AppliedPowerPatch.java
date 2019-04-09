package Thmod.Patches.RemiriaPatch;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.lang.reflect.Field;

import Thmod.Cards.ScarletCard.uncommonCards.BathedInBlood;
import Thmod.Power.remiria.MiserableFatePower;

public class AppliedPowerPatch {
    @SpirePatch(
            clz= ApplyPowerAction.class,
            method="update"
    )
    public static class updatePatch {
        @SpireInsertPatch(rloc=6)
        public static void Insert(ApplyPowerAction _inst) throws NoSuchFieldException, IllegalAccessException {
            Field powerToApply;
            powerToApply = _inst.getClass().getDeclaredField("powerToApply");
            powerToApply.setAccessible(true);

            if(_inst.target.hasPower(MiserableFatePower.POWER_ID)){
                if(_inst.target.hasPower(((AbstractPower)powerToApply.get(_inst)).ID)){
                    ((MiserableFatePower) _inst.target.getPower(MiserableFatePower.POWER_ID)).onStackedPower((AbstractPower) powerToApply.get(_inst), _inst.amount, _inst.source);
                }
                else {
                    ((MiserableFatePower) _inst.target.getPower(MiserableFatePower.POWER_ID)).onAppliedPower((AbstractPower) powerToApply.get(_inst), _inst.source);
                }
            }

            for(AbstractCard card : AbstractDungeon.player.hand.group){
                if(card instanceof BathedInBlood){
                    ((BathedInBlood) card).appliedPower((AbstractPower) powerToApply.get(_inst));
                }
            }
        }
    }
}
