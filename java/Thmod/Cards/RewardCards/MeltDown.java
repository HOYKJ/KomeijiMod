package Thmod.Cards.RewardCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

import Thmod.Cards.AbstractKomeijiCards;

public class MeltDown extends AbstractKomeijiCards {
    public static final String ID = "MeltDown";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;

    public MeltDown() {
        super("MeltDown", MeltDown.NAME,  3, MeltDown.DESCRIPTION, CardType.POWER, CardRarity.SPECIAL, CardTarget.SELF, CardSet_k.OTHER);
        this.upgraded = true;
        this.baseMagicNumber = 15;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        for(AbstractCard card : p.hand.group){
            if(!card.exhaust) {
                card.exhaust = true;
                card.rawDescription = card.rawDescription + EXTENDED_DESCRIPTION[0];
                card.initializeDescription();
            }
        }
        for(AbstractCard card : p.drawPile.group){
            if(!card.exhaust) {
                card.exhaust = true;
                card.rawDescription = card.rawDescription + EXTENDED_DESCRIPTION[0];
                card.initializeDescription();
            }
        }
        for(AbstractCard card : p.discardPile.group){
            if(!card.exhaust) {
                card.exhaust = true;
                card.rawDescription = card.rawDescription + EXTENDED_DESCRIPTION[0];
                card.initializeDescription();
            }
        }

        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, this.magicNumber), this.magicNumber));
    }

    public boolean canUpgrade()
    {
        return false;
    }

    public AbstractCard makeCopy() {
        return new MeltDown();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("MeltDown");
        NAME = MeltDown.cardStrings.NAME;
        DESCRIPTION = MeltDown.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = MeltDown.cardStrings.EXTENDED_DESCRIPTION;
    }
}
