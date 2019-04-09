package Thmod.Patches.MusicPatch;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.beyond.TimeEater;

import Thmod.ThMod;

@SpirePatch(cls="com.megacrit.cardcrawl.monsters.beyond.TimeEater", method="usePreBattleAction")
public class TimeEaterBGMPatch
{
    @SpireInsertPatch(rloc=10)
    public static void Insert(TimeEater _inst)
    {
        if(ThMod.MusicOpen) {
            CardCrawlGame.music.silenceTempBgmInstantly();
            AbstractDungeon.getCurrRoom().playBgmInstantly("LunaDial.mp3");
        }
    }
}
