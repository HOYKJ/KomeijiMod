package Thmod.Cards.ElementCards.SpellCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import Thmod.Power.ReflectorPower;

public class SunshineReflector extends AbstractElementSpellCards {
    public static final String ID = "SunshineReflector";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 2;

    public SunshineReflector() {
        super("SunshineReflector", SunshineReflector.NAME,  2, SunshineReflector.DESCRIPTION, CardType.POWER, CardRarity.SPECIAL, CardTarget.SELF,ElementType.Metal,ElementType.Luna);
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        super.use(p,m);
        boolean powerExists = false;
        for (AbstractPower pow : p.powers) {
            if (pow.ID.equals("ReflectorPower"))
            {
                powerExists = true;
                break;
            }
        }
        if (!powerExists) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new ReflectorPower(p)));
        }
    }

    public AbstractCard makeCopy() {
        return new SunshineReflector();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("SunshineReflector");
        NAME = SunshineReflector.cardStrings.NAME;
        DESCRIPTION = SunshineReflector.cardStrings.DESCRIPTION;
    }
}
