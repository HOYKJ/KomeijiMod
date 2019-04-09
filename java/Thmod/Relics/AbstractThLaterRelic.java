package Thmod.Relics;

import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import Thmod.ThMod;

public abstract class AbstractThLaterRelic extends AbstractThRelic {

    public AbstractThLaterRelic(final String id, final AbstractRelic.RelicTier tier, final AbstractRelic.LandingSound landingSound){
        super(id, tier, landingSound);
    }

    public void enteredRoom(AbstractRoom room){ }
}
