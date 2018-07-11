package Thmod.Cards.SpellCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.unique.RemoveDebuffsAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

import Thmod.Power.StrengthDownPower;

public class MissingPurplePower extends AbstractSpellCards {
    public static final String ID = "MissingPurplePower";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 1;
    private int pointcost;

    public MissingPurplePower() {
        super("MissingPurplePower", MissingPurplePower.NAME,  1, MissingPurplePower.DESCRIPTION, CardType.POWER, CardRarity.SPECIAL, CardTarget.NONE);
        this.pointcost = 4;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        if (p.hasPower("PointPower")) {
            if (p.getPower("PointPower").amount >= this.pointcost) {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, 10), 10));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthDownPower(p, 10)));
                AbstractDungeon.actionManager.addToBottom(new RemoveDebuffsAction(p));
                AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(p,p,"PointPower",this.pointcost));
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
        return new MissingPurplePower();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("MissingPurplePower");
        NAME = MissingPurplePower.cardStrings.NAME;
        DESCRIPTION = MissingPurplePower.cardStrings.DESCRIPTION;
    }
}
