package Thmod.Patches;

import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import com.megacrit.cardcrawl.screens.DeathScreen;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SpirePatch(cls="com.megacrit.cardcrawl.screens.DeathScreen", method="<ctor>")
public class DeathScreenPatch
{
    public static final Logger logger = LogManager.getLogger(DeathScreenPatch.class);

    @SpireInsertPatch(rloc=86, localvars={"bgmKey"})
    public static void Insert(DeathScreen _inst, MonsterGroup m, @ByRef String[] bgmKey)
    {
        bgmKey[0] = "playerScore.mp3";
    }
}
