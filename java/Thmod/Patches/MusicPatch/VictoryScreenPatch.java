package Thmod.Patches.MusicPatch;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import com.megacrit.cardcrawl.screens.VictoryScreen;

@SpirePatch(cls="com.megacrit.cardcrawl.screens.VictoryScreen", method="<ctor>")
public class VictoryScreenPatch
{
    @SpireInsertPatch(rloc=32)
    public static void Insert(VictoryScreen _inst, MonsterGroup m)
    {
        CardCrawlGame.music.fadeOutTempBGM();
        CardCrawlGame.music.playTempBgmInstantly("蒸汽机关.mp3", false);
    }
}
