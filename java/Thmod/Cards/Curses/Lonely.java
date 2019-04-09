package Thmod.Cards.Curses;

import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;

public class Lonely extends CustomCard
{
    public static final String ID = "Lonely";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = -2;

    public Lonely()
    {
        super("Lonely", Lonely.NAME,"images/cards/curse/Lonely.png", -2, Lonely.DESCRIPTION,CardType.CURSE, CardColor.CURSE, CardRarity.CURSE, CardTarget.NONE);
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
    }

    public void triggerOnEndOfTurnForPlayingCard()
    {
    }

    public void drawCard(){
        AbstractDungeon.actionManager.addToBottom(new DiscardSpecificCardAction(this));
    }

    public AbstractCard makeCopy()
    {
        return new Lonely();
    }

    public void upgrade() {}

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("Lonely");
        NAME = Lonely.cardStrings.NAME;
        DESCRIPTION = Lonely.cardStrings.DESCRIPTION;
    }
}
