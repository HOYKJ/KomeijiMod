package Thmod.Power;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import Thmod.vfx.FireDamageEffect;
import Thmod.vfx.animation.YumeFlameCore;

public class MusuNoYumePower extends AbstractPower {
    public static final String POWER_ID = "MusuNoYumePower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("MusuNoYumePower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private int damage;
    private float timer;

    public MusuNoYumePower(AbstractCreature owner,int damage) {
        this.name = NAME;
        this.ID = "MusuNoYumePower";
        this.owner = owner;
        this.amount = 3;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/MusuNoYumePower.png");
        this.type = PowerType.DEBUFF;
        this.damage = damage;
        this.timer = 2;

        for(int i = 0; i < 4; i ++){
            AbstractDungeon.effectList.add(new YumeFlameCore(this.owner.hb));
        }
    }

    public void atStartOfTurn() {
        AbstractDungeon.actionManager.addToTop(new DamageAction(this.owner, new DamageInfo(this.owner, this.damage, DamageInfo.DamageType.HP_LOSS), AbstractGameAction.AttackEffect.NONE));
        this.owner.tint.color.set(Color.NAVY.cpy());
        this.owner.tint.changeColor(Color.WHITE.cpy(), 2.0F);
        AbstractDungeon.effectList.add(new FireDamageEffect(this.owner.hb.cX, this.owner.hb.cY, Color.NAVY.cpy()));
        //flash();
        if (this.amount == 1)
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, this));
        else
            this.amount -= 1;
    }

    @Override
    public void update(int slot) {
        super.update(slot);
        this.timer -= Gdx.graphics.getDeltaTime();
        if(this.timer < 0){
            this.timer = 2;
            AbstractDungeon.effectList.add(new YumeFlameCore(this.owner.hb));
        }
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0];
    }
}
