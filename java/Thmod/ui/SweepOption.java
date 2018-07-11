package Thmod.ui;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;

import Thmod.vfx.campfire.CampfireSweepEffect;

public class SweepOption extends AbstractCampfireOption {
    public static final String LABEL = "转换";
    public static final String DESCRIPTION = "选择一张卡进行转换.";

    public static Texture GetUpgradeOptionTexture()
    {
        return new Texture("images/ui/SweepOption.png");
    }

    public SweepOption(boolean active)
    {
        this.label = "联想";
        this.usable = active;
        if (active) {
            this.description = "选择一张卡进行转换.";
            this.img = GetUpgradeOptionTexture();
        } else {
            this.description = "选择一张卡进行转换.";
            this.img = GetUpgradeOptionTexture();
        }
    }
    public void useOption()
    {
        AbstractDungeon.effectList.add(new CampfireSweepEffect());
    }
}
