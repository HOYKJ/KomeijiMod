package Thmod.Cards.ScarletCard.uncommonCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Actions.Remiria.RemoveDebuffsActionChange;
import Thmod.Cards.ScarletCard.AbstractRemiriaCards;
import Thmod.Characters.RemiriaScarlet;
import Thmod.Power.remiria.BloodBruisePower;
import Thmod.Power.remiria.ScarletLordPower;
import basemod.helpers.TooltipInfo;

public class BloodSurge extends AbstractRemiriaCards {
    public static final String ID = "BloodSurge";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;

    public BloodSurge() {
        this(false);
    }

    public BloodSurge(boolean isPlus) {
        super("BloodSurge", BloodSurge.NAME,  0, BloodSurge.DESCRIPTION, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF, isPlus);
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
        this.addTips();
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new BloodBruisePower(p, this.magicNumber), this.magicNumber));
        AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(this.magicNumber));
        super.use(p, m);
    }

    public AbstractCard makeCopy() {
        if(AbstractDungeon.player != null){
            if((AbstractDungeon.player instanceof RemiriaScarlet) && (AbstractDungeon.player.hasPower(ScarletLordPower.POWER_ID))){
                return new BloodSurge(true);
            }
        }
        return new BloodSurge();
    }

    @Override
    public void plusCard() {
        super.plusCard();
        this.retain = true;
    }

    @Override
    public void normalCard() {
        super.normalCard();
        this.retain = false;
    }

    @Override
    public void update() {
        super.update();
        if((this.isPlus) && (!this.retain)){
            this.retain = true;
        }
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
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
        cardStrings = CardCrawlGame.languagePack.getCardStrings("BloodSurge");
        NAME = BloodSurge.cardStrings.NAME;
        DESCRIPTION = BloodSurge.cardStrings.DESCRIPTION;
        UPGRADE_DESCRIPTION = BloodSurge.cardStrings.UPGRADE_DESCRIPTION;
        EXTENDED_DESCRIPTION = BloodSurge.cardStrings.EXTENDED_DESCRIPTION;
    }
}
