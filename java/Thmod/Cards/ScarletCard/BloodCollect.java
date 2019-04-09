package Thmod.Cards.ScarletCard;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import Thmod.Characters.RemiriaScarlet;
import Thmod.Power.remiria.BloodBruisePower;
import Thmod.Power.remiria.BloodCollectPower;
import Thmod.Power.remiria.ScarletLordPower;
import basemod.helpers.TooltipInfo;

public class BloodCollect extends AbstractRemiriaCards {
    public static final String ID = "BloodCollect";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;

    public BloodCollect() {
        this(false);
    }

    public BloodCollect(boolean isPlus) {
        super("BloodCollect", BloodCollect.NAME,  0, BloodCollect.DESCRIPTION, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY, isPlus);
        this.baseMagicNumber = 8;
        this.magicNumber = this.baseMagicNumber;
        this.addTips();
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, 1));
        if(m.hasPower(BloodBruisePower.POWER_ID)) {
            AbstractPower blood = m.getPower(BloodBruisePower.POWER_ID);
            if(blood.amount > this.magicNumber) {
                blood.amount -= this.magicNumber;
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new BloodCollectPower(p, 1), 1));
                if (this.isPlus) {
                    if(blood.amount > this.magicNumber) {
                        blood.amount -= this.magicNumber;
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new BloodCollectPower(p, 1), 1));
                    }
                    else if(blood.amount == this.magicNumber){
                        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(m, m, BloodBruisePower.POWER_ID));
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new BloodCollectPower(p, 1), 1));
                    }
                }
            }
            else if(blood.amount == this.magicNumber){
                AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(m, m, BloodBruisePower.POWER_ID));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new BloodCollectPower(p, 1), 1));
            }
        }
        super.use(p, m);
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
            this.upgradeMagicNumber(-2);
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
        EXTENDED_DESCRIPTION = BloodCollect.cardStrings.EXTENDED_DESCRIPTION;
    }
}
