package Thmod.Cards.ScarletCard.uncommonCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.unique.RemoveDebuffsAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

import Thmod.Actions.Remiria.RemoveDebuffsActionChange;
import Thmod.Cards.ScarletCard.AbstractRemiriaCards;
import Thmod.Characters.RemiriaScarlet;
import Thmod.Power.remiria.BloodBruisePower;
import Thmod.Power.remiria.ScarletLordPower;
import basemod.helpers.TooltipInfo;

public class BloodPact extends AbstractRemiriaCards {
    public static final String ID = "BloodPact";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;

    public BloodPact() {
        this(false);
    }

    public BloodPact(boolean isPlus) {
        super("BloodPact", BloodPact.NAME,  1, BloodPact.DESCRIPTION, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF, isPlus);
        this.baseMagicNumber = 4;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
        this.addTips();
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new RemoveDebuffsActionChange(p));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new BloodBruisePower(p, this.magicNumber), this.magicNumber));
        if(this.isPlus){
            for (int i = 0; i < AbstractDungeon.getCurrRoom().monsters.monsters.size(); i++) {
                AbstractMonster target = AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
                if ((!(target.isDying)) && (target.currentHealth > 0) && (!(target.isEscaping))) {
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target, p, new BloodBruisePower(target, this.baseMagicNumber / 2), this.baseMagicNumber / 2));
                }
            }
        }
        for(AbstractCard card : p.hand.group){
            if((card.type == CardType.STATUS) || (card.type == CardType.CURSE)){
                AbstractDungeon.actionManager.addToBottom(new ExhaustSpecificCardAction(card, p.hand));
            }
        }
        for(AbstractCard card : p.discardPile.group){
            if((card.type == CardType.STATUS) || (card.type == CardType.CURSE)){
                AbstractDungeon.actionManager.addToBottom(new ExhaustSpecificCardAction(card, p.discardPile));
            }
        }
        for(AbstractCard card : p.drawPile.group){
            if((card.type == CardType.STATUS) || (card.type == CardType.CURSE)){
                AbstractDungeon.actionManager.addToBottom(new ExhaustSpecificCardAction(card, p.drawPile));
            }
        }
        super.use(p, m);
    }

    public AbstractCard makeCopy() {
        if(AbstractDungeon.player != null){
            if((AbstractDungeon.player instanceof RemiriaScarlet) && (AbstractDungeon.player.hasPower(ScarletLordPower.POWER_ID))){
                return new BloodPact(true);
            }
        }
        return new BloodPact();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.upgradeBaseCost(0);
        }
    }

    @Override
    public void addTips() {
        this.tips.clear();
        this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("BloodPact");
        NAME = BloodPact.cardStrings.NAME;
        DESCRIPTION = BloodPact.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = BloodPact.cardStrings.EXTENDED_DESCRIPTION;
    }
}
