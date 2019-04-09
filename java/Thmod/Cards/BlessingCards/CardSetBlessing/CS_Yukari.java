package Thmod.Cards.BlessingCards.CardSetBlessing;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Cards.BlessingCards.AbstractBlessingCard;
import Thmod.Cards.DeriveCards.AbstractDeriveCards;
import Thmod.Cards.ElementCards.SpellCards.AbstractElementSpellCards;
import Thmod.Cards.SpellCards.AbstractSpellCards;

public class CS_Yukari extends AbstractBlessingCard
{
    public static final String ID = "CS_Yukari";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;

    public CS_Yukari()
    {
        super("CS_Yukari", CS_Yukari.NAME, CS_Yukari.DESCRIPTION);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
    }

    public void triggerWhenDrawn()
    {
        CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        AbstractPlayer p = AbstractDungeon.player;
        AbstractDungeon.actionManager.addToTop(new DrawCardAction(p, 1));
        for(AbstractCard card : p.exhaustPile.group){
            if(!(card instanceof AbstractDeriveCards) && !(card instanceof AbstractSpellCards) && !(card instanceof AbstractElementSpellCards)){
                tmp.addToTop(card);
            }
        }
        if(tmp.group.size() > 0){
            int i = tmp.size() - 1;
            int roll = MathUtils.random(i);
            p.hand.group.add(tmp.group.get(roll).makeCopy());
            p.exhaustPile.group.remove(tmp.group.get(roll));
        }
        AbstractDungeon.actionManager.addToBottom(new ExhaustSpecificCardAction(this, p.hand));
    }

    public AbstractCard makeCopy()
    {
        return new CS_Yukari();
    }

    public void upgrade() {}

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("CS_Yukari");
        NAME = CS_Yukari.cardStrings.NAME;
        DESCRIPTION = CS_Yukari.cardStrings.DESCRIPTION;
    }
}
