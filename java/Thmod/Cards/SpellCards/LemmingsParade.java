package Thmod.Cards.SpellCards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Actions.common.ChangeOrbAction;
import Thmod.Orbs.Helan;
import Thmod.Orbs.NingyouOrb;
import Thmod.Orbs.Penglai;
import Thmod.Orbs.Shanghai;
import Thmod.Orbs.TateNingyou;
import Thmod.Orbs.YariNingyou;
import Thmod.Orbs.YumiNingyou;

public class LemmingsParade extends AbstractSpellCards {
    public static final String ID = "LemmingsParade";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 3;
    private static final int ATTACK_DMG = 10;
    private int pointcost;

    public LemmingsParade() {
        super("LemmingsParade", LemmingsParade.NAME,  3, LemmingsParade.DESCRIPTION, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ALL_ENEMY);
        this.baseDamage = 10;
        this.isMultiDamage = true;
        this.pointcost = 5;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        if (p.hasPower("PointPower")) {
            if (p.getPower("PointPower").amount >= this.pointcost) {
                AbstractDungeon.actionManager.addToTop(new ReducePowerAction(p,p,"PointPower",this.pointcost));
                for(int i = 0;i < p.orbs.size();i++) {
                    if((p.orbs.get(i) instanceof NingyouOrb) || (p.orbs.get(i) instanceof Shanghai) || (p.orbs.get(i) instanceof Penglai) || (p.orbs.get(i) instanceof Helan))
                        AbstractDungeon.actionManager.addToBottom(new ChangeOrbAction(i,true));
                    if((p.orbs.get(i) instanceof YariNingyou) || (p.orbs.get(i) instanceof TateNingyou) || (p.orbs.get(i) instanceof YumiNingyou))
                        AbstractDungeon.actionManager.addToBottom(new ChangeOrbAction(i,false));
                    AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                }
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
        return new LemmingsParade();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("LemmingsParade");
        NAME = LemmingsParade.cardStrings.NAME;
        DESCRIPTION = LemmingsParade.cardStrings.DESCRIPTION;
    }
}
