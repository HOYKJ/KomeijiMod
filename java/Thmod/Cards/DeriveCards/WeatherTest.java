package Thmod.Cards.DeriveCards;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FadingPower;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import java.util.ArrayList;

import Thmod.Actions.Special.MusouFuuyinAction;
import Thmod.Actions.common.TenseiAttackAction;
import Thmod.Power.MusouTenseiPower;
import Thmod.Power.TimeLockPower;
import Thmod.Power.Weather.Fuuu;
import Thmod.Power.Weather.Haku;
import Thmod.Power.Weather.KawaGiri;
import Thmod.Power.Weather.KiriSame;
import Thmod.Power.Weather.KyoKkou;
import Thmod.Power.Weather.Nagi;
import Thmod.Power.Weather.NouMu;
import Thmod.Power.Weather.Soyuki;
import Thmod.Power.Weather.TenkiYume;
import Thmod.Power.WocchiPower;
import Thmod.vfx.JyouchiEffect;
import Thmod.vfx.JyouchiReiEffect;
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
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new Nagi(p)));
        //AbstractDungeon.actionManager.addToBottom(new TenseiAttackAction(1, p.hb));
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
