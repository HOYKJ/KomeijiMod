package Thmod.Power;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class PhotosynthesisPower extends AbstractPower {
    public static final String POWER_ID = "PhotosynthesisPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("PhotosynthesisPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public PhotosynthesisPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = "PhotosynthesisPower";
        this.owner = owner;
        this.amount = amount;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/PhotosynthesisPower.png");
        this.type = PowerType.BUFF;
    }

    public void atStartOfTurn()
    {
        AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(this.amount));
        flash();
    }

    public void updateDescription()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(DESCRIPTIONS[0]);
        for (int i = 0; i < this.amount; i++) {
            sb.append(" [R] ");
        }
        sb.append(DESCRIPTIONS[1]);
        this.description = sb.toString();
    }
}
