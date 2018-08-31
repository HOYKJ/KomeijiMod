package Thmod.Cards.DeriveCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Power.Weather.TenkiYume;

public class WeatherTest extends AbstractDeriveCards {
    public static final String ID = "WeatherTest";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 0;

    public WeatherTest() {
        super("WeatherTest", WeatherTest.NAME,  0, WeatherTest.DESCRIPTION, CardType.SKILL, CardRarity.SPECIAL, CardTarget.NONE);
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p, p, new TenkiYume(p)));
    }

    public AbstractCard makeCopy() {
        return new WeatherTest();
    }

    public void upgrade() {
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("WeatherTest");
        NAME = WeatherTest.cardStrings.NAME;
        DESCRIPTION = WeatherTest.cardStrings.DESCRIPTION;
    }
}
