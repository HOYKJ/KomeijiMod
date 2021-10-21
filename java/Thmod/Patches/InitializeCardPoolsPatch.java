package Thmod.Patches;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.scenes.AbstractScene;

import java.util.ArrayList;

import Thmod.Cards.ScarletCard.AbstractRemiriaFate;
import Thmod.ThMod;

public class InitializeCardPoolsPatch {

    @SpirePatch(
            clz = AbstractDungeon.class,
            method = "initializeCardPools"
    )
    public static class initializeCardPools {
        @SpirePostfixPatch
        public static void postfix(AbstractDungeon _inst) {
            ThMod.fateCardPool.clear();
            ArrayList<AbstractCard> tmpPool = new ArrayList<>();
            AbstractDungeon.player.getCardPool(tmpPool);
            for (AbstractCard card : tmpPool){
                if(card instanceof AbstractRemiriaFate){
                    ThMod.fateCardPool.addToTop(card.makeCopy());
                }
            }
        }
    }
}
