package Thmod.Power;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class MusuNoYumePower extends AbstractPower {
    public static final String POWER_ID = "MusuNoYumePower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("MusuNoYumePower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private int damage;

    public MusuNoYumePower(AbstractCreature owner,int damage) {
        this.name = NAME;
        this.ID = "MusuNoYumePower";
        this.owner = owner;
        this.amount = 3;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/MusuNoYumePower.png");
        this.type = PowerType.DEBUFF;
        this.damage = damage;
    }

//    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
//        if((target == AbstractDungeon.player) && (damageAmount > target.currentBlock)){
//            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "MusuNoYumePower"));
//        }
//    }

    public void atEndOfRound() {
        AbstractDungeon.actionManager.addToTop(new DamageAction(this.owner, new DamageInfo(this.owner, this.damage, DamageInfo.DamageType.HP_LOSS), AbstractGameAction.AttackEffect.NONE));
        flash();
        if (this.amount == 1)
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "MusuNoYumePower"));
        else
            this.amount -= 1;
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0];
    }
}
