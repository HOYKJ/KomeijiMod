package Thmod.Patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.campfire.CampfireSmithEffect;

import Thmod.Actions.unique.UpgradeEffectChoose;
import Thmod.Cards.UncommonCards.SenseofElegance;
import Thmod.ThMod;

public class CampfireSmithEffectPatch {
    @SpirePatch(
            clz = CampfireSmithEffect.class,
            method = "update"
    )
    public static class update {
        @SpireInsertPatch(rloc = 0)
        public static void Insert(CampfireSmithEffect _inst) {
            if ((!AbstractDungeon.isScreenUp) && (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) && (AbstractDungeon.gridSelectScreen.forUpgrade)) {
                for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
                    if(c instanceof SenseofElegance){
                        if((c.timesUpgraded - 1) >= ((SenseofElegance)c).upgradeHelper){
                            if(((SenseofElegance)c).upgradeHelper == 0) {
                                for (int b : ((SenseofElegance)c).extraEffect) {
                                    if(b == 1) {
                                        ((SenseofElegance)c).upgradeHelper += 2;
                                    }
                                }
                            }
                        }
                        if((((SenseofElegance)c).timesUpgraded - 1) >= ((SenseofElegance)c).upgradeHelper){
                            AbstractDungeon.topLevelEffectsQueue.add(new UpgradeEffectChoose(((SenseofElegance)c)));
                            ((SenseofElegance)c).upgradeHelper = ((((SenseofElegance)c).timesUpgraded + 1) / 2) * 2;
                            ThMod.logger.info("times Upgraded " + ((SenseofElegance)c).timesUpgraded);
                            ThMod.logger.info("upgrade helper " + ((SenseofElegance)c).upgradeHelper);
                        }
                    }
                }
            }
        }
    }
}
