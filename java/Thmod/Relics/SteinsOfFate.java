package Thmod.Relics;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class SteinsOfFate extends AbstractThRelic {
    public static final String ID = "SteinsOfFate";

    public SteinsOfFate()
    {
        super("SteinsOfFate",  RelicTier.SPECIAL, LandingSound.MAGICAL);
        this.counter = 0;
    }

    public void atTurnStartPostDraw() {
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, 1));
    }

    public void onMonsterDeath(AbstractMonster m) {
        this.counter += 1;
    }

    protected  void onRightClick(){
        if(AbstractDungeon.currMapNode != null) {
            if ((AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) && (this.counter > 0)) {
                this.counter -= 1;
                AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, 1));
            }
        }
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new SteinsOfFate();
    }
}
