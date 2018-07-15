package Thmod.Cards.SpellCards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Actions.common.DiscardAndDamageAction;
import Thmod.Cards.HagoromoMizu;
import Thmod.Cards.JyouchiJin;
import Thmod.Cards.KeiseiJin;
import Thmod.Cards.KinbakuJin;
import Thmod.Cards.UncommonCards.NarrowSpark;
import Thmod.ThMod;

public class HagoromoToki extends AbstractSpellCards {
    public static final String ID = "HagoromoToki";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 1;
    private static final int ATTACK_DMG = 5;
    private static final int BLOCK_AMT = 5;
    private int pointcost;
    public static boolean discarded;

    public HagoromoToki() {
        super("HagoromoToki", HagoromoToki.NAME,  1, HagoromoToki.DESCRIPTION, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF_AND_ENEMY);
        this.baseDamage = 8;
        this.baseBlock = 5;
        this.pointcost = 1;
        discarded = false;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        if (p.hasPower("PointPower")) {
            if (p.getPower("PointPower").amount >= this.pointcost) {
                AbstractDungeon.actionManager.addToTop(new GainBlockAction(p, p, this.block));
                AbstractDungeon.actionManager.addToTop(new DiscardAndDamageAction(p));
                if (discarded)
                    AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
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
        return new HagoromoToki();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("HagoromoToki");
        NAME = HagoromoToki.cardStrings.NAME;
        DESCRIPTION = HagoromoToki.cardStrings.DESCRIPTION;
    }
}
