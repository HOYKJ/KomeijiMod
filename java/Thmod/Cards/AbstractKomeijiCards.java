package Thmod.Cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import Thmod.Patches.AbstractCardEnum;
import Thmod.ThMod;
import basemod.abstracts.CustomCard;

public abstract class AbstractKomeijiCards extends AbstractSetCards {
    public AbstractKomeijiCards(final String id, final String name, final int cost, final String description, final AbstractCard.CardType type, final AbstractCard.CardRarity rarity, final AbstractCard.CardTarget target, CardSet_k cardSet_k) {
        super(id, name, ThMod.komeijiCardImage(id,false), cost, description, type, AbstractCardEnum.古明地觉, rarity, target, cardSet_k);
    }

//    public void applyPowers() {
//        if(AbstractDungeon.player.hasPower("DashPower"))
//            this.setCostForTurn(-9);
//    }
}
