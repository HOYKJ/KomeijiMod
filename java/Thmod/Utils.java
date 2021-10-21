package Thmod;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.screens.CardRewardScreen;
import com.megacrit.cardcrawl.ui.buttons.SingingBowlButton;
import com.megacrit.cardcrawl.ui.buttons.SkipCardButton;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import java.lang.reflect.Method;
import java.util.ArrayList;

import Thmod.Cards.SpellCards.AbstractSpellCards;
import basemod.ReflectionHacks;

public class Utils {
    public static void openCardRewardsScreen(final ArrayList<AbstractCard> cards, final boolean allowSkip,int appearnum) {
        openCardRewardsScreen(cards, allowSkip, appearnum, null);
    }

    public static void openCardRewardsScreen(final ArrayList<AbstractCard> cards, final boolean allowSkip, String text) {
        openCardRewardsScreen(cards, allowSkip, -1, text);
    }

    public static void openCardRewardsScreen(final ArrayList<AbstractCard> cards, final boolean allowSkip,int appearnum, String text) {
        final CardRewardScreen crs = AbstractDungeon.cardRewardScreen;
        crs.rItem = null;
        ReflectionHacks.setPrivate(crs, CardRewardScreen.class, "codex", true);
        ReflectionHacks.setPrivate(crs, CardRewardScreen.class, "draft", false);
        crs.codexCard = null;
        ((SingingBowlButton)ReflectionHacks.getPrivate(crs, CardRewardScreen.class, "bowlButton")).hide();
        if (allowSkip) {
            ((SkipCardButton)ReflectionHacks.getPrivate(crs, CardRewardScreen.class, "skipButton")).show();
        }
        else {
            ((SkipCardButton) ReflectionHacks.getPrivate(crs, CardRewardScreen.class, "skipButton")).hide();
        }
        //crs.onCardSelect = true;
        AbstractDungeon.topPanel.unhoverHitboxes();
        crs.rewardGroup = cards;
        AbstractDungeon.isScreenUp = true;
        AbstractDungeon.screen = AbstractDungeon.CurrentScreen.CARD_REWARD;
        if(appearnum != -1) {
            AbstractDungeon.dynamicBanner.appear(AbstractSpellCards.EXTENDED_DESCRIPTION[appearnum]);
        }
        else {
            AbstractDungeon.dynamicBanner.appear(text);
        }
        AbstractDungeon.overlayMenu.showBlackScreen();
        final float CARD_TARGET_Y = Settings.HEIGHT * 0.45f;
        try {
            final Method method = CardRewardScreen.class.getDeclaredMethod("placeCards", Float.TYPE, Float.TYPE);
            method.setAccessible(true);
            method.invoke(crs, Settings.WIDTH / 2.0f, CARD_TARGET_Y);
        }
        catch (Exception ex) {
        }
        for (final AbstractCard c : cards) {
            UnlockTracker.markCardAsSeen(c.cardID);
        }
    }
}
