package Thmod.Power.Weather;

import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;
import java.util.Iterator;

import Thmod.Power.HibernatePower;
import Thmod.vfx.weather.DiamondDustBack;
import Thmod.vfx.weather.DiamondDustEffect;

public class DaiyamondoDasuto extends AbstractPower {
    public static final String POWER_ID = "KaiSei";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("DaiyamondoDasuto");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private ArrayList<DiamondDustEffect> dust = new ArrayList<>();
    private float dustTimer = 1.0F;
    private DiamondDustBack back;

    public DaiyamondoDasuto(AbstractCreature owner) {
        this.name = NAME;
        this.ID = "DaiyamondoDasuto";
        this.owner = owner;
        this.amount = 5;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/Weather/DaiyamondoDasuto.png");
        this.type = PowerType.BUFF;
        this.back = new DiamondDustBack();
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
            this.dust.add(new DiamondDustEffect());
            AbstractDungeon.effectList.add(this.dust.get(this.dust.size() - 1));
        }
        for (Iterator<DiamondDustEffect> e = this.dust.iterator(); e.hasNext();)
        {
            DiamondDustEffect effect = e.next();
            effect.update();
            if (effect.isDone) {
                e.remove();
            }
        }
    }

    public void atEndOfRound() {
        for (int i = (AbstractDungeon.getCurrRoom().monsters.monsters.size() - 1); i >= 0; i--) {
            AbstractMonster target = AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
            if ((!(target.isDying)) && (target.currentHealth > 0) && (!(target.isEscaping))) {
                if(AbstractMonster.Intent.valueOf(target.intent.name()) == AbstractMonster.Intent.SLEEP) {
                    AbstractDungeon.actionManager.addToTop(new DamageAction(target, new DamageInfo(this.owner, 999, DamageInfo.DamageType.HP_LOSS)));
                    flash();
                }
            }
        }
        if (this.amount <= 1)
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "DaiyamondoDasuto"));
        else
            this.amount -= 1;

        if(AbstractDungeon.player.hasPower(HibernatePower.POWER_ID)){
            AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, new DamageInfo(this.owner, 1, DamageInfo.DamageType.HP_LOSS)));
        }
    }

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
