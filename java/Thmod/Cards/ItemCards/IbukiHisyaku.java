package Thmod.Cards.ItemCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Power.PointPower;
import Thmod.Power.WocchiPower;

public class IbukiHisyaku extends AbstractItemCards {
    public static final String ID = "IbukiHisyaku";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 0;

    public IbukiHisyaku() {
        super("IbukiHisyaku", IbukiHisyaku.NAME,  0, IbukiHisyaku.DESCRIPTION, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF);
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        if (p.hasPower("PointPower")) {
            if (p.getPower("PointPower").amount >= 1) {
                AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, 2));
                AbstractDungeon.actionManager.addToTop(new ReducePowerAction(p,p,"PointPower",1));
            }
        }
    }

    public AbstractCard makeCopy() {
        return new IbukiHisyaku();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("IbukiHisyaku");
        NAME = IbukiHisyaku.cardStrings.NAME;
        DESCRIPTION = IbukiHisyaku.cardStrings.DESCRIPTION;
    }
}
