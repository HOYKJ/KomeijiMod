package Thmod.Actions.Special;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;

import java.util.ArrayList;

import Thmod.vfx.RoundDiggerEffect;

public class RoundDiggerAction extends AbstractGameEffect {
    private int num;
    private boolean isRed;
    private ArrayList<AbstractCreature> target;
    private float stakeTimer = 0.1F;
    private boolean hasScreen = false;

    public RoundDiggerAction(int num, boolean isRed, ArrayList<AbstractCreature> target){
        this.num = num;
        this.isRed = isRed;
        this.target = target;
    }

    public void update(){
        if(!hasScreen){
            if(isRed)
                AbstractDungeon.effectsQueue.add(new BorderLongFlashEffect(Color.CORAL));
            else
                AbstractDungeon.effectsQueue.add(new BorderLongFlashEffect(Color.SKY));
            hasScreen = true;
        }
        this.stakeTimer -= Gdx.graphics.getDeltaTime();
//        if(this.stakeTimer < 0.0F) {
//            for(AbstractCreature target:this.target) {
//                if ((!(target.isDying)) && (target.currentHealth > 0) && (!(target.isEscaping))) {
//                    AbstractDungeon.actionManager.addToTop(new NewDamageAction(target, new DamageInfo(AbstractDungeon.player, 1, DamageInfo.DamageType.THORNS), true));
//                }
//            }
//        }
        if (this.stakeTimer < 0.0F){
            for(AbstractCreature target:this.target) {
                if ((!(target.isDying)) && (target.currentHealth > 0) && (!(target.isEscaping))) {
                    AbstractDungeon.effectsQueue.add(new RoundDiggerEffect(target.hb.cX, target.hb.cY, this.isRed, target));
                }
            }
            this.stakeTimer = 0.1F;
            this.num -= 1;
            if (this.num == 0) {
                this.isDone = true;
            }
        }
    }
    public void render(SpriteBatch sb)
    {

    }

    public void dispose(){}
}
