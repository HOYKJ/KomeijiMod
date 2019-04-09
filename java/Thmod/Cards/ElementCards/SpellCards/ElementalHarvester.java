package Thmod.Cards.ElementCards.SpellCards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ElementalHarvester extends AbstractElementSpellCards {
    public static final String ID = "ElementalHarvester";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;

    public ElementalHarvester() {
        super("ElementalHarvester", ElementalHarvester.NAME,  2, ElementalHarvester.DESCRIPTION, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF,ElementType.Metal,ElementType.Wood);
        this.baseBlock = 15;
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        super.use(p,m);
        AbstractDungeon.actionManager.addToTop(new GainBlockAction(p, p, this.block));
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, this.magicNumber));
    }

    public AbstractCard makeCopy() {
        return new ElementalHarvester();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("ElementalHarvester");
        NAME = ElementalHarvester.cardStrings.NAME;
        DESCRIPTION = ElementalHarvester.cardStrings.DESCRIPTION;
    }
}
