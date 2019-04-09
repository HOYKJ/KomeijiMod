package Thmod.Relics.remiria;

import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import Thmod.Characters.RemiriaScarlet;
import basemod.abstracts.CustomRelic;

public abstract class AbstractRemiriaRelic extends CustomRelic {
    private boolean RclickStart;
    private boolean Rclick;
    public AbstractRemiriaRelic(final String id, final AbstractRelic.RelicTier tier, final AbstractRelic.LandingSound landingSound){
        super(id, ImageMaster.loadImage(RemiriaScarlet.remiriaRelicImage(id)),ImageMaster.loadImage(RemiriaScarlet.remiriaRelicOutlineImage(id)), tier, landingSound);
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
        }
        if((this.Rclick)){
            this.Rclick=false;
            this.onRightClick();
        }
    }
}
