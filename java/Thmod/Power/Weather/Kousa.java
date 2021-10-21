package Thmod.Power.Weather;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;
import java.util.Iterator;

import Thmod.vfx.weather.KousaBack;
import Thmod.vfx.weather.KousaDustEffect;

public class Kousa extends AbstractPower {
    public static final String POWER_ID = "Kousa";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Kousa");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private boolean damaged;
    private ArrayList<KousaDustEffect> dust = new ArrayList<>();
    private float dustTimer = 1.0F;
    private KousaBack back;

    public Kousa(AbstractCreature owner) {
        this.name = NAME;
        this.ID = "KaiSei";
        this.owner = owner;
        this.amount = 5;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/Weather/Kousa.png");
        this.type = PowerType.BUFF;
        this.damaged = true;
        this.back = new KousaBack();
        this.back.initializeData();
        AbstractDungeon.effectList.add(this.back);
    }

    @Override
    public void update(int slot) {
        super.update(slot);
        this.dustTimer -= Gdx.graphics.getDeltaTime();
        if (this.dustTimer < 0.0F)
        {
            this.dustTimer = 0.03F;
            this.dust.add(new KousaDustEffect());
            AbstractDungeon.effectList.add(this.dust.get(this.dust.size() - 1));
        }
        for (Iterator<KousaDustEffect> e = this.dust.iterator(); e.hasNext();)
        {
            KousaDustEffect effect = e.next();
            effect.update();
            if (effect.isDone) {
                e.remove();
            }
        }
    }

    public float atDamageGive(float damage, DamageInfo.DamageType type)
    {
        if ((type == DamageInfo.DamageType.NORMAL)&&(this.damaged)) {
            flash();
            this.damaged = false;
            return (damage * 1.33F);
        }
        else
            return damage;
    }

    public void atEndOfRound() {
        if (this.amount <= 1)
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "Kousa"));
        else
            this.amount -= 1;
        this.damaged = true;
    }

//    @Override
//    public void renderIcons(SpriteBatch sb, float x, float y, Color c) {
//        super.renderIcons(sb, x, y, c);
//        for (KousaDustEffect e : this.dust) {
//            e.render(sb, 0.0F, -50.0F * Settings.scale + Settings.HEIGHT - 1300.0F * Settings.scale);
//        }
//    }

    @Override
    public void onRemove() {
        super.onRemove();
        this.back.remove();
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0];
    }
}
