package Thmod.Patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.screens.DeathScreen;

import Thmod.ThMod;

@SpirePatch(cls="com.megacrit.cardcrawl.core.CardCrawlGame", method="startOverButShowCredits")
public class StartOverButShowCreditsPatch
{
    @SpireInsertPatch(rloc=0)
    public static void Insert()
    {
        if(ThMod.MusicOpen)
            CardCrawlGame.sound.fadeOut(DeathScreen.STINGER_KEY, DeathScreen.STINGER_ID);
    }
}
