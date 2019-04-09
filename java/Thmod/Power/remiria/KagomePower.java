package Thmod.Power.remiria;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;

public class KagomePower extends AbstractPower {
    public static final String POWER_ID = "KagomePower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("KagomePower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private AbstractCard card1, card2, card3;
    private int damageCounter;

    public KagomePower(AbstractCreature owner, AbstractCard card1, AbstractCard card2, AbstractCard card3) {
        this.name = NAME;
        this.ID = "KagomePower";
        this.owner = owner;
        this.amount = 4;
        this.img = ImageMaster.loadImage("images/power/32/remiria/KagomePower.png");
        this.type = PowerType.BUFF;
        this.card1 = card1;
        this.card2 = card2;
        this.card3 = card3;
        this.damageCounter = 0;
        updateDescription();
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        this.damageCounter += damageAmount;
        if(this.damageCounter >= 50){
            if(this.card1 != null){
                if (AbstractDungeon.player.hand.size() != 10) {
                    AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(this.card1, 1, false));
                } else {
                    AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAction(this.card1, 1));
                }
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, 1), 1));
                this.card1 = null;
            }
            else if(this.card2 != null){
                if (AbstractDungeon.player.hand.size() != 10) {
                    AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(this.card2, 1, false));
                } else {
                    AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAction(this.card2, 1));
                }
                this.card2 = null;
            }
            else if(this.card3 != null){
                if (AbstractDungeon.player.hand.size() != 10) {
                    AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(this.card3, 1, false));
                } else {
                    AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAction(this.card3, 1));
                }
                this.card3 = null;
                AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, this));
            }
            this.damageCounter = 0;
        }
        updateDescription();
        return super.onAttacked(info, damageAmount);
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        super.atEndOfTurn(isPlayer);
        this.amount -= 1;
        if(this.amount < 1){
            AbstractCard cardToRemove = null;
            AbstractCard cardToRemove2 = null;
            AbstractCard cardToRemove3 = null;
            int sum = 0;
            if(this.card1 != null){
                for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
                    if (c.uuid.equals(this.card1.uuid))
                    {
                        cardToRemove = c;
                        sum ++;
                        break;
                    }
                }
            }
            if(this.card2 != null){
                for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
                    if (c.uuid.equals(this.card2.uuid))
                    {
                        cardToRemove2 = c;
                        sum ++;
                        break;
                    }
                }
            }
            if(this.card3 != null){
                for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
                    if (c.uuid.equals(this.card3.uuid))
                    {
                        cardToRemove3 = c;
                        sum ++;
                        break;
                    }
                }
            }
            switch (sum){
                case 1:
                    if(cardToRemove3 != null){
                        AbstractDungeon.player.masterDeck.group.remove(cardToRemove3);
                        AbstractDungeon.topLevelEffects.add(new PurgeCardEffect(
                                cardToRemove3, Settings.WIDTH / 2.0F - AbstractCard.IMG_WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
                    }
                    break;
                case 2:
                    if(cardToRemove2 != null){
                        AbstractDungeon.player.masterDeck.group.remove(cardToRemove2);
                        AbstractDungeon.topLevelEffects.add(new PurgeCardEffect(
                                cardToRemove2, Settings.WIDTH / 2.0F - 30.0F * Settings.scale - AbstractCard.IMG_WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
                    }
                    if(cardToRemove3 != null){
                        AbstractDungeon.player.masterDeck.group.remove(cardToRemove3);
                        AbstractDungeon.topLevelEffects.add(new PurgeCardEffect(
                                cardToRemove3, Settings.WIDTH / 2.0F + 30.0F * Settings.scale - AbstractCard.IMG_WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
                    }
                    break;
                case 3:
                    if(cardToRemove != null){
                        AbstractDungeon.player.masterDeck.group.remove(cardToRemove);
                        AbstractDungeon.topLevelEffects.add(new PurgeCardEffect(
                                cardToRemove, Settings.WIDTH / 2.0F - 60.0F * Settings.scale - AbstractCard.IMG_WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
                    }
                    if(cardToRemove2 != null){
                        AbstractDungeon.player.masterDeck.group.remove(cardToRemove2);
                        AbstractDungeon.topLevelEffects.add(new PurgeCardEffect(
                                cardToRemove2, Settings.WIDTH / 2.0F - AbstractCard.IMG_WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
                    }
                    if(cardToRemove3 != null){
                        AbstractDungeon.player.masterDeck.group.remove(cardToRemove3);
                        AbstractDungeon.topLevelEffects.add(new PurgeCardEffect(
                                cardToRemove3, Settings.WIDTH / 2.0F + 60.0F * Settings.scale - AbstractCard.IMG_WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
                    }
                    break;
            }
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, this));
        }
        updateDescription();
    }

    public void updateDescription()
    {
        if(this.card1 != null){
            this.description = DESCRIPTIONS[0] + this.card1.name + "," + this.card2.name + "," + this.card3.name + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2]
                    + this.damageCounter + DESCRIPTIONS[3];
        }
        else if(this.card2 != null){
            this.description = DESCRIPTIONS[0] + this.card2.name + "," + this.card3.name + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2] + this.damageCounter + DESCRIPTIONS[3];
        }
        else if(this.card3 != null){
            this.description = DESCRIPTIONS[0] + this.card3.name + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2] + this.damageCounter + DESCRIPTIONS[3];
        }
    }
}
