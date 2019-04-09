package Thmod.Patches.EventPatch;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.city.TheLibrary;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import java.lang.reflect.Field;

import Thmod.Cards.RewardCards.IdRelease;
import Thmod.Cards.RewardCards.MeltDown;
import Thmod.Cards.RewardCards.NeedleMountain;
import Thmod.Characters.KomeijiSatori;

public class TheLibraryPatch {
    @SpirePatch(
            clz = TheLibrary.class,
            method = "update"
    )
    public static class update {
        @SpireInsertPatch(rloc = 1)
        public static void Insert(TheLibrary _inst) throws NoSuchFieldException, IllegalAccessException {
            if ((AbstractDungeon.player != null) && (AbstractDungeon.player instanceof KomeijiSatori)) {
                final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("TheLibraryPatch");
                final String[] TEXT = uiStrings.TEXT;
                Field screenNum;
                screenNum = _inst.getClass().getDeclaredField("screenNum");
                screenNum.setAccessible(true);
                if ((TheLibraryField.isAddOption.get(_inst)) && ((int) screenNum.get(_inst) == 0)) {
                    _inst.imageEventText.setDialogOption(TEXT[3]);
                    TheLibraryField.isAddOption.set(_inst, false);
                }
            }
        }
    }

    @SpirePatch(
            clz = TheLibrary.class,
            method = "buttonEffect"
    )
    public static class buttonEffect {
        @SpireInsertPatch(rloc = 0)
        public static SpireReturn Insert(TheLibrary _inst, int buttonPressed) throws NoSuchFieldException, IllegalAccessException {
            if ((AbstractDungeon.player != null) && (AbstractDungeon.player instanceof KomeijiSatori)) {
                final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("TheLibraryPatch");
                final String[] TEXT = uiStrings.TEXT;
                Field screenNum;
                Field healAmt;
                Field pickCard;

                screenNum = _inst.getClass().getDeclaredField("screenNum");
                healAmt = _inst.getClass().getDeclaredField("healAmt");
                pickCard = _inst.getClass().getDeclaredField("pickCard");

                screenNum.setAccessible(true);
                healAmt.setAccessible(true);
                pickCard.setAccessible(true);

                switch ((int) screenNum.get(_inst)) {
                    case 0:
                        if (buttonPressed == 2) {
                            screenNum.set(_inst, 2);
                            _inst.imageEventText.updateBodyText(TEXT[0]);
                            _inst.imageEventText.updateDialogOption(0, TEXT[4]);
                            _inst.imageEventText.clearRemainingOptions();
                            _inst.imageEventText.setDialogOption(TEXT[5]);
                            return SpireReturn.Return(null);
                        }
                        break;
                    case 2:
                        switch (buttonPressed) {
                            case 0:
                                screenNum.set(_inst, 3);
                                _inst.imageEventText.updateBodyText(TEXT[1]);
                                _inst.imageEventText.updateDialogOption(0, TEXT[6]);
                                _inst.imageEventText.clearRemainingOptions();
                                return SpireReturn.Return(null);
                            case 1:
                                screenNum.set(_inst, 0);
                                _inst.imageEventText.updateBodyText(TEXT[9]);
                                _inst.imageEventText.updateDialogOption(0, TheLibrary.OPTIONS[0]);
                                _inst.imageEventText.updateDialogOption(1, TheLibrary.OPTIONS[1] + healAmt.get(_inst) + TheLibrary.OPTIONS[2]);
                                break;
                        }
                        break;
                    case 3:
                        screenNum.set(_inst, 1);
                        _inst.imageEventText.updateBodyText(TEXT[2]);
                        _inst.imageEventText.updateDialogOption(0, TEXT[7]);
                        _inst.imageEventText.clearRemainingOptions();
                        AbstractDungeon.player.decreaseMaxHealth(5);
                        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.MED, false);
                        CardCrawlGame.sound.play("BLUNT_FAST");
                        pickCard.set(_inst, true);
                        CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
                        group.addToTop(new MeltDown());
                        group.addToTop(new IdRelease());
                        group.addToTop(new NeedleMountain());
                        for (AbstractCard c : group.group) {
                            UnlockTracker.markCardAsSeen(c.cardID);
                        }
                        AbstractDungeon.gridSelectScreen.open(group, 1, TEXT[8], false);
                        return SpireReturn.Return(null);
                }

                if ((int) screenNum.get(_inst) == 1) {
                    TheLibraryField.isAddOption.set(_inst, true);
                }
            }
            return SpireReturn.Continue();
        }
    }

}
