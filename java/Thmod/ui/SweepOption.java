package Thmod.ui;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;

import Thmod.vfx.campfire.CampfireSweepEffect;

public class SweepOption extends AbstractCampfireOption {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("SweepOption");
    public static final String[] TEXT = uiStrings.TEXT;
    private int healAmt;

    public static Texture GetUpgradeOptionTexture()
    {
        return new Texture("images/ui/SweepOption.png");
    }

    public SweepOption(boolean active)
    {
        this.healAmt = (int)(AbstractDungeon.player.maxHealth * 0.1F);
        this.label = TEXT[0];
        this.usable = active;
        if (active) {
            this.description = TEXT[1] + this.healAmt + TEXT[2];
            this.img = GetUpgradeOptionTexture();
        } else {
            this.description = TEXT[1] + this.healAmt + TEXT[2];
            this.img = GetUpgradeOptionTexture();
        }
    }

    public void useOption()
    {
        AbstractDungeon.effectList.add(new CampfireSweepEffect(this.healAmt));
    }
}
