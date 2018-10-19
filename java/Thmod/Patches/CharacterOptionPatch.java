package Thmod.Patches;

import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.screens.charSelect.CharacterOption;
import com.megacrit.cardcrawl.screens.custom.CustomModeCharacterButton;

import Thmod.Characters.KomeijiSatori;
import Thmod.ThMod;
import basemod.DevConsole;

@SpirePatch(cls="com.megacrit.cardcrawl.screens.custom.CustomModeCharacterButton", method="updateHitbox")
public class CharacterOptionPatch {
    @SpireInsertPatch(rloc=0)
    public static void Postfix(final CustomModeCharacterButton obj_instance) {
        AbstractPlayer ch = obj_instance.c;
//        if(ch instanceof KomeijiSatori) {
//            if (ThMod.SoundOpen)
//                CardCrawlGame.sound.playA("select00", MathUtils.random(-0.2F, 0.2F));
//        }
    }
}
