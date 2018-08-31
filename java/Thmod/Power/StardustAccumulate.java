package Thmod.Power;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;

import Thmod.Cards.DeriveCards.AbstractDeriveCards;
import Thmod.Cards.DeriveCards.Stardust;
import basemod.DevConsole;

public class StardustAccumulate extends AbstractPower {
    public static final String POWER_ID = "StardustAccumulate";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("StardustAccumulate");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
//    private static int stardustIdOffset;

    public StardustAccumulate(AbstractCreature owner) {
        this.name = NAME;
        this.ID = "StardustAccumulate";
//        stardustIdOffset += 1;
        this.owner = owner;
        this.amount = 0;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/StardustAccumulate.png");
        this.type = PowerType.BUFF;
    }

    public void atStartOfTurnPostDraw() {
        AbstractDeriveCards c = new Stardust(this.amount);
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
