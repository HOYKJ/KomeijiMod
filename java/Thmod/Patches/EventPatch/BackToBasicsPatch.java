package Thmod.Patches.EventPatch;

import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;

@SpirePatch(cls="com.megacrit.cardcrawl.events.city.BackToBasics", method="upgradeStrikeAndDefends")
public class BackToBasicsPatch
{
    @SpireInsertPatch(rloc=0)
    public static void Insert(Object __obj_instance)
    {
        for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
            if (((c.cardID.equals("Strike_Komeiji")) || ((c.cardID.equals("Defend_Komeiji")))) && (c.canUpgrade()))
            {
                c.upgrade();
                AbstractDungeon.player.bottledCardUpgradeCheck(c);
                AbstractDungeon.effectList.add(new ShowCardBrieflyEffect(c
                        .makeStatEquivalentCopy(), MathUtils.random(0.1F, 0.9F) * Settings.WIDTH,
                        MathUtils.random(0.2F, 0.8F) * Settings.HEIGHT));
            }
        }
    }
}
