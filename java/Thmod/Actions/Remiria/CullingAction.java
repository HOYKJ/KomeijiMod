package Thmod.Actions.Remiria;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

public class CullingAction extends AbstractGameAction
{
    private int amount;
    private DamageInfo info;
    private boolean canDraw;

    public CullingAction(AbstractCreature target, DamageInfo info, int amount, boolean canDraw)
    {
        this.info = info;
        setValues(target, info);
        this.amount = amount;
        this.actionType = AbstractGameAction.ActionType.DAMAGE;
        this.duration = Settings.ACTION_DUR_FASTER;
        this.canDraw = canDraw;
    }

    public void update()
    {
        if ((this.duration == Settings.ACTION_DUR_FASTER) &&
                (this.target != null))
        {
            AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AttackEffect.SLASH_HEAVY));

            this.target.damage(this.info);
            if ((((AbstractMonster)this.target).isDying) || (this.target.currentHealth <= 0)) {
                AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(this.amount));
                if(this.canDraw) {
                    AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, this.amount));
                }
            }
            if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                AbstractDungeon.actionManager.clearPostCombatActions();
            }
        }
        tickDuration();
    }
}
