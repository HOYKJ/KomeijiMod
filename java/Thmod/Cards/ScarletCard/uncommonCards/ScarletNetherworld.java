package Thmod.Cards.ScarletCard.uncommonCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ThornsPower;

import Thmod.Actions.Remiria.RemoveDebuffsActionChange;
import Thmod.Cards.ScarletCard.AbstractRemiriaCards;
import Thmod.Characters.RemiriaScarlet;
import Thmod.Power.remiria.BloodBruisePower;
import Thmod.Power.remiria.ScarletLordPower;
import basemod.helpers.TooltipInfo;

public class ScarletNetherworld extends AbstractRemiriaCards {
    public static final String ID = "ScarletNetherworld";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;

    public ScarletNetherworld() {
        this(false);
    }

    public ScarletNetherworld(boolean isPlus) {
        super("ScarletNetherworld", ScarletNetherworld.NAME,  2, ScarletNetherworld.DESCRIPTION, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF, isPlus);
        this.baseBlock = 12;
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
        this.addTips();
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        int count = 0;
        AbstractDungeon.actionManager.addToTop(new GainBlockAction(p, p, this.block));
        if(p.hasPower(BloodBruisePower.POWER_ID)) {
            count = Math.min(this.magicNumber, p.getPower(BloodBruisePower.POWER_ID).amount);
            if(p.getPower(BloodBruisePower.POWER_ID).amount > this.magicNumber){
                p.getPower(BloodBruisePower.POWER_ID).amount -= this.magicNumber;
            }
            else {
                AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, BloodBruisePower.POWER_ID));
            }
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new ThornsPower(p, count), count));
        }
        if(this.isPlus){
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new ThornsPower(p, count), count));
        }
        super.use(p, m);
    }

    public AbstractCard makeCopy() {
        if(AbstractDungeon.player != null){
            if((AbstractDungeon.player instanceof RemiriaScarlet) && (AbstractDungeon.player.hasPower(ScarletLordPower.POWER_ID))){
                return new ScarletNetherworld(true);
            }
        }
        return new ScarletNetherworld();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.upgradeMagicNumber(2);
        }
    }

    @Override
    public void plusCard() {
        super.plusCard();
        this.name = EXTENDED_DESCRIPTION[2];
        this.initializeTitle();
    }

    @Override
    public void normalCard() {
        super.normalCard();
        this.name = NAME;
        this.initializeTitle();
    }

    @Override
    public void addTips() {
        this.tips.clear();
        this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("ScarletNetherworld");
        NAME = ScarletNetherworld.cardStrings.NAME;
        DESCRIPTION = ScarletNetherworld.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = ScarletNetherworld.cardStrings.EXTENDED_DESCRIPTION;
    }
}
