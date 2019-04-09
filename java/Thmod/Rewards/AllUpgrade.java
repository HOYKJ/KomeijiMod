package Thmod.Rewards;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;

import Thmod.Patches.CrystalPatch.RewardItemEnum;
import basemod.abstracts.CustomReward;

public class AllUpgrade extends CustomReward
{
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("RewardItem_Rem");
    public static final String[] TEXT = uiStrings.TEXT;

    public AllUpgrade()
    {
        super(ImageMaster.loadImage("images/ui/run_mods/sealed_deck.png"), TEXT[0], RewardItemEnum.ALL_UPGRADE);
    }

    public boolean claimReward()
    {
        int effectCount = 0;
        for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
            if (c.canUpgrade())
            {
                effectCount++;
                if (effectCount <= 20)
                {
                    float x = MathUtils.random(0.1F, 0.9F) * Settings.WIDTH;
                    float y = MathUtils.random(0.2F, 0.8F) * Settings.HEIGHT;

                    AbstractDungeon.effectList.add(new ShowCardBrieflyEffect(c
                            .makeStatEquivalentCopy(), x, y));
                    AbstractDungeon.topLevelEffects.add(new UpgradeShineEffect(x, y));
                }
                c.upgrade();
            }
        }
        return true;
    }
}

