package Thmod.Cards.DeriveCards;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FadingPower;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.EmpowerEffect;
import com.megacrit.cardcrawl.vfx.combat.RoomTintEffect;

import java.util.ArrayList;

import Thmod.Actions.Special.MusouFuuyinAction;
import Thmod.Actions.animation.LiftAction;
import Thmod.Actions.common.LatterAction;
import Thmod.Actions.common.TenseiAttackAction;
import Thmod.Power.MusouTenseiPower;
import Thmod.Power.MusuNoTegataPower;
import Thmod.Power.TenmizuPower;
import Thmod.Power.TimeLockPower;
import Thmod.Power.Weather.DaiyamondoDasuto;
import Thmod.Power.Weather.Fuuu;
import Thmod.Power.Weather.Haku;
import Thmod.Power.Weather.KawaGiri;
import Thmod.Power.Weather.KiriSame;
import Thmod.Power.Weather.Kousa;
import Thmod.Power.Weather.KyoKkou;
import Thmod.Power.Weather.Nagi;
import Thmod.Power.Weather.NouMu;
import Thmod.Power.Weather.Soyuki;
import Thmod.Power.Weather.TenkiYume;
import Thmod.Power.WocchiPower;
import Thmod.vfx.JyouchiEffect;
import Thmod.vfx.JyouchiReiEffect;
import Thmod.vfx.MusouFuuinEffect;
import Thmod.vfx.SoulEffect;
import Thmod.vfx.StopAnimEffect;
import Thmod.vfx.SunderLineHandler;
import Thmod.vfx.SunderSoulEffect;
import Thmod.vfx.YomeiCutEffect;
import Thmod.vfx.animation.ColorWaterDropEffect;
import Thmod.vfx.animation.YumeFlameCore;
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
//        AbstractDungeon.actionManager.addToBottom(new LiftAction(m));
//        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, 10, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        AbstractDungeon.effectList.add(new ColorWaterDropEffect(p.hb.cX, p.hb.cY + 32, 1.0F));
        AbstractDungeon.actionManager.addToBottom(new LatterAction(()->{
            AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p, p, new TenmizuPower(p, 1)));
        }, 0.8F));
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
