package Thmod.Cards.BlessingCards.CardSetBlessing;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Actions.common.LatterAction;
import Thmod.Actions.unique.ChooseAction;
import Thmod.Cards.BlessingCards.AbstractBlessingCard;
import Thmod.Cards.DeriveCards.AbstractDeriveCards;
import Thmod.Cards.ElementCards.SpellCards.AbstractElementSpellCards;
import Thmod.Cards.ItemCards.AbstractItemCards;
import Thmod.Cards.SpellCards.AbstractSpellCards;

public class CS_Utsuho extends AbstractBlessingCard
{
    public static final String ID = "CS_Utsuho";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;

    public CS_Utsuho()
    {
        super("CS_Utsuho", CS_Utsuho.NAME, CS_Utsuho.DESCRIPTION);
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
    }

    public void triggerWhenDrawn()
    {
        AbstractPlayer p = AbstractDungeon.player;
        AbstractDungeon.actionManager.addToTop(new DrawCardAction(p, 1));
        AbstractDungeon.actionManager.addToBottom(new LatterAction(()-> {
            final ChooseAction choice = new ChooseAction(this, null, CS_Utsuho.EXTENDED_DESCRIPTION[0], true, this.magicNumber, true);
            for (AbstractCard card : p.hand.group) {
                if (!(card instanceof AbstractSpellCards) && !(card instanceof AbstractElementSpellCards) && !(card instanceof AbstractDeriveCards) && !(card instanceof AbstractItemCards)) {
                    choice.add(card, () -> {
                        AbstractDungeon.actionManager.addToBottom(new ExhaustSpecificCardAction(card, p.hand));
                    });
                }
            }
            AbstractDungeon.actionManager.addToBottom(choice);
        }, 0.1f));
        AbstractDungeon.actionManager.addToBottom(new ExhaustSpecificCardAction(this, p.hand));
    }

    public AbstractCard makeCopy()
    {
        return new CS_Utsuho();
    }

    public void upgrade() {}

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("CS_Utsuho");
        NAME = CS_Utsuho.cardStrings.NAME;
        DESCRIPTION = CS_Utsuho.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = CS_Utsuho.cardStrings.EXTENDED_DESCRIPTION;
    }
}
