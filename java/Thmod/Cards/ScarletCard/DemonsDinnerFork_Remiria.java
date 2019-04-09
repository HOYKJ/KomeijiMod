package Thmod.Cards.ScarletCard;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import Thmod.Cards.ScarletCard.DeriveCards.DemonsFork_Remiria;
import Thmod.Characters.RemiriaScarlet;
import Thmod.Power.remiria.ScarletLordPower;
import basemod.helpers.TooltipInfo;

public class DemonsDinnerFork_Remiria extends AbstractRemiriaCards {
    public static final String ID = "DemonsDinnerFork_Remiria";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;

    public DemonsDinnerFork_Remiria() {
        this(false);
    }

    public DemonsDinnerFork_Remiria(boolean isPlus) {
        super("DemonsDinnerFork_Remiria", DemonsDinnerFork_Remiria.NAME,  -1, DemonsDinnerFork_Remiria.DESCRIPTION, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE, isPlus);
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.addTips();
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        if (this.energyOnUse < EnergyPanel.totalCount) {
            this.energyOnUse = EnergyPanel.totalCount;
        }
        if(this.isPlus){
            this.magicNumber += 1;
        }
        AbstractCard c = new DemonsFork_Remiria().makeCopy();
        if(this.upgraded) {
            c.upgrade();
        }
        if (p.hasRelic("Chemical X")) {
            this.energyOnUse += 2;
            p.getRelic("Chemical X").flash();
        }
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c, this.energyOnUse * this.magicNumber));
        if(!this.freeToPlayOnce) {
            p.energy.use(EnergyPanel.totalCount);
        }
        super.use(p, m);
    }

    public AbstractCard makeCopy() {
        if(AbstractDungeon.player != null){
            if((AbstractDungeon.player instanceof RemiriaScarlet) && (AbstractDungeon.player.hasPower(ScarletLordPower.POWER_ID))){
                return new DemonsDinnerFork_Remiria(true);
            }
        }
        return new DemonsDinnerFork_Remiria();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.rawDescription = UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public void addTips() {
        this.tips.clear();
        this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
        this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[2], EXTENDED_DESCRIPTION[3]));
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("DemonsDinnerFork_Remiria");
        NAME = DemonsDinnerFork_Remiria.cardStrings.NAME;
        DESCRIPTION = DemonsDinnerFork_Remiria.cardStrings.DESCRIPTION;
        UPGRADE_DESCRIPTION = DemonsDinnerFork_Remiria.cardStrings.UPGRADE_DESCRIPTION;
        EXTENDED_DESCRIPTION = DemonsDinnerFork_Remiria.cardStrings.EXTENDED_DESCRIPTION;
    }
}
