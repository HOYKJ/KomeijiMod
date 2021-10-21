package Thmod.Cards.ScarletCard.rareCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Actions.Remiria.PlusCardAction;
import Thmod.Actions.common.LatterAction;
import Thmod.Cards.ScarletCard.AbstractRemiriaCards;
import Thmod.Characters.RemiriaScarlet;
import Thmod.Power.remiria.ScarletLordPower;
import Thmod.Power.remiria.StarOfDavidPower;
import basemod.helpers.TooltipInfo;

public class StarOfDavid extends AbstractRemiriaCards {
    public static final String ID = "StarOfDavid";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;

    public StarOfDavid() {
        this(false);
    }

    public StarOfDavid(boolean isPlus) {
        super("StarOfDavid", StarOfDavid.NAME,  3, StarOfDavid.DESCRIPTION, CardType.POWER, CardRarity.RARE, CardTarget.NONE, isPlus);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.addTips();
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StarOfDavidPower(p, this.magicNumber), this.magicNumber));
        if(this.isPlus){
            AbstractDungeon.actionManager.addToBottom(new PlusCardAction(p.drawPile, true, this, true));
            AbstractDungeon.actionManager.addToBottom(new PlusCardAction(p.hand, true, this, true));
            AbstractDungeon.actionManager.addToBottom(new PlusCardAction(p.discardPile, true, this, true));
        }
//        if(this.upgraded){
//            AbstractDungeon.actionManager.addToBottom(new LatterAction(()->{
//                if(p.hasPower(StarOfDavidPower.POWER_ID)){
//                    ((StarOfDavidPower)p.getPower(StarOfDavidPower.POWER_ID)).trigger();
//                }
//            }, 0.1f));
//        }
        super.use(p, m);
    }

    public AbstractCard makeCopy() {
        if(AbstractDungeon.player != null){
            if((AbstractDungeon.player instanceof RemiriaScarlet) && (AbstractDungeon.player.hasPower(ScarletLordPower.POWER_ID))){
                return new StarOfDavid(true);
            }
        }
        return new StarOfDavid();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            //this.isInnate = true;
//            this.rawDescription = UPGRADE_DESCRIPTION;
//            this.initializeDescription();
            this.upgradeBaseCost(2);
        }
    }

    @Override
    public void addTips() {
        this.tips.clear();
        this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("StarOfDavid");
        NAME = StarOfDavid.cardStrings.NAME;
        DESCRIPTION = StarOfDavid.cardStrings.DESCRIPTION;
        UPGRADE_DESCRIPTION = StarOfDavid.cardStrings.UPGRADE_DESCRIPTION;
        EXTENDED_DESCRIPTION = StarOfDavid.cardStrings.EXTENDED_DESCRIPTION;
    }
}
