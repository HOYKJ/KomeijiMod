package Thmod.Power;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import java.util.ArrayList;

import Thmod.Actions.common.LatterAction;
import Thmod.ThMod;
import Thmod.vfx.FireDamageEffect;
import Thmod.vfx.animation.CustomPetalEffect;
import Thmod.vfx.animation.TegataFlameCore;

public class MusuNoTegataPower extends AbstractPower {
    public static final String POWER_ID = "MusuNoTegataPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("MusuNoTegataPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private int damage;
    private ArrayList<TegataFlameCore> effcts = new ArrayList<>();

    public MusuNoTegataPower(AbstractCreature owner, int Amount) {
        this.name = NAME;
        this.ID = "MusuNoTegataPower";
        this.owner = owner;
        this.amount = 6;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/MusuNoTegataPower.png");
        this.type = PowerType.DEBUFF;
        this.damage = Amount;
        for(int i = 0; i < 7; i ++){
            this.effcts.add(new TegataFlameCore(this.owner.hb, i));
            AbstractDungeon.effectsQueue.add(this.effcts.get(i));
        }
    }

    public void atStartOfTurn() {
        if (this.amount == 1) {
            this.effcts.get(0).end();
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, this));
            AbstractDungeon.actionManager.addToBottom(new LatterAction(()->{
                AbstractDungeon.actionManager.addToTop(new DamageAction(this.owner, new DamageInfo(this.owner, this.damage, DamageInfo.DamageType.HP_LOSS), AbstractGameAction.AttackEffect.NONE));
                AbstractDungeon.actionManager.addToBottom(new LatterAction(()->{
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new VulnerablePower(this.owner, 2, false), 1));
                }, 0.6F));

                this.owner.tint.color.set(Color.PINK.cpy());
                this.owner.tint.changeColor(Color.WHITE.cpy(), 2.0F);
                AbstractDungeon.effectList.add(new FireDamageEffect(this.owner.hb.cX, this.owner.hb.cY, Color.PINK.cpy()));
                for(int i = 0; i < 18; i ++){
                    AbstractDungeon.effectList.add(new CustomPetalEffect(this.owner.hb.cX, this.owner.hb.cY, 0.4F, Color.PINK.cpy(), false));
                }
            }, 2F));

        } else {
            this.amount -= 1;
            this.effcts.get(this.amount).end();
        }
    }

    @Override
    public void onRemove() {
        super.onRemove();
        for(int i = 0; i < 7; i ++){
            this.effcts.get(i).remove();
        }
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
