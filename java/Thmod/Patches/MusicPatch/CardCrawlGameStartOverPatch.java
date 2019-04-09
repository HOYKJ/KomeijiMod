package Thmod.Patches.MusicPatch;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SpirePatch(cls="com.megacrit.cardcrawl.core.CardCrawlGame", method="startOver")
public class CardCrawlGameStartOverPatch
{
    public static final Logger logger = LogManager.getLogger(CardCrawlGameStartOverPatch.class);

    @SpireInsertPatch(rloc=0)
    public static void Insert()
    {
        CardCrawlGame.music.fadeOutTempBGM();
    }
}
