package Thmod.Cards.ScarletCard.uncommonCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Cards.ScarletCard.AbstractRemiriaCards;
import Thmod.Characters.RemiriaScarlet;
import Thmod.Power.remiria.NaturallyHealingPower;
import Thmod.Power.remiria.ScarletLordPower;
import basemod.helpers.TooltipInfo;

public class NaturallyHealing extends AbstractRemiriaCards {
    public static final String ID = "NaturallyHealing";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;

    public NaturallyHealing() {
        this(false);
    }

    public NaturallyHealing(boolean isPlus) {
        super("NaturallyHealing", NaturallyHealing.NAME,  1, NaturallyHealing.DESCRIPTION, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF, isPlus);
        this.addTips();
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new NaturallyHealingPower(p, 1), 1));
        if(this.isPlus){
            if(p.hasPower(NaturallyHealingPower.POWER_ID)){
                ((NaturallyHealingPower)p.getPower(NaturallyHealingPower.POWER_ID)).trigger();
            }
        }
        super.use(p, m);
    }

    public AbstractCard makeCopy() {
        if(AbstractDungeon.player != null){
            if((AbstractDungeon.player instanceof RemiriaScarlet) && (AbstractDungeon.player.hasPower(ScarletLordPower.POWER_ID))){
                return new NaturallyHealing(true);
            }
        }
        return new NaturallyHealing();
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
        cardStrings = CardCrawlGame.languagePack.getCardStrings("NaturallyHealing");
        NAME = NaturallyHealing.cardStrings.NAME;
        DESCRIPTION = NaturallyHealing.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = NaturallyHealing.cardStrings.EXTENDED_DESCRIPTION;
    }
}
