package Thmod.Neow;

import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.neow.NeowReward;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

import Thmod.Cards.RewardCards.RanYakumo;

public class ShikigamiReward extends NeowReward
{
    private static final String DESCRIPTION = "[ #b获得一张 #p特殊 #b卡 ]";

    public ShikigamiReward()
    {
        super(0);

        this.optionLabel = "[ #b获得一张 #p特殊 #b卡]";
    }

    public void activate()
    {
        AbstractDungeon.topLevelEffects.add(new ShowCardAndObtainEffect(new RanYakumo(), Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
    }
}

