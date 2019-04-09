package Thmod.Cards.BlessingCards.CardSetBlessing;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ThornsPower;

import Thmod.Cards.BlessingCards.AbstractBlessingCard;

public class CS_Iku extends AbstractBlessingCard
{
    public static final String ID = "CS_Iku";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;

    public CS_Iku()
    {
        super("CS_Iku", CS_Iku.NAME, CS_Iku.DESCRIPTION);
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
    }

    public void triggerWhenDrawn()
    {
        AbstractPlayer p = AbstractDungeon.player;
        AbstractDungeon.actionManager.addToTop(new DrawCardAction(p, 1));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new ThornsPower(p, this.magicNumber)));
        AbstractDungeon.actionManager.addToBottom(new ExhaustSpecificCardAction(this, p.hand));
    }

    public AbstractCard makeCopy()
    {
        return new CS_Iku();
    }

    public void upgrade() {}

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("CS_Iku");
        NAME = CS_Iku.cardStrings.NAME;
        DESCRIPTION = CS_Iku.cardStrings.DESCRIPTION;
    }
}
