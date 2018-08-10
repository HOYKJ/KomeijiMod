package Thmod.Cards.RewardCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import Thmod.Cards.AbstractKomeijiCards;

public class MiserableFate extends AbstractKomeijiCards {
    public static final String ID = "MiserableFate";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 2;

    public MiserableFate() {
        super("MiserableFate", MiserableFate.NAME,  2, MiserableFate.DESCRIPTION, CardType.SKILL, CardRarity.SPECIAL, CardTarget.ENEMY);
        this.baseMagicNumber = 6;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
        this.timesUpgraded = 1;
        this.upgraded = true;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m,p,new StrengthPower(m,-this.magicNumber),-this.magicNumber));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,new StrengthPower(p,this.magicNumber),this.magicNumber));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m,p,new VulnerablePower(p,99,false),99));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m,p,new FrailPower(p,99,false),99));
    }

    public boolean canUpgrade()
    {
        return false;
    }

    public AbstractCard makeCopy() {
        return new MiserableFate();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("MiserableFate");
        NAME = MiserableFate.cardStrings.NAME;
        DESCRIPTION = MiserableFate.cardStrings.DESCRIPTION;
    }
}
