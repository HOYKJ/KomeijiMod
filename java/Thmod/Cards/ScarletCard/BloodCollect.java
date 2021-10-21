package Thmod.Cards.ScarletCard;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.WeakPower;

import Thmod.Characters.RemiriaScarlet;
import Thmod.Power.remiria.BloodBruisePower;
import Thmod.Power.remiria.BloodCollectPower;
import Thmod.Power.remiria.ScarletLordPower;
import basemod.helpers.TooltipInfo;

public class BloodCollect extends AbstractRemiriaFate {
    public static final String ID = "BloodCollect";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;

    public BloodCollect() {
        this(false);
    }

    public BloodCollect(boolean isPlus) {
        super("BloodCollect", BloodCollect.NAME,  -2, BloodCollect.DESCRIPTION, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY, isPlus);
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
        this.addTips();
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
//        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, 1));
//        if(m.hasPower(BloodBruisePower.POWER_ID)) {
//            AbstractPower blood = m.getPower(BloodBruisePower.POWER_ID);
//            if(blood.amount > this.magicNumber) {
//                blood.amount -= this.magicNumber;
//                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new BloodCollectPower(p, 1), 1));
//                if (this.isPlus) {
//                    if(blood.amount > this.magicNumber) {
//                        blood.amount -= this.magicNumber;
//                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new BloodCollectPower(p, 1), 1));
//                    }
//                    else if(blood.amount == this.magicNumber){
//                        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(m, m, BloodBruisePower.POWER_ID));
//                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new BloodCollectPower(p, 1), 1));
//                    }
//                }
//            }
//            else if(blood.amount == this.magicNumber){
//                AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(m, m, BloodBruisePower.POWER_ID));
//                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new BloodCollectPower(p, 1), 1));
//            }
//        }

        for (int i = 0; i < AbstractDungeon.getCurrRoom().monsters.monsters.size(); i++) {
            AbstractMonster target = AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
            if ((!(target.isDying)) && (target.currentHealth > 0) && (!(target.isEscaping))
                    && (target.hasPower(BloodBruisePower.POWER_ID))) {
                if(target.getPower(BloodBruisePower.POWER_ID).amount >= this.magicNumber){
                    AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(target, p, BloodBruisePower.POWER_ID, this.magicNumber));
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, this.magicNumber), this.magicNumber));
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new LoseStrengthPower(p, this.magicNumber), this.magicNumber));
                }
                else {
                    AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(target, p, BloodBruisePower.POWER_ID, target.getPower(BloodBruisePower.POWER_ID).amount));
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, target.getPower(BloodBruisePower.POWER_ID).amount), target.getPower(BloodBruisePower.POWER_ID).amount));
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new LoseStrengthPower(p, target.getPower(BloodBruisePower.POWER_ID).amount), target.getPower(BloodBruisePower.POWER_ID).amount));
                }
            }
        }
        if(this.upgraded){
            if((p.hasPower(BloodBruisePower.POWER_ID))){
                if(p.getPower(BloodBruisePower.POWER_ID).amount >= this.magicNumber){
                    AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(p, p, BloodBruisePower.POWER_ID, this.magicNumber));
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, this.magicNumber), this.magicNumber));
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new LoseStrengthPower(p, this.magicNumber), this.magicNumber));
                }
                else {
                    AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(p, p, BloodBruisePower.POWER_ID, p.getPower(BloodBruisePower.POWER_ID).amount));
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, p.getPower(BloodBruisePower.POWER_ID).amount), p.getPower(BloodBruisePower.POWER_ID).amount));
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new LoseStrengthPower(p, p.getPower(BloodBruisePower.POWER_ID).amount), p.getPower(BloodBruisePower.POWER_ID).amount));
                }
            }
        }
        if(this.isPlus){
            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, 1));
        }
        super.use(p, m);
    }

    @Override
    public void triggerOnExhaust() {
        super.triggerOnExhaust();
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new BloodCollectPower(AbstractDungeon.player, 1), 1));
    }

    public AbstractCard makeCopy() {
        if(AbstractDungeon.player != null){
            if((AbstractDungeon.player instanceof RemiriaScarlet) && (AbstractDungeon.player.hasPower(ScarletLordPower.POWER_ID))){
                return new BloodCollect(true);
            }
        }
        return new BloodCollect();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.rawDescription = UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public void addTips() {
        this.tips.clear();
        this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("BloodCollect");
        NAME = BloodCollect.cardStrings.NAME;
        DESCRIPTION = BloodCollect.cardStrings.DESCRIPTION;
        UPGRADE_DESCRIPTION = BloodCollect.cardStrings.UPGRADE_DESCRIPTION;
        EXTENDED_DESCRIPTION = BloodCollect.cardStrings.EXTENDED_DESCRIPTION;
    }
}
