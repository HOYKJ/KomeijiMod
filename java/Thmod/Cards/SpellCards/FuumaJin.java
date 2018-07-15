package Thmod.Cards.SpellCards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

import Thmod.Cards.JyouchiJin;
import Thmod.Cards.KeiseiJin;
import Thmod.Cards.KinbakuJin;
import Thmod.ThMod;

public class FuumaJin extends AbstractSpellCards {
    public static final String ID = "FuumaJin";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 1;
    private static final int ATTACK_DMG = 5;
    private int pointcost;

    public FuumaJin() {
        super("FuumaJin", FuumaJin.NAME,  1, FuumaJin.DESCRIPTION, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ALL_ENEMY);
        this.baseDamage = 5;
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
        this.isMultiDamage = true;
        this.pointcost = 2;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        if (p.hasPower("PointPower")) {
            if (p.getPower("PointPower").amount >= this.pointcost) {
                AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                for (int i = 0; i < AbstractDungeon.getCurrRoom().monsters.monsters.size(); ++i) {
                    AbstractMonster target = (AbstractMonster) AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
                    if ((!(target.isDying)) && (target.currentHealth > 0) && (!(target.isEscaping)))
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target, p, new WeakPower(target, this.magicNumber,false), this.magicNumber));
                }
                AbstractDungeon.actionManager.addToTop(new ReducePowerAction(p,p,"PointPower",this.pointcost));
            }
        }
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m){
        if (p.hasPower("PointPower")) {
            if (p.getPower("PointPower").amount >= this.pointcost) {
                return true;
            }
        }
        this.cantUseMessage = "我没有足够的P点";
        return false;
    }

    public AbstractCard makeCopy() {
        return new FuumaJin();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("FuumaJin");
        NAME = FuumaJin.cardStrings.NAME;
        DESCRIPTION = FuumaJin.cardStrings.DESCRIPTION;
    }
}
