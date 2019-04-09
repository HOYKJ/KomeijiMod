package Thmod.Cards.ScarletCard.rareCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Cards.ScarletCard.AbstractRemiriaCards;
import Thmod.Characters.RemiriaScarlet;
import Thmod.Power.remiria.BloodBruisePower;
import Thmod.Power.remiria.BloodCoagulationPower;
import Thmod.Power.remiria.ScarletLordPower;
import basemod.helpers.TooltipInfo;

public class BloodCoagulation extends AbstractRemiriaCards {
    public static final String ID = "BloodCoagulation";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;

    public BloodCoagulation() {
        this(false);
    }

    public BloodCoagulation(boolean isPlus) {
        super("BloodCoagulation", BloodCoagulation.NAME,  1, BloodCoagulation.DESCRIPTION, CardType.POWER, CardRarity.RARE, CardTarget.NONE, isPlus);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.addTips();
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new BloodCoagulationPower(p, this.magicNumber), this.magicNumber));
        if(this.isPlus){
            if(p.hasPower(BloodBruisePower.POWER_ID)){
                AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, p.getPower(BloodBruisePower.POWER_ID).amount));
            }
            for (int i = 0; i < AbstractDungeon.getCurrRoom().monsters.monsters.size(); i++) {
                AbstractMonster target = AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
                if ((!(target.isDying)) && (target.currentHealth > 0) && (!(target.isEscaping))) {
                    if(target.hasPower(BloodBruisePower.POWER_ID)){
                        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, target.getPower(BloodBruisePower.POWER_ID).amount));
                    }
                }
            }
        }
        super.use(p, m);
    }

    public AbstractCard makeCopy() {
        if(AbstractDungeon.player != null){
            if((AbstractDungeon.player instanceof RemiriaScarlet) && (AbstractDungeon.player.hasPower(ScarletLordPower.POWER_ID))){
                return new BloodCoagulation(true);
            }
        }
        return new BloodCoagulation();
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
        cardStrings = CardCrawlGame.languagePack.getCardStrings("BloodCoagulation");
        NAME = BloodCoagulation.cardStrings.NAME;
        DESCRIPTION = BloodCoagulation.cardStrings.DESCRIPTION;
        UPGRADE_DESCRIPTION = BloodCoagulation.cardStrings.UPGRADE_DESCRIPTION;
        EXTENDED_DESCRIPTION = BloodCoagulation.cardStrings.EXTENDED_DESCRIPTION;
    }
}
