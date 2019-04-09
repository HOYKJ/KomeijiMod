package Thmod.Cards.ScarletCard.uncommonCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import Thmod.Cards.ScarletCard.AbstractRemiriaCards;
import Thmod.Characters.RemiriaScarlet;
import Thmod.Power.remiria.CombatMeterPower;
import Thmod.Power.remiria.ScarletLordPower;
import basemod.helpers.TooltipInfo;

public class CombatMeter extends AbstractRemiriaCards {
    public static final String ID = "CombatMeter";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;

    public CombatMeter() {
        this(false);
    }

    public CombatMeter(boolean isPlus) {
        super("CombatMeter", CombatMeter.NAME,  1, CombatMeter.DESCRIPTION, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF, isPlus);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.addTips();
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractPower power = new CombatMeterPower(p, this.magicNumber);
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, power, this.magicNumber));
        if(this.isPlus){
            ((CombatMeterPower)power).active(this);
        }
        super.use(p, m);
    }

    public AbstractCard makeCopy() {
        if(AbstractDungeon.player != null){
            if((AbstractDungeon.player instanceof RemiriaScarlet) && (AbstractDungeon.player.hasPower(ScarletLordPower.POWER_ID))){
                return new CombatMeter(true);
            }
        }
        return new CombatMeter();
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
        cardStrings = CardCrawlGame.languagePack.getCardStrings("CombatMeter");
        NAME = CombatMeter.cardStrings.NAME;
        DESCRIPTION = CombatMeter.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = CombatMeter.cardStrings.EXTENDED_DESCRIPTION;
    }
}
