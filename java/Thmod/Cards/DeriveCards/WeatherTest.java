package Thmod.Cards.DeriveCards;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.MindblastEffect;
import com.megacrit.cardcrawl.vfx.combat.RoomTintEffect;
import com.megacrit.cardcrawl.vfx.combat.ThrowShivEffect;

import java.util.ArrayList;

import Thmod.Actions.common.RoundDiggerAction;
import Thmod.Actions.common.ShivWeaveAction;
import Thmod.vfx.AgararetaCoverEffect;
import Thmod.vfx.BorderVeryLongFlashEffect;
import Thmod.vfx.MusouFuinEffect;
import Thmod.vfx.RedFogCoverEffect;
import Thmod.vfx.RoundDiggerEffect;
import Thmod.vfx.ShivWeaveEffect;
import Thmod.vfx.SparkEffect;

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
