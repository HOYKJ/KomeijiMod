package Thmod.Cards.BlessingCards.CardSetBlessing;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

import Thmod.Actions.common.LatterAction;
import Thmod.Cards.BlessingCards.AbstractBlessingCard;

public class CS_Yuyuko extends AbstractBlessingCard
{
    public static final String ID = "CS_Yuyuko";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;

    public CS_Yuyuko()
    {
        super("CS_Yuyuko", CS_Yuyuko.NAME, CS_Yuyuko.DESCRIPTION);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
    }

    public void triggerWhenDrawn()
    {
        AbstractPlayer p = AbstractDungeon.player;
        AbstractDungeon.actionManager.addToTop(new DrawCardAction(p, 1));
        AbstractDungeon.actionManager.addToBottom(new LatterAction(()-> {
            ArrayList<AbstractCard> cards = new ArrayList<>();
            for (AbstractCard card : p.hand.group) {
                if (card.canUpgrade()) {
                    cards.add(card);
                }
            }
            if (cards.size() > 0) {
                int i = cards.size() - 1;
                int roll = MathUtils.random(i);
                cards.get(roll).upgrade();
            }
        }, 0.1f));
        AbstractDungeon.actionManager.addToBottom(new ExhaustSpecificCardAction(this, p.hand));
    }

    public AbstractCard makeCopy()
    {
        return new CS_Yuyuko();
    }

    public void upgrade() {}

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("CS_Yuyuko");
        NAME = CS_Yuyuko.cardStrings.NAME;
        DESCRIPTION = CS_Yuyuko.cardStrings.DESCRIPTION;
    }
}
