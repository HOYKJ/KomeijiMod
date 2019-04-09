package Thmod.Power.remiria;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class MillenniumVampirePower extends AbstractPower {
    public static final String POWER_ID = "MillenniumVampirePower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("MillenniumVampirePower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private boolean isCard;

    public MillenniumVampirePower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = "MillenniumVampirePower";
        this.owner = owner;
        this.amount = amount;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/remiria/MillenniumVampirePower.png");
        this.type = PowerType.BUFF;
        this.isCard = false;
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        super.onUseCard(card, action);
        this.isCard = true;
    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        super.onAttack(info, damageAmount, target);
        if(this.isCard) {
            if((target != this.owner) || (info.type != DamageInfo.DamageType.HP_LOSS)) {
                if (target.hasPower(BloodBruisePower.POWER_ID)) {
                    AbstractDungeon.actionManager.addToBottom(new DamageAction(target, new DamageInfo(info.owner, (int) ((float) damageAmount * 0.25)), AbstractGameAction.AttackEffect.NONE));
                }
            }
            this.isCard = false;
        }
    }

    public void onMonsterDeath(AbstractMonster monster){
        if((monster.hasPower(BloodBruisePower.POWER_ID)) && (!monster.hasPower("Minion"))){
            this.owner.heal(this.amount);
        }
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
