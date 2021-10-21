package Thmod.Power.remiria;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class CombatMeterPower extends AbstractPower {
    public static final String POWER_ID = "CombatMeterPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("CombatMeterPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private int check;
    private int counter;

    public CombatMeterPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = "CombatMeterPower";
        this.owner = owner;
        this.amount = amount;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/remiria/CombatMeterPower.png");
        this.type = PowerType.BUFF;
        this.check = 0;
        this.counter = amount;
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        super.onUseCard(card, action);
        this.active(card);
    }

    @Override
    public void atStartOfTurn() {
        super.atStartOfTurn();
        this.check = 0;
        this.counter = this.amount;
    }

    public void active(AbstractCard card){
        switch (card.type){
            case ATTACK:
                if(this.check != 1){
                    this.check = 1;
                    AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this.owner, this.owner, this.counter));
                    this.counter += this.amount;
                }
                break;
            case SKILL:
                if(this.check != 2){
                    this.check = 2;
                    AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this.owner, this.owner, this.counter));
                    this.counter += this.amount;
                }
                break;
            case POWER:
                if(this.check != 3){
                    this.check = 3;
                    AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this.owner, this.owner, this.counter));
                    this.counter += this.amount;
                }
                break;
            case STATUS:
                if(this.check != 4){
                    this.check = 4;
                    AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this.owner, this.owner, this.counter));
                    this.counter += this.amount;
                }
                break;
            case CURSE:
                if(this.check != 5){
                    this.check = 5;
                    AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this.owner, this.owner, this.counter));
                    this.counter += this.amount;
                }
                break;
        }
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0] + this.counter + DESCRIPTIONS[1];
    }
}
