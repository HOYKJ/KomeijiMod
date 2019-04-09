package Thmod.Cards.ElementCards.SpellCards;

import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import Thmod.Cards.ElementCards.AbstractElementCards;
import Thmod.ThMod;

public abstract class AbstractElementSpellCards extends AbstractElementCards{
    private String id;
    private AbstractCard addcard;

    public AbstractElementSpellCards(final String id, final String name, final int cost, final String description, final AbstractCard.CardType type, final AbstractCard.CardRarity rarity, final AbstractCard.CardTarget target, final ElementType elementType){
        super(id,name,cost,description,type,rarity,target,elementType);
        this.isEthereal = true;
        this.exhaust = true;
        this.upgraded = true;
        this.id = id;
    }

    public AbstractElementSpellCards(final String id, final String name, final int cost, final String description, final AbstractCard.CardType type, final AbstractCard.CardRarity rarity, final AbstractCard.CardTarget target, final ElementType elementType,boolean isSP){
        super(id,name,cost,description,type,rarity,target,elementType,isSP);
        this.isEthereal = true;
        this.exhaust = true;
        this.upgraded = true;
        this.id = id;
    }

    public AbstractElementSpellCards(final String id, final String name, final int cost, final String description, final AbstractCard.CardType type, final AbstractCard.CardRarity rarity, final AbstractCard.CardTarget target, final ElementType elementType, final ElementType mixElementType){
        super(id,name,cost,description,type,rarity,target,elementType,mixElementType);
        this.isEthereal = true;
        this.exhaust = true;
        this.upgraded = true;
        this.id = id;
    }

    public AbstractElementSpellCards(final String id, final String name, final int cost, final String description, final AbstractCard.CardType type, final AbstractCard.CardRarity rarity, final AbstractCard.CardTarget target, final boolean allElements){
        super(id,name,cost,description,type,rarity,target,allElements);
        this.isEthereal = true;
        this.exhaust = true;
        this.upgraded = true;
        this.id = id;
    }

    public boolean canUpgrade()
    {
        return false;
    }

    public void triggerOnExhaust() {
        if(ThMod.removedcardids.size() > 0) {
            this.addcard = ThMod.removedcardids.get(this.id);
            if(this.addcard != null) {
                AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAction(this.addcard, 1));
            }
        }
    }

    public void triggerOnManualDiscard(){
        AbstractDungeon.actionManager.addToTop(new ExhaustSpecificCardAction(this, AbstractDungeon.player.discardPile));
        AbstractDungeon.actionManager.addToTop(new ExhaustSpecificCardAction(this, AbstractDungeon.player.hand));
    }
}
