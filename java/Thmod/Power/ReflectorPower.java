package Thmod.Power;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class ReflectorPower extends AbstractPower {
    public static final String POWER_ID = "ReflectorPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("ReflectorPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private float damageReceive;

    public ReflectorPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = "ReflectorPower";
        this.owner = owner;
        this.amount = amount;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/ReflectorPower.png");
        this.type = PowerType.BUFF;
    }

    public float atDamageReceive(float damage, com.megacrit.cardcrawl.cards.DamageInfo.DamageType damageType) {
        this.damageReceive = damage;
        return damage;
    }

    public int onAttacked(DamageInfo info, int damageAmount)
    {
        if ((info.owner != null) && (info.type != DamageInfo.DamageType.THORNS) && (info.type != DamageInfo.DamageType.HP_LOSS) && (info.owner != this.owner))
        {
            flash();
            AbstractDungeon.actionManager.addToTop(new DamageAction(info.owner, new DamageInfo(this.owner, (int)this.damageReceive, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.SMASH));
        }

        return damageAmount;
    }

    public void atStartOfTurnPostDraw() {
        if(this.amount > 1){
            this.amount -= 1;
        }
        else {
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, this));
        }
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0];
    }
}
