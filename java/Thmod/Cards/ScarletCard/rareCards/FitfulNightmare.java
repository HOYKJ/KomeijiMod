package Thmod.Cards.ScarletCard.rareCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Cards.ScarletCard.AbstractRemiriaCards;
import Thmod.Characters.RemiriaScarlet;
import Thmod.Power.remiria.FitfulNightmarePower;
import Thmod.Power.remiria.ScarletLordPower;
import basemod.helpers.TooltipInfo;

public class FitfulNightmare extends AbstractRemiriaCards {
    public static final String ID = "FitfulNightmare";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;

    public FitfulNightmare() {
        this(false);
    }

    public FitfulNightmare(boolean isPlus) {
        super("FitfulNightmare", FitfulNightmare.NAME,  3, FitfulNightmare.DESCRIPTION, CardType.POWER, CardRarity.RARE, CardTarget.NONE, isPlus);
        this.addTips();
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        if(!this.isPlus){
            AbstractCard card = AbstractDungeon.returnRandomCurse().makeCopy();
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAction(card, 1));
        }
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new FitfulNightmarePower(p)));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new ScarletLordPower(p, 1), 1));
        super.use(p, m);
    }

    public AbstractCard makeCopy() {
        if(AbstractDungeon.player != null){
            if((AbstractDungeon.player instanceof RemiriaScarlet) && (AbstractDungeon.player.hasPower(ScarletLordPower.POWER_ID))){
                return new FitfulNightmare(true);
            }
        }
        return new FitfulNightmare();
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
        cardStrings = CardCrawlGame.languagePack.getCardStrings("FitfulNightmare");
        NAME = FitfulNightmare.cardStrings.NAME;
        DESCRIPTION = FitfulNightmare.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = FitfulNightmare.cardStrings.EXTENDED_DESCRIPTION;
    }
}
