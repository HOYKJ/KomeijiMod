package Thmod.Cards.BlessingCards;


import Thmod.ThMod;
import basemod.abstracts.CustomCard;

public abstract class AbstractBlessingCard extends CustomCard {
    public AbstractBlessingCard(final String id, final String name, final String description) {
        super(id, name, ThMod.statusCardImage(id), -2, description, CardType.STATUS, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.NONE);
    }
}
