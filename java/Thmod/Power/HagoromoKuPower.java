package Thmod.Power;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class HagoromoKuPower extends AbstractPower {
    public static final String POWER_ID = "HagoromoKuPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("HagoromoKuPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public HagoromoKuPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = "HagoromoKuPower";
        this.owner = owner;
        this.amount = amount;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/HagoromoKuPower.png");
        this.type = PowerType.BUFF;
    }

    public void onUseCard(AbstractCard card, UseCardAction action)
    {
        if(card.type == AbstractCard.CardType.SKILL)
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, this.amount));
        flash();
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0];
    }
}
