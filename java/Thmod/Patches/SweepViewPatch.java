package Thmod.Patches;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.neow.NeowEvent;
import com.megacrit.cardcrawl.screens.SingleCardViewPopup;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import Thmod.Cards.AbstractSweepCards;
import Thmod.Cards.ElementCards.AbstractElementSweepCards;
import Thmod.ThMod;
import basemod.DevConsole;
import basemod.abstracts.CustomCard;

import static com.megacrit.cardcrawl.screens.SingleCardViewPopup.isViewingUpgrade;

public class SweepViewPatch {

    @SpirePatch(cls="com.megacrit.cardcrawl.screens.SingleCardViewPopup", method="updateUpgradePreview")
    public static class updateSweepView{
        @SpireInsertPatch(rloc=1)
        public static void Insert(SingleCardViewPopup _inst) throws IllegalAccessException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {
            Field card;
            Field upgradeHb;

            card = _inst.getClass().getDeclaredField("card");
            upgradeHb = _inst.getClass().getDeclaredField("upgradeHb");

            card.setAccessible(true);
            upgradeHb.setAccessible(true);

            if ((card.get(_inst) instanceof AbstractSweepCards) || (card.get(_inst) instanceof AbstractElementSweepCards)) {
                if ((((Hitbox) upgradeHb.get(_inst)).hovered) && (InputHelper.justClickedLeft)) {
                    ((Hitbox) upgradeHb.get(_inst)).clickStarted = true;
                }
                if ((((Hitbox) upgradeHb.get(_inst)).clicked) || (CInputActionSet.proceed.isJustPressed())) {
                    CInputActionSet.proceed.unpress();
                    ((Hitbox) upgradeHb.get(_inst)).clicked = false;
                    SweepViewField.isSweepView.set(_inst, !SweepViewField.isSweepView.get(_inst));
                    if(SweepViewField.isSweepView.get(_inst)) {
                        SweepViewField.isSweepView2.set(_inst, false);
                    }

                    Method loadPortraitImg = SingleCardViewPopup.class.getDeclaredMethod("loadPortraitImg");
                    loadPortraitImg.setAccessible(true);
                    loadPortraitImg.invoke(_inst);

                    DevConsole.logger.info("Show Sweep instead Upgrade");
                }
            }
            else{
                SweepViewField.isSweepView.set(_inst, false);
                SweepViewField.isSweepView2.set(_inst, false);
            }

            if(SweepViewField.sweepHb.get(_inst) != null) {
                SweepViewField.sweepHb.get(_inst).update();
                if ((SweepViewField.sweepHb.get(_inst).hovered) && (InputHelper.justClickedLeft)) {
                    SweepViewField.sweepHb.get(_inst).clickStarted = true;
                }
                if ((SweepViewField.sweepHb.get(_inst).clicked) || (CInputActionSet.proceed.isJustPressed())) {
                    CInputActionSet.proceed.unpress();
                    SweepViewField.sweepHb.get(_inst).clicked = false;
                    SweepViewField.isSweepView2.set(_inst, !SweepViewField.isSweepView2.get(_inst));
                    if(SweepViewField.isSweepView2.get(_inst)) {
                        SweepViewField.isSweepView.set(_inst, false);
                    }

                    Method loadPortraitImg = SingleCardViewPopup.class.getDeclaredMethod("loadPortraitImg");
                    loadPortraitImg.setAccessible(true);
                    loadPortraitImg.invoke(_inst);

                    DevConsole.logger.info("Show Sweep2 instead Upgrade");
                }
            }
        }
    }

    @SpirePatch(cls="com.megacrit.cardcrawl.screens.SingleCardViewPopup", method="renderUpgradeViewToggle")
    public static class renderToggle1{
        @SpireInsertPatch(rloc=2)
        public static SpireReturn Insert(SingleCardViewPopup _inst, SpriteBatch sb) throws IllegalAccessException, NoSuchFieldException {
            final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("CreatePanel");
            final String[] TEXT = uiStrings.TEXT;
            Field card;
            Field upgradeHb;

            card = _inst.getClass().getDeclaredField("card");
            upgradeHb = _inst.getClass().getDeclaredField("upgradeHb");

            card.setAccessible(true);
            upgradeHb.setAccessible(true);

            if ((card.get(_inst) instanceof AbstractSweepCards) || (card.get(_inst) instanceof AbstractElementSweepCards)) {
                if (((Hitbox) upgradeHb.get(_inst)).hovered) {
                    FontHelper.renderFont(sb, FontHelper.cardTitleFont, TEXT[18], ((Hitbox) upgradeHb.get(_inst)).cX - 45.0F * Settings.scale, ((Hitbox) upgradeHb.get(_inst)).cY + 10.0F * Settings.scale, Settings.BLUE_TEXT_COLOR);
                } else {
                    FontHelper.renderFont(sb, FontHelper.cardTitleFont, TEXT[18], ((Hitbox) upgradeHb.get(_inst)).cX - 45.0F * Settings.scale, ((Hitbox) upgradeHb.get(_inst)).cY + 10.0F * Settings.scale, Settings.GOLD_COLOR);
                }
                if (SweepViewField.isSweepView.get(_inst)) {
                    sb.setColor(Color.WHITE);
                    sb.draw(ImageMaster.TICK, ((Hitbox) upgradeHb.get(_inst)).cX - 80.0F * Settings.scale - 32.0F, ((Hitbox) upgradeHb.get(_inst)).cY - 32.0F, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
                }
                ((Hitbox) upgradeHb.get(_inst)).render(sb);

                if(SweepViewField.sweepHb.get(_inst) != null){
                    sb.draw(ImageMaster.CHECKBOX, SweepViewField.sweepHb.get(_inst).cX - 80.0F * Settings.scale - 32.0F, SweepViewField.sweepHb.get(_inst).cY - 32.0F, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
                    if (SweepViewField.sweepHb.get(_inst).hovered) {
                        FontHelper.renderFont(sb, FontHelper.cardTitleFont, TEXT[19], SweepViewField.sweepHb.get(_inst).cX - 45.0F * Settings.scale, SweepViewField.sweepHb.get(_inst).cY + 10.0F * Settings.scale, Settings.BLUE_TEXT_COLOR);
                    } else {
                        FontHelper.renderFont(sb, FontHelper.cardTitleFont, TEXT[19], SweepViewField.sweepHb.get(_inst).cX - 45.0F * Settings.scale, SweepViewField.sweepHb.get(_inst).cY + 10.0F * Settings.scale, Settings.GOLD_COLOR);
                    }
                    if (SweepViewField.isSweepView2.get(_inst)) {
                        sb.setColor(Color.WHITE);
                        sb.draw(ImageMaster.TICK, SweepViewField.sweepHb.get(_inst).cX - 80.0F * Settings.scale - 32.0F, SweepViewField.sweepHb.get(_inst).cY - 32.0F, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
                    }
                    SweepViewField.sweepHb.get(_inst).render(sb);
                }
                return SpireReturn.Return(null);
            }

            return SpireReturn.Continue();
        }
    }

    @SpirePatch(cls="com.megacrit.cardcrawl.screens.SingleCardViewPopup", method="close")
    public static class close{
        public static void Prefix(SingleCardViewPopup _inst){
            SweepViewField.isSweepView.set(_inst, false);
            SweepViewField.isSweepView2.set(_inst, false);
        }
    }

    @SpirePatch(clz=SingleCardViewPopup.class,
            method="open",
            paramtypez={
                    AbstractCard.class,
                    CardGroup.class,
            })
    public static class openpatch{
        public static void Postfix(SingleCardViewPopup _inst, AbstractCard card, CardGroup group) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
            Method m = SingleCardViewPopup.class.getDeclaredMethod("canToggleBetaArt");
            m.setAccessible(true);
            Method me = SingleCardViewPopup.class.getDeclaredMethod("allowUpgradePreview");
            me.setAccessible(true);

            Field upgradeHb;
            Field betaArtHb;

            upgradeHb = _inst.getClass().getDeclaredField("upgradeHb");
            betaArtHb = _inst.getClass().getDeclaredField("betaArtHb");

            upgradeHb.setAccessible(true);
            betaArtHb.setAccessible(true);

            if (card instanceof AbstractSweepCards) {
                ArrayList<AbstractSweepCards> oppo = ((AbstractSweepCards)card).getOpposite();
                if(oppo.size() > 1){
                    SweepViewField.twoSweep.set(_inst, true);
                }
                else {
                    SweepViewField.twoSweep.set(_inst, false);
                }
            }

            if(card instanceof AbstractElementSweepCards){
                ArrayList<AbstractElementSweepCards> oppo = ((AbstractElementSweepCards)card).getOpposite();
                if(oppo.size() > 1){
                    SweepViewField.twoSweep.set(_inst, true);
                }
                else {
                    SweepViewField.twoSweep.set(_inst, false);
                }
            }

            DevConsole.logger.info("card name:" + card.name);

            if(SweepViewField.twoSweep.get(_inst)) {
                SweepViewField.sweepHb.set(_inst, new Hitbox(250.0F * Settings.scale, 80.0F * Settings.scale));
                if ((boolean) m.invoke(_inst)) {
                    if ((boolean) me.invoke(_inst)) {
                        ((Hitbox) betaArtHb.get(_inst)).move(Settings.WIDTH / 2.0F + 300.0F * Settings.scale, 70.0F * Settings.scale);
                        SweepViewField.sweepHb.get(_inst).move(Settings.WIDTH / 2.0F, 70.0F * Settings.scale);
                        ((Hitbox) upgradeHb.get(_inst)).move(Settings.WIDTH / 2.0F - 250.0F * Settings.scale, 70.0F * Settings.scale);
                    } else {
                        ((Hitbox) betaArtHb.get(_inst)).move(Settings.WIDTH / 2.0F, 70.0F * Settings.scale);
                    }
                } else {
                    ((Hitbox) upgradeHb.get(_inst)).move(Settings.WIDTH / 2.0F - 180.0F * Settings.scale, 70.0F * Settings.scale);
                    SweepViewField.sweepHb.get(_inst).move(Settings.WIDTH / 2.0F + 270.0F * Settings.scale, 70.0F * Settings.scale);
                }
            }
            else {
                SweepViewField.sweepHb.set(_inst,null);
                if ((boolean) m.invoke(_inst)) {
                    if ((boolean) me.invoke(_inst)) {
                        ((Hitbox) betaArtHb.get(_inst)).move(Settings.WIDTH / 2.0F + 270.0F * Settings.scale, 70.0F * Settings.scale);
                        ((Hitbox) upgradeHb.get(_inst)).move(Settings.WIDTH / 2.0F - 180.0F * Settings.scale, 70.0F * Settings.scale);
                    } else {
                        ((Hitbox) betaArtHb.get(_inst)).move(Settings.WIDTH / 2.0F, 70.0F * Settings.scale);
                    }
                } else {
                    ((Hitbox) upgradeHb.get(_inst)).move(Settings.WIDTH / 2.0F, 70.0F * Settings.scale);
                }
            }
        }
    }

    @SpirePatch(clz=SingleCardViewPopup.class,
            method="open",
            paramtypez={
                    AbstractCard.class
            })
    public static class openpatch2{
        public static void Postfix(SingleCardViewPopup _inst, AbstractCard card) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
            Method m = SingleCardViewPopup.class.getDeclaredMethod("canToggleBetaArt");
            m.setAccessible(true);
            Method me = SingleCardViewPopup.class.getDeclaredMethod("allowUpgradePreview");
            me.setAccessible(true);

            Field upgradeHb;
            Field betaArtHb;

            upgradeHb = _inst.getClass().getDeclaredField("upgradeHb");
            betaArtHb = _inst.getClass().getDeclaredField("betaArtHb");

            upgradeHb.setAccessible(true);
            betaArtHb.setAccessible(true);

            if (card instanceof AbstractSweepCards) {
                ArrayList<AbstractSweepCards> oppo = ((AbstractSweepCards)card).getOpposite();
                if(oppo.size() > 1){
                    SweepViewField.twoSweep.set(_inst, true);
                }
                else {
                    SweepViewField.twoSweep.set(_inst, false);
                }
            }

            if(card instanceof AbstractElementSweepCards){
                ArrayList<AbstractElementSweepCards> oppo = ((AbstractElementSweepCards)card).getOpposite();
                if(oppo.size() > 1){
                    SweepViewField.twoSweep.set(_inst, true);
                }
                else {
                    SweepViewField.twoSweep.set(_inst, false);
                }
            }

            DevConsole.logger.info("card name:" + card.name);

            if(SweepViewField.twoSweep.get(_inst)) {
                SweepViewField.sweepHb.set(_inst, new Hitbox(250.0F * Settings.scale, 80.0F * Settings.scale));
                if ((boolean) m.invoke(_inst)) {
                    if ((boolean) me.invoke(_inst)) {
                        ((Hitbox) betaArtHb.get(_inst)).move(Settings.WIDTH / 2.0F + 300.0F * Settings.scale, 70.0F * Settings.scale);
                        SweepViewField.sweepHb.get(_inst).move(Settings.WIDTH / 2.0F, 70.0F * Settings.scale);
                        ((Hitbox) upgradeHb.get(_inst)).move(Settings.WIDTH / 2.0F - 250.0F * Settings.scale, 70.0F * Settings.scale);
                    } else {
                        ((Hitbox) betaArtHb.get(_inst)).move(Settings.WIDTH / 2.0F, 70.0F * Settings.scale);
                    }
                } else {
                    ((Hitbox) upgradeHb.get(_inst)).move(Settings.WIDTH / 2.0F - 180.0F * Settings.scale, 70.0F * Settings.scale);
                    SweepViewField.sweepHb.get(_inst).move(Settings.WIDTH / 2.0F + 270.0F * Settings.scale, 70.0F * Settings.scale);
                }
            }
            else {
                SweepViewField.sweepHb.set(_inst, null);
                if ((boolean) m.invoke(_inst)) {
                    if ((boolean) me.invoke(_inst)) {
                        ((Hitbox) betaArtHb.get(_inst)).move(Settings.WIDTH / 2.0F + 270.0F * Settings.scale, 70.0F * Settings.scale);
                        ((Hitbox) upgradeHb.get(_inst)).move(Settings.WIDTH / 2.0F - 180.0F * Settings.scale, 70.0F * Settings.scale);
                    } else {
                        ((Hitbox) betaArtHb.get(_inst)).move(Settings.WIDTH / 2.0F, 70.0F * Settings.scale);
                    }
                } else {
                    ((Hitbox) upgradeHb.get(_inst)).move(Settings.WIDTH / 2.0F, 70.0F * Settings.scale);
                }
            }
        }
    }

    @SpirePatch(cls="com.megacrit.cardcrawl.screens.SingleCardViewPopup", method="updateInput")
    public static class updateInput{
        @SpireInsertPatch(rloc=12)
        public static SpireReturn Insert(SingleCardViewPopup _inst) throws NoSuchMethodException, NoSuchFieldException, InvocationTargetException, IllegalAccessException {
            Method openPrev = SingleCardViewPopup.class.getDeclaredMethod("openPrev");
            openPrev.setAccessible(true);
            Method openNext = SingleCardViewPopup.class.getDeclaredMethod("openNext");
            openNext.setAccessible(true);

            Field upgradeHb;
            Field betaArtHb;
            Field cardHb;
            Field prevCard;
            Field nextCard;

            upgradeHb = _inst.getClass().getDeclaredField("upgradeHb");
            betaArtHb = _inst.getClass().getDeclaredField("betaArtHb");
            cardHb = _inst.getClass().getDeclaredField("cardHb");
            prevCard = _inst.getClass().getDeclaredField("prevCard");
            nextCard = _inst.getClass().getDeclaredField("nextCard");

            upgradeHb.setAccessible(true);
            betaArtHb.setAccessible(true);
            cardHb.setAccessible(true);
            prevCard.setAccessible(true);
            nextCard.setAccessible(true);

            if (InputHelper.justReleasedClickLeft)
            {
                if ((!((Hitbox) cardHb.get(_inst)).hovered) && (!((Hitbox) upgradeHb.get(_inst)).hovered) &&
                        (((betaArtHb.get(_inst)) == null) || ((betaArtHb.get(_inst) != null) && (!((Hitbox) betaArtHb.get(_inst)).hovered))) &&
                        ((SweepViewField.sweepHb.get(_inst) == null) || ((SweepViewField.sweepHb.get(_inst) != null) && (!SweepViewField.sweepHb.get(_inst).hovered)))) {
                    _inst.close();
                }
            }
            else if ((InputHelper.pressedEscape) || (CInputActionSet.cancel.isJustPressed()))
            {
                CInputActionSet.cancel.unpress();
                InputHelper.pressedEscape = false;
                _inst.close();
            }
            if ((prevCard.get(_inst) != null) && (InputActionSet.left.isJustPressed())) {
                openPrev.invoke(_inst);
            } else if ((nextCard.get(_inst) != null) && (InputActionSet.right.isJustPressed())) {
                openNext.invoke(_inst);
            }
            return SpireReturn.Return(null);
        }
    }

    @SpirePatch(cls="com.megacrit.cardcrawl.screens.SingleCardViewPopup", method="render")
    public static class renderPatch{
        public static void Prefix(SingleCardViewPopup _inst, SpriteBatch sb) throws NoSuchFieldException, IllegalAccessException {
            Field card;
            card = _inst.getClass().getDeclaredField("card");
            card.setAccessible(true);

            if(SweepViewField.isSweepView.get(_inst)){
                SweepViewField.temCard.set(_inst,(AbstractCard)card.get(_inst));
                if(card.get(_inst) instanceof AbstractSweepCards){
                    ArrayList<AbstractSweepCards> cg = ((AbstractSweepCards)card.get(_inst)).getOpposite();
                    card.set(_inst, cg.get(0));
                }

                if(card.get(_inst) instanceof AbstractElementSweepCards){
                    ArrayList<AbstractElementSweepCards> cg = ((AbstractElementSweepCards)card.get(_inst)).getOpposite();
                    card.set(_inst, cg.get(0));
                }
            }

            if(SweepViewField.isSweepView2.get(_inst)){
                SweepViewField.temCard.set(_inst,(AbstractCard)card.get(_inst));

                if(card.get(_inst) instanceof AbstractSweepCards){
                    ArrayList<AbstractSweepCards> cg = ((AbstractSweepCards)card.get(_inst)).getOpposite();
                    card.set(_inst, cg.get(1));
                }

                if(card.get(_inst) instanceof AbstractElementSweepCards){
                    ArrayList<AbstractElementSweepCards> cg = ((AbstractElementSweepCards)card.get(_inst)).getOpposite();
                    card.set(_inst, cg.get(1));
                }
            }
        }

        public static void Postfix(SingleCardViewPopup _inst, SpriteBatch sb) throws NoSuchFieldException, IllegalAccessException {
            Field card;
            card = _inst.getClass().getDeclaredField("card");
            card.setAccessible(true);

            if(SweepViewField.temCard.get(_inst) != null){
                card.set(_inst, SweepViewField.temCard.get(_inst));
                SweepViewField.temCard.set(_inst, null);
            }
        }
    }

    @SpirePatch(cls="com.megacrit.cardcrawl.screens.SingleCardViewPopup", method="loadPortraitImg")
    public static class loadPortraitImg{
        public static void Postfix(SingleCardViewPopup _inst) throws NoSuchFieldException, IllegalAccessException {
            Field portraitImg;
            portraitImg = _inst.getClass().getDeclaredField("portraitImg");
            portraitImg.setAccessible(true);
            Field card;
            card = _inst.getClass().getDeclaredField("card");
            card.setAccessible(true);

            if(SweepViewField.isSweepView.get(_inst)) {
                if(card.get(_inst) instanceof AbstractSweepCards) {
                    ArrayList<AbstractSweepCards> cg = ((AbstractSweepCards) card.get(_inst)).getOpposite();
                    int endingIndex = ThMod.komeijiCardImage(cg.get(0).cardID,false).lastIndexOf(".");
                    String newPath = (ThMod.komeijiCardImage(cg.get(0).cardID,false).substring(0, endingIndex) + "_p" + ThMod.komeijiCardImage(cg.get(0).cardID,false).substring(endingIndex));
                    //DevConsole.logger.info("newPath "+ newPath);
                    portraitImg.set(_inst, ImageMaster.loadImage(newPath));
                }
                else {
                    ArrayList<AbstractElementSweepCards> cg = ((AbstractElementSweepCards) card.get(_inst)).getOpposite();
                    int endingIndex = ThMod.komeijiCardImage(cg.get(0).cardID,false).lastIndexOf(".");
                    String newPath = (ThMod.komeijiCardImage(cg.get(0).cardID,false).substring(0, endingIndex) + "_p" + ThMod.komeijiCardImage(cg.get(0).cardID,false).substring(endingIndex));
                    portraitImg.set(_inst, ImageMaster.loadImage(newPath));
                }
            }

            if(SweepViewField.isSweepView2.get(_inst)) {
                if(card.get(_inst) instanceof AbstractSweepCards) {
                    ArrayList<AbstractSweepCards> cg = ((AbstractSweepCards) card.get(_inst)).getOpposite();
                    int endingIndex = ThMod.komeijiCardImage(cg.get(1).cardID,false).lastIndexOf(".");
                    String newPath = (ThMod.komeijiCardImage(cg.get(1).cardID,false).substring(0, endingIndex) + "_p" + ThMod.komeijiCardImage(cg.get(1).cardID,false).substring(endingIndex));
                    portraitImg.set(_inst, ImageMaster.loadImage(newPath));
                }
                else {
                    ArrayList<AbstractElementSweepCards> cg = ((AbstractElementSweepCards) card.get(_inst)).getOpposite();
                    int endingIndex = ThMod.komeijiCardImage(cg.get(1).cardID,false).lastIndexOf(".");
                    String newPath = (ThMod.komeijiCardImage(cg.get(1).cardID,false).substring(0, endingIndex) + "_p" + ThMod.komeijiCardImage(cg.get(1).cardID,false).substring(endingIndex));
                    portraitImg.set(_inst, ImageMaster.loadImage(newPath));
                }
            }
        }
    }
}
