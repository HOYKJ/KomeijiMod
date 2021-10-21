package Thmod.Patches.RemiriaPatch;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import Thmod.Cards.ScarletCard.uncommonCards.BathedInBlood;
import Thmod.Power.remiria.BloodBruisePower;
import Thmod.Power.remiria.MiserableFatePower;
import Thmod.ThMod;
import basemod.helpers.SuperclassFinder;

public class BloodBruiseRenderPatch {
    @SpirePatch(
            clz= AbstractCreature.class,
            method="renderHealth"
    )
    public static class renderHealth {
        @SpireInsertPatch(rloc=0)
        public static SpireReturn Insert(AbstractCreature _inst, SpriteBatch sb) throws IllegalAccessException, InvocationTargetException, NoSuchFieldException, NoSuchMethodException {
            Field targetHealthBarWidth;
            Field hbYOffset;

                targetHealthBarWidth = SuperclassFinder.getSuperclassField(_inst.getClass(),"targetHealthBarWidth");
                hbYOffset = SuperclassFinder.getSuperclassField(_inst.getClass(),"hbYOffset");
                targetHealthBarWidth.setAccessible(true);
                hbYOffset.setAccessible(true);

                Method renderHealthBg;
                Method renderOrangeHealthBar;
                Method renderGreenHealthBar;
                Method renderBlockOutline;
                Method renderHealthText;
                Method renderBlockIconAndValue;
                Method renderPowerIcons;

                renderHealthBg = SuperclassFinder.getSuperClassMethod(_inst.getClass(), "renderHealthBg", SpriteBatch.class, float.class, float.class);
                renderOrangeHealthBar = SuperclassFinder.getSuperClassMethod(_inst.getClass(), "renderOrangeHealthBar", SpriteBatch.class, float.class, float.class);
                renderGreenHealthBar = SuperclassFinder.getSuperClassMethod(_inst.getClass(), "renderGreenHealthBar", SpriteBatch.class, float.class, float.class);
                renderBlockOutline = SuperclassFinder.getSuperClassMethod(_inst.getClass(), "renderBlockOutline", SpriteBatch.class, float.class, float.class);
                renderHealthText = SuperclassFinder.getSuperClassMethod(_inst.getClass(), "renderHealthText", SpriteBatch.class, float.class);
                renderBlockIconAndValue = SuperclassFinder.getSuperClassMethod(_inst.getClass(), "renderBlockIconAndValue", SpriteBatch.class, float.class, float.class);
                renderPowerIcons = SuperclassFinder.getSuperClassMethod(_inst.getClass(), "renderPowerIcons", SpriteBatch.class, float.class, float.class);

                renderHealthBg.setAccessible(true);
                renderOrangeHealthBar.setAccessible(true);
                renderGreenHealthBar.setAccessible(true);
                renderBlockOutline.setAccessible(true);
                renderHealthText.setAccessible(true);
                renderBlockIconAndValue.setAccessible(true);
                renderPowerIcons.setAccessible(true);

                if (Settings.hideCombatElements) {
                    return SpireReturn.Return(null);
                }
                float x = _inst.hb.cX - _inst.hb.width / 2.0F;
                float y = _inst.hb.cY - _inst.hb.height / 2.0F + (float)hbYOffset.get(_inst);

                renderHealthBg.invoke(_inst, sb, x, y);
                if ((float)targetHealthBarWidth.get(_inst) != 0.0F)
                {
                    renderOrangeHealthBar.invoke(_inst, sb, x, y);
                    if (_inst.hasPower("Poison")) {
                        renderGreenHealthBar.invoke(_inst, sb, x, y);
                    }
                    if (_inst.hasPower(BloodBruisePower.POWER_ID)) {
                        renderDeepRedHealthBar(sb, x, y, _inst);
                    }
                    renderRedHealthBar(sb, x, y, _inst);
                }
                if ((_inst.currentBlock != 0) && (_inst.hbAlpha != 0.0F)) {
                    renderBlockOutline.invoke(_inst, sb, x, y);
                }
                renderHealthText.invoke(_inst, sb, y);
                if ((_inst.currentBlock != 0) && (_inst.hbAlpha != 0.0F)) {
                    renderBlockIconAndValue.invoke(_inst, sb, x, y);
                }
                renderPowerIcons.invoke(_inst, sb, x, y);

            return SpireReturn.Return(null);
        }
    }

    public static void renderDeepRedHealthBar(SpriteBatch sb, float x, float y, AbstractCreature _inst) throws IllegalAccessException, NoSuchFieldException {
        Field blueHbBarColor;
        Field redHbBarColor;
        Field HEALTH_BAR_HEIGHT;
        Field HEALTH_BAR_OFFSET_Y;
        Field targetHealthBarWidth;


            blueHbBarColor = SuperclassFinder.getSuperclassField(_inst.getClass(), "blueHbBarColor");
            redHbBarColor = SuperclassFinder.getSuperclassField(_inst.getClass(), "redHbBarColor");
            HEALTH_BAR_HEIGHT = SuperclassFinder.getSuperclassField(_inst.getClass(), "HEALTH_BAR_HEIGHT");
            HEALTH_BAR_OFFSET_Y = SuperclassFinder.getSuperclassField(_inst.getClass(), "HEALTH_BAR_OFFSET_Y");
            targetHealthBarWidth = SuperclassFinder.getSuperclassField(_inst.getClass(), "targetHealthBarWidth");

            blueHbBarColor.setAccessible(true);
            redHbBarColor.setAccessible(true);
            HEALTH_BAR_HEIGHT.setAccessible(true);
            HEALTH_BAR_OFFSET_Y.setAccessible(true);
            targetHealthBarWidth.setAccessible(true);

            sb.setColor(new Color(0.5F, 0.05f, 0.01f, 1.0f));

            if (!_inst.hasPower("Poison"))
            {
                if (_inst.currentHealth > 0) {
                    sb.draw(ImageMaster.HEALTH_BAR_L, x - (float)HEALTH_BAR_HEIGHT.get(_inst), y + (float)HEALTH_BAR_OFFSET_Y.get(_inst), (float)HEALTH_BAR_HEIGHT.get(_inst), (float)HEALTH_BAR_HEIGHT.get(_inst));
                }
                sb.draw(ImageMaster.HEALTH_BAR_B, x, y + (float)HEALTH_BAR_OFFSET_Y.get(_inst), (float)targetHealthBarWidth.get(_inst), (float)HEALTH_BAR_HEIGHT.get(_inst));

                sb.draw(ImageMaster.HEALTH_BAR_R, x + (float)targetHealthBarWidth.get(_inst), y + (float)HEALTH_BAR_OFFSET_Y.get(_inst), (float)HEALTH_BAR_HEIGHT.get(_inst), (float)HEALTH_BAR_HEIGHT.get(_inst));
            }
            else
            {
                float poisonAmt = _inst.getPower("Poison").amount;
                if ((poisonAmt > 0) && (_inst.hasPower("Intangible"))) {
                    poisonAmt = 1;
                }
                if (_inst.currentHealth > poisonAmt)
                {
                    float w = 1.0F - (_inst.currentHealth - poisonAmt) / _inst.currentHealth;
                    w *= (float)targetHealthBarWidth.get(_inst);
                    if (_inst.currentHealth > 0) {
                        sb.draw(ImageMaster.HEALTH_BAR_L, x - (float)HEALTH_BAR_HEIGHT.get(_inst), y + (float)HEALTH_BAR_OFFSET_Y.get(_inst), (float)HEALTH_BAR_HEIGHT.get(_inst), (float)HEALTH_BAR_HEIGHT.get(_inst));
                    }
                    sb.draw(ImageMaster.HEALTH_BAR_B, x, y + (float)HEALTH_BAR_OFFSET_Y.get(_inst), (float)targetHealthBarWidth.get(_inst) - w, (float)HEALTH_BAR_HEIGHT.get(_inst));

                    sb.draw(ImageMaster.HEALTH_BAR_R, x + (float)targetHealthBarWidth.get(_inst) - w, y + (float)HEALTH_BAR_OFFSET_Y.get(_inst), (float)HEALTH_BAR_HEIGHT.get(_inst), (float)HEALTH_BAR_HEIGHT.get(_inst));
                }
            }

    }

    public static void renderRedHealthBar(SpriteBatch sb, float x, float y, AbstractCreature _inst) throws IllegalAccessException, NoSuchFieldException {
        Field blueHbBarColor;
        Field redHbBarColor;
        Field HEALTH_BAR_HEIGHT;
        Field HEALTH_BAR_OFFSET_Y;
        Field targetHealthBarWidth;


            blueHbBarColor = SuperclassFinder.getSuperclassField(_inst.getClass(), "blueHbBarColor");
            redHbBarColor = SuperclassFinder.getSuperclassField(_inst.getClass(), "redHbBarColor");
            HEALTH_BAR_HEIGHT = SuperclassFinder.getSuperclassField(_inst.getClass(), "HEALTH_BAR_HEIGHT");
            HEALTH_BAR_OFFSET_Y = SuperclassFinder.getSuperclassField(_inst.getClass(), "HEALTH_BAR_OFFSET_Y");
            targetHealthBarWidth = SuperclassFinder.getSuperclassField(_inst.getClass(), "targetHealthBarWidth");

            blueHbBarColor.setAccessible(true);
            redHbBarColor.setAccessible(true);
            HEALTH_BAR_HEIGHT.setAccessible(true);
            HEALTH_BAR_OFFSET_Y.setAccessible(true);
            targetHealthBarWidth.setAccessible(true);

            if (_inst.currentBlock > 0) {
                sb.setColor((Color) blueHbBarColor.get(_inst));
            } else {
                sb.setColor((Color) redHbBarColor.get(_inst));
            }
            if ((!_inst.hasPower("Poison")) && (!_inst.hasPower(BloodBruisePower.POWER_ID)))
            {
                if (_inst.currentHealth > 0) {
                    sb.draw(ImageMaster.HEALTH_BAR_L, x - (float)HEALTH_BAR_HEIGHT.get(_inst), y + (float)HEALTH_BAR_OFFSET_Y.get(_inst), (float)HEALTH_BAR_HEIGHT.get(_inst), (float)HEALTH_BAR_HEIGHT.get(_inst));
                }
                sb.draw(ImageMaster.HEALTH_BAR_B, x, y + (float)HEALTH_BAR_OFFSET_Y.get(_inst), (float)targetHealthBarWidth.get(_inst), (float)HEALTH_BAR_HEIGHT.get(_inst));

                sb.draw(ImageMaster.HEALTH_BAR_R, x + (float)targetHealthBarWidth.get(_inst), y + (float)HEALTH_BAR_OFFSET_Y.get(_inst), (float)HEALTH_BAR_HEIGHT.get(_inst), (float)HEALTH_BAR_HEIGHT.get(_inst));
            }
            else
            {
                float totalAmt = 0;
                if(_inst.hasPower("Poison")) {
                    int poisonAmt = _inst.getPower("Poison").amount;
                    if ((poisonAmt > 0) && (_inst.hasPower("Intangible"))) {
                        poisonAmt = 1;
                    }
                    totalAmt += poisonAmt;
                }
                if(_inst.hasPower(BloodBruisePower.POWER_ID)){
                    int bloodAmt = _inst.getPower(BloodBruisePower.POWER_ID).amount;
                    float multiple = 0.5f * (float) Math.pow(1.5, bloodAmt / 12);
                    bloodAmt *= multiple;
                    if ((bloodAmt > 0) && (_inst.hasPower("Intangible"))) {
                        bloodAmt = 1;
                    }
                    totalAmt += bloodAmt;
                }
                //ThMod.logger.info("totalAmt : " + totalAmt);
                if (_inst.currentHealth > totalAmt)
                {
                    float w = 1.0F - (_inst.currentHealth - totalAmt) / _inst.currentHealth;
                    w *= (float)targetHealthBarWidth.get(_inst);
                    if (_inst.currentHealth > 0) {
                        sb.draw(ImageMaster.HEALTH_BAR_L, x - (float)HEALTH_BAR_HEIGHT.get(_inst), y + (float)HEALTH_BAR_OFFSET_Y.get(_inst), (float)HEALTH_BAR_HEIGHT.get(_inst), (float)HEALTH_BAR_HEIGHT.get(_inst));
                    }
                    sb.draw(ImageMaster.HEALTH_BAR_B, x, y + (float)HEALTH_BAR_OFFSET_Y.get(_inst), (float)targetHealthBarWidth.get(_inst) - w, (float)HEALTH_BAR_HEIGHT.get(_inst));

                    sb.draw(ImageMaster.HEALTH_BAR_R, x + (float)targetHealthBarWidth.get(_inst) - w, y + (float)HEALTH_BAR_OFFSET_Y.get(_inst), (float)HEALTH_BAR_HEIGHT.get(_inst), (float)HEALTH_BAR_HEIGHT.get(_inst));
                }
            }

    }
}
