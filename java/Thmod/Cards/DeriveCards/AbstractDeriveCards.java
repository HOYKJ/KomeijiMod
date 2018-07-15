package Thmod.Cards.DeriveCards;

import com.megacrit.cardcrawl.cards.AbstractCard;

import Thmod.Patches.AbstractCardEnum;
import Thmod.ThMod;
import basemod.abstracts.CustomCard;

public abstract class AbstractDeriveCards extends CustomCard {
    public AbstractDeriveCards(final String id, final String name, final int cost, final String description, final AbstractCard.CardType type, final AbstractCard.CardRarity rarity, final AbstractCard.CardTarget target) {
        super(id, name, ThMod.deriveCardImage(id), cost, description, type, AbstractCardEnum.衍生卡, rarity, target);
        this.isEthereal = true;
        this.exhaust = true;
    }
}
