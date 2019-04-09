package Thmod.Power;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;

import Thmod.vfx.JyouchiReiEffect;

public class JyouchiRei extends AbstractPower {
    public static final String POWER_ID = "JyouchiRei";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("JyouchiRei");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private AbstractPlayer p = AbstractDungeon.player;
    private ArrayList<JyouchiReiEffect> reiEffects = new ArrayList<>();

    public JyouchiRei(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = "JyouchiRei";
        this.owner = owner;
        this.amount = amount;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/JyouchiRei.png");
        this.type = PowerType.DEBUFF;
        for(int i = 0; i < amount; i++){
            if(amount == 1){
                reiEffects.add(new JyouchiReiEffect(new Color(MathUtils.random(0.8F, 1.0F), MathUtils.random(0.3F, 0.5F), MathUtils.random(0.5F, 0.8F), 1.0F), this.owner.hb));
                AbstractDungeon.effectList.add(reiEffects.get(i));
            }
            else{
                reiEffects.add(new JyouchiReiEffect(new Color(MathUtils.random(0.1F, 0.2F), MathUtils.random(0.6F, 0.8F), MathUtils.random(0.8F, 1.0F), 1.0F), this.owner.hb));
                AbstractDungeon.effectList.add(reiEffects.get(i));
            }
        }
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        super.atEndOfTurn(isPlayer);
        AbstractDungeon.actionManager.addToTop(new DamageAction(this.owner, new DamageInfo(p, this.amount, DamageInfo.DamageType.HP_LOSS), AbstractGameAction.AttackEffect.FIRE));
    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        super.onAttack(info, damageAmount, target);
        if(target != this.owner){
            AbstractDungeon.actionManager.addToTop(new DamageAction(this.owner, new DamageInfo(this.owner, this.amount, DamageInfo.DamageType.HP_LOSS), AbstractGameAction.AttackEffect.FIRE));
        }
    }

    @Override
    public void onRemove() {
        super.onRemove();
        for(JyouchiReiEffect e:reiEffects){
            e.powerRemove();
        }
    }

    @Override
    public void onDeath() {
        super.onDeath();
        for(JyouchiReiEffect e:reiEffects){
            e.powerRemove();
        }
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
