package Thmod.Patches.RemiriaPatch;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.lang.reflect.Field;

import Thmod.Cards.ScarletCard.uncommonCards.NightDance;

public class UsedCardPatch {
    @SpirePatch(
            clz= UseCardAction.class,
            method="update"
    )
    public static class updatePatch {
        @SpireInsertPatch(rloc=1)
        public static void Insert(UseCardAction _inst) throws NoSuchFieldException, IllegalAccessException {
            Field targetCard;
            //Field duration;
            targetCard = _inst.getClass().getDeclaredField("targetCard");
            //duration = _inst.getClass().getDeclaredField("duration");
            targetCard.setAccessible(true);
            //duration.setAccessible(true);


                for (AbstractCard card : AbstractDungeon.player.hand.group) {
                    if (card instanceof NightDance) {
                        ((NightDance) card).onUsedCard((AbstractCard) targetCard.get(_inst));
                    }
                }

        }
    }
}
