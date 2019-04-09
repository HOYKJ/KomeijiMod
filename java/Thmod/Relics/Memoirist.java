package Thmod.Relics;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import Thmod.Actions.unique.AssociationAction;
import Thmod.Actions.unique.ChooseAction;
import Thmod.Cards.AbstractSweepCards;
import Thmod.Cards.ElementCards.AbstractElementSweepCards;

public class Memoirist extends AbstractThRelic {
    public static final String ID = "Memoirist";
    private boolean candDo;

    public Memoirist()
    {
        super("Memoirist",  RelicTier.BOSS, LandingSound.FLAT);
        this.candDo = false;
    }

    protected void onRightClick(){
        if(AbstractDungeon.currMapNode != null) {
            if ((AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT)) {
                final ChooseAction choice = new ChooseAction(null, null, DESCRIPTIONS[1], false, 1);
                for (AbstractCard card : AbstractDungeon.player.hand.group) {
                    if ((card instanceof AbstractSweepCards) || (card instanceof AbstractElementSweepCards)) {
                        if(!card.purgeOnUse) {
                            this.candDo = true;
                            choice.add(card, () -> {
                                AbstractDungeon.actionManager.addToBottom(new AssociationAction(card));
                            });
                        }
                    }
                }
                if (this.candDo) {
                    AbstractDungeon.actionManager.addToBottom(choice);
                    this.candDo = false;
                }
            }
        }
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new Memoirist();
    }
}
