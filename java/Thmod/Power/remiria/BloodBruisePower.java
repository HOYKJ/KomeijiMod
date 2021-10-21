package Thmod.Power.remiria;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;

import Thmod.Cards.ScarletCard.uncommonCards.PainBurst;
import Thmod.Characters.RemiriaScarlet;
import Thmod.vfx.BloodTrailEffect;
import Thmod.vfx.JyouchiReiEffect;

public class BloodBruisePower extends AbstractPower {
    public static final String POWER_ID = "BloodBruisePower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("BloodBruisePower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private ArrayList<BloodTrailEffect> bloodEffects = new ArrayList<>();

    public BloodBruisePower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = "BloodBruisePower";
        this.owner = owner;
        this.amount = amount;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/remiria/BloodBruisePower.png");
        this.type = PowerType.DEBUFF;
    }

    public void init(){
        for(int i = 0; i <= amount / 12; i++){
            this.bloodEffects.add(new BloodTrailEffect(new Color(MathUtils.random(0.6F, 0.65F), MathUtils.random(0.02F, 0.07F), MathUtils.random(0.00F, 0.02F), 1.0F), this.owner.hb));
            AbstractDungeon.effectList.add(bloodEffects.get(i));
            if(this.bloodEffects.size() >= 2000){
                return;
            }
        }
    }

    @Override
    public void atEndOfRound() {
        super.atEndOfRound();
        if(!this.owner.hasPower(RedMagicPower.POWER_ID)) {
            float multiple = 0.5f * (float) Math.pow(1.5, this.amount / 12);

            AbstractDungeon.actionManager.addToBottom(new DamageAction(this.owner, new DamageInfo(this.owner, (int) (this.amount * multiple), DamageInfo.DamageType.HP_LOSS),
                    AbstractGameAction.AttackEffect.NONE));
            if (this.owner instanceof RemiriaScarlet) {
                ((RemiriaScarlet) this.owner).bloodCounter += (int) ((float) this.amount / 2);
                for (AbstractCard card : AbstractDungeon.player.hand.group) {
                    if (card instanceof PainBurst) {
                        ((PainBurst) card).reduceCost();
                    }
                }
                for (AbstractCard card : AbstractDungeon.player.drawPile.group) {
                    if (card instanceof PainBurst) {
                        ((PainBurst) card).reduceCost();
                    }
                }
                for (AbstractCard card : AbstractDungeon.player.discardPile.group) {
                    if (card instanceof PainBurst) {
                        ((PainBurst) card).reduceCost();
                    }
                }
            }
        }
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        super.atEndOfTurn(isPlayer);

    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        super.onAttack(info, damageAmount, target);
        if(this.owner instanceof RemiriaScarlet){
            if(target.hasPower(this.ID)) {
                int tmp = Math.min(damageAmount, target.currentHealth);
                float num = Math.min(this.amount * 0.015f, 0.3f);
                AbstractDungeon.player.heal((int) (num * tmp));
            }
        }
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        if(this.bloodEffects.size() >= 2000){
            return;
        }
        if(this.amount / 12 >= this.bloodEffects.size()){
            for(int i = this.bloodEffects.size(); i <= amount / 12; i++){
                this.bloodEffects.add(new BloodTrailEffect(new Color(MathUtils.random(0.6F, 0.65F), MathUtils.random(0.02F, 0.07F), MathUtils.random(0.00F, 0.02F), 1.0F), this.owner.hb));
                AbstractDungeon.effectList.add(bloodEffects.get(i));
                if(this.bloodEffects.size() >= 2000){
                    return;
                }
            }
        }
    }

    @Override
    public void reducePower(int reduceAmount) {
        super.reducePower(reduceAmount);
        while (this.amount / 12 < this.bloodEffects.size()){
            this.bloodEffects.get(0).powerRemove();
            this.bloodEffects.remove(0);
        }
    }

    @Override
    public void onRemove() {
        super.onRemove();
        for(BloodTrailEffect e : bloodEffects){
            e.powerRemove();
        }
        this.bloodEffects.clear();
    }

    @Override
    public void onDeath() {
        super.onDeath();
        for(BloodTrailEffect e : bloodEffects){
            e.powerRemove();
        }
        this.bloodEffects.clear();
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0] + DESCRIPTIONS[5] + (int)(0.5f * (float) Math.pow(1.5, this.amount / 12) * this.amount) + DESCRIPTIONS[6];
    }
}
