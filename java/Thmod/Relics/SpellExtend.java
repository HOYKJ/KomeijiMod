package Thmod.Relics;

import com.megacrit.cardcrawl.relics.AbstractRelic;

public class SpellExtend extends AbstractThRelic {
    public static final String ID = "SpellExtend";

    public SpellExtend()
    {
        super("SpellExtend",  RelicTier.COMMON, LandingSound.MAGICAL);
    }

    protected  void onRightClick(){
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new SpellExtend();
    }
}
