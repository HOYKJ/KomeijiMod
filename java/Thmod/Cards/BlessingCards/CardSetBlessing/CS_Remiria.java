package Thmod.Cards.BlessingCards.CardSetBlessing;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.unique.GamblingChipAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Cards.BlessingCards.AbstractBlessingCard;

public class CS_Remiria extends AbstractBlessingCard
{
    public static final String ID = "CS_Remiria";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;

    public CS_Remiria()
    {
        super("CS_Remiria", CS_Remiria.NAME, CS_Remiria.DESCRIPTION);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
    }

    public void triggerWhenDrawn()
    {
        AbstractPlayer p = AbstractDungeon.player;
        AbstractDungeon.actionManager.addToTop(new ExhaustSpecificCardAction(this, p.hand));
        AbstractDungeon.actionManager.addToTop(new DrawCardAction(p, 1));
        AbstractDungeon.actionManager.addToBottom(new GamblingChipAction(p));
    }

    public AbstractCard makeCopy()
    {
        return new CS_Remiria();
    }

    public void upgrade() {}

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("CS_Remiria");
        NAME = CS_Remiria.cardStrings.NAME;
        DESCRIPTION = CS_Remiria.cardStrings.DESCRIPTION;
    }
}
