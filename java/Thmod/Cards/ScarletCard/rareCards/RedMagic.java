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
import Thmod.Power.remiria.BloodBruisePower;
import Thmod.Power.remiria.RedMagicPower;
import Thmod.Power.remiria.ScarletLordPower;
import basemod.helpers.TooltipInfo;

public class RedMagic extends AbstractRemiriaCards {
    public static final String ID = "RedMagic";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;

    public RedMagic() {
        this(false);
    }

    public RedMagic(boolean isPlus) {
        super("RedMagic", RedMagic.NAME,  2, RedMagic.DESCRIPTION, CardType.POWER, CardRarity.RARE, CardTarget.SELF, isPlus);
        this.addTips();
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new RedMagicPower(p)));
        if(this.isPlus){
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new BloodBruisePower(p, 6), 6));
        }
        super.use(p, m);
    }

    public AbstractCard makeCopy() {
        if(AbstractDungeon.player != null){
            if((AbstractDungeon.player instanceof RemiriaScarlet) && (AbstractDungeon.player.hasPower(ScarletLordPower.POWER_ID))){
                return new RedMagic(true);
            }
        }
        return new RedMagic();
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
        cardStrings = CardCrawlGame.languagePack.getCardStrings("RedMagic");
        NAME = RedMagic.cardStrings.NAME;
        DESCRIPTION = RedMagic.cardStrings.DESCRIPTION;
        UPGRADE_DESCRIPTION = RedMagic.cardStrings.UPGRADE_DESCRIPTION;
        EXTENDED_DESCRIPTION = RedMagic.cardStrings.EXTENDED_DESCRIPTION;
    }
}
