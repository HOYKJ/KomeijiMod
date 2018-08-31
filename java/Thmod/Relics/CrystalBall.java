package Thmod.Relics;

import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import Thmod.Actions.unique.ChooseAction;
import Thmod.Actions.unique.PlayerTalkAction;
import Thmod.Cards.ElementCards.RareCards.ElementMix;
import Thmod.Orbs.ElementOrb.EarthOrb;
import Thmod.Orbs.ElementOrb.FireOrb;
import Thmod.Orbs.ElementOrb.MetalOrb;
import Thmod.Orbs.ElementOrb.WaterOrb;
import Thmod.Orbs.ElementOrb.WoodOrb;

public class CrystalBall extends AbstractThRelic {
    public static final String ID = "CrystalBall";
    private boolean playerturn;
    private boolean clicked;
    private boolean selected;

    public CrystalBall()
    {
        super("CrystalBall",  RelicTier.UNCOMMON, LandingSound.MAGICAL);
        this.playerturn = false;
        this.selected = false;
    }

    protected  void onRightClick(){
        AbstractPlayer p = AbstractDungeon.player;
        if ((AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT)) {
            if (!(this.selected)) {
                if (this.playerturn) {
                    if (!(this.clicked)) {
                        this.clicked = true;
                        final ChooseAction choice = new ChooseAction(new ElementMix(), null, this.DESCRIPTIONS[1], true, 1);
                        choice.add(ElementMix.EXTENDED_DESCRIPTION[1], ElementMix.EXTENDED_DESCRIPTION[2], () -> {
                            AbstractOrb orb = new EarthOrb();
                            AbstractDungeon.actionManager.addToBottom(new ChannelAction(orb));
                            this.selected = true;
                        });
                        choice.add(ElementMix.EXTENDED_DESCRIPTION[3], ElementMix.EXTENDED_DESCRIPTION[4], () -> {
                            AbstractOrb orb = new FireOrb();
                            AbstractDungeon.actionManager.addToBottom(new ChannelAction(orb));
                            this.selected = true;
                        });
                        choice.add(ElementMix.EXTENDED_DESCRIPTION[7], ElementMix.EXTENDED_DESCRIPTION[8], () -> {
                            AbstractOrb orb = new MetalOrb();
                            AbstractDungeon.actionManager.addToBottom(new ChannelAction(orb));
                            this.selected = true;
                        });
                        choice.add(ElementMix.EXTENDED_DESCRIPTION[11], ElementMix.EXTENDED_DESCRIPTION[12], () -> {
                            AbstractOrb orb = new WaterOrb();
                            AbstractDungeon.actionManager.addToBottom(new ChannelAction(orb));
                            this.selected = true;
                        });
                        choice.add(ElementMix.EXTENDED_DESCRIPTION[13], ElementMix.EXTENDED_DESCRIPTION[14], () -> {
                            AbstractOrb orb = new WoodOrb();
                            AbstractDungeon.actionManager.addToBottom(new ChannelAction(orb));
                            this.selected = true;
                        });
                        AbstractDungeon.actionManager.addToBottom(choice);
                        this.pulse = false;
                    } else {
                        AbstractDungeon.actionManager.addToTop(new PlayerTalkAction(p, DESCRIPTIONS[2]));
                    }
                } else {
                    AbstractDungeon.actionManager.addToTop(new PlayerTalkAction(p, DESCRIPTIONS[3]));
                }
            } else {
                AbstractDungeon.actionManager.addToTop(new PlayerTalkAction(p, DESCRIPTIONS[4]));
            }
        }
    }

    public void atTurnStart() {
        this.playerturn = true;
        this.clicked = false;
        beginPulse();
        this.pulse = true;
    }

    public void onPlayerEndTurn() {
        this.selected = false;
        this.playerturn = false;
        this.pulse = false;
    }

    public void onVictory() {
        this.selected = false;
        this.pulse = false;
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new CrystalBall();
    }
}
