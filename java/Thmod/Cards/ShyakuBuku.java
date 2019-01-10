package Thmod.Cards;

import com.megacrit.cardcrawl.actions.common.PlayTopCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Cards.UncommonCards.TenguNoTaiko;

public class ShyakuBuku extends AbstractKomeijiCards {
    public static final String ID = "ShyakuBuku";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION;
    private static final int COST = 1;

    public ShyakuBuku() {
        super("ShyakuBuku", ShyakuBuku.NAME,  1, ShyakuBuku.DESCRIPTION, CardType.ATTACK, CardRarity.COMMON, CardTarget.NONE);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(new PlayTopCardAction(AbstractDungeon.getCurrRoom().monsters.getRandomMonster(null, true, AbstractDungeon.cardRng), false));
    }

    public AbstractCard makeCopy() {
        return new ShyakuBuku();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.exhaust = false;
            this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("ShyakuBuku");
        NAME = ShyakuBuku.cardStrings.NAME;
        DESCRIPTION = ShyakuBuku.cardStrings.DESCRIPTION;
        UPGRADE_DESCRIPTION = ShyakuBuku.cardStrings.UPGRADE_DESCRIPTION;
    }
}
