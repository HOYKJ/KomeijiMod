package Thmod.Cards.ElementCards.UncommonCards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
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

public class SpringWind extends AbstractElementSweepCards {
    public static final String ID = "SpringWind";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 0;

    public SpringWind() {
        super("SpringWind", SpringWind.NAME,  0, SpringWind.DESCRIPTION, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE,ElementType.Wood);
        this.baseMagicNumber = 2;
        this.magicNumber = baseMagicNumber;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        super.use(p,m);
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, this.magicNumber));
    }

    @Override
    public ArrayList<AbstractElementSweepCards> getOpposite() {
        final ArrayList<AbstractElementSweepCards> opposite = new ArrayList<>();
        opposite.add(new FlashOfSpring());
        opposite.add(new StaticGreen());
        return opposite;
    }

    public AbstractCard makeCopy() {
        return new SpringWind();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("SpringWind");
        NAME = SpringWind.cardStrings.NAME;
        DESCRIPTION = SpringWind.cardStrings.DESCRIPTION;
    }
}
