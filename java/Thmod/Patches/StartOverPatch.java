package Thmod.Patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.screens.DeathScreen;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Thmod.ThMod;

@SpirePatch(cls="com.megacrit.cardcrawl.core.CardCrawlGame", method="startOver")
public class StartOverPatch
{
    public static final Logger logger = LogManager.getLogger(StartOverPatch.class);

    @SpireInsertPatch(rloc=0)
    public static void Insert()
    {
        logger.info("=================START OVER=================");
        logger.info(DeathScreen.STINGER_KEY);
        logger.info(Long.valueOf(DeathScreen.STINGER_ID));
        if(ThMod.MusicOpen)
            CardCrawlGame.sound.fadeOut(DeathScreen.STINGER_KEY, DeathScreen.STINGER_ID);
    }
}
