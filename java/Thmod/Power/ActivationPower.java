package Thmod.Power;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import Thmod.Cards.DeriveCards.Boundaries;

public class ActivationPower extends AbstractPower {
    public static final String POWER_ID = "ActivationPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("ActivationPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private AbstractPlayer p = AbstractDungeon.player;
    private int skillNum;
    private int attackNum;

    public ActivationPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = "ActivationPower";
        this.owner = owner;
        this.amount = amount;
        this.attackNum = (amount / 2);
        this.skillNum = this.attackNum;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/ActivationPower.png");
        this.type = PowerType.BUFF;
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if(card.type == AbstractCard.CardType.ATTACK){
            if(this.attackNum > 0) {
                this.amount -= 1;
                this.attackNum -= 1;
            }
        }
        if(card.type == AbstractCard.CardType.SKILL){
            if(this.skillNum > 0) {
                this.amount -= 1;
                this.skillNum -= 1;
            }
        }
        updateDescription();
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0] + this.attackNum + DESCRIPTIONS[1] + this.skillNum + DESCRIPTIONS[2];
    }
}
