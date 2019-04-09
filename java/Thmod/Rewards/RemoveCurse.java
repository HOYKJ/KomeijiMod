package Thmod.Rewards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;

import Thmod.Patches.CrystalPatch.RewardItemEnum;
import basemod.abstracts.CustomReward;

public class RemoveCurse extends CustomReward
{
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("RewardItem_Rem");
    public static final String[] TEXT = uiStrings.TEXT;

    public RemoveCurse()
    {
        super(ImageMaster.loadImage("images/ui/run_mods/shiny.png"), TEXT[2], RewardItemEnum.REMOVE_CURSE);
    }

    public boolean claimReward()
    {
        for (int i = AbstractDungeon.player.masterDeck.group.size() - 1; i >= 0; i--) {
            if (((AbstractDungeon.player.masterDeck.group.get(i)).type == AbstractCard.CardType.CURSE) && (!(AbstractDungeon.player.masterDeck.group.get(i)).inBottleFlame)
                    && (!(AbstractDungeon.player.masterDeck.group.get(i)).inBottleLightning))
            {
                AbstractDungeon.effectList.add(new PurgeCardEffect(AbstractDungeon.player.masterDeck.group.get(i)));
                AbstractDungeon.player.masterDeck.removeCard(AbstractDungeon.player.masterDeck.group.get(i));
            }
        }
        return true;
    }
}
