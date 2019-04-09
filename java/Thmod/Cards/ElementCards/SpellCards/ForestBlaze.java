package Thmod.Cards.ElementCards.SpellCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.Iterator;

import Thmod.Power.ForestBlazePower;

public class ForestBlaze extends AbstractElementSpellCards {
    public static final String ID = "ForestBlaze";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;

    public ForestBlaze() {
        super("ForestBlaze", ForestBlaze.NAME,  2, ForestBlaze.DESCRIPTION, CardType.POWER, CardRarity.SPECIAL, CardTarget.NONE,ElementType.Wood,ElementType.Fire);
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        super.use(p,m);
        boolean powerExists = false;
        for (Iterator localIterator = p.powers.iterator(); localIterator.hasNext(); ) {
            AbstractPower pow = (AbstractPower)localIterator.next();
            if (pow.ID.equals("ForestBlazePower")) {
                powerExists = true;
                break;
            }
        }

        if (!(powerExists))
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,new ForestBlazePower(p)));
    }

    public AbstractCard makeCopy() {
        return new ForestBlaze();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("ForestBlaze");
        NAME = ForestBlaze.cardStrings.NAME;
        DESCRIPTION = ForestBlaze.cardStrings.DESCRIPTION;
    }
}
