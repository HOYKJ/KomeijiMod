package Thmod.Power.remiria;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class BloodyMagicSquarePower extends AbstractPower {
    public static final String POWER_ID = "BloodyMagicSquarePower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("BloodyMagicSquarePower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public BloodyMagicSquarePower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = "BloodyMagicSquarePower";
        this.owner = owner;
        this.amount = amount;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/remiria/BloodyMagicSquarePower.png");
        this.type = PowerType.BUFF;
    }

    @Override
    public void atStartOfTurn() {
        super.atStartOfTurn();
        this.trigger();
    }

    public void trigger(){
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                new BloodBruisePower(AbstractDungeon.player, this.amount / 2), this.amount / 2));
        for (int i = 0; i < AbstractDungeon.getCurrRoom().monsters.monsters.size(); i++) {
            AbstractMonster target = AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
            if ((!(target.isDying)) && (target.currentHealth > 0) && (!(target.isEscaping))) {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target, AbstractDungeon.player, new BloodBruisePower(target, this.amount), this.amount));
            }
        }
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
