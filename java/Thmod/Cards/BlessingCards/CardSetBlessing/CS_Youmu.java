package Thmod.Cards.BlessingCards.CardSetBlessing;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Actions.common.LatterAction;
import Thmod.Cards.BlessingCards.AbstractBlessingCard;
import Thmod.vfx.CardFeedBack;

public class CS_Youmu extends AbstractBlessingCard
{
    public static final String ID = "CS_Youmu";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;

    public CS_Youmu()
    {
        super("CS_Youmu", CS_Youmu.NAME, CS_Youmu.DESCRIPTION);
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
    }

    public void triggerWhenDrawn()
    {
        AbstractPlayer p = AbstractDungeon.player;
        AbstractDungeon.actionManager.addToTop(new DrawCardAction(p, 1));
        AbstractDungeon.effectList.add(new CardFeedBack(1f));
        AbstractDungeon.actionManager.addToBottom(new LatterAction(()-> {
            for (AbstractCard card : p.hand.group) {
                if (card.baseDamage > 0) {
                    card.baseDamage += this.magicNumber;
                    card.isDamageModified = true;
                }
            }
        }, 0.1f));
        AbstractDungeon.actionManager.addToBottom(new ExhaustSpecificCardAction(this, p.hand));
    }

    public AbstractCard makeCopy()
    {
        return new CS_Youmu();
    }

    public void upgrade() {}

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("CS_Youmu");
        NAME = CS_Youmu.cardStrings.NAME;
        DESCRIPTION = CS_Youmu.cardStrings.DESCRIPTION;
    }
}
