package Thmod.Cards.ColorlessCards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Actions.common.DrawSpeCardAction;

public class Muse extends AbstractColorlessCards {
    public static final String ID = "Muse";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 0;

    public Muse() {
        super("Muse", Muse.NAME,  0, Muse.DESCRIPTION, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        this.baseBlock = 0;
        this.exhaust = true;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToTop(new GainBlockAction(p, p, this.block));
        for(AbstractCard c:p.drawPile.group){
            if(c.cardID.equals(this.cardID)) {
                AbstractDungeon.actionManager.addToBottom(new DrawSpeCardAction(c));
                break;
            }
        }
    }

    public void applyPowers()
    {
        AbstractPlayer p = AbstractDungeon.player;
        if(this.upgraded)
            this.baseBlock = 3;
        else
            this.baseBlock = 0;

        for(AbstractCard c:p.hand.group){
            if(c.cardID.equals(this.cardID))
                this.baseBlock += 1;
        }

        for (AbstractCard c : p.discardPile.group) {
            if(c.cardID.equals(this.cardID))
                this.baseBlock += 1;
        }

        for (AbstractCard c : p.drawPile.group) {
            if(c.cardID.equals(this.cardID))
                this.baseBlock += 1;
        }

        super.applyPowers();

        initializeDescription();
    }

    public void onMoveToDiscard()
    {
        this.rawDescription = DESCRIPTION;
        initializeDescription();
    }

    public void calculateCardDamage(AbstractMonster mo)
    {
        super.calculateCardDamage(mo);
        initializeDescription();
    }

    public void triggerOnEndOfTurnForPlayingCard()
    {
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAction(this,1));
    }

    public AbstractCard makeCopy() {
        return new Muse();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.upgradeBlock(3);
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("Muse");
        NAME = Muse.cardStrings.NAME;
        DESCRIPTION = Muse.cardStrings.DESCRIPTION;
    }
}
