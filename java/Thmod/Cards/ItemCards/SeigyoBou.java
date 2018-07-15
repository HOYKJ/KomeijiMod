package Thmod.Cards.ItemCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import Thmod.Power.PointPower;

public class SeigyoBou extends AbstractItemCards {
    public static final String ID = "SeigyoBou";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 0;

    public SeigyoBou() {
        super("SeigyoBou", SeigyoBou.NAME,  0, SeigyoBou.DESCRIPTION, CardType.POWER, CardRarity.SPECIAL, CardTarget.SELF);
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        if (p.hasPower("PointPower")) {
            if (p.getPower("PointPower").amount >= 1) {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, 3), 3));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DexterityPower(p, -1), -1));
                AbstractDungeon.actionManager.addToTop(new ReducePowerAction(p,p,"PointPower",1));
            }
        }
    }

    public AbstractCard makeCopy() {
        return new SeigyoBou();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("SeigyoBou");
        NAME = SeigyoBou.cardStrings.NAME;
        DESCRIPTION = SeigyoBou.cardStrings.DESCRIPTION;
    }
}
