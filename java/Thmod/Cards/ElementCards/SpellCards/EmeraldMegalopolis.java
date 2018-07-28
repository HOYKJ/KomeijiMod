package Thmod.Cards.ElementCards.SpellCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.BarricadePower;

import java.util.Iterator;

public class EmeraldMegalopolis extends AbstractElementSpellCards {
    public static final String ID = "EmeraldMegalopolis";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 2;

    public EmeraldMegalopolis() {
        super("EmeraldMegalopolis", EmeraldMegalopolis.NAME,  2, EmeraldMegalopolis.DESCRIPTION, CardType.POWER, CardRarity.SPECIAL, CardTarget.SELF,ElementType.Metal,ElementType.Earth);
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        super.use(p,m);
        boolean powerExists = false;
        for (Iterator localIterator = p.powers.iterator(); localIterator.hasNext(); ) {
            AbstractPower pow = (AbstractPower)localIterator.next();
            if (pow.ID.equals("Barricade")) {
                powerExists = true;
                break;
            }
        }

        if (!(powerExists))
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new BarricadePower(p)));
    }

    public AbstractCard makeCopy() {
        return new EmeraldMegalopolis();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("EmeraldMegalopolis");
        NAME = EmeraldMegalopolis.cardStrings.NAME;
        DESCRIPTION = EmeraldMegalopolis.cardStrings.DESCRIPTION;
    }
}
