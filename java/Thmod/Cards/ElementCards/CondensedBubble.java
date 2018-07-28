package Thmod.Cards.ElementCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;

import java.util.ArrayList;

public class CondensedBubble extends AbstractElementSweepCards {
    public static final String ID = "CondensedBubble";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 1;

    public CondensedBubble() {
        super("CondensedBubble", CondensedBubble.NAME,  1, CondensedBubble.DESCRIPTION, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE,ElementType.Water);
        this.baseMagicNumber = 4;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        super.use(p,m);
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p, p, new PlatedArmorPower(AbstractDungeon.player, this.magicNumber), this.magicNumber));
    }

    @Override
    public ArrayList<AbstractElementSweepCards> getOpposite() {
        final ArrayList<AbstractElementSweepCards> opposite = new ArrayList<>();
        opposite.add(new WinterElement());
        opposite.add(new StickyBubble());
        return opposite;
    }

    public AbstractCard makeCopy() {
        return new CondensedBubble();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("CondensedBubble");
        NAME = CondensedBubble.cardStrings.NAME;
        DESCRIPTION = CondensedBubble.cardStrings.DESCRIPTION;
    }
}
