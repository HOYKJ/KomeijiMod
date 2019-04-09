package Thmod.Cards.ScarletCard.uncommonCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import Thmod.Cards.ScarletCard.AbstractRemiriaCards;
import Thmod.Characters.RemiriaScarlet;
import Thmod.Power.remiria.BathedInBloodPower;
import Thmod.Power.remiria.BloodBruisePower;
import Thmod.Power.remiria.ScarletLordPower;
import basemod.helpers.TooltipInfo;

public class BathedInBlood extends AbstractRemiriaCards {
    public static final String ID = "BathedInBlood";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;

    public BathedInBlood() {
        this(false);
    }

    public BathedInBlood(boolean isPlus) {
        super("BathedInBlood", BathedInBlood.NAME,  1, BathedInBlood.DESCRIPTION, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF, isPlus);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.addTips();
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new BathedInBloodPower(p, this.magicNumber), this.magicNumber));
        super.use(p, m);
    }

    public void appliedPower(AbstractPower power){
        if(this.isPlus) {
            if ((power instanceof BloodBruisePower) && (power.owner == AbstractDungeon.player)) {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, this.magicNumber), this.magicNumber));
            }
        }
    }

    public AbstractCard makeCopy() {
        if(AbstractDungeon.player != null){
            if((AbstractDungeon.player instanceof RemiriaScarlet) && (AbstractDungeon.player.hasPower(ScarletLordPower.POWER_ID))){
                return new BathedInBlood(true);
            }
        }
        return new BathedInBlood();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.isInnate = true;
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
        cardStrings = CardCrawlGame.languagePack.getCardStrings("BathedInBlood");
        NAME = BathedInBlood.cardStrings.NAME;
        DESCRIPTION = BathedInBlood.cardStrings.DESCRIPTION;
        UPGRADE_DESCRIPTION = BathedInBlood.cardStrings.UPGRADE_DESCRIPTION;
        EXTENDED_DESCRIPTION = BathedInBlood.cardStrings.EXTENDED_DESCRIPTION;
    }
}
