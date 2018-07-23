package Thmod.Relics;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import Thmod.Orbs.TateNingyou;

public class MigarariNingyou extends AbstractThRelic {
    public static final String ID = "MigarariNingyou";

    public MigarariNingyou()
    {
        super("MigarariNingyou",  RelicTier.UNCOMMON, LandingSound.MAGICAL);
    }

    public void atPreBattle()
    {
        AbstractDungeon.player.channelOrb(new TateNingyou());
    }

    protected  void onRightClick(){
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new MigarariNingyou();
    }
}
