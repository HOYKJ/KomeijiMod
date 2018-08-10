package Thmod.Relics;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class ReiryokuShyu extends AbstractThRelic {
    public static final String ID = "ReiryokuShyu";
    private AbstractPlayer p =AbstractDungeon.player;

    public ReiryokuShyu()
    {
        super("ReiryokuShyu",  RelicTier.BOSS, LandingSound.MAGICAL);
    }

    public void atTurnStart() {
        if (p.hasPower("PointPower")) {
            if (p.getPower("PointPower").amount >= 4) {
                AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(1));
                this.img = ImageMaster.loadImage("images/relics/ReiryokuShyu2.png");
            }
        }
    }

    protected  void onRightClick(){
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new ReiryokuShyu();
    }
}
