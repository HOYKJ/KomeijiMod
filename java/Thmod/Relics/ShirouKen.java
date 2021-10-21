package Thmod.Relics;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class ShirouKen extends AbstractThRelic {
    public static final String ID = "ShirouKen";

    public ShirouKen()
    {
        super("ShirouKen",  RelicTier.RARE, LandingSound.HEAVY);
        this.counter = 0;
    }

    public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {
        if(targetCard.type == AbstractCard.CardType.ATTACK) {

            this.counter += 1;
        }
    }

    @Override
    public int onAttackToChangeDamage(DamageInfo info, int damageAmount) {
        if ((info.owner != null) && (info.type != DamageInfo.DamageType.HP_LOSS) && (info.type != DamageInfo.DamageType.THORNS))
        {
            flash();
            return (damageAmount + this.counter - 1);
        }
        return damageAmount;
    }

//    public int onAttackedMonster(DamageInfo info, int damageAmount)
//    {
//        if ((info.owner != null) && (info.type != DamageInfo.DamageType.HP_LOSS) && (info.type != DamageInfo.DamageType.THORNS))
//        {
//            flash();
//            return (damageAmount + this.counter - 1);
//        }
//        return damageAmount;
//    }

    public void onPlayerEndTurn() {
        this.counter = 0;
    }

    public void onVictory() {
        this.counter = 0;
    }

    protected  void onRightClick(){
    }

    public String getUpdatedDescription() {
        return (this.DESCRIPTIONS[0]);
    }

    public AbstractRelic makeCopy() {
        return new ShirouKen();
    }
}
