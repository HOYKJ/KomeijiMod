package Thmod.Cards.ColorlessCards;

import com.megacrit.cardcrawl.cards.AbstractCard;

import Thmod.ThMod;
import basemod.abstracts.CustomCard;

public abstract class AbstractColorlessCards extends CustomCard {
    public AbstractColorlessCards(final String id, final String name, final int cost, final String description, final AbstractCard.CardType type, final AbstractCard.CardRarity rarity, final AbstractCard.CardTarget target) {
        super(id, name, ThMod.colorlessCardImage(id), cost, description, type, CardColor.COLORLESS, rarity, target);
    }
}
