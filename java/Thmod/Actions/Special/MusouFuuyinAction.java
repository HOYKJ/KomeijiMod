package Thmod.Actions.Special;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

import java.util.ArrayList;

import Thmod.Actions.common.RandomAttackAction;
import Thmod.vfx.MusouFuuinEffect;
import Thmod.vfx.RoundDiggerEffect;

public class MusouFuuyinAction extends AbstractGameEffect{
    private int num;
    private int colorNum = 1;
    private Color colorNew;
    private float stakeTimer = 0.1F;
    private boolean hasScreen = false;
    private DamageInfo info;
    private AbstractGameAction.AttackEffect attackEffect;

    public MusouFuuyinAction(int num, DamageInfo info, AbstractGameAction.AttackEffect attackEffect){
        this.num = num;
        this.info = info;
        this.attackEffect = attackEffect;
    }

    public void update(){
//        if(!hasScreen){
//            AbstractDungeon.effectsQueue.add(new BorderLongFlashEffect(Color.SKY));
//            hasScreen = true;
//        }


        this.stakeTimer -= Gdx.graphics.getDeltaTime();
        if (this.stakeTimer <= 0.0F){
            switch (this.colorNum) {
                case 1:
                    this.colorNew = Color.SCARLET.cpy();
                    break;
                case 2:
                    this.colorNew = Color.ORANGE.cpy();
                    break;
                case 3:
                    this.colorNew = Color.YELLOW.cpy();
                    break;
                case 4:
                    this.colorNew = Color.GREEN.cpy();
                    break;
                case 5:
                    this.colorNew = Color.SKY.cpy();
                    break;
                case 6:
                    this.colorNew = Color.BLUE.cpy();
                    break;
                case 7:
                    this.colorNew = Color.PURPLE.cpy();
                    break;
                default:
                    this.colorNew = Color.SCARLET.cpy();
                    break;
            }
            this.colorNum ++;

            AbstractDungeon.effectsQueue.add(new MusouFuuinEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, this.colorNew, this.info, this.attackEffect));
            this.stakeTimer = 0.2F;
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
