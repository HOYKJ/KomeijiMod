package Thmod.Patches.RelicPatch;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import Thmod.Power.DashPower;
import Thmod.Relics.AbstractThRelic;

public class ChannelOrbPatch {

    @SpirePatch(
            clz = AbstractPlayer.class,
            method = "channelOrb"
    )
    public static class channelOrb {
        @SpirePrefixPatch
        public static void prefix(AbstractPlayer _inst, AbstractOrb orbToSet) {
            if(_inst.maxOrbs > 0){
                for(AbstractRelic relic : _inst.relics){
                    if(relic instanceof AbstractThRelic){
                        ((AbstractThRelic) relic).onChannelOrb(orbToSet);
                    }
                }
            }
        }
    }
}
