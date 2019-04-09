package Thmod.Power.remiria;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import Thmod.Cards.ScarletCard.rareCards.RemiliaStalker;

public class RemiliaStalkerPower extends AbstractPower {
    public static final String POWER_ID = "RemiliaStalkerPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("RemiliaStalkerPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private boolean canRefresh;
    private boolean firstCard;

    public RemiliaStalkerPower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = "RemiliaStalkerPower";
        this.owner = owner;
        this.amount = -1;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/remiria/RemiliaStalkerPower.png");
        this.type = PowerType.BUFF;
        this.firstCard = false;
        this.canRefresh = false;
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        super.atEndOfTurn(isPlayer);
        this.firstCard = false;
        this.canRefresh = true;
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        super.onUseCard(card, action);
        if((this.canRefresh) && !(card instanceof RemiliaStalker)){
            this.firstCard = true;
            this.canRefresh = false;
        }
        else {
            this.firstCard = false;
        }
    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        super.onAttack(info, damageAmount, target);
        if(firstCard){
            this.firstCard = false;
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this.owner, this.owner, damageAmount));
        }
    }

    public void active(){
        this.canRefresh = true;
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0];
    }
}
