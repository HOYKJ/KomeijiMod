package Thmod.Rewards;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.UIStrings;

import Thmod.Patches.CrystalPatch.RewardItemEnum;
import basemod.abstracts.CustomReward;

public class FullHealing extends CustomReward
{
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("RewardItem_Rem");
    public static final String[] TEXT = uiStrings.TEXT;

    public FullHealing()
    {
        super(ImageMaster.TP_HP, TEXT[1], RewardItemEnum.FULL_HEAL);
    }

    public boolean claimReward()
    {
        AbstractDungeon.player.heal(AbstractDungeon.player.maxHealth);
        return true;
    }
}
