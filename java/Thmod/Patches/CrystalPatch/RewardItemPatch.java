package Thmod.Patches.CrystalPatch;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.neow.NeowEvent;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.vfx.ObtainKeyEffect;
import com.megacrit.cardcrawl.vfx.campfire.CampfireSmithEffect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import Thmod.Relics.CrystalOfMemory;
import Thmod.ThMod;

import static com.megacrit.cardcrawl.rewards.RewardItem.RewardType.EMERALD_KEY;
import static com.megacrit.cardcrawl.rewards.RewardItem.RewardType.RELIC;

public class RewardItemPatch {
    @SpirePatch(
            clz = RewardItem.class,
            method = "render"
    )
    public static class render {
        //@SpireInsertPatch(rloc = 0)
        public static void Postfix(RewardItem _inst, SpriteBatch sb) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
            Method m = RewardItem.class.getDeclaredMethod("renderRelicLink", SpriteBatch.class);
            m.setAccessible(true);

            if (_inst.type == RewardItemEnum.CRYSTAL) {
                m.invoke(_inst, sb);
                //ThMod.logger.info("render link");
            }
        }

        @SpireInsertPatch(rloc = 152, localvars={"tips"})
        public static void Insert(RewardItem _inst, SpriteBatch sb, ArrayList<PowerTip> tips) {
            if (_inst.relicLink.type == RewardItemEnum.CRYSTAL) {
                tips.add(new PowerTip(RewardItem.TEXT[7], RewardItem.TEXT[8] + FontHelper.colorString(_inst.relicLink.relic.name + RewardItem.TEXT[9], "y")));
            }
        }

        @SpireInsertPatch(rloc = 184)
        public static void Insert(RewardItem _inst, SpriteBatch sb) {
            if (_inst.type == RewardItemEnum.CRYSTAL) {
                _inst.relic.renderWithoutAmount(sb, new Color(0.0F, 0.0F, 0.0F, 0.25F));
                if (_inst.hb.hovered) {
                    if (_inst.relicLink != null)
                    {
                        ArrayList<PowerTip> tips = new ArrayList<>();
                        tips.add(new PowerTip(_inst.relic.name, _inst.relic.description));
                        if(_inst.relicLink.type == EMERALD_KEY){
                            tips.add(new PowerTip(RewardItem.TEXT[7], RewardItem.TEXT[8] + FontHelper.colorString(RewardItem.TEXT[5] + RewardItem.TEXT[9], "y")));
                        }
                        else if(_inst.relicLink.type == RELIC){
                            tips.add(new PowerTip(RewardItem.TEXT[7], RewardItem.TEXT[8] + FontHelper.colorString(_inst.relicLink.relic.name + RewardItem.TEXT[9], "y")));
                        }

                        TipHelper.queuePowerTips(360.0F * Settings.scale, InputHelper.mY + 50.0F * Settings.scale, tips);
                    }
                    else
                    {
                        _inst.relic.renderTip(sb);
                    }
                }
            }
            if (_inst.type == RewardItem.RewardType.EMERALD_KEY) {
                if ((_inst.hb.hovered) && (_inst.relicLink != null)) {
                    TipHelper.renderGenericTip(360.0F * Settings.scale, InputHelper.mY + 50.0F * Settings.scale, RewardItem.TEXT[7], RewardItem.TEXT[8] +

                            FontHelper.colorString(_inst.relicLink.relic.name + RewardItem.TEXT[9], "y"));
                }
            }
        }
    }

    @SpirePatch(
            clz = RewardItem.class,
            method = "claimReward"
    )
    public static class claimReward {
        @SpireInsertPatch(rloc = 0)
        public static SpireReturn<Boolean> Insert(RewardItem _inst) {
            if (_inst.type == RewardItemEnum.CRYSTAL) {
                if (!_inst.ignoreReward)
                {
                    _inst.relic.instantObtain();
                    CardCrawlGame.metricData.addRelicObtainData(_inst.relic);
                }
                if (_inst.relicLink != null)
                {
                    _inst.relicLink.isDone = true;
                    _inst.relicLink.ignoreReward = true;
                }
                return SpireReturn.Return(true);
            }
            if (_inst.type == RewardItem.RewardType.EMERALD_KEY) {
                if (_inst.relicLink != null)
                {
                    if (!_inst.ignoreReward) {
                        AbstractDungeon.topLevelEffects.add(new ObtainKeyEffect(ObtainKeyEffect.KeyColor.GREEN));
                    }
                    _inst.relicLink.isDone = true;
                    _inst.relicLink.ignoreReward = true;
                    return SpireReturn.Return(true);
                }
            }
            return SpireReturn.Continue();
        }
    }

    @SpirePatch(
            clz = RewardItem.class,
            method = "move"
    )
    public static class move {
        @SpireInsertPatch(rloc = 0)
        public static void Insert(RewardItem _inst, float y) {
            if (_inst.type == RewardItemEnum.CRYSTAL)
            {
                _inst.relic.currentX = RewardItem.REWARD_ITEM_X;
                _inst.relic.currentY = y;
                _inst.relic.targetX = RewardItem.REWARD_ITEM_X;
                _inst.relic.targetY = y;
            }
        }
    }
}
