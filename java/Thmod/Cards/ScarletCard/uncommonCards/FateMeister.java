package Thmod.Cards.ScarletCard.uncommonCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Cards.ScarletCard.AbstractRemiriaCards;
import Thmod.Characters.RemiriaScarlet;
import Thmod.Power.remiria.FateMeisterPower;
import Thmod.Power.remiria.ScarletLordPower;
import basemod.helpers.TooltipInfo;

public class FateMeister extends AbstractRemiriaCards {
    public static final String ID = "FateMeister";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;

    public FateMeister() {
        this(false);
    }

    public FateMeister(boolean isPlus) {
        super("FateMeister", FateMeister.NAME,  1, FateMeister.DESCRIPTION, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF, isPlus);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.addTips();
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new FateMeisterPower(p, this.magicNumber), this.magicNumber));
        if(this.isPlus){
            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, 2));
        }
        super.use(p, m);
    }

    public AbstractCard makeCopy() {
        if(AbstractDungeon.player != null){
            if((AbstractDungeon.player instanceof RemiriaScarlet) && (AbstractDungeon.player.hasPower(ScarletLordPower.POWER_ID))){
                return new FateMeister(true);
            }
        }
        return new FateMeister();
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
        cardStrings = CardCrawlGame.languagePack.getCardStrings("FateMeister");
        NAME = FateMeister.cardStrings.NAME;
        DESCRIPTION = FateMeister.cardStrings.DESCRIPTION;
        UPGRADE_DESCRIPTION = FateMeister.cardStrings.UPGRADE_DESCRIPTION;
        EXTENDED_DESCRIPTION = FateMeister.cardStrings.EXTENDED_DESCRIPTION;
    }
}
