package Thmod.Actions.Remiria;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.GetAllInBattleInstances;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

import java.util.UUID;

import Thmod.Power.remiria.BloodBruisePower;

public class BloodyKnifeAction extends AbstractGameAction
{
    private int bloodAmount;
    private DamageInfo info;
    private static final float DURATION = 0.1F;
    private AttackEffect attackEffect;
    private boolean isPlus;

    public BloodyKnifeAction(AbstractCreature target, DamageInfo info, AttackEffect effect, int bloodAmount, boolean isPlus)
    {
        this.info = info;
        setValues(target, info);
        this.bloodAmount = bloodAmount;
        this.actionType = AbstractGameAction.ActionType.DAMAGE;
        this.duration = 0.1F;
        this.attackEffect = effect;
        this.isPlus = isPlus;
    }

    public void update()
    {
        if ((this.duration == 0.1F) &&
                (this.target != null))
        {
            AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, this.attackEffect));
            this.target.damage(this.info);
            if (((((AbstractMonster)this.target).isDying) || (this.target.currentHealth <= 0)) && (!this.target.halfDead)) {
                for (int i = 0; i < AbstractDungeon.getCurrRoom().monsters.monsters.size(); i++) {
                    AbstractMonster monster = AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
                    if ((!(monster.isDying)) && (monster.currentHealth > 0) && (!(monster.isEscaping))) {
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster, info.owner, new BloodBruisePower(monster, this.bloodAmount), this.bloodAmount));
                    }
                }
                if(!this.isPlus){
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, info.owner, new BloodBruisePower(AbstractDungeon.player, this.bloodAmount), this.bloodAmount));
                }
            }
            if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                AbstractDungeon.actionManager.clearPostCombatActions();
            }
        }
        tickDuration();
    }
}
