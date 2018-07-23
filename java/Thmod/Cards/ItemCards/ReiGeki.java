package Thmod.Cards.ItemCards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.Iterator;

public class ReiGeki extends AbstractItemCards {
    public static final String ID = "ReiGeki";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 0;
    private int exhaustnum;

    public ReiGeki() {
        super("ReiGeki", ReiGeki.NAME,  0, ReiGeki.DESCRIPTION, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF);
        this.exhaustnum = 0;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        if (p.hasPower("PointPower")) {
            if (p.getPower("PointPower").amount >= 1) {
                for (Iterator localIterator = AbstractDungeon.player.hand.group.iterator(); localIterator.hasNext(); ) {
                    AbstractCard c = (AbstractCard) localIterator.next();
                    if (c.type == CardType.CURSE || c.type == CardType.STATUS) {
                        AbstractDungeon.actionManager.addToTop(new ExhaustSpecificCardAction(c, AbstractDungeon.player.hand));
                        this.exhaustnum += 1;
                    }
                }
                AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, this.exhaustnum));
                AbstractDungeon.actionManager.addToTop(new ReducePowerAction(p,p,"PointPower",1));
            }
        }
    }

    public AbstractCard makeCopy() {
        return new ReiGeki();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("ReiGeki");
        NAME = ReiGeki.cardStrings.NAME;
        DESCRIPTION = ReiGeki.cardStrings.DESCRIPTION;
    }
}
