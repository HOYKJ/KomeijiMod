package Thmod.Cards.SpellCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Power.RoundTable;

public class DollofRoundTable extends AbstractSpellCards {
    public static final String ID = "DollofRoundTable";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 1;
    private int pointcost;

    public DollofRoundTable() {
        super("DollofRoundTable", DollofRoundTable.NAME,  1, DollofRoundTable.DESCRIPTION, CardType.SKILL, CardRarity.SPECIAL, CardTarget.ALL_ENEMY);
        this.baseBlock = 2;
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.pointcost = 3;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        if (p.hasPower("PointPower")) {
            if (p.getPower("PointPower").amount >= this.pointcost) {
                AbstractDungeon.actionManager.addToTop(new ReducePowerAction(p,p,"PointPower",this.pointcost));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,new RoundTable(p,this.magicNumber),this.magicNumber));
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
        return new DollofRoundTable();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("DollofRoundTable");
        NAME = DollofRoundTable.cardStrings.NAME;
        DESCRIPTION = DollofRoundTable.cardStrings.DESCRIPTION;
    }
}
