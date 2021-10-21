package Thmod.Power.remiria;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import Thmod.Actions.common.LatterAction;
import Thmod.Cards.ScarletCard.AbstractRemiriaCards;
import Thmod.Characters.RemiriaScarlet;
import Thmod.vfx.RemovePowerEffect;

public class ScarletLordPower extends AbstractPower {
    public static final String POWER_ID = "ScarletLordPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("ScarletLordPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private int counter;

    public ScarletLordPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = "ScarletLordPower";
        this.owner = owner;
        this.amount = amount;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/remiria/ScarletLordPower.png");
        this.type = PowerType.BUFF;
        this.counter = 6;
        updateDescription();
    }

    public void onApply(){
        AbstractPlayer player = AbstractDungeon.player;
        AbstractDungeon.actionManager.addToBottom(new LatterAction(()->{
            if(player instanceof RemiriaScarlet){
                ((RemiriaScarlet) player).changeState("GUNGNIR");
            }
        }, 0f));
        for(AbstractCard card : player.hand.group){
            if(card instanceof AbstractRemiriaCards){
                ((AbstractRemiriaCards) card).plusCard();
            }
        }
        for(AbstractCard card : player.discardPile.group){
            if(card instanceof AbstractRemiriaCards){
                ((AbstractRemiriaCards) card).plusCard();
            }
        }
        for(AbstractCard card : player.drawPile.group){
            if(card instanceof AbstractRemiriaCards){
                ((AbstractRemiriaCards) card).plusCard();
            }
        }
        this.counter = 6;
        updateDescription();
    }

    @Override
    public void onRemove() {
        super.onRemove();
        AbstractPlayer player = AbstractDungeon.player;
        if(player instanceof RemiriaScarlet){
            ((RemiriaScarlet) player).changeState("NORMAL");
        }
        for(AbstractCard card : player.hand.group){
            if(card instanceof AbstractRemiriaCards){
                ((AbstractRemiriaCards) card).normalCard();
            }
        }
        for(AbstractCard card : player.discardPile.group){
            if(card instanceof AbstractRemiriaCards){
                ((AbstractRemiriaCards) card).normalCard();
            }
        }
        for(AbstractCard card : player.drawPile.group){
            if(card instanceof AbstractRemiriaCards){
                ((AbstractRemiriaCards) card).normalCard();
            }
        }
    }

    public void onPlayCard(AbstractCard card, AbstractMonster m)
    {

    }

    @Override
    public void onAfterCardPlayed(AbstractCard usedCard) {
        super.onAfterCardPlayed(usedCard);
        if ((this.counter > 0) && (usedCard.type != AbstractCard.CardType.CURSE))
        {
            this.counter -= 1;
            updateDescription();
            if (this.counter <= 0) {
                AbstractDungeon.actionManager.addToBottom(new DiscardAction(AbstractDungeon.player, AbstractDungeon.player, AbstractDungeon.player.hand.group.size(), true));
                this.counter = 8;
                updateDescription();
            }
        }
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        super.atEndOfTurn(isPlayer);
        this.counter = 6;
        updateDescription();
//        if(!this.owner.hasPower(FitfulNightmarePower.POWER_ID)) {
            if (this.amount > 1) {
                this.amount -= 1;
            } else {
                AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, this));
            }
        //}
    }

    @Override
    public void onVictory() {
        super.onVictory();
        AbstractDungeon.effectsQueue.add(new RemovePowerEffect(this.owner, this.owner, this));
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0] + this.counter + DESCRIPTIONS[1];
    }
}
