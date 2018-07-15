package Thmod.Patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.beyond.Deca;

import Thmod.ThMod;

@SpirePatch(cls="com.megacrit.cardcrawl.monsters.beyond.Deca", method="usePreBattleAction")
public class DecaBGMPatch {
    @SpireInsertPatch(rloc=3)
    public static void Insert(Deca _inst)
    {
        if(ThMod.MusicOpen) {
            CardCrawlGame.music.silenceTempBgmInstantly();
            AbstractDungeon.getCurrRoom().playBgmInstantly("TH_BGM_DECADONU");
        }
    }
}
