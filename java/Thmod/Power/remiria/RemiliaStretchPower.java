package Thmod.Power.remiria;

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
import com.megacrit.cardcrawl.powers.WeakPower;

import Thmod.Characters.RemiriaScarlet;

public class RemiliaStretchPower extends AbstractPower {
    public static final String POWER_ID = "RemiliaStretchPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("RemiliaStretchPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private boolean firstTurn;

    public RemiliaStretchPower(AbstractCreature owner, int amount, boolean firstTurn) {
        this.name = NAME;
        this.ID = "RemiliaStretchPower";
        this.owner = owner;
        this.amount = amount;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/remiria/RemiliaStretchPower.png");
        this.type = PowerType.BUFF;
        this.firstTurn = firstTurn;
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        super.atEndOfTurn(isPlayer);
        if(!this.firstTurn){
            this.firstTurn = true;
        }
        else {
            for (int i = 0; i < AbstractDungeon.getCurrRoom().monsters.monsters.size(); i++) {
                AbstractMonster target = AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
                if ((!(target.isDying)) && (target.currentHealth > 0) && (!(target.isEscaping))) {
                    AbstractDungeon.actionManager.addToBottom(new DamageAction(target, new DamageInfo(this.owner, this.amount, DamageInfo.DamageType.THORNS),
                            AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                }
            }
            if((AbstractDungeon.player.hasPower(ScarletLordPower.POWER_ID)) && (AbstractDungeon.player instanceof RemiriaScarlet)){
                ((RemiriaScarlet) AbstractDungeon.player).changeState("H_ATTACK");
            }
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, this));
        }
    }

    public void updateDescription()
    {
        if(!this.firstTurn) {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
        }
        else {
            this.description = DESCRIPTIONS[2] + this.amount + DESCRIPTIONS[1];
        }
    }
}
