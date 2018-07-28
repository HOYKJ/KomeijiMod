package Thmod.Cards.ElementCards.SpellCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Power.PhotosynthesisPower;

public class Photosynthesis extends AbstractElementSpellCards {
    public static final String ID = "Photosynthesis";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 2;

    public Photosynthesis() {
        super("Photosynthesis", Photosynthesis.NAME,  2, Photosynthesis.DESCRIPTION, CardType.POWER, CardRarity.SPECIAL, CardTarget.NONE,ElementType.Sun,ElementType.Wood);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        super.use(p,m);
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,new PhotosynthesisPower(p,this.magicNumber),this.magicNumber));
    }

    public AbstractCard makeCopy() {
        return new Photosynthesis();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("Photosynthesis");
        NAME = Photosynthesis.cardStrings.NAME;
        DESCRIPTION = Photosynthesis.cardStrings.DESCRIPTION;
    }
}
