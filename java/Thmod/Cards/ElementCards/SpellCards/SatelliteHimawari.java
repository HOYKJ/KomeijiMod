package Thmod.Cards.ElementCards.SpellCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Power.HimawariPower;

public class SatelliteHimawari extends AbstractElementSpellCards {
    public static final String ID = "SatelliteHimawari";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 3;

    public SatelliteHimawari() {
        super("SatelliteHimawari", SatelliteHimawari.NAME,  3, SatelliteHimawari.DESCRIPTION, CardType.POWER, CardRarity.SPECIAL, CardTarget.NONE,ElementType.Luna,ElementType.Wood);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        super.use(p,m);
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,new HimawariPower(p,this.magicNumber),this.magicNumber));
    }

    public AbstractCard makeCopy() {
        return new SatelliteHimawari();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("SatelliteHimawari");
        NAME = SatelliteHimawari.cardStrings.NAME;
        DESCRIPTION = SatelliteHimawari.cardStrings.DESCRIPTION;
    }
}
