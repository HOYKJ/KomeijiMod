package Thmod.Patches;

import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.screens.charSelect.CharacterOption;

import Thmod.ThMod;
import basemod.DevConsole;

@SpirePatch(cls="com.megacrit.cardcrawl.screens.charSelect.CharacterOption", method="updateHitbox")
public class CharacterOptionPatch {
    @SpireInsertPatch(rloc = 64)
    public static void Insert(final CharacterOption obj_instance) {
        AbstractPlayer.PlayerClass ch = obj_instance.c;
        switch (ch.ordinal()) {
            case 3:
                if(ThMod.SoundOpen)
                    CardCrawlGame.sound.playA("select00", MathUtils.random(-0.2F, 0.2F));
                CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, false);
                break;
        }
    }
}
