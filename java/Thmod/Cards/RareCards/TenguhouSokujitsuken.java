package Thmod.Cards.RareCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawPower;

import Thmod.Cards.AbstractKomeijiCards;

public class TenguhouSokujitsuken extends AbstractKomeijiCards {
    public static final String ID = "TenguhouSokujitsuken";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 2;

    public TenguhouSokujitsuken() {
        super("TenguhouSokujitsuken", TenguhouSokujitsuken.NAME,  2, TenguhouSokujitsuken.DESCRIPTION, CardType.POWER, CardRarity.RARE, CardTarget.NONE);
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DrawPower(p, this.magicNumber), this.magicNumber));
    }

    public AbstractCard makeCopy() {
        return new TenguhouSokujitsuken();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.upgradeBaseCost(1);
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("TenguhouSokujitsuken");
        NAME = TenguhouSokujitsuken.cardStrings.NAME;
        DESCRIPTION = TenguhouSokujitsuken.cardStrings.DESCRIPTION;
    }
}
