package Thmod.Cards.SpellCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Cards.Sabishigari;
import Thmod.Power.JyouchiRei;
import Thmod.ThMod;

public class MirenKamai extends AbstractSpellCards {
    public static final String ID = "MirenKamai";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 1;
    private int pointcost;

    public MirenKamai() {
        super("MirenKamai", MirenKamai.NAME,  1, MirenKamai.DESCRIPTION, CardType.SKILL, CardRarity.SPECIAL, CardTarget.ALL_ENEMY);
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
        this.pointcost = 3;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        if (p.hasPower("PointPower")) {
            if (p.getPower("PointPower").amount >= this.pointcost) {
                for (int i = 0; i < this.magicNumber;i++){
                    AbstractMonster mo = AbstractDungeon.getMonsters().getRandomMonster(true);
                    AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(mo,p,new JyouchiRei(mo,1),1));
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
        return new MirenKamai();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("MirenKamai");
        NAME = MirenKamai.cardStrings.NAME;
        DESCRIPTION = MirenKamai.cardStrings.DESCRIPTION;
    }
}
