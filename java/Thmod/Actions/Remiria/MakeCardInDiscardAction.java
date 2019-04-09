package Thmod.Actions.Remiria;

import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;

public class MakeCardInDiscardAction extends AbstractGameAction {
    private AbstractCard cardToMake;

    public MakeCardInDiscardAction(AbstractCard card) {
        UnlockTracker.markCardAsSeen(card.cardID);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
        this.cardToMake = card;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {

            AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(this.cardToMake));

            this.duration -= Gdx.graphics.getDeltaTime();
        }

        this.tickDuration();
    }
}
