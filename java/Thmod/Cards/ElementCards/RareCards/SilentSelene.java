package Thmod.Cards.ElementCards.RareCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;

import Thmod.Cards.ElementCards.AbstractElementCards;
import Thmod.Power.SelenePower;

public class SilentSelene extends AbstractElementCards {
    public static final String ID = "SilentSelene";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 3;

    public SilentSelene() {
        super("SilentSelene", SilentSelene.NAME,  3, SilentSelene.DESCRIPTION, CardType.SKILL, CardRarity.RARE, CardTarget.SELF,ElementType.Luna);
        this.baseMagicNumber = 15;
        this.magicNumber = baseMagicNumber;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        super.use(p,m);
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,new IntangiblePlayerPower(p,1),1));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,new SelenePower(p,this.magicNumber),this.magicNumber));
    }

    public AbstractCard makeCopy() {
        return new SilentSelene();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.upgradeMagicNumber(7);
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("SilentSelene");
        NAME = SilentSelene.cardStrings.NAME;
        DESCRIPTION = SilentSelene.cardStrings.DESCRIPTION;
    }
}
