package Thmod.Power;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import java.util.Map;

import Thmod.Cards.AbstractSweepCards;
import Thmod.Cards.ElementCards.AbstractElementSweepCards;

public class RidiculousPower extends AbstractPower {
    public static final String POWER_ID = "RidiculousPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("RidiculousPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static CardGroup sweepCardPool = new CardGroup(CardGroup.CardGroupType.CARD_POOL);

    public RidiculousPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = "RidiculousPower";
        this.owner = owner;
        this.amount = amount;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/RidiculousPower.png");
        this.type = PowerType.BUFF;
    }

    public void atStartOfTurn() {
        AbstractCard card;
        for (Map.Entry<String, AbstractCard> c : CardLibrary.cards.entrySet())
        {
            card = c.getValue();
            if (((card instanceof AbstractSweepCards) || (card instanceof AbstractElementSweepCards)) && ((!UnlockTracker.isCardLocked(c.getKey())) || (Settings.treatEverythingAsUnlocked()))) {
                sweepCardPool.addToTop(card);
            }
        }
        for (int i = 0; i < this.amount; i++) {
            AbstractCard giveCard = sweepCardPool.getRandomCard(AbstractDungeon.cardRandomRng).makeCopy();
            giveCard.setCostForTurn(0);
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(giveCard, true));
        }
    }

    public void updateDescription()
    {
        this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
    }
}
