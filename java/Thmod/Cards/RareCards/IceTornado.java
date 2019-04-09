package Thmod.Cards.RareCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawPower;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;

import Thmod.Cards.AbstractKomeijiCards;
import Thmod.Power.IceTornadoPower;

public class IceTornado extends AbstractKomeijiCards {
    public static final String ID = "IceTornado";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 3;

    public IceTornado() {
        super("IceTornado", IceTornado.NAME,  3, IceTornado.DESCRIPTION, CardType.POWER, CardRarity.RARE, CardTarget.NONE, CardSet_k.CIRNO);
        this.baseMagicNumber = 10;
        this.magicNumber = this.baseMagicNumber;
        this.isEthereal = true;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new IceTornadoPower(p, this.magicNumber)));
        //AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new IntangiblePlayerPower(p, 2),2));
    }

    public AbstractCard makeCopy() {
        return new IceTornado();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.upgradeMagicNumber(4);
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("IceTornado");
        NAME = IceTornado.cardStrings.NAME;
        DESCRIPTION = IceTornado.cardStrings.DESCRIPTION;
    }
}
