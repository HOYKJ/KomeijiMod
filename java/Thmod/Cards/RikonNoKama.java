package Thmod.Cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.FlyingOrbEffect;

import java.util.ArrayList;

import Thmod.Power.JyouchiRei;
import Thmod.Power.SoulPower;

public class RikonNoKama extends AbstractSweepCards {
    public static final String ID = "RikonNoKama";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private int extraDamage;

    public RikonNoKama() {
        super("RikonNoKama", RikonNoKama.NAME,  1, RikonNoKama.DESCRIPTION, CardType.ATTACK, CardRarity.COMMON, CardTarget.SELF_AND_ENEMY, CardSet_k.KOMACHI);
        this.baseDamage = 8;
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.extraDamage = 0;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToTop(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        AbstractDungeon.actionManager.addToBottom(new VFXAction(new FlyingOrbEffect(m.hb.cX, m.hb.cY)));
        AbstractDungeon.actionManager.addToBottom(new VFXAction(new FlyingOrbEffect(m.hb.cX, m.hb.cY)));
        AbstractDungeon.actionManager.addToBottom(new VFXAction(new FlyingOrbEffect(m.hb.cX, m.hb.cY)));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new SoulPower(p,3,this.magicNumber),3));
        if(m.hasPower(JyouchiRei.POWER_ID)){
            for(AbstractPower power : m.powers){
                if(power instanceof JyouchiRei){
                    //this.extraDamage = (int) Math.pow(2, (double) power.amount / 2) / 2;
                    this.extraDamage = power.amount * 2;
                    AbstractDungeon.actionManager.addToTop(new DamageAction(m, new DamageInfo(p, this.extraDamage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
                }
            }
        }
    }

    @Override
    public ArrayList<AbstractSweepCards> getOpposite() {
        final ArrayList<AbstractSweepCards> opposite = new ArrayList<>();
        opposite.add(new Sabishigari());
        return opposite;
    }

    public AbstractCard makeCopy() {
        return new RikonNoKama();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("RikonNoKama");
        NAME = RikonNoKama.cardStrings.NAME;
        DESCRIPTION = RikonNoKama.cardStrings.DESCRIPTION;
    }
}
