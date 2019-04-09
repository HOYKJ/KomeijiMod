package Thmod.Cards.UncommonCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Cards.AbstractKomeijiCards;
import Thmod.Cards.Demotivation;
import Thmod.Power.HagoromoKuPower;

public class HagoromoKu extends AbstractKomeijiCards {
    public static final String ID = "HagoromoKu";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION ;
    private static final int COST = 1;

    public HagoromoKu() {
        super("HagoromoKu", HagoromoKu.NAME,  1, HagoromoKu.DESCRIPTION, CardType.POWER, CardRarity.UNCOMMON, CardTarget.NONE, CardSet_k.IKU);
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new HagoromoKuPower(p, this.magicNumber), this.magicNumber));
    }

    public AbstractCard makeCopy() {
        return new HagoromoKu();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.isInnate = true;
            this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("HagoromoKu");
        NAME = HagoromoKu.cardStrings.NAME;
        DESCRIPTION = HagoromoKu.cardStrings.DESCRIPTION;
        UPGRADE_DESCRIPTION = HagoromoKu.cardStrings.UPGRADE_DESCRIPTION;
    }
}
