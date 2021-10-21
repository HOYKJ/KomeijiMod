package Thmod.Cards.SpellCards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.FlyingOrbEffect;

import Thmod.Cards.RikonNoKama;
import Thmod.Power.JyouchiRei;
import Thmod.Power.SoulPower;
import Thmod.ThMod;

public class SeikonRyuuri extends AbstractSpellCards {
    public static final String ID = "SeikonRyuuri";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 1;
    private static final int ATTACK_DMG = 12;
    private int pointcost;
    private int extraDamage;

    public SeikonRyuuri() {
        super("SeikonRyuuri", SeikonRyuuri.NAME,  1, SeikonRyuuri.DESCRIPTION, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY);
        this.baseDamage = 12;
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
        this.pointcost = 4;
        this.extraDamage = 0;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        if (p.hasPower("PointPower")) {
            if (p.getPower("PointPower").amount >= this.pointcost) {
                AbstractDungeon.actionManager.addToTop(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                AbstractDungeon.actionManager.addToBottom(new VFXAction(new FlyingOrbEffect(m.hb.cX, m.hb.cY)));
                AbstractDungeon.actionManager.addToBottom(new VFXAction(new FlyingOrbEffect(m.hb.cX, m.hb.cY)));
                AbstractDungeon.actionManager.addToBottom(new VFXAction(new FlyingOrbEffect(m.hb.cX, m.hb.cY)));
                AbstractDungeon.actionManager.addToBottom(new VFXAction(new FlyingOrbEffect(m.hb.cX, m.hb.cY)));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new SoulPower(p, 4, this.magicNumber), 4));
                AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(p, p, "PointPower", this.pointcost));
                if(m.hasPower(JyouchiRei.POWER_ID)){
                    for(AbstractPower power : m.powers){
                        if(power instanceof JyouchiRei){
                            this.extraDamage = (int) Math.pow(2, (double) power.amount) / 2;
                            AbstractDungeon.actionManager.addToTop(new DamageAction(m, new DamageInfo(p, this.extraDamage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
                            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(m, m, power));
                        }
                    }
                }
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
        return new SeikonRyuuri();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("SeikonRyuuri");
        NAME = SeikonRyuuri.cardStrings.NAME;
        DESCRIPTION = SeikonRyuuri.cardStrings.DESCRIPTION;
    }
}
