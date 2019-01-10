package Thmod.Actions.unique;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import Thmod.Relics.FamiliarSpoon;

public class GetFamiliarSpoon extends AbstractGameAction {

    public void update() {
        AbstractDungeon.player.loseRelic("Strange Spoon");
        AbstractRelic relic = new FamiliarSpoon();
        UnlockTracker.markRelicAsSeen(relic.relicId);
        relic.obtain();
        relic.isObtained = true;
        relic.isAnimating = false;
        relic.isDone = false;
        this.isDone = true;
    }
}
