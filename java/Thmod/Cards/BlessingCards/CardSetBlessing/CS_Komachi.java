package Thmod.Cards.BlessingCards.CardSetBlessing;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

import Thmod.Actions.common.LatterAction;
import Thmod.Actions.unique.ChooseAction;
import Thmod.Cards.BlessingCards.AbstractBlessingCard;
import Thmod.Cards.DeriveCards.AbstractDeriveCards;
import Thmod.Cards.ElementCards.SpellCards.AbstractElementSpellCards;
import Thmod.Cards.ItemCards.AbstractItemCards;
import Thmod.Cards.SpellCards.AbstractSpellCards;

public class CS_Komachi extends AbstractBlessingCard
{
    public static final String ID = "CS_Komachi";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;
    public AbstractCard card1;

    public CS_Komachi()
    {
        super("CS_Komachi", CS_Komachi.NAME, CS_Komachi.DESCRIPTION);
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
    }

    public void triggerWhenDrawn()
    {
        AbstractPlayer p = AbstractDungeon.player;
        ArrayList<AbstractCard> cardGroup = new ArrayList<>();
        AbstractDungeon.actionManager.addToTop(new DrawCardAction(p, 1));
        AbstractDungeon.actionManager.addToBottom(new LatterAction(()-> {
            for (AbstractCard card : p.hand.group) {
                if ((card.costForTurn >= 0) && !(card instanceof AbstractSpellCards) && !(card instanceof AbstractElementSpellCards) && !(card instanceof AbstractDeriveCards) && !(card instanceof AbstractItemCards)) {
                    cardGroup.add(card);
                }
            }
            if (cardGroup.size() >= 2) {
                final ChooseAction choice = new ChooseAction(this, null, CS_Komachi.EXTENDED_DESCRIPTION[0], true, 2);
                for (AbstractCard card : cardGroup) {
                    choice.add(card, () -> {
                        if (this.card1 == null) {
                            this.card1 = card;
                        } else {
                            int cost1 = card.costForTurn;
                            card.costForTurn = this.card1.costForTurn;

                            if (card.cost != card.costForTurn) {
                                card.isCostModified = true;
                            }

                            card.cost = card.costForTurn;

                            this.card1.costForTurn = cost1;

                            if (this.card1.cost != this.card1.costForTurn) {
                                this.card1.isCostModified = true;
                            }

                            this.card1.cost = this.card1.costForTurn;
                        }
                    });
                }
                AbstractDungeon.actionManager.addToBottom(choice);
            }
        }, 0.1f));
        AbstractDungeon.actionManager.addToBottom(new ExhaustSpecificCardAction(this, p.hand));
    }

    public AbstractCard makeCopy()
    {
        return new CS_Komachi();
    }

    public void upgrade() {}

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("CS_Komachi");
        NAME = CS_Komachi.cardStrings.NAME;
        DESCRIPTION = CS_Komachi.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = CS_Komachi.cardStrings.EXTENDED_DESCRIPTION;
    }
}
