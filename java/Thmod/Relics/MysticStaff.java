package Thmod.Relics;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import Thmod.Actions.unique.ElementMixAction;
import Thmod.Actions.unique.PlayerTalkAction;
import Thmod.Orbs.ElementOrb.AbstractElementOrb;

public class MysticStaff extends AbstractThRelic {
    public static final String ID = "MysticStaff";
    private boolean playerturn;
    private boolean clicked;
    private boolean selected;

    public MysticStaff()
    {
        super("MysticStaff",  RelicTier.UNCOMMON, LandingSound.MAGICAL);
        this.playerturn = false;
        this.selected = false;
    }

    protected  void onRightClick(){
        AbstractPlayer p = AbstractDungeon.player;
        if(AbstractDungeon.currMapNode != null) {
            if ((AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT)) {
                if (!(this.selected)) {
                    if (this.playerturn) {
                        if (!(this.clicked)) {
                            this.clicked = true;
                            this.pulse = false;
                            AbstractElementOrb orb1 = null;
                            AbstractElementOrb orb2 = null;
                            for (int i = 0; i < p.orbs.size(); i++) {
                                if (orb1 == null) {
                                    if (p.orbs.get(i) instanceof AbstractElementOrb) {
                                        orb1 = (AbstractElementOrb) p.orbs.get(i);
                                    }
                                } else if (orb2 == null) {
                                    if (p.orbs.get(i) instanceof AbstractElementOrb) {
                                        orb2 = (AbstractElementOrb) p.orbs.get(i);
                                    }
                                }
                            }
                            if (orb2 == null)
                                AbstractDungeon.actionManager.addToTop(new PlayerTalkAction(p, DESCRIPTIONS[4]));
                            else {
                                ElementMixAction elementMixAction = new ElementMixAction(orb2);
                                elementMixAction.addOrb(orb1);
                                AbstractDungeon.actionManager.addToTop(elementMixAction);
                            }
                        } else {
                            AbstractDungeon.actionManager.addToTop(new PlayerTalkAction(p, DESCRIPTIONS[1]));
                        }
                    } else {
                        AbstractDungeon.actionManager.addToTop(new PlayerTalkAction(p, DESCRIPTIONS[2]));
                    }
                } else {
                    AbstractDungeon.actionManager.addToTop(new PlayerTalkAction(p, DESCRIPTIONS[3]));
                }
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
        this.clicked = false;
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
        return new MysticStaff();
    }
}
