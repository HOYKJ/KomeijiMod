package Thmod.Cards.RareCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;

import Thmod.Actions.unique.TurnEndAction;
import Thmod.Cards.AbstractKomeijiCards;
import Thmod.Power.HibernatePower;

public class EverywhereHibernate extends AbstractKomeijiCards {
    public static final String ID = "EverywhereHibernate";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 3;

    public EverywhereHibernate() {
        super("EverywhereHibernate", EverywhereHibernate.NAME,  3, EverywhereHibernate.DESCRIPTION, CardType.POWER, CardRarity.RARE, CardTarget.NONE);
        this.baseMagicNumber = 6;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new IntangiblePlayerPower(p, 3), 3));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new HibernatePower(p, this.magicNumber)));
        AbstractDungeon.actionManager.addToBottom(new TurnEndAction());
    }

    public AbstractCard makeCopy() {
        return new EverywhereHibernate();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.upgradeMagicNumber(2);
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("EverywhereHibernate");
        NAME = EverywhereHibernate.cardStrings.NAME;
        DESCRIPTION = EverywhereHibernate.cardStrings.DESCRIPTION;
    }
}
