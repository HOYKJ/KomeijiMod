package Thmod.Relics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import Thmod.ThMod;
import basemod.abstracts.CustomRelic;

public abstract class AbstractThRelic extends CustomRelic {
    private boolean RclickStart;
    private boolean Rclick;
    public AbstractThRelic(final String id, final AbstractRelic.RelicTier tier, final AbstractRelic.LandingSound landingSound){
        super(id, new Texture(Gdx.files.internal(ThMod.komeijiRelicImage(id))), new Texture(Gdx.files.internal(ThMod.komeijiRelicOutlineImage(id))), tier, landingSound);
        this.Rclick=false;
        this.RclickStart=false;
    }
    protected abstract void onRightClick();

    @Override
    public void update() {
        super.update();
        if(this.RclickStart&& InputHelper.justReleasedClickRight) {
            if(this.hb.hovered) {
                this.Rclick=true;
            }
            this.RclickStart=false;
        }
        if((this.isObtained)&&(this.hb != null)&&((this.hb.hovered) && (InputHelper.justClickedRight))) {
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
