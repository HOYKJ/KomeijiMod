package Thmod.Cards.ScarletCard.rareCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Cards.ScarletCard.AbstractRemiriaCards;
import Thmod.Characters.RemiriaScarlet;
import Thmod.Power.remiria.MiserableFatePower;
import Thmod.Power.remiria.ScarletLordPower;
import basemod.helpers.TooltipInfo;

public class MiserableFate_Remiria extends AbstractRemiriaCards {
    public static final String ID = "MiserableFate_Remiria";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;

    public MiserableFate_Remiria() {
        this(false);
    }

    public MiserableFate_Remiria(boolean isPlus) {
        super("MiserableFate_Remiria", MiserableFate_Remiria.NAME,  3, MiserableFate_Remiria.DESCRIPTION, CardType.POWER, CardRarity.RARE, CardTarget.NONE, isPlus);
        this.addTips();
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new MiserableFatePower(p)));
        if(this.isPlus){
            AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(1));
        }
        super.use(p, m);
    }

    public AbstractCard makeCopy() {
        if(AbstractDungeon.player != null){
            if((AbstractDungeon.player instanceof RemiriaScarlet) && (AbstractDungeon.player.hasPower(ScarletLordPower.POWER_ID))){
                return new MiserableFate_Remiria(true);
            }
        }
        return new MiserableFate_Remiria();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.upgradeBaseCost(2);
        }
    }

    @Override
    public void addTips() {
        this.tips.clear();
        this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("MiserableFate_Remiria");
        NAME = MiserableFate_Remiria.cardStrings.NAME;
        DESCRIPTION = MiserableFate_Remiria.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = MiserableFate_Remiria.cardStrings.EXTENDED_DESCRIPTION;
    }
}
