package Thmod.Cards.SpellCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ConservePower;
import com.megacrit.cardcrawl.powers.EquilibriumPower;

import Thmod.Actions.unique.PlayerTalkAction;
import Thmod.Cards.RareCards.LunaDial;
import Thmod.Power.MyWorldPower;
import Thmod.Power.WocchiPower;

public class SakuyaNoSekai extends AbstractSpellCards {
    public static final String ID = "SakuyaNoSekai";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private int pointcost;

    public SakuyaNoSekai() {
        super("SakuyaNoSekai", SakuyaNoSekai.NAME,  1, SakuyaNoSekai.DESCRIPTION, CardType.SKILL, CardRarity.SPECIAL, CardTarget.ALL_ENEMY);
        this.pointcost = 5;
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        if (p.hasPower("PointPower")) {
            if (p.getPower("PointPower").amount >= this.pointcost) {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new WocchiPower(p,true)));
                AbstractDungeon.actionManager.addToTop(new ReducePowerAction(p,p,"PointPower",this.pointcost));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new EquilibriumPower(p, this.magicNumber), this.magicNumber));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new ConservePower(p, this.magicNumber), this.magicNumber));
            }
        }
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m){
        super.canUse(p,m);
        if (p.hasPower("PointPower")) {
            if (p.getPower("PointPower").amount >= this.pointcost) {
                if(p.hasPower(MyWorldPower.POWER_ID)){
                    AbstractDungeon.actionManager.addToTop(new PlayerTalkAction(AbstractDungeon.player, LunaDial.EXTENDED_DESCRIPTION[0]));
                    return false;
                }
                return true;
            }
        }
        this.cantUseMessage = AbstractSpellCards.EXTENDED_DESCRIPTION[4];
        return false;
    }

    public AbstractCard makeCopy() {
        return new SakuyaNoSekai();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("SakuyaNoSekai");
        NAME = SakuyaNoSekai.cardStrings.NAME;
        DESCRIPTION = SakuyaNoSekai.cardStrings.DESCRIPTION;
    }
}
