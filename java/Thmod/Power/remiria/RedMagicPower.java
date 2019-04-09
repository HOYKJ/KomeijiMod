package Thmod.Power.remiria;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class RedMagicPower extends AbstractPower {
    public static final String POWER_ID = "RedMagicPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("RedMagicPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public RedMagicPower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = "RedMagicPower";
        this.owner = owner;
        this.amount = -1;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/remiria/RedMagicPower.png");
        this.type = PowerType.BUFF;
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0];
    }
}
