package Thmod.Patches.MusicPatch;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.beyond.AwakenedOne;

import Thmod.ThMod;


@SpirePatch(cls="com.megacrit.cardcrawl.monsters.beyond.AwakenedOne", method="damage")
public class AwakenedOneBGMStop {

    @SpireInsertPatch(rloc=11)
    public static void Insert(AwakenedOne _inst, DamageInfo info)
    {
        if(ThMod.MusicOpen) {
            if ((_inst.currentHealth <= 0) && (!_inst.halfDead)) {
                CardCrawlGame.music.silenceTempBgmInstantly();
            }
        }
    }
}
