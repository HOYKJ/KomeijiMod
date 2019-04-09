package Thmod.Cards.UncommonCards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

import Thmod.Actions.unique.CardSelectAction;
import Thmod.Cards.AbstractKomeijiCards;

public class TenguNoTaiko extends AbstractKomeijiCards {
    public static final String ID = "TenguNoTaiko";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION;
    private static final int COST = 1;
    private ArrayList<AbstractCard> cardid = new ArrayList<>();

    public TenguNoTaiko() {
        super("TenguNoTaiko", TenguNoTaiko.NAME,  1, TenguNoTaiko.DESCRIPTION, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF, CardSet_k.AYA);
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        this.cardid.clear();
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, 1));
        this.cardid.add(this);
        AbstractDungeon.actionManager.addToBottom(new CardSelectAction(1, false, true, -1,this.cardid ));
    }

    public AbstractCard makeCopy() {
        return new TenguNoTaiko();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("TenguNoTaiko");
        NAME = TenguNoTaiko.cardStrings.NAME;
        DESCRIPTION = TenguNoTaiko.cardStrings.DESCRIPTION;
        UPGRADE_DESCRIPTION = TenguNoTaiko.cardStrings.UPGRADE_DESCRIPTION;
    }
}
