package Thmod.Patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.beyond.AwakenedOne;

import Thmod.ThMod;

@SpirePatch(cls="com.megacrit.cardcrawl.monsters.beyond.AwakenedOne", method="takeTurn")
public class AwakenedOneBGMPatch2
{
    @SpireInsertPatch(rloc=0)
    public static void Insert(AwakenedOne _inst)
    {
        if(ThMod.MusicOpen) {
            if (_inst.nextMove == 3) {
                CardCrawlGame.music.silenceTempBgmInstantly();
                AbstractDungeon.getCurrRoom().playBgmInstantly("TH_BGM_AWAKEN2");
            }
        }
    }
}
