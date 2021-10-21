package Thmod.Power.remiria;

import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import Thmod.Actions.unique.ChooseAction;
import Thmod.Cards.ScarletCard.uncommonCards.SealingFear;

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

//    @Override
//    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
//        super.onApplyPower(power, target, source);
//        if((target == AbstractDungeon.player) && (power instanceof BloodBruisePower)){
//            final ChooseAction choice = new ChooseAction(null, null, SealingFear.EXTENDED_DESCRIPTION[2], true, 1, true);
//            for(AbstractCard card : AbstractDungeon.player.hand.group){
//                choice.add(card, () -> {
//                    AbstractDungeon.actionManager.addToBottom(new DiscardSpecificCardAction(card, AbstractDungeon.player.hand));
//                    AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, 1));
//                });
//            }
//            AbstractDungeon.actionManager.addToBottom(choice);
//        }
//    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0];
    }
}
