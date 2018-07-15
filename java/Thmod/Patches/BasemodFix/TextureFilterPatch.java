package Thmod.Patches.BasemodFix;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.screens.charSelect.CharacterOption;

import java.util.ArrayList;
import java.util.HashMap;

import basemod.abstracts.CustomPlayer;

public class TextureFilterPatch {
    @SpirePatch(cls="basemod.abstracts.CustomPlayer", method="buildCustomOrb")
    public static class buildCustomOrbFix
    {
        @SpireInsertPatch(rloc=5, localvars={"energyLayers", "orbVfx"})
        public static void Insert(CustomPlayer _inst, String[] orbTextures, String orbVfxPath, ArrayList<Texture> energyLayers, Texture orbVfx)
        {
            for (Texture t : energyLayers) {
                t.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            }
            orbVfx.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }
    }

    @SpirePatch(cls="com.megacrit.cardcrawl.screens.charSelect.CharacterOption", method="updateHitbox")
    public static class UpdateHitboxBgImgFix
    {
        @SpireInsertPatch(rloc=40)
        public static void Insert(CharacterOption _inst)
        {
            CardCrawlGame.mainMenuScreen.charSelectScreen.bgCharImg.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }
    }

    @SpirePatch(cls="basemod.abstracts.CustomCard", method="loadTextureFromString")
    public static class loadTextureFromStringFix
    {
        @SpireInsertPatch(rloc=2, localvars={"imgMap"})
        public static void Insert(String textureString, HashMap<String, Texture> imgMap)
        {
            (imgMap.get(textureString)).setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }
    }
}
