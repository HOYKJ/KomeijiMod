package Thmod.Cards.ScarletCard;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.List;

import Thmod.Characters.RemiriaScarlet;
import Thmod.Power.remiria.ScarletLordPower;
import basemod.helpers.TooltipInfo;

public class DamonLordCradle_Remiria extends AbstractRemiriaCards {
    public static final String ID = "DamonLordCradle_Remiria";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;

    public DamonLordCradle_Remiria() {
        this(false);
    }

    public DamonLordCradle_Remiria(boolean isPlus) {
        super("DamonLordCradle_Remiria", DamonLordCradle_Remiria.NAME,  1, DamonLordCradle_Remiria.DESCRIPTION, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF, isPlus);
        this.baseBlock = 8;
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.addTips();
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToTop(new GainBlockAction(p, p, this.block));
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, this.magicNumber));
        if(this.isPlus){
            AbstractDungeon.actionManager.addToTop(new GainBlockAction(p, p, this.block));
        }
        super.use(p, m);
    }

    public AbstractCard makeCopy() {
        if(AbstractDungeon.player != null){
            if((AbstractDungeon.player instanceof RemiriaScarlet) && (AbstractDungeon.player.hasPower(ScarletLordPower.POWER_ID))){
                return new DamonLordCradle_Remiria(true);
            }
        }
        return new DamonLordCradle_Remiria();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.upgradeBlock(1);
            this.upgradeMagicNumber(1);
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
        cardStrings = CardCrawlGame.languagePack.getCardStrings("DamonLordCradle_Remiria");
        NAME = DamonLordCradle_Remiria.cardStrings.NAME;
        DESCRIPTION = DamonLordCradle_Remiria.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = DamonLordCradle_Remiria.cardStrings.EXTENDED_DESCRIPTION;
    }
}
