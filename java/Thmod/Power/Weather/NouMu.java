package Thmod.Power.Weather;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class NouMu extends AbstractPower {
    public static final String POWER_ID = "NouMu";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("NouMu");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public NouMu(AbstractCreature owner) {
        this.name = NAME;
        this.ID = "NouMu";
        this.owner = owner;
        this.amount = 5;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/Weather/NouMu.png");
        this.type = PowerType.BUFF;
    }

    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        if(info.type != DamageInfo.DamageType.HP_LOSS) {
            AbstractDungeon.player.heal((int) (damageAmount * 0.15));
            flash();
        }
    }

    public void atEndOfRound() {
        if (this.amount <= 1)
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "NouMu"));
        else
            this.amount -= 1;
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0];
    }
}
