package Thmod.Cards.SpellCards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.combat.ShockWaveEffect;

import Thmod.Power.KyoufuSaiminPower;
import Thmod.Power.RoseHellPower;

public class RoseHell extends AbstractSpellCards {
    public static final String ID = "RoseHell";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 3;
    private int pointcost;

    public RoseHell() {
        super("RoseHell", RoseHell.NAME,  3, RoseHell.DESCRIPTION, CardType.SKILL, CardRarity.SPECIAL, CardTarget.ALL_ENEMY);
        this.baseMagicNumber = 5;
        this.magicNumber = this.baseMagicNumber;
        this.pointcost = 5;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        if (p.hasPower("PointPower")) {
            if (p.getPower("PointPower").amount >= this.pointcost) {
                AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new ShockWaveEffect(p.hb.cX, p.hb.cY, Color.PURPLE.cpy(), ShockWaveEffect.ShockWaveType.CHAOTIC), 0.5F));

                for (int i = 0; i < AbstractDungeon.getCurrRoom().monsters.monsters.size(); i++) {
                    AbstractMonster target = AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
                    if ((!(target.isDying)) && (target.currentHealth > 0) && (!(target.isEscaping))) {
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target, p, new WeakPower(target, this.magicNumber,false), this.magicNumber));
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target, p, new VulnerablePower(target, this.magicNumber,false), this.magicNumber));
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target, p, new FrailPower(target, this.magicNumber,false), this.magicNumber));
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target, p, new StrengthPower(target, -this.magicNumber), -this.magicNumber));
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target, p, new DexterityPower(target, -this.magicNumber), -this.magicNumber));
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target, p, new RoseHellPower(target)));
                    }
                }
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
        return new RoseHell();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("RoseHell");
        NAME = RoseHell.cardStrings.NAME;
        DESCRIPTION = RoseHell.cardStrings.DESCRIPTION;
    }
}
