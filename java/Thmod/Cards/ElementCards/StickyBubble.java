package Thmod.Cards.ElementCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

import Thmod.Power.BubblePower;

public class StickyBubble extends AbstractElementSweepCards {
    public static final String ID = "StickyBubble";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 1;

    public StickyBubble() {
        super("StickyBubble", StickyBubble.NAME,  1, StickyBubble.DESCRIPTION, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY,ElementType.Water);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        super.use(p,m);
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new BubblePower(m, this.magicNumber), this.magicNumber));
    }

    public void triggerOnExhaust(){
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(this, 1));
    }

    @Override
    public ArrayList<AbstractElementSweepCards> getOpposite() {
        final ArrayList<AbstractElementSweepCards> opposite = new ArrayList<>();
        opposite.add(new WinterElement());
        opposite.add(new CondensedBubble());
        return opposite;
    }

    public AbstractCard makeCopy() {
        return new StickyBubble();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("StickyBubble");
        NAME = StickyBubble.cardStrings.NAME;
        DESCRIPTION = StickyBubble.cardStrings.DESCRIPTION;
    }
}
