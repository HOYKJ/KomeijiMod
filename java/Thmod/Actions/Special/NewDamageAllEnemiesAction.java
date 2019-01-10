package Thmod.Actions.Special;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.BlockImpactLineEffect;
import com.megacrit.cardcrawl.vfx.combat.BlockedNumberEffect;
import com.megacrit.cardcrawl.vfx.combat.BlockedWordEffect;
import com.megacrit.cardcrawl.vfx.combat.DeckPoofEffect;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import com.megacrit.cardcrawl.vfx.combat.HbBlockBrokenEffect;
import com.megacrit.cardcrawl.vfx.combat.StrikeEffect;

import java.util.Iterator;

import Thmod.vfx.NewStrikeEffect;
import basemod.DevConsole;

import static com.megacrit.cardcrawl.core.AbstractCreature.TEXT;

public class NewDamageAllEnemiesAction extends AbstractGameAction
{
    public int[] damage;
    private int num;
    private boolean firstFrame = true;

    private float BLOCK_ICON_X = -14.0F * Settings.scale;
    private float BLOCK_ICON_Y = -14.0F * Settings.scale;

    public NewDamageAllEnemiesAction(AbstractCreature source, int[] amount, DamageInfo.DamageType type, AbstractGameAction.AttackEffect effect, int num)
    {
        setValues(null, source, amount[0]);
        this.damage = amount;
        this.actionType = AbstractGameAction.ActionType.DAMAGE;
        this.damageType = type;
        this.attackEffect = effect;

        this.duration = 0.03F;
        this.num = num;
        DevConsole.logger.info("num2 " + num);
    }

    public void update()
    {
        boolean playedMusic;
        if (this.firstFrame)
        {
            playedMusic = false;
            int temp = AbstractDungeon.getCurrRoom().monsters.monsters.size();
            for (int i = 0; i < temp; i++) {
                if ((!(AbstractDungeon.getCurrRoom().monsters.monsters.get(i)).isDying) &&
                        ((AbstractDungeon.getCurrRoom().monsters.monsters.get(i)).currentHealth > 0) &&
                        (!(AbstractDungeon.getCurrRoom().monsters.monsters.get(i)).isEscaping)) {
                    if (playedMusic)
                    {
                        AbstractDungeon.effectList.add(new FlashAtkImgEffect(

                                (AbstractDungeon.getCurrRoom().monsters.monsters.get(i)).hb.cX,
                                (AbstractDungeon.getCurrRoom().monsters.monsters.get(i)).hb.cY, this.attackEffect, true));
                    }
                    else
                    {
                        playedMusic = true;
                        AbstractDungeon.effectList.add(new FlashAtkImgEffect(

                                (AbstractDungeon.getCurrRoom().monsters.monsters.get(i)).hb.cX,
                                (AbstractDungeon.getCurrRoom().monsters.monsters.get(i)).hb.cY, this.attackEffect));
                    }
                }
            }
            this.firstFrame = false;
        }
        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration <= 0)
        {
            for (AbstractPower p : AbstractDungeon.player.powers) {
                p.onDamageAllEnemies(this.damage);
            }
            int temp = AbstractDungeon.getCurrRoom().monsters.monsters.size();
            for (int i = 0; i < temp; i++) {
                if (!(AbstractDungeon.getCurrRoom().monsters.monsters.get(i)).isDeadOrEscaped())
                {
                    if (this.attackEffect == AbstractGameAction.AttackEffect.POISON)
                    {
                        (AbstractDungeon.getCurrRoom().monsters.monsters.get(i)).tint.color = Color.CHARTREUSE.cpy();
                        (AbstractDungeon.getCurrRoom().monsters.monsters.get(i)).tint.changeColor(Color.WHITE.cpy());
                    }
                    else if (this.attackEffect == AbstractGameAction.AttackEffect.FIRE)
                    {
                        (AbstractDungeon.getCurrRoom().monsters.monsters.get(i)).tint.color = Color.RED.cpy();
                        (AbstractDungeon.getCurrRoom().monsters.monsters.get(i)).tint.changeColor(Color.WHITE.cpy());
                    }
//                    (AbstractDungeon.getCurrRoom().monsters.monsters.get(i)).damage(new DamageInfo(this.source, this.damage[i], this.damageType));
                    if(this.damage[i] > 0) {
                        AbstractDungeon.getCurrRoom().monsters.monsters.get(i).damage(new DamageInfo(this.source, 1, this.damageType));
                        this.damage[i] --;
                    }
                }
            }
            if(this.num >= 1){
                this.duration = 0.03F;
                this.num --;
                this.firstFrame = true;
                if(this.damageType == DamageInfo.DamageType.NORMAL)
                    this.damageType = DamageInfo.DamageType.THORNS;
            }
            else {
                this.isDone = true;
            }
            if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                AbstractDungeon.actionManager.clearPostCombatActions();
            }
        }
    }

    private void damage(DamageInfo info, AbstractMonster m) {
        if (info.output > 0 && m.hasPower("IntangiblePlayer")) {
            info.output = 1;
        }

        int damageAmount = info.output;
        if (!m.isDying && !m.isEscaping) {
            if (damageAmount < 0) {
                damageAmount = 0;
            }

            damageAmount = decrementBlock(info, damageAmount,m);

            if (damageAmount > 0) {
                AbstractDungeon.effectList.add(new NewStrikeEffect(m, m.hb.cX, m.hb.cY, damageAmount));
            }
        }
    }

    private int decrementBlock(DamageInfo info, int damageAmount, AbstractMonster m) {
        if (info.type != DamageInfo.DamageType.HP_LOSS && m.currentBlock > 0) {
            if (damageAmount > m.currentBlock) {
                damageAmount -= m.currentBlock;
            } else if (damageAmount == m.currentBlock) {
                damageAmount = 0;
            } else {
                damageAmount = 0;
            }
        }

        return damageAmount;
    }
}
