package Thmod.Cards.DeriveCards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

import Thmod.Actions.Special.MusouFuuyinAction;
import Thmod.Power.TimeLockPower;
import Thmod.Power.Weather.Fuuu;
import Thmod.Power.Weather.Haku;
import Thmod.Power.Weather.KawaGiri;
import Thmod.Power.Weather.KiriSame;
import Thmod.Power.Weather.KyoKkou;
import Thmod.Power.Weather.NouMu;
import Thmod.Power.Weather.Soyuki;
import Thmod.Power.Weather.TenkiYume;
import Thmod.Power.WocchiPower;
import Thmod.vfx.JyouchiEffect;
import Thmod.vfx.MusouFuuinEffect;
import basemod.DevConsole;

public class WeatherTest extends AbstractDeriveCards {
    public static final String ID = "WeatherTest";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 0;
    private ArrayList<AbstractCreature> target = new ArrayList<>();

    public WeatherTest() {
        super("WeatherTest", WeatherTest.NAME,  0, WeatherTest.DESCRIPTION, CardType.SKILL, CardRarity.SPECIAL, CardTarget.ENEMY);
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p,p,new KawaGiri(p),1));

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
