package Thmod.Cards.RewardCards;

import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.PlayTopCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Cards.AbstractKomeijiCards;

public class IdRelease extends AbstractKomeijiCards {
    public static final String ID = "IdRelease";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;

    public IdRelease() {
        super("IdRelease", IdRelease.NAME,  3, IdRelease.DESCRIPTION, CardType.SKILL, CardRarity.SPECIAL, CardTarget.NONE, CardSet_k.OTHER);
        this.exhaust = true;
        this.upgraded = true;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        for(AbstractCard card : p.hand.group){
            AbstractDungeon.actionManager.addToTop(new ExhaustSpecificCardAction(card, p.hand));
            AbstractDungeon.actionManager.addToBottom(new PlayTopCardAction(AbstractDungeon.getCurrRoom().monsters.getRandomMonster(null, true, AbstractDungeon.cardRng), false));
        }
    }

    public boolean canUpgrade()
    {
        return false;
    }

    public AbstractCard makeCopy() {
        return new IdRelease();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("IdRelease");
        NAME = IdRelease.cardStrings.NAME;
        DESCRIPTION = IdRelease.cardStrings.DESCRIPTION;
    }
}
