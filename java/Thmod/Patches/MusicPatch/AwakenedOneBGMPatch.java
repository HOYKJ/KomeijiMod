package Thmod.Patches.MusicPatch;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.beyond.AwakenedOne;

import Thmod.ThMod;

@SpirePatch(cls="com.megacrit.cardcrawl.monsters.beyond.AwakenedOne", method="usePreBattleAction")
public class AwakenedOneBGMPatch
{
    @SpireInsertPatch(rloc=3)
    public static void Insert(AwakenedOne _inst)
    {
        if(ThMod.MusicOpen) {
            CardCrawlGame.music.silenceTempBgmInstantly();
            AbstractDungeon.getCurrRoom().playBgmInstantly("MysteriousMountain1.mp3");
        }
    }
}
