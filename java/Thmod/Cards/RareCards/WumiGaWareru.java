package Thmod.Cards.RareCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Cards.AbstractKomeijiCards;
import Thmod.Power.WumiGaWareruPower;

public class WumiGaWareru extends AbstractKomeijiCards {
    public static final String ID = "WumiGaWareru";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 2;

    public WumiGaWareru() {
        super("WumiGaWareru", WumiGaWareru.NAME,  2, WumiGaWareru.DESCRIPTION, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY);
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
        this.baseDamage = 8;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new WumiGaWareruPower(p, this.magicNumber,this.damage), this.magicNumber));
    }

    public AbstractCard makeCopy() {
        return new WumiGaWareru();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.upgradeDamage(3);
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("WumiGaWareru");
        NAME = WumiGaWareru.cardStrings.NAME;
        DESCRIPTION = WumiGaWareru.cardStrings.DESCRIPTION;
    }
}
