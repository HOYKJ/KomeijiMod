package Thmod.Cards.SpellCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

import Thmod.Orbs.Helan;
import Thmod.Orbs.YumiNingyou;

public class DollsWar extends AbstractSpellCards {
    public static final String ID = "DollsWar";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 1;
    private int pointcost;

    public DollsWar() {
        super("DollsWar", DollsWar.NAME,  1, DollsWar.DESCRIPTION, CardType.POWER, CardRarity.SPECIAL, CardTarget.NONE);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.pointcost = 4;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        if (p.hasPower("PointPower")) {
            if (p.getPower("PointPower").amount >= this.pointcost) {
                int YumiNum = 0;
                AbstractDungeon.actionManager.addToTop(new ReducePowerAction(p,p,"PointPower",this.pointcost));
                for (int i = 0; i < p.orbs.size(); i++) {
                    if ((p.orbs.get(i) instanceof YumiNingyou) || (p.orbs.get(i) instanceof Helan))
                        YumiNum += 1;
                }
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,new StrengthPower(p,(this.magicNumber * YumiNum)),(this.magicNumber * YumiNum)));
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
        return new DollsWar();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("DollsWar");
        NAME = DollsWar.cardStrings.NAME;
        DESCRIPTION = DollsWar.cardStrings.DESCRIPTION;
    }
}
