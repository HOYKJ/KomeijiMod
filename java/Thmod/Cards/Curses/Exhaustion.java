package Thmod.Cards.Curses;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ConstrictedPower;

import basemod.abstracts.CustomCard;

public class Exhaustion extends CustomCard
{
    public static final String ID = "Exhaustion";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = -2;

    public Exhaustion()
    {
        super("Exhaustion", Exhaustion.NAME,"images/cards/curse/Exhaustion.png", -2, Exhaustion.DESCRIPTION,CardType.CURSE, CardColor.CURSE, CardRarity.CURSE, CardTarget.NONE);
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
    }

    public void triggerWhenDrawn() {
        AbstractPlayer p = AbstractDungeon.player;
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p, p, new ConstrictedPower(p, p, this.magicNumber), this.magicNumber));
        AbstractDungeon.actionManager.addToTop(new ExhaustSpecificCardAction(this, p.hand));
    }

    public void triggerOnEndOfTurnForPlayingCard()
    {
    }

    public AbstractCard makeCopy()
    {
        return new Exhaustion();
    }

    public void upgrade() {}

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("Exhaustion");
        NAME = Exhaustion.cardStrings.NAME;
        DESCRIPTION = Exhaustion.cardStrings.DESCRIPTION;
    }
}
