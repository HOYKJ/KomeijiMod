package Thmod.Cards.SpellCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Power.RokkonShyoujyouDefend;

public class RokkonShyoujyou extends AbstractSpellCards {
    public static final String ID = "RokkonShyoujyou";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 2;
    private static final int ATTACK_DMG = 18;
    private int pointcost;

    public RokkonShyoujyou() {
        super("RokkonShyoujyou", RokkonShyoujyou.NAME,  2, RokkonShyoujyou.DESCRIPTION, CardType.SKILL, CardRarity.SPECIAL, CardTarget.ALL_ENEMY);
        this.baseDamage = 18;
        this.pointcost = 5;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        if (p.hasPower("PointPower")) {
            if (p.getPower("PointPower").amount >= this.pointcost) {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,new RokkonShyoujyouDefend(p,this.damage)));
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
        return new RokkonShyoujyou();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("RokkonShyoujyou");
        NAME = RokkonShyoujyou.cardStrings.NAME;
        DESCRIPTION = RokkonShyoujyou.cardStrings.DESCRIPTION;
    }
}
