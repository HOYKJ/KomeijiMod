package Thmod.Cards.ElementCards;

import com.megacrit.cardcrawl.cards.AbstractCard;

import java.util.ArrayList;

public abstract class AbstractElementSweepCards extends AbstractElementCards{
    public AbstractElementSweepCards(final String id, final String name, final int cost, final String description, final AbstractCard.CardType type, final AbstractCard.CardRarity rarity, final AbstractCard.CardTarget target, final ElementType elementType) {
        super(id, name, cost, description, type, rarity, target, elementType);
        this.upgraded = true;
    }

    public boolean canUpgrade()
    {
        return false;
    }

    public abstract ArrayList<AbstractElementSweepCards> getOpposite();
}
