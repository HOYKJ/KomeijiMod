package Thmod.Relics;

import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import Thmod.Actions.unique.ChooseAction;
import Thmod.Actions.unique.PlayerTalkAction;
import Thmod.Actions.unique.ResonateAction;
import Thmod.Cards.ElementCards.AbstractElementCards;
import Thmod.Cards.ElementCards.RareCards.ElementMix;
import Thmod.Orbs.ElementOrb.AbstractElementOrb;
import Thmod.Orbs.ElementOrb.EarthOrb;
import Thmod.Orbs.ElementOrb.FireOrb;
import Thmod.Orbs.ElementOrb.MetalOrb;
import Thmod.Orbs.ElementOrb.WaterOrb;
import Thmod.Orbs.ElementOrb.WoodOrb;
import Thmod.ThMod;

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
        this.counter = 0;
    }

    protected  void onRightClick(){
        AbstractPlayer p = AbstractDungeon.player;
        boolean hasElement = false;
        if(AbstractDungeon.currMapNode != null) {
            if ((AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT)) {
                if (!(this.selected)) {
                    if (this.playerturn) {
                        if (!(this.clicked)) {
                            this.clicked = true;
                            for(AbstractOrb orb : p.orbs){
                                if((orb instanceof AbstractElementOrb) && (((AbstractElementOrb) orb).elementType != AbstractElementCards.ElementType.Sun)
                                        && (((AbstractElementOrb) orb).elementType != AbstractElementCards.ElementType.Luna)){
                                    hasElement = true;
                                    ThMod.logger.info("has element");
                                    break;
                                }
                            }
                            if(!hasElement) {
                                final ChooseAction choice = new ChooseAction(new ElementMix(), null, this.DESCRIPTIONS[1], true, 1);
                                choice.add(ElementMix.EXTENDED_DESCRIPTION[1], ElementMix.EXTENDED_DESCRIPTION[2], () -> {
                                    AbstractElementOrb orb = new EarthOrb();
                                    AbstractDungeon.actionManager.addToBottom(new ChannelAction(orb));
                                    this.selected = true;
                                    this.counter = 2;
                                });
                                choice.add(ElementMix.EXTENDED_DESCRIPTION[3], ElementMix.EXTENDED_DESCRIPTION[4], () -> {
                                    AbstractElementOrb orb = new FireOrb();
                                    AbstractDungeon.actionManager.addToBottom(new ChannelAction(orb));
                                    this.selected = true;
                                    this.counter = 2;
                                });
                                choice.add(ElementMix.EXTENDED_DESCRIPTION[7], ElementMix.EXTENDED_DESCRIPTION[8], () -> {
                                    AbstractElementOrb orb = new MetalOrb();
                                    AbstractDungeon.actionManager.addToBottom(new ChannelAction(orb));
                                    this.selected = true;
                                    this.counter = 2;
                                });
                                choice.add(ElementMix.EXTENDED_DESCRIPTION[11], ElementMix.EXTENDED_DESCRIPTION[12], () -> {
                                    AbstractElementOrb orb = new WaterOrb();
                                    AbstractDungeon.actionManager.addToBottom(new ChannelAction(orb));
                                    this.selected = true;
                                    this.counter = 2;
                                });
                                choice.add(ElementMix.EXTENDED_DESCRIPTION[13], ElementMix.EXTENDED_DESCRIPTION[14], () -> {
                                    AbstractElementOrb orb = new WoodOrb();
                                    AbstractDungeon.actionManager.addToBottom(new ChannelAction(orb));
                                    this.selected = true;
                                    this.counter = 2;
                                });
                                AbstractDungeon.actionManager.addToBottom(choice);
                                this.pulse = false;
                            }
                            else {
                                final ChooseAction choice = new ChooseAction(new ElementMix(), null, this.DESCRIPTIONS[1], true, 1);
                                for(AbstractOrb orb1 : p.orbs) {
                                    if(orb1 instanceof AbstractElementOrb) {
                                        switch (((AbstractElementOrb) orb1).elementType) {
                                            case Earth:
                                                choice.add(ElementMix.EXTENDED_DESCRIPTION[1], ElementMix.EXTENDED_DESCRIPTION[2], () -> {
                                                    AbstractElementOrb orb = new EarthOrb();
                                                    AbstractDungeon.actionManager.addToBottom(new ResonateAction(orb, true));
                                                    this.selected = true;
                                                    this.counter = 2;
                                                });
                                                break;
                                            case Fire:
                                                choice.add(ElementMix.EXTENDED_DESCRIPTION[3], ElementMix.EXTENDED_DESCRIPTION[4], () -> {
                                                    AbstractElementOrb orb = new FireOrb();
                                                    AbstractDungeon.actionManager.addToBottom(new ResonateAction(orb, true));
                                                    this.selected = true;
                                                    this.counter = 2;
                                                });
                                                break;
                                            case Metal:
                                                choice.add(ElementMix.EXTENDED_DESCRIPTION[7], ElementMix.EXTENDED_DESCRIPTION[8], () -> {
                                                    AbstractElementOrb orb = new MetalOrb();
                                                    AbstractDungeon.actionManager.addToBottom(new ResonateAction(orb, true));
                                                    this.selected = true;
                                                    this.counter = 2;
                                                });
                                                break;
                                            case Water:
                                                choice.add(ElementMix.EXTENDED_DESCRIPTION[11], ElementMix.EXTENDED_DESCRIPTION[12], () -> {
                                                    AbstractElementOrb orb = new WaterOrb();
                                                    AbstractDungeon.actionManager.addToBottom(new ResonateAction(orb, true));
                                                    this.selected = true;
                                                    this.counter = 2;
                                                });
                                                break;
                                            case Wood:
                                                choice.add(ElementMix.EXTENDED_DESCRIPTION[13], ElementMix.EXTENDED_DESCRIPTION[14], () -> {
                                                    AbstractElementOrb orb = new WoodOrb();
                                                    AbstractDungeon.actionManager.addToBottom(new ResonateAction(orb, true));
                                                    this.selected = true;
                                                    this.counter = 2;
                                                });
                                                break;
                                        }
                                    }
                                }
                                AbstractDungeon.actionManager.addToBottom(choice);
                                this.pulse = false;
                            }
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
    }

    public void atTurnStart() {
        this.playerturn = true;
        this.clicked = false;
        if(!this.selected) {
            beginPulse();
            this.pulse = true;
        }
    }

    public void onPlayerEndTurn() {
        this.playerturn = false;
        this.pulse = false;
        if(this.counter == 1) {
            this.selected = false;
            this.counter -= 1;
        }
        else {
            this.counter -= 1;
        }
    }

    public void onVictory() {
        this.counter = 0;
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
