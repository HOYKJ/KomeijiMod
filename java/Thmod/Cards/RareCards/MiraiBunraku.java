package Thmod.Cards.RareCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FocusPower;

import Thmod.Cards.AbstractKomeijiCards;
import Thmod.Cards.UncommonCards.NarrowSpark;

public class MiraiBunraku extends AbstractKomeijiCards {
    public static final String ID = "MiraiBunraku";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 1;

    public MiraiBunraku() {
        super("MiraiBunraku", MiraiBunraku.NAME,  1, MiraiBunraku.DESCRIPTION, CardType.POWER, CardRarity.RARE, CardTarget.NONE, CardSet_k.ALICE);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new FocusPower(p, this.magicNumber), this.magicNumber));
    }

    public AbstractCard makeCopy() {
        return new MiraiBunraku();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("MiraiBunraku");
        NAME = MiraiBunraku.cardStrings.NAME;
        DESCRIPTION = MiraiBunraku.cardStrings.DESCRIPTION;
    }
}
