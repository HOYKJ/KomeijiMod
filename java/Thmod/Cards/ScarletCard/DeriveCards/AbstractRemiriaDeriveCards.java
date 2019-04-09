package Thmod.Cards.ScarletCard.DeriveCards;

import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import Thmod.Cards.ScarletCard.AbstractRemiriaCards;
import Thmod.Patches.AbstractCardEnum;
import Thmod.ThMod;

public abstract class AbstractRemiriaDeriveCards extends AbstractRemiriaCards {
    public AbstractRemiriaDeriveCards(final String id, final String name, final int cost, final String description, final AbstractCard.CardType type, final AbstractCard.CardRarity rarity, final AbstractCard.CardTarget target, final boolean isPlus) {
        super(id, name, cost, description, type, rarity, target, isPlus);
        this.color = AbstractCardEnum.彩蛋;
        this.isEthereal = true;
        this.exhaust = true;
    }

    public void triggerOnManualDiscard(){
        AbstractDungeon.actionManager.addToTop(new ExhaustSpecificCardAction(this, AbstractDungeon.player.discardPile));
        AbstractDungeon.actionManager.addToTop(new ExhaustSpecificCardAction(this, AbstractDungeon.player.hand));
    }
}

