package Thmod.Patches.CrystalPatch;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rewards.RewardItem;

import java.util.ArrayList;

import Thmod.Relics.CrystalOfMemory;

public class CardRewardPatch {
    @SpirePatch(
            clz = AbstractDungeon.class,
            method = "getColorlessRewardCards"
    )
    public static class getColorlessRewardCards {
        @SpireInsertPatch(rloc = 6, localvars={"numCards"})
        public static void Insert(@ByRef int[] numCards) {
            if(AbstractDungeon.player.hasRelic(CrystalOfMemory.ID)){
                if (((CrystalOfMemory) AbstractDungeon.player.getRelic(CrystalOfMemory.ID)).number[0] == 1){
                    numCards[0] += 1;
                }
            }
        }
    }

    @SpirePatch(
            clz = AbstractDungeon.class,
            method = "getRewardCards"
    )
    public static class getRewardCards {
        @SpireInsertPatch(rloc = 6, localvars={"numCards"})
        public static void Insert(@ByRef int[] numCards) {
            if(AbstractDungeon.player.hasRelic(CrystalOfMemory.ID)){
                if (((CrystalOfMemory) AbstractDungeon.player.getRelic(CrystalOfMemory.ID)).number[0] == 1){
                    numCards[0] += 1;
                }
            }
        }
    }

    @SpirePatch(
            clz=RewardItem.class,
            method="claimReward"
    )
    public static class claimReward
    {
        @SpireInsertPatch(rloc=54)
        public static void Insert(RewardItem _inst)
        {
            if(AbstractDungeon.player.hasRelic(CrystalOfMemory.ID)){
                if (((CrystalOfMemory) AbstractDungeon.player.getRelic(CrystalOfMemory.ID)).number[0] == 1){
                    AbstractDungeon.player.getRelic(CrystalOfMemory.ID).flash();
                }
            }
        }
    }
}
