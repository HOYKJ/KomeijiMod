package Thmod.Power.remiria;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import Thmod.Characters.RemiriaScarlet;

public class CoercionPower extends AbstractPower {
    public static final String POWER_ID = "CoercionPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("CoercionPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private boolean isPlus;

    public CoercionPower(AbstractCreature owner, boolean isPlus) {
        this.name = NAME;
        this.ID = "CoercionPower";
        this.owner = owner;
        this.amount = -1;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/remiria/CoercionPower.png");
        this.type = PowerType.BUFF;
        this.isPlus = isPlus;
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        super.onUseCard(card, action);
        if(!this.isPlus) {
            if (card.type == AbstractCard.CardType.ATTACK) {
                AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, this));
            }
        }
    }

    @Override
    public void atStartOfTurnPostDraw() {
        super.atStartOfTurnPostDraw();
        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, this));
    }

    @Override
    public void onRemove() {
        super.onRemove();
        if(this.owner instanceof RemiriaScarlet){
            if(this.owner.hasPower(ScarletLordPower.POWER_ID)){
                ((RemiriaScarlet) this.owner).changeState("GUNGNIR");
            }
            else {
                ((RemiriaScarlet) this.owner).changeState("NORMAL");
            }
        }
    }

    @Override
    public int onLoseHp(int damageAmount) {
        return 0;
    }

    public void updateDescription()
    {
        if(this.isPlus){
            this.description = DESCRIPTIONS[1];
        }
        else {
            this.description = DESCRIPTIONS[0];
        }
    }
}
