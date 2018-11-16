package Thmod.Cards.VictoryCards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.ThMod;
import basemod.abstracts.CustomCard;

public abstract class AbstractVictoryCards extends CustomCard {
    public AbstractVictoryCards(final String id, final String name, final String description, final AbstractCard.CardType type, final AbstractCard.CardRarity rarity, final AbstractCard.CardTarget target) {
        super(id, name, ThMod.victoryCardImage(id), -2, description, type, CardColor.COLORLESS, rarity, target);
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return false;
    }

    public abstract void onVictory();
}
