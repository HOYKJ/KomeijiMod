package Thmod.Power;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;

import Thmod.Cards.DeriveCards.AbstractDeriveCards;
import Thmod.Cards.DeriveCards.Thrasher;

public class ThrasherAccumulate extends AbstractPower {
    public static final String POWER_ID = "ThrasherAccumulate";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("ThrasherAccumulate");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public ThrasherAccumulate(AbstractCreature owner) {
        this.name = NAME;
        this.ID = "ThrasherAccumulate";
        this.owner = owner;
        this.amount = 1;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/ThrasherAccumulate.png");
        this.type = PowerType.BUFF;
    }

    public void atStartOfTurnPostDraw() {
        AbstractDeriveCards c = new Thrasher(this.amount);
        AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(c));
    }

    public void atEndOfRound() {
            this.amount += 1;
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0];
    }
}
