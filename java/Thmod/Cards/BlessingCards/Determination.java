package Thmod.Cards.BlessingCards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.ThMod;

public class Determination extends AbstractBlessingCard
{
    public static final String ID = "Determination";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;

    public Determination()
    {
        super("Determination", Determination.NAME, Determination.DESCRIPTION);
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
    }

    public void triggerWhenDrawn()
    {
        AbstractPlayer p = AbstractDungeon.player;
        if(ThMod.blessingOfDetermination >= 1) {
            AbstractDungeon.actionManager.addToTop(new ExhaustSpecificCardAction(this, p.hand));
            AbstractDungeon.actionManager.addToBottom(new HealAction(p, p, this.magicNumber));
            AbstractDungeon.actionManager.addToTop(new DrawCardAction(p, 1));
        }
    }

    public AbstractCard makeCopy()
    {
        return new Determination();
    }

    public void upgrade() {}

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("Determination");
        NAME = Determination.cardStrings.NAME;
        DESCRIPTION = Determination.cardStrings.DESCRIPTION;
    }
}
