package Thmod.Relics;

import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import Thmod.Actions.unique.PlayerTalkAction;
import Thmod.Orbs.NingyouOrb;

public class RingOfDollmaker extends AbstractThRelic {
    public static final String ID = "RingOfDollmaker";
    private boolean playerturn;
    private boolean clicked;
    private boolean selected;

    public RingOfDollmaker()
    {
        super("RingOfDollmaker",  RelicTier.UNCOMMON, LandingSound.MAGICAL);
    }

    public void atPreBattle() {
        this.selected = false;
        beginPulse();
        this.pulse = true;
    }

    protected  void onRightClick() {
        AbstractPlayer p = AbstractDungeon.player;
        if(AbstractDungeon.currMapNode != null) {
            if ((AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT)) {
                if (!(this.selected)) {
                    if (this.playerturn) {
                        this.pulse = false;
                        this.selected = true;
                        int orbEmptyNum = 0;
                        for (int i = 0; i < p.orbs.size(); i++) {
                            if (p.orbs.get(i) instanceof EmptyOrbSlot) {
                                orbEmptyNum += 1;
                            }
                        }
                        if (orbEmptyNum > 3) {
                            orbEmptyNum = 3;
                        }
                        for (int i = 0; i < orbEmptyNum; i++) {
                            AbstractOrb orb = new NingyouOrb();
                            AbstractDungeon.actionManager.addToBottom(new ChannelAction(orb));
                        }
                    } else {
                        AbstractDungeon.actionManager.addToTop(new PlayerTalkAction(p, DESCRIPTIONS[1]));
                    }
                } else {
                    AbstractDungeon.actionManager.addToTop(new PlayerTalkAction(p, DESCRIPTIONS[2]));
                }
            }
        }
    }
    public void atTurnStart() {
        this.playerturn = true;
    }

    public void onPlayerEndTurn() {
        this.playerturn = false;
    }

    @Override
    public void onVictory() {
        super.onVictory();
        this.pulse = false;
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new RingOfDollmaker();
    }
}
