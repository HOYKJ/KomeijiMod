package Thmod.Cards.ItemCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Actions.unique.PlayerTalkAction;
import Thmod.Cards.RareCards.LunaDial;
import Thmod.Power.MyWorldPower;
import Thmod.Power.PointPower;
import Thmod.Power.WocchiPower;

public class SutoppuWocchi extends AbstractItemCards {
    public static final String ID = "SutoppuWocchi";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 0;

    public SutoppuWocchi() {
        super("SutoppuWocchi", SutoppuWocchi.NAME,  0, SutoppuWocchi.DESCRIPTION, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF);
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        if (p.hasPower("PointPower")) {
            if (p.getPower("PointPower").amount >= 1) {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new WocchiPower(p,false)));
                AbstractDungeon.actionManager.addToTop(new ReducePowerAction(p,p,"PointPower",1));
            }
        }
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if(p.hasPower(MyWorldPower.POWER_ID)){
            AbstractDungeon.actionManager.addToTop(new PlayerTalkAction(AbstractDungeon.player, LunaDial.EXTENDED_DESCRIPTION[0]));
            return false;
        }
        return super.canUse(p, m);
    }

    public AbstractCard makeCopy() {
        return new SutoppuWocchi();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("SutoppuWocchi");
        NAME = SutoppuWocchi.cardStrings.NAME;
        DESCRIPTION = SutoppuWocchi.cardStrings.DESCRIPTION;
    }
}
