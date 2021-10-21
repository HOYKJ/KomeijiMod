package Thmod.Actions.Remiria;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.GetAllInBattleInstances;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

import java.util.Iterator;
import java.util.UUID;

import Thmod.Power.remiria.BloodBruisePower;

public class HeartBreakAction extends AbstractGameAction
{
    private int[] damage;
    private boolean firstFrame;
    private float multi;

    public HeartBreakAction(AbstractCreature source, int[] amount, DamageInfo.DamageType type, AttackEffect effect, float multi)
    {
        this.firstFrame = true;
        this.damage = amount;
        this.setValues(null, source, amount[0]);
        this.actionType = AbstractGameAction.ActionType.DAMAGE;
        this.damageType = type;
        this.duration = 0.1F;
        this.attackEffect = effect;
        this.multi = multi;
    }

    public void update()
    {
        if (this.firstFrame) {
            boolean playedMusic = false;
            for(int i = 0; i < AbstractDungeon.getCurrRoom().monsters.monsters.size(); ++i) {
                if (!(AbstractDungeon.getCurrRoom().monsters.monsters.get(i)).isDying && (AbstractDungeon.getCurrRoom().monsters.monsters.get(i)).currentHealth > 0 && !(AbstractDungeon.getCurrRoom().monsters.monsters.get(i)).isEscaping) {
                    if (playedMusic) {
                        AbstractDungeon.effectList.add(new FlashAtkImgEffect((AbstractDungeon.getCurrRoom().monsters.monsters.get(i)).hb.cX, (AbstractDungeon.getCurrRoom().monsters.monsters.get(i)).hb.cY, this.attackEffect, true));
                    } else {
                        playedMusic = true;
                        AbstractDungeon.effectList.add(new FlashAtkImgEffect((AbstractDungeon.getCurrRoom().monsters.monsters.get(i)).hb.cX, (AbstractDungeon.getCurrRoom().monsters.monsters.get(i)).hb.cY, this.attackEffect));
                    }
                }
            }

            this.firstFrame = false;
        }

        this.tickDuration();
        if (this.isDone) {
            Iterator var4 = AbstractDungeon.player.powers.iterator();

            while(var4.hasNext()) {
                AbstractPower p = (AbstractPower)var4.next();
                p.onDamageAllEnemies(this.damage);
            }

            int temp = AbstractDungeon.getCurrRoom().monsters.monsters.size();

            for(int i = 0; i < temp; ++i) {
                if (!(AbstractDungeon.getCurrRoom().monsters.monsters.get(i)).isDeadOrEscaped()) {

                    AbstractMonster monster = AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
                    monster.damage(new DamageInfo(this.source, this.damage[i], this.damageType));
                    if (!((((monster).isDying) || (monster.currentHealth <= 0)) && (!monster.halfDead))) {
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster, this.source, new BloodBruisePower(monster,
                                (int)(this.multi * this.damage[i])), (int)(this.multi * this.damage[i])));
                    }
                }
            }

            if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                AbstractDungeon.actionManager.clearPostCombatActions();
            }

            if (!Settings.FAST_MODE) {
                AbstractDungeon.actionManager.addToTop(new WaitAction(0.1F));
            }
        }
    }
}
