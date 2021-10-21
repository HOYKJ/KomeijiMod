package Thmod.Actions.common;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class BlockDamageAction extends AbstractGameAction
{
    private DamageInfo info;

    public BlockDamageAction(AbstractCreature target, DamageInfo info)
    {
        this.info = info;
        setValues(target, info);
        this.actionType = AbstractGameAction.ActionType.DAMAGE;
        this.startDuration = Settings.ACTION_DUR_FAST;
        this.duration = this.startDuration;
    }

    public void update()
    {
        if (shouldCancelAction())
        {
            this.isDone = true;
            return;
        }
        tickDuration();
        if (this.isDone)
        {
            this.target.damage(this.info);
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this.source, this.source, this.target.lastDamageTaken));
            if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                AbstractDungeon.actionManager.clearPostCombatActions();
            } else {
                AbstractDungeon.actionManager.addToBottom(new WaitAction(0.1F));
            }
        }
    }
}
