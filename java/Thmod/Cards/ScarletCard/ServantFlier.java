package Thmod.Cards.ScarletCard;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import java.util.List;

import Thmod.Actions.Remiria.MakeCardInHandAction;
import Thmod.Characters.RemiriaScarlet;
import Thmod.Power.remiria.ScarletLordPower;
import Thmod.Power.remiria.ServantFlierPower;
import basemod.helpers.TooltipInfo;

public class ServantFlier extends AbstractRemiriaCards {
    public static final String ID = "ServantFlier";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;

    public ServantFlier() {
        this(false);
    }

    public ServantFlier(boolean isPlus) {
        super("ServantFlier", ServantFlier.NAME,  1, ServantFlier.DESCRIPTION, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE, isPlus);
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.addTips();
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new ServantFlierPower(p, this.magicNumber), this.magicNumber));
        if(this.isPlus){
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new ServantFlierPower(p, 2), 2));
        }
        super.use(p, m);
    }

    public AbstractCard makeCopy() {
        if(AbstractDungeon.player != null){
            if((AbstractDungeon.player instanceof RemiriaScarlet) && (AbstractDungeon.player.hasPower(ScarletLordPower.POWER_ID))){
                return new ServantFlier(true);
            }
        }
        return new ServantFlier();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
        }
    }

    @Override
    public void addTips() {
        this.tips.clear();
        this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("ServantFlier");
        NAME = ServantFlier.cardStrings.NAME;
        DESCRIPTION = ServantFlier.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = ServantFlier.cardStrings.EXTENDED_DESCRIPTION;
    }
}
