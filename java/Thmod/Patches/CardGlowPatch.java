package Thmod.Patches;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.cardManip.CardGlowBorder;

import java.lang.reflect.Field;

import Thmod.Cards.DeriveCards.AbstractDeriveCards;
import Thmod.Cards.ElementCards.AbstractElementCards;
import Thmod.Cards.ElementCards.SpellCards.AbstractElementSpellCards;
import Thmod.Cards.ItemCards.AbstractItemCards;
import Thmod.Cards.ScarletCard.AbstractRemiriaCards;
import Thmod.Cards.SpellCards.AbstractSpellCards;
import Thmod.Power.ForestBlazePower;
import Thmod.ThMod;
import basemod.helpers.SuperclassFinder;

public class CardGlowPatch
{
    @SpirePatch(cls="com.megacrit.cardcrawl.vfx.cardManip.CardGlowBorder", method="<ctor>",
        paramtypez = {
                AbstractCard.class,
                Color.class
        })
    public static class colorFix
    {
        public static void Postfix(CardGlowBorder _inst, AbstractCard card, Color gColor) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException
        {
            AbstractPlayer p = AbstractDungeon.player;
            Field f = SuperclassFinder.getSuperclassField(CardGlowBorder.class, "color");
            f.setAccessible(true);
            if(card instanceof AbstractDeriveCards){
                f.set(_inst, Color.LIGHT_GRAY.cpy());
            }
            else if((card instanceof AbstractSpellCards) || (card instanceof AbstractElementSpellCards) || (card instanceof AbstractItemCards)){
                f.set(_inst, Color.ORANGE.cpy());
            }
            else if((p != null) && (p.hasPower(ForestBlazePower.POWER_ID)) && (card instanceof AbstractElementCards)){
                f.set(_inst, Color.RED.cpy());
            }
            if((ThMod.cardFeedback) && (card.baseDamage > 0)){
                f.set(_inst, Color.GOLD.cpy());
            }
            if((card instanceof AbstractRemiriaCards) && (((AbstractRemiriaCards) card).isPlus)){
                f.set(_inst, ThMod.Remiria.cpy());
            }
        }
    }
}
