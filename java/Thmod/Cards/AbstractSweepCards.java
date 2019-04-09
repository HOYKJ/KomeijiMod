package Thmod.Cards;

import com.megacrit.cardcrawl.cards.AbstractCard;

import java.util.ArrayList;

public abstract class AbstractSweepCards extends AbstractKomeijiCards{
    public AbstractSweepCards(final String id, final String name, final int cost, final String description, final AbstractCard.CardType type, final AbstractCard.CardRarity rarity, final AbstractCard.CardTarget target, CardSet_k cardSet_k) {
        super(id, name,  cost, description, type, rarity, target, cardSet_k);
        this.timesUpgraded = 1;
        this.upgraded = true;
    }

    public boolean canUpgrade()
    {
        return false;
    }

    public abstract ArrayList<AbstractSweepCards> getOpposite();
}
