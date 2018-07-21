package Thmod.Cards.UncommonCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;

import Thmod.Cards.AbstractKomeijiCards;

public class SuitokuNoKyoryu extends AbstractKomeijiCards {
    public static final String ID = "SuitokuNoKyoryu";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 1;
    private static final int DEX_AMOUNT = 2;

    public SuitokuNoKyoryu() {
        super("SuitokuNoKyoryu", SuitokuNoKyoryu.NAME,  1, SuitokuNoKyoryu.DESCRIPTION, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DexterityPower(p, this.magicNumber), this.magicNumber));
    }

    public AbstractCard makeCopy() {
        return new SuitokuNoKyoryu();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            upgradeMagicNumber(1);
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("SuitokuNoKyoryu");
        NAME = SuitokuNoKyoryu.cardStrings.NAME;
        DESCRIPTION = SuitokuNoKyoryu.cardStrings.DESCRIPTION;
    }
}
