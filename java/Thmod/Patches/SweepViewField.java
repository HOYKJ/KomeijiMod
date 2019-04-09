package Thmod.Patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.screens.SingleCardViewPopup;

@SpirePatch(
        clz=SingleCardViewPopup.class,
        method= SpirePatch.CLASS
)
public class SweepViewField {
    public static SpireField<Boolean> isSweepView = new SpireField<>(() -> false);
    public static SpireField<Boolean> isSweepView2 = new SpireField<>(() -> false);
    public static SpireField<Boolean> twoSweep = new SpireField<>(() -> false);
    public static SpireField<Hitbox> sweepHb = new SpireField<>(() -> null);
    public static SpireField<AbstractCard> temCard = new SpireField<>(() -> null);
}
