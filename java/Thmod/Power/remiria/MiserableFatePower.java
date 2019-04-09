package Thmod.Power.remiria;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class MiserableFatePower extends AbstractPower {
    public static final String POWER_ID = "MiserableFatePower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("MiserableFatePower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public MiserableFatePower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = "MiserableFatePower";
        this.owner = owner;
        this.amount = -1;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/remiria/MiserableFatePower.png");
        this.type = PowerType.BUFF;
    }

    public void onAppliedPower(AbstractPower power, AbstractCreature source){
        if(power.type == PowerType.DEBUFF){
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, power));
            AbstractMonster monster = AbstractDungeon.getCurrRoom().monsters.getRandomMonster(true);
            if(monster != null) {
                power.owner = monster;
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster, source, power, power.amount));
            }
        }
    }

    public void onStackedPower(AbstractPower power, int amount, AbstractCreature source){
        if(power.type == PowerType.DEBUFF){
            AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, power.ID, power.amount));
            AbstractMonster monster = AbstractDungeon.getCurrRoom().monsters.getRandomMonster(true);
            if(monster != null) {
                power.owner = monster;
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster, source, power, power.amount));
            }
        }
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0];
    }
}
