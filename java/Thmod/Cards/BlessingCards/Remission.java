package Thmod.Cards.BlessingCards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.unique.RemoveDebuffsAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.ThMod;

public class Remission extends AbstractBlessingCard
{
    public static final String ID = "Remission";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;

    public Remission()
    {
        super("Remission", Remission.NAME, Remission.DESCRIPTION);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
    }

    public void triggerWhenDrawn()
    {
        AbstractPlayer p = AbstractDungeon.player;
        if(ThMod.blessingOfRemission >= 1) {
            AbstractDungeon.actionManager.addToTop(new ExhaustSpecificCardAction(this, p.hand));
            AbstractDungeon.actionManager.addToBottom(new RemoveDebuffsAction(p));
            AbstractDungeon.actionManager.addToTop(new DrawCardAction(p, this.magicNumber));
        }
    }

    public AbstractCard makeCopy()
    {
        return new Remission();
    }

    public void upgrade() {}

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("Remission");
        NAME = Remission.cardStrings.NAME;
        DESCRIPTION = Remission.cardStrings.DESCRIPTION;
    }
}
