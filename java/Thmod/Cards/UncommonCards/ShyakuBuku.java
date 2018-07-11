package Thmod.Cards.UncommonCards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Cards.AbstractKomeijiCards;

public class ShyakuBuku extends AbstractKomeijiCards {
    public static final String ID = "ShyakuBuku";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 0;

    public ShyakuBuku() {
        super("ShyakuBuku", ShyakuBuku.NAME,  0, ShyakuBuku.DESCRIPTION, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new AbstractGameAction() {
            public void update() {
                m.rollMove();
                AbstractDungeon.getMonsters().showIntent();
                this.isDone = true;
            }
        });
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, this.magicNumber));
    }

    public AbstractCard makeCopy() {
        return new ShyakuBuku();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("ShyakuBuku");
        NAME = ShyakuBuku.cardStrings.NAME;
        DESCRIPTION = ShyakuBuku.cardStrings.DESCRIPTION;
    }
}
