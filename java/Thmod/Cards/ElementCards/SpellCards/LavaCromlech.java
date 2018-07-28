package Thmod.Cards.ElementCards.SpellCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Power.CromlechPower;

public class LavaCromlech extends AbstractElementSpellCards {
    public static final String ID = "LavaCromlech";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 2;

    public LavaCromlech() {
        super("LavaCromlech", LavaCromlech.NAME,  2, LavaCromlech.DESCRIPTION, CardType.POWER, CardRarity.SPECIAL, CardTarget.NONE,ElementType.Earth,ElementType.Fire);
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        super.use(p,m);
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,new CromlechPower(p,this.magicNumber),this.magicNumber));
    }

    public AbstractCard makeCopy() {
        return new LavaCromlech();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("LavaCromlech");
        NAME = LavaCromlech.cardStrings.NAME;
        DESCRIPTION = LavaCromlech.cardStrings.DESCRIPTION;
    }
}
