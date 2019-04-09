package Thmod.Relics;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import Thmod.Actions.unique.PlayerTalkAction;

public class ColorfulQuillpen extends AbstractThRelic {
    public static final String ID = "ColorfulQuillpen";
    private boolean selected;

    public ColorfulQuillpen()
    {
        super("ColorfulQuillpen",  RelicTier.UNCOMMON, LandingSound.SOLID);
        this.selected = false;
    }

    public void atPreBattle() {
        this.selected = false;
        beginPulse();
        this.pulse = true;
    }

    protected  void onRightClick(){
        AbstractPlayer p = AbstractDungeon.player;
        if(!(this.selected)) {
            if(AbstractDungeon.currMapNode != null) {
                if ((AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT)) {
                    if (!(SpellCardsRule.newCards)) {
                        SpellCardsRule.newCards = true;
                        this.selected = true;
                        this.pulse = false;
                    } else {
                        AbstractDungeon.actionManager.addToTop(new PlayerTalkAction(p, DESCRIPTIONS[1]));
                    }
                }
            }
        }
        else {
            AbstractDungeon.actionManager.addToTop(new PlayerTalkAction(p, DESCRIPTIONS[2]));
        }
    }

    @Override
    public void atTurnStartPostDraw() {
        super.atTurnStartPostDraw();
        this.selected = false;
        beginPulse();
        this.pulse = true;
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new ColorfulQuillpen();
    }
}
