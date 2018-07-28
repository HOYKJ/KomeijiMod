package Thmod.Cards.ElementCards.UncommonCards;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

import Thmod.Cards.ElementCards.AbstractElementSweepCards;
import Thmod.Cards.ElementCards.SummerFlame;
import Thmod.Cards.ElementCards.WipeMoisture;

public class StaticGreen extends AbstractElementSweepCards {
    public static final String ID = "StaticGreen";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 0;

    public StaticGreen() {
        super("StaticGreen", StaticGreen.NAME,  0, StaticGreen.DESCRIPTION, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE,ElementType.Wood);
        this.baseMagicNumber = 1;
        this.magicNumber = baseMagicNumber;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        super.use(p,m);
        AbstractDungeon.actionManager.addToTop(new GainEnergyAction(this.magicNumber));
    }

    @Override
    public ArrayList<AbstractElementSweepCards> getOpposite() {
        final ArrayList<AbstractElementSweepCards> opposite = new ArrayList<>();
        opposite.add(new SpringWind());
        opposite.add(new FlashOfSpring());
        return opposite;
    }

    public AbstractCard makeCopy() {
        return new StaticGreen();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("StaticGreen");
        NAME = StaticGreen.cardStrings.NAME;
        DESCRIPTION = StaticGreen.cardStrings.DESCRIPTION;
    }
}
