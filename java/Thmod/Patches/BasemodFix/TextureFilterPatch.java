package Thmod.Patches.BasemodFix;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.screens.charSelect.CharacterOption;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

import basemod.abstracts.CustomCard;
import basemod.abstracts.CustomPlayer;

public class TextureFilterPatch {

    @SpirePatch(cls="basemod.abstracts.CustomPlayer",
            method="buildCustomOrb"
    )

    public static class buildCustomOrbFix{

        @SpireInsertPatch(
                rloc=5,
                localvars={"energyLayers","orbVfx"}
        )

        public static void Insert(CustomPlayer _inst, String[] orbTextures, String orbVfxPath, ArrayList<Texture> energyLayers, Texture orbVfx) {

            for(Texture t : energyLayers) {
                t.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            }
            orbVfx.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }
    }

    @SpirePatch(cls="com.megacrit.cardcrawl.screens.charSelect.CharacterOption",
            method="updateHitbox"
    )

    public static class UpdateHitboxBgImgFix{

        @SpireInsertPatch(
                rloc=40
        )

        public static void Insert(CharacterOption _inst) {
            CardCrawlGame.mainMenuScreen.charSelectScreen.bgCharImg.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }

    }

    @SpirePatch(cls="com.megacrit.cardcrawl.screens.charSelect.CharacterOption",
            method="ctor",
            paramtypes={"java.lang.String", "com.megacrit.cardcrawl.characters.AbstractPlayer$PlayerClass", "java.lang.String", "java.lang.String"}
    )

    public static class ButtonImageFix{
        @SpireInsertPatch(
                rloc=32
        )

        public static void Insert(Object __obj_instance, String optionName, Object cObj, String buttonUrl, String portraiImg) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException
        {
            Field buttonImgField = CharacterOption.class.getDeclaredField("buttonImg");
            buttonImgField.setAccessible(true);
            ((Texture)buttonImgField.get(__obj_instance)).setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }
    }

    @SpirePatch(cls="basemod.abstracts.CustomCard",
            method="loadTextureFromString"
    )

    public static class loadTextureFromStringFix{

		  /*
		  @SpireInsertPatch(
					rloc=2,
					localvars={"imgMap"}
				)


		  public static void Insert(String textureString, HashMap<String, Texture> imgMap) {
			  imgMap.get(textureString).setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
			  */

        public static void PostFix(String textureString) {
            Texture t = CustomCard.imgMap.get(textureString);
            if(t==null)return;
            t.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            CustomCard.imgMap.put(textureString, t);
        }

    }

    @SpirePatch(cls="basemod.abstracts.CustomCard",
            method="getPortraitImage"
    )

    public static class getPortraitImageFix{

        @SpireInsertPatch(
                rloc=13,
                localvars={"portraitTexture"}
        )

        public static void Insert(CustomCard card, Texture portraitTexture) {
            if(portraitTexture==null)return;
            portraitTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }
    }

    @SpirePatch(cls="basemod.BaseMod",
            method="saveEnergyOrbTexture"
    )

    public static class saveEnergyOrbFix{
        @SpireInsertPatch(
                rloc=0
        )

        public static void Insert(final String color, final Texture tex) {
            tex.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }
    }

    @SpirePatch(cls="basemod.BaseMod",
            method="saveEnergyOrbPortraitTexture"
    )

    public static class saveEnergyOrbPortraitFix{
        @SpireInsertPatch(
                rloc=0
        )

        public static void Insert(final String color, final Texture tex) {
            tex.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }
    }
}
