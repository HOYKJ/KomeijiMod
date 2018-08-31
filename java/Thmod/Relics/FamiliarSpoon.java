package Thmod.Relics;

import com.megacrit.cardcrawl.relics.AbstractRelic;

public class FamiliarSpoon extends AbstractThRelic {
    public static final String ID = "FamiliarSpoon";

    public FamiliarSpoon()
    {
        super("FamiliarSpoon",  RelicTier.SPECIAL, LandingSound.CLINK);
    }

    protected  void onRightClick(){
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new FamiliarSpoon();
    }
}
