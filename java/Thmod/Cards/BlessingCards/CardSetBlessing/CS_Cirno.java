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

import static Thmod.Power.LunaticRedEyesPower.DESCRIPTIONS;

public class CS_Cirno extends AbstractBlessingCard
{
    public static final String ID = "CS_Cirno";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;

    public CS_Cirno()
    {
        super("CS_Cirno", CS_Cirno.NAME, CS_Cirno.DESCRIPTION);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
    }

    public void triggerWhenDrawn()
    {
        AbstractPlayer p = AbstractDungeon.player;
        AbstractDungeon.actionManager.addToTop(new DrawCardAction(p, 1));
        AbstractDungeon.actionManager.addToBottom(new LatterAction(()->{
            final ChooseAction choice = new ChooseAction(this, null, CS_Cirno.EXTENDED_DESCRIPTION[0], true, 1, true);
            for(AbstractCard card : p.hand.group){
                if(!(card instanceof AbstractSpellCards) && !(card instanceof AbstractElementSpellCards) && !(card instanceof AbstractDeriveCards) && !(card instanceof AbstractItemCards)) {
                    choice.add(card, () -> {
                        card.rawDescription = card.rawDescription + CS_Cirno.EXTENDED_DESCRIPTION[1];
                        card.retain = true;
                        card.initializeDescription();
                    });
                }
            }
            AbstractDungeon.actionManager.addToBottom(choice);
        }, 0.1f));
        AbstractDungeon.actionManager.addToBottom(new ExhaustSpecificCardAction(this, p.hand));
    }

    public AbstractCard makeCopy()
    {
        return new CS_Cirno();
    }

    public void upgrade() {}

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("CS_Cirno");
        NAME = CS_Cirno.cardStrings.NAME;
        DESCRIPTION = CS_Cirno.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = CS_Cirno.cardStrings.EXTENDED_DESCRIPTION;
    }
}
