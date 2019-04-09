package Thmod.Patches.MusicPatch;

import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import com.megacrit.cardcrawl.screens.DeathScreen;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Thmod.ThMod;

@SpirePatch(cls="com.megacrit.cardcrawl.screens.DeathScreen", method="<ctor>")
public class DeathScreenPatch
{
    public static final Logger logger = LogManager.getLogger(DeathScreenPatch.class);

    public static void Prefix(DeathScreen __instance)
    {
        CardCrawlGame.sound.play("pldead00");
    }

    @SpireInsertPatch(rloc=86, localvars={"bgmKey"})
    public static void Insert(DeathScreen _inst, MonsterGroup m, @ByRef String[] bgmKey)
    {
        if(ThMod.MusicOpen)
            bgmKey[0] = "playerScore.mp3";
    }
}
