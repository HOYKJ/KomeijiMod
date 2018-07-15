package Thmod.Power;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import Thmod.Cards.DeriveCards.AbstractDeriveCards;
import Thmod.Cards.DeriveCards.DemonsFork;

public class DemonsForkAccumulate extends AbstractPower {
    public static final String POWER_ID = "DemonsForkAccumulate";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("DemonsForkAccumulate");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private boolean upgraded;

    public DemonsForkAccumulate(AbstractCreature owner, boolean upgraded) {
        this.name = NAME;
        this.ID = "DemonsForkAccumulate";
        this.owner = owner;
        this.amount = 1;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/DemonsForkAccumulate.png");
        this.type = PowerType.BUFF;
        this.upgraded = upgraded;
    }

    public void atStartOfTurnPostDraw() {
        AbstractDeriveCards c = new DemonsFork(this.amount);
        if(this.upgraded)
            c.upgrade();
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c, false));
    }

    public void atEndOfRound() {
        if(this.amount < 3)
            this.amount += 1;
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0];
    }
}
