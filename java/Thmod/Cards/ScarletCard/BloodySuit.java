package Thmod.Cards.ScarletCard;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Characters.RemiriaScarlet;
import Thmod.Power.remiria.BloodBruisePower;
import Thmod.Power.remiria.ScarletLordPower;
import basemod.helpers.TooltipInfo;

public class BloodySuit extends AbstractRemiriaCards {
    public static final String ID = "BloodySuit";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;

    public BloodySuit() {
        this(false);
    }

    public BloodySuit(boolean isPlus) {
        super("BloodySuit", BloodySuit.NAME,  1, BloodySuit.DESCRIPTION, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF, isPlus);
        this.baseBlock = 8;
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.addTips();
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        if(AbstractDungeon.player.hasPower(BloodBruisePower.POWER_ID)) {
            this.block += AbstractDungeon.player.getPower(BloodBruisePower.POWER_ID).amount * this.magicNumber;
            if (isPlus) {
                this.block += AbstractDungeon.player.getPower(BloodBruisePower.POWER_ID).amount;
            }
            this.isBlockModified = true;
        }
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
        if(AbstractDungeon.player.hasPower(BloodBruisePower.POWER_ID)) {
            this.block += AbstractDungeon.player.getPower(BloodBruisePower.POWER_ID).amount * this.magicNumber;
            if (isPlus) {
                this.block += AbstractDungeon.player.getPower(BloodBruisePower.POWER_ID).amount;
            }
            this.isBlockModified = true;
        }
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
        super.use(p, m);
    }

    public AbstractCard makeCopy() {
        if(AbstractDungeon.player != null){
            if((AbstractDungeon.player instanceof RemiriaScarlet) && (AbstractDungeon.player.hasPower(ScarletLordPower.POWER_ID))){
                return new BloodySuit(true);
            }
        }
        return new BloodySuit();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.upgradeBlock(4);
        }
    }

    @Override
    public void addTips() {
        this.tips.clear();
        this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("BloodySuit");
        NAME = BloodySuit.cardStrings.NAME;
        DESCRIPTION = BloodySuit.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = BloodySuit.cardStrings.EXTENDED_DESCRIPTION;
    }
}
