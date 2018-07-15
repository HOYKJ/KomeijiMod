package Thmod.Patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import com.megacrit.cardcrawl.screens.DeathScreen;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SpirePatch(cls="com.megacrit.cardcrawl.screens.DeathScreen", method="<ctor>")
public class DeathScreenPatch
{
    public static final Logger logger = LogManager.getLogger(DeathScreenPatch.class);

    @SpireInsertPatch(rloc=106)
    public static void Insert(DeathScreen _inst, MonsterGroup m)
    {
        logger.info(DeathScreen.STINGER_KEY);
        logger.info(Long.valueOf(DeathScreen.STINGER_ID));
        DeathScreen.STINGER_KEY = "playerScore";
    }
}
