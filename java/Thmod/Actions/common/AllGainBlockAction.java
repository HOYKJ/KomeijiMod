package Thmod.Actions.common;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

public class AllGainBlockAction extends AbstractGameAction {
    public AllGainBlockAction(AbstractCreature source, int amount){
        this.duration = 0.5F;
        this.source = source;
        this.amount = amount;
        this.actionType = AbstractGameAction.ActionType.BLOCK;
    }

    public void update(){
        if (this.duration == 0.5F){
            for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
                if ((!(m.isDying)) && (m.currentHealth > 0) && (!(m.isEscaping))) {
                    this.target = m;
                    AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AbstractGameAction.AttackEffect.SHIELD));
                    this.target.addBlock(this.amount);
                }
            }
        }
        tickDuration();
    }
}
