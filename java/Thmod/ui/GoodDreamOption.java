package Thmod.ui;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;

import Thmod.vfx.DreamFogCoverEffect;
import Thmod.vfx.campfire.DreamEventEffect;

public class GoodDreamOption extends AbstractCampfireOption {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("GoodDreamOption");
    public static final String[] TEXT = uiStrings.TEXT;

    public static Texture GetUpgradeOptionTexture()
    {
        return new Texture("images/ui/SweepOption.png");
    }

    public GoodDreamOption(boolean active)
    {
        this.label = TEXT[0];
        this.usable = active;
        if (active) {
            this.description = TEXT[1];
            this.img = GetUpgradeOptionTexture();
        } else {
            this.description = TEXT[1];
            this.img = GetUpgradeOptionTexture();
        }
    }
    public void useOption()
    {
        for (int i = 0; i < 56; i++) {
            AbstractDungeon.effectList.add(new DreamFogCoverEffect());
        }
        DreamEventEffect Dream = new DreamEventEffect();
        AbstractDungeon.effectList.add(Dream);
        Dream.updateDreamText(TEXT[11]);
        Dream.canAddButton();
    }
}
