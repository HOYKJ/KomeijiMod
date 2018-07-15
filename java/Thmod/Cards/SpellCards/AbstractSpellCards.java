package Thmod.Cards.SpellCards;

import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;

import Thmod.Patches.AbstractCardEnum;
import Thmod.ThMod;
import basemod.abstracts.CustomCard;

public abstract class AbstractSpellCards extends CustomCard {
    private static final CardStrings cardStrings;
    public static final String[] EXTENDED_DESCRIPTION;
    private String id;
    private AbstractCard addcard;

    public AbstractSpellCards(final String id, final String name, final int cost, final String description, final AbstractCard.CardType type, final AbstractCard.CardRarity rarity, final AbstractCard.CardTarget target) {
        super(id, name, ThMod.spellCardImage(id), cost, description, type, AbstractCardEnum.Sp符卡, rarity, target);
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
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAction(this.addcard, 1));
        }
    }

    public void triggerOnManualDiscard(){
        AbstractDungeon.actionManager.addToTop(new ExhaustSpecificCardAction(this, AbstractDungeon.player.discardPile));
        AbstractDungeon.actionManager.addToTop(new ExhaustSpecificCardAction(this, AbstractDungeon.player.hand));
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("SpellCards");
        EXTENDED_DESCRIPTION = AbstractSpellCards.cardStrings.EXTENDED_DESCRIPTION;
    }
}
