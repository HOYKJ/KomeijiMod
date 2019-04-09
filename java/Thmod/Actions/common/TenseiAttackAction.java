package Thmod.Actions.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

import Thmod.Power.MusouTenseiPower;
import Thmod.vfx.ChangeBGM;
import Thmod.vfx.TenseiAttackSpell;
import Thmod.vfx.TenseiAttackWave;

import static java.lang.Math.PI;

public class TenseiAttackAction extends AbstractGameAction {
    private ArrayList<Float> ro = new ArrayList<>();
    private int damageAmount;
    private int damageTimes;
    private int damagePerOnce;
    private int spellNum;
    private int waveNum;
    private Vector2 starter;
    private float duration2;
    private float duration2Num;
    private float waiter;

    public TenseiAttackAction(int damage, Hitbox hb){
        this.startDuration = 0.1f;
        this.duration = this.startDuration;
        this.spellNum = 35;
        this.duration2Num = 0.15f;
        this.duration2 = this.duration2Num;
        this.waveNum = 22;
        //this.waveNum = 1;

        ro.add(0.0f);
        ro.add((float)(PI / 2));
        ro.add((float)(PI));
        ro.add((float)(PI / 2 * 3));

        ro.add(0.0f);
        ro.add((float)(PI / 3 * 2));
        ro.add((float)(PI / 3 * 4));

        this.starter = new Vector2(hb.cX - 15, hb.cY);
        this.waiter = 1.5f;

        this.damageAmount = damage;
        if(this.damageAmount >= 200){
            this.damageTimes = 200;
            while (this.damageAmount >= 200){
                this.damageAmount -= 200;
                this.damagePerOnce += 1;
            }
        }
        else {
            this.damageTimes = this.damageAmount;
            this.damageAmount = 0;
            this.damagePerOnce = 1;
        }

        CardCrawlGame.music.silenceTempBgmInstantly();
        CardCrawlGame.music.playTempBgmInstantly("th123_st99.mp3",false);
        AbstractDungeon.effectList.add(new ChangeBGM(79));
    }

    @Override
    public void update() {
        if(this.waiter <= 0) {
            this.duration -= Gdx.graphics.getDeltaTime();
            this.duration2 -= Gdx.graphics.getDeltaTime();
        }
        else {
            this.waiter -= Gdx.graphics.getDeltaTime();
        }

        if(this.duration <= 0){
            this.duration += this.startDuration;

            for(int i = 0;i < 7;i++) {
                AbstractDungeon.effectList.add(new TenseiAttackSpell(this.starter, this.ro.get(i)));
            }

            float vRo = (float) PI / 30;
            for(int i = 0;i < 4;i++) {
                this.ro.set(i, ro.get(i) + vRo);
            }
            for(int i = 4;i < 7;i++) {
                this.ro.set(i, ro.get(i) - vRo);
            }

            if(this.spellNum > 0){
                this.spellNum -= 1;
            }
            else {
                if(this.damageTimes <= 0) {
                    AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, MusouTenseiPower.POWER_ID));
                    this.isDone = true;
                }
            }
        }

        if(this.duration2 <= 0){
            if(this.waveNum > 0) {
                this.duration2 += this.duration2Num;
                AbstractDungeon.effectList.add(new TenseiAttackWave(this.starter));
                this.waveNum -= 1;
            }
        }

        if(this.spellNum <= 25) {
            if(this.damageTimes > 0) {
                if(this.damageAmount > 0){
                    for (AbstractMonster monster : AbstractDungeon.getCurrRoom().monsters.monsters) {
                        DamageInfo info = new DamageInfo(monster, this.damagePerOnce + 1);
                        monster.damageFlash = false;
                        monster.damageFlashFrames = 0;
                        info.applyPowers(info.owner, monster);
                        monster.damage(info);
                    }
                    this.damageAmount -= 1;
                }
                else {
                    for (AbstractMonster monster : AbstractDungeon.getCurrRoom().monsters.monsters) {
                        DamageInfo info = new DamageInfo(monster, this.damagePerOnce);
                        monster.damageFlash = false;
                        monster.damageFlashFrames = 0;
                        info.applyPowers(info.owner, monster);
                        monster.damage(info);
                    }
                }
                this.damageTimes -= 1;
            }
        }
    }
}
