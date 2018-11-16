package Thmod.Power;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;

import Thmod.Actions.common.RoundDiggerAction;

public class CounterAttackPower extends AbstractPower {
    public static final String POWER_ID = "CounterAttackPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("CounterAttackPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public CounterAttackPower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = "CounterAttackPower";
        this.owner = owner;
        this.amount = -1;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/CounterAttackPower.png");
        this.type = PowerType.BUFF;
    }

    public int onAttacked(DamageInfo info, int damageAmount)
    {
        if ((info.type != DamageInfo.DamageType.HP_LOSS) && (info.owner != null) && (info.owner != this.owner))
        {
            if(damageAmount > 0) {
                flash();
                ArrayList<AbstractCreature> target = new ArrayList<>();
                target.add(info.owner);
                AbstractDungeon.effectList.add(new RoundDiggerAction(10,false,target));
                if (!(this.owner.hasPower("PointPower"))) {
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new PointPower(this.owner, 1), 1));
                } else if (this.owner.getPower("PointPower").amount < 5) {
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new PointPower(this.owner, 1), 1));
                }
                AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, "CounterAttackPower"));
            }
            return 0;
        }
        else
            return damageAmount;
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0];
    }
}
