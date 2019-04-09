package Thmod.Actions.Remiria;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.GetAllInBattleInstances;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.beyond.Donu;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

import java.util.UUID;

public class IncreaseDamageAction extends AbstractGameAction
{
    private int increaseAmount;
    private DamageInfo info;
    private static final float DURATION = 0.1F;
    private AttackEffect attackEffect;
    private UUID uuid;

    public IncreaseDamageAction(AbstractCreature target, DamageInfo info, AttackEffect effect, int increaseAmount, UUID targetUUID)
    {
        this.info = info;
        setValues(target, info);
        this.increaseAmount = increaseAmount;
        this.actionType = AbstractGameAction.ActionType.DAMAGE;
        this.duration = 0.1F;
        this.attackEffect = effect;
        this.uuid = targetUUID;
    }

    public void update()
    {
        if ((this.duration == 0.1F) &&
                (this.target != null))
        {
            AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, this.attackEffect));
            this.target.damage(this.info);
            if (((((AbstractMonster)this.target).isDying) || (this.target.currentHealth <= 0)) && (!this.target.halfDead) &&
                    (!this.target.hasPower("Minion"))) {
                for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
                    if (c.uuid.equals(this.uuid))
                    {
                        c.misc += this.increaseAmount;
                        c.applyPowers();
                        c.baseDamage = c.misc;
                        c.isBlockModified = false;
                    }
                }
                for (AbstractCard c : GetAllInBattleInstances.get(this.uuid))
                {
                    c.misc += this.increaseAmount;
                    c.applyPowers();
                    c.baseDamage = c.misc;
                }
            }
            if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                AbstractDungeon.actionManager.clearPostCombatActions();
            }
        }
        tickDuration();
    }
}
