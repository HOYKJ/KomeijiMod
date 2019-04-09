package Thmod.Patches.MusicPatch;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;

@SpirePatch(cls="com.megacrit.cardcrawl.core.CardCrawlGame", method="startOverButShowCredits")
public class CardCrawlGameStartOverButShowCreditsPatch
{
    @SpireInsertPatch(rloc=0)
    public static void Insert()
    {
        CardCrawlGame.music.fadeOutTempBGM();
    }
}
