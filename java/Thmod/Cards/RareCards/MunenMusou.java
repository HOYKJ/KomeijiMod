package Thmod.Cards.RareCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.unique.RemoveDebuffsAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;

import java.util.Iterator;

import Thmod.Cards.AbstractKomeijiCards;

public class MunenMusou extends AbstractKomeijiCards {
    public static final String ID = "MunenMusou";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION;
    private static final int COST = 3;
    private int exhaustnum;

    public MunenMusou() {
        super("MunenMusou", MunenMusou.NAME,  3, MunenMusou.DESCRIPTION, CardType.POWER, CardRarity.RARE, CardTarget.NONE);
        this.exhaustnum = 0;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new RemoveDebuffsAction(p));
        for (Iterator localIterator = p.hand.group.iterator(); localIterator.hasNext(); ){
            AbstractCard c = (AbstractCard) localIterator.next();
            if ((c.type == CardType.STATUS) || (c.type == CardType.CURSE)){
                AbstractDungeon.actionManager.addToTop(new ExhaustSpecificCardAction(c, AbstractDungeon.player.hand));
                this.exhaustnum += 1;
            }
        }
        for (Iterator localIterator = p.discardPile.group.iterator(); localIterator.hasNext(); ){
            AbstractCard c = (AbstractCard) localIterator.next();
            if ((c.type == CardType.STATUS) || (c.type == CardType.CURSE))
                AbstractDungeon.actionManager.addToTop(new ExhaustSpecificCardAction(c, AbstractDungeon.player.discardPile));
        }
        for (Iterator localIterator = p.drawPile.group.iterator(); localIterator.hasNext(); ){
            AbstractCard c = (AbstractCard) localIterator.next();
            if ((c.type == CardType.STATUS) || (c.type == CardType.CURSE))
                AbstractDungeon.actionManager.addToTop(new ExhaustSpecificCardAction(c, AbstractDungeon.player.drawPile));
        }
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, this.exhaustnum));
        this.exhaustnum = 0;
        if(this.upgraded)
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,new ArtifactPower(p,3),3));
    }

    public AbstractCard makeCopy() {
        return new MunenMusou();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.rawDescription = UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("MunenMusou");
        NAME = MunenMusou.cardStrings.NAME;
        DESCRIPTION = MunenMusou.cardStrings.DESCRIPTION;
        UPGRADE_DESCRIPTION = MunenMusou.cardStrings.UPGRADE_DESCRIPTION;
    }
}
