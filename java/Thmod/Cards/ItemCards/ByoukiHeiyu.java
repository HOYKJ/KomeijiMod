package Thmod.Cards.ItemCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Power.HeiyuPower;
import Thmod.Power.PointPower;

public class ByoukiHeiyu extends AbstractItemCards {
    public static final String ID = "ByoukiHeiyu";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 0;

    public ByoukiHeiyu() {
        super("ByoukiHeiyu", ByoukiHeiyu.NAME,  0, ByoukiHeiyu.DESCRIPTION, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF);
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        if (p.hasPower("PointPower")) {
            if (p.getPower("PointPower").amount >= 1) {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new HeiyuPower(p)));
                AbstractDungeon.actionManager.addToTop(new ReducePowerAction(p,p,"PointPower",1));
            }
        }
    }

    public AbstractCard makeCopy() {
        return new ByoukiHeiyu();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("ByoukiHeiyu");
        NAME = ByoukiHeiyu.cardStrings.NAME;
        DESCRIPTION = ByoukiHeiyu.cardStrings.DESCRIPTION;
    }
}
