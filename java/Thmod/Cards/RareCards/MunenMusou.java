package Thmod.Cards.RareCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
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
import Thmod.Cards.BlessingCards.AbstractBlessingCard;

public class MunenMusou extends AbstractKomeijiCards {
    public static final String ID = "MunenMusou";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION;
    private int exhaustnum;

    public MunenMusou() {
        super("MunenMusou", MunenMusou.NAME,  1, MunenMusou.DESCRIPTION, CardType.POWER, CardRarity.RARE, CardTarget.NONE, CardSet_k.TENSHI);
        this.exhaustnum = 0;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new RemoveDebuffsAction(p));
        for (AbstractCard c : p.hand.group) {
            if (((c.type == CardType.STATUS) || (c.type == CardType.CURSE)) && !(c instanceof AbstractBlessingCard)) {
                AbstractDungeon.actionManager.addToTop(new ExhaustSpecificCardAction(c, AbstractDungeon.player.hand));
                this.exhaustnum += 1;
            }
        }
        for (AbstractCard c : p.discardPile.group) {
            if (((c.type == CardType.STATUS) || (c.type == CardType.CURSE)) && !(c instanceof AbstractBlessingCard)) {
                AbstractDungeon.actionManager.addToTop(new ExhaustSpecificCardAction(c, AbstractDungeon.player.discardPile));
            }
        }
        for (AbstractCard c : p.drawPile.group) {
            if (((c.type == CardType.STATUS) || (c.type == CardType.CURSE)) && !(c instanceof AbstractBlessingCard)) {
                AbstractDungeon.actionManager.addToTop(new ExhaustSpecificCardAction(c, AbstractDungeon.player.drawPile));
            }
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
