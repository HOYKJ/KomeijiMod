package Thmod.Cards.ScarletCard.rareCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Cards.ScarletCard.AbstractRemiriaCards;
import Thmod.Characters.RemiriaScarlet;
import Thmod.Power.remiria.MillenniumVampirePower;
import Thmod.Power.remiria.ScarletLordPower;
import basemod.helpers.TooltipInfo;

public class MillenniumVampire extends AbstractRemiriaCards {
    public static final String ID = "MillenniumVampire";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;

    public MillenniumVampire() {
        this(false);
    }

    public MillenniumVampire(boolean isPlus) {
        super("MillenniumVampire", MillenniumVampire.NAME,  2, MillenniumVampire.DESCRIPTION, CardType.POWER, CardRarity.RARE, CardTarget.NONE, isPlus);
        this.baseMagicNumber = 4;
        this.magicNumber = this.baseMagicNumber;
        this.addTips();
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        if(this.isPlus){
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new MillenniumVampirePower(p, this.magicNumber * 2), this.magicNumber * 2));
        }
        else {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new MillenniumVampirePower(p, this.magicNumber), this.magicNumber));
        }
        super.use(p, m);
    }

    public AbstractCard makeCopy() {
        if(AbstractDungeon.player != null){
            if((AbstractDungeon.player instanceof RemiriaScarlet) && (AbstractDungeon.player.hasPower(ScarletLordPower.POWER_ID))){
                return new MillenniumVampire(true);
            }
        }
        return new MillenniumVampire();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.upgradeBaseCost(1);
        }
    }

    @Override
    public void addTips() {
        this.tips.clear();
        this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("MillenniumVampire");
        NAME = MillenniumVampire.cardStrings.NAME;
        DESCRIPTION = MillenniumVampire.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = MillenniumVampire.cardStrings.EXTENDED_DESCRIPTION;
    }
}
