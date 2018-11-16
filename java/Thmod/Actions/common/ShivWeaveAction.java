package Thmod.Actions.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;

import java.util.ArrayList;

import Thmod.vfx.RoundDiggerEffect;
import Thmod.vfx.ShivWeaveEffect;

public class ShivWeaveAction extends AbstractGameEffect {
    private int num = 0;
    private int[] damage;
    private float stakeTimer = 0.1F;
    private boolean hasScreen = false;
    private Color color;
    private boolean attacked;

    public ShivWeaveAction(int[] amount, boolean isRed){
        this.damage = amount;
        for(int i = 0; i < amount.length; i++){
            if(amount[i] > this.num)
                this.num = amount[i];
        }
        if(isRed)
            this.color = Color.CORAL;
        else
            this.color = Color.SKY;
    }

    public void update(){
        if(!hasScreen){
            AbstractDungeon.effectsQueue.add(new BorderLongFlashEffect(this.color));
            hasScreen = true;
        }
        this.stakeTimer -= Gdx.graphics.getDeltaTime();

        if (this.stakeTimer < 0.0F){
            AbstractDungeon.effectsQueue.add(new ShivWeaveEffect(MathUtils.random(1200.0F, 2000.0F) * Settings.scale, AbstractDungeon.floorY + MathUtils.random(-100.0F, 500.0F) * Settings.scale, this.color));
            if(!this.attacked) {
                AbstractDungeon.actionManager.addToTop(new NewDamageAllEnemiesAction(AbstractDungeon.player, this.damage, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL, this.num));
                this.attacked = true;
            }
            this.stakeTimer = 0.03F;
            this.num -= 1;
            if (this.num <= 0) {
                this.isDone = true;
            }
        }
    }
    public void render(SpriteBatch sb)
    {

    }
}
