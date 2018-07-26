package Thmod.Relics;

import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import Thmod.ThMod;
import basemod.abstracts.CustomRelic;

public abstract class AbstractThRelic extends CustomRelic {
    private boolean RclickStart;
    private boolean Rclick;
    public AbstractThRelic(final String id, final AbstractRelic.RelicTier tier, final AbstractRelic.LandingSound landingSound){
        super(id, ImageMaster.loadImage(ThMod.komeijiRelicImage(id)),ImageMaster.loadImage(ThMod.komeijiRelicOutlineImage(id)), tier, landingSound);
        this.Rclick=false;
        this.RclickStart=false;
    }
    protected abstract void onRightClick();

    @Override
    public void update() {
        super.update();
        if(this.RclickStart && InputHelper.justReleasedClickRight) {
            if(this.hb.hovered) {
                this.Rclick=true;
            }
            this.RclickStart=false;
        }
        if((this.isObtained) && (this.hb != null) && ((this.hb.hovered) && (InputHelper.justClickedRight))) {
            this.RclickStart=true;
            //logger.info("rcs");
        }
        if((this.Rclick)){
            //logger.info("rc");
            this.Rclick=false;
            this.onRightClick();
        }
    }
}
