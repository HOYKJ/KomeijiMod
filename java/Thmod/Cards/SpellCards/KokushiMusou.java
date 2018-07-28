package Thmod.Cards.SpellCards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import Thmod.Relics.SpellCardsRule;

public class KokushiMusou extends AbstractSpellCards {
    public static final String ID = "KokushiMusou";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 1;
    private int pointcost;

    public KokushiMusou() {
        super("KokushiMusou", KokushiMusou.NAME,  1, KokushiMusou.DESCRIPTION, CardType.SKILL, CardRarity.SPECIAL, CardTarget.NONE);
        this.pointcost = 3;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        if (p.hasPower("PointPower")) {
            if (p.getPower("PointPower").amount >= this.pointcost) {
                if(SpellCardsRule.Kokushinum < 4){
                    AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p,p,new DexterityPower(p,2),2));
                    AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p,p,new StrengthPower(p,2),2));
                    SpellCardsRule.Kokushinum += 1;
                }
                else if(SpellCardsRule.Kokushinum == 4){
                    AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p,p,new DexterityPower(p,-6),-6));
                    AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p,p,new StrengthPower(p,-6),-6));
                    SpellCardsRule.Kokushinum = 0;
                    for (int i = 0; i < AbstractDungeon.getCurrRoom().monsters.monsters.size(); ++i) {
                        AbstractMonster target = (AbstractMonster) AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
                        if ((!(target.isDying)) && (target.currentHealth > 0) && (!(target.isEscaping))) {
                            AbstractDungeon.actionManager.addToBottom(new DamageAction(target, new DamageInfo(p, 36, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                        }
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
        this.cantUseMessage = "我没有足够的P点";
        return false;
    }

    public AbstractCard makeCopy() {
        return new KokushiMusou();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("KokushiMusou");
        NAME = KokushiMusou.cardStrings.NAME;
        DESCRIPTION = KokushiMusou.cardStrings.DESCRIPTION;
    }
}
