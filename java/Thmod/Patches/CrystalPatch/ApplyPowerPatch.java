package Thmod.Patches.CrystalPatch;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

import java.lang.reflect.Field;

import Thmod.Characters.RemiriaScarlet;
import Thmod.Power.remiria.ScarletLordPower;
import Thmod.Relics.CrystalOfMemory;
import Thmod.ThMod;

public class ApplyPowerPatch {
    @SpirePatch(
            clz = ApplyPowerAction.class,
            method = SpirePatch.CONSTRUCTOR,
            paramtypez={
                    AbstractCreature.class,
                    AbstractCreature.class,
                    AbstractPower.class,
                    int.class,
                    boolean.class,
                    AbstractGameAction.AttackEffect.class
            }
    )
    public static class ApplyPowerActionPatch {
        //@SpireInsertPatch(rloc = 10)
        public static void Postfix(ApplyPowerAction _inst, AbstractCreature target, AbstractCreature source, AbstractPower powerToApply, int stackAmount, boolean isFast, AbstractGameAction.AttackEffect effect) throws NoSuchFieldException, IllegalAccessException {
            if(AbstractDungeon.player.hasRelic(CrystalOfMemory.ID)){
                if (((CrystalOfMemory) AbstractDungeon.player.getRelic(CrystalOfMemory.ID)).number[0] == 11){
                    if((powerToApply.ID.equals(WeakPower.POWER_ID)) || (powerToApply.ID.equals(VulnerablePower.POWER_ID)))
                    {
                        Field powerToApply1;
                        powerToApply1 = _inst.getClass().getDeclaredField("powerToApply");
                        powerToApply1.setAccessible(true);
                        AbstractDungeon.player.getRelic(CrystalOfMemory.ID).flash();
                        ((AbstractPower) powerToApply1.get(_inst)).amount += 1;
                        _inst.amount += 1;
                    }
                }
                else if(((CrystalOfMemory) AbstractDungeon.player.getRelic(CrystalOfMemory.ID)).number[0] == 18){
                    for(int i = 0;i < 20;i++){
                        String weatherid = ThMod.weathers.get(i);
                        if(powerToApply.ID.equals(weatherid)){
                            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                                    new StrengthPower(AbstractDungeon.player, 1), 1));
                            AbstractDungeon.player.getRelic(CrystalOfMemory.ID).flash();
                        }
                    }
                }
            }
            if((AbstractDungeon.player instanceof RemiriaScarlet) && (powerToApply.ID.equals(ScarletLordPower.POWER_ID))){
                ((ScarletLordPower)powerToApply).onApply();
            }
        }
    }
}
