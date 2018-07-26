package Thmod.Actions.common;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;


public class JinkouTaiyouAction extends AbstractGameAction
{
    private DamageInfo info;
    private float startingDuration;

    public JinkouTaiyouAction(AbstractCreature target, DamageInfo info)
    {
        this.info = info;
        setValues(target, info);
        this.actionType = AbstractGameAction.ActionType.WAIT;
        this.attackEffect = AbstractGameAction.AttackEffect.FIRE;
        this.startingDuration = Settings.ACTION_DUR_FAST;
        this.duration = this.startingDuration;
    }

    public void update()
    {
        if (this.duration == this.startingDuration) {
            int count = AbstractDungeon.player.hand.size();
            for (int i = 0; i < count; ++i) {
                for (int i1 = 0; i1 < AbstractDungeon.getCurrRoom().monsters.monsters.size(); ++i1) {
                    AbstractMonster target = (AbstractMonster) AbstractDungeon.getCurrRoom().monsters.monsters.get(i1);
                    if ((!(target.isDying)) && (target.currentHealth > 0) && (!(target.isEscaping))) {
                        AbstractDungeon.actionManager.addToTop(new DamageAction(target, this.info, AbstractGameAction.AttackEffect.FIRE));
                    }
                }
                AbstractDungeon.actionManager.addToTop(new ExhaustAction(AbstractDungeon.player, AbstractDungeon.player, 1, true, true));
            }
        }
        tickDuration();
    }
}
