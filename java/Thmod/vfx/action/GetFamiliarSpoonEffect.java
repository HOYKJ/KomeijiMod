package Thmod.vfx.action;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

import Thmod.Relics.FamiliarSpoon;

public class GetFamiliarSpoonEffect extends AbstractGameEffect {

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

    @Override
    public void render(SpriteBatch spriteBatch) {

    }

    @Override
    public void dispose() {

    }
}
