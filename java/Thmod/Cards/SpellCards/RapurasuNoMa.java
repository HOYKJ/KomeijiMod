package Thmod.Cards.SpellCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Power.HenyouMirumePower;

public class RapurasuNoMa extends AbstractSpellCards {
    public static final String ID = "RapurasuNoMa";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 1;
    private int pointcost;

    public RapurasuNoMa() {
        super("RapurasuNoMa", RapurasuNoMa.NAME,  1, RapurasuNoMa.DESCRIPTION, CardType.SKILL, CardRarity.SPECIAL, CardTarget.ALL_ENEMY);
        this.pointcost = 4;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        if (p.hasPower("PointPower")) {
            if (p.getPower("PointPower").amount >= this.pointcost) {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,new HenyouMirumePower(p,0)));
                AbstractDungeon.actionManager.addToTop(new ReducePowerAction(p,p,"PointPower",this.pointcost));
            }
        }
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m){
        super.canUse(p,m);
        if (p.hasPower("PointPower")) {
            if (p.getPower("PointPower").amount >= this.pointcost) {
                return true;
            }
        }
        this.cantUseMessage = AbstractSpellCards.EXTENDED_DESCRIPTION[4];
        return false;
    }

    public AbstractCard makeCopy() {
        return new RapurasuNoMa();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("RapurasuNoMa");
        NAME = RapurasuNoMa.cardStrings.NAME;
        DESCRIPTION = RapurasuNoMa.cardStrings.DESCRIPTION;
    }
}
