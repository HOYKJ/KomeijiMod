package Thmod.Cards.DeriveCards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

import Thmod.Relics.NitorisBag;

public class MixedItem extends AbstractDeriveCards {
    public static final String ID = "MixedItem";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private ArrayList<Runnable> action = new ArrayList<>();

    public MixedItem(Runnable action,Runnable action2,Runnable action3) {
        super("MixedItem", MixedItem.NAME,  0, MixedItem.DESCRIPTION, CardType.POWER, CardRarity.SPECIAL, CardTarget.NONE);
        this.isEthereal = false;
        this.upgraded = true;
        this.action.add(action);
        this.action.add(action2);
        if(action3 != null)
            action3.run();
        this.cost = NitorisBag.costForMix;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        this.action.get(0).run();
        this.action.get(1).run();
    }

    public AbstractCard makeCopy() {
        return new MixedItem(null,null,null);
    }

    public void upgrade() {

    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("MixedItem");
        NAME = MixedItem.cardStrings.NAME;
        DESCRIPTION = MixedItem.cardStrings.DESCRIPTION;
    }
}
