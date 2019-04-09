package Thmod.Cards.BlessingCards.CardSetBlessing;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;

import java.util.ArrayList;

import Thmod.Actions.common.LatterAction;
import Thmod.Actions.unique.PlayerTalkAction;
import Thmod.Cards.BlessingCards.AbstractBlessingCard;
import Thmod.Cards.DeriveCards.AbstractDeriveCards;
import Thmod.Cards.ElementCards.SpellCards.AbstractElementSpellCards;
import Thmod.Cards.ItemCards.AbstractItemCards;
import Thmod.Cards.SpellCards.AbstractSpellCards;
import Thmod.ThMod;

import static Thmod.Power.LunaticRedEyesPower.DESCRIPTIONS;

public class CS_Reisen extends AbstractBlessingCard
{
    public static final String ID = "CS_Reisen";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;

    public CS_Reisen()
    {
        super("CS_Reisen", CS_Reisen.NAME, CS_Reisen.DESCRIPTION);
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
                if (!(card instanceof AbstractSpellCards) && !(card instanceof AbstractElementSpellCards) && !(card instanceof AbstractDeriveCards) && !(card instanceof AbstractItemCards)) {
                    cards.add(card);
                }
            }
            if (cards.size() > 0) {
                int i = cards.size() - 1;
                int roll = MathUtils.random(i);
                AbstractCard c = cards.get(roll).makeStatEquivalentCopy();
                if (!(c.exhaust))
                    c.rawDescription = c.rawDescription + DESCRIPTIONS[1];
                if (!(c.isEthereal))
                    c.rawDescription = c.rawDescription + DESCRIPTIONS[2];
                c.isEthereal = true;
                c.purgeOnUse = true;
                c.initializeDescription();
                if (AbstractDungeon.player.hand.size() >= 10)
                    AbstractDungeon.actionManager.addToTop(new PlayerTalkAction(p, DESCRIPTIONS[3]));
                else
                    AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(c));
            }
        }, 0.1f));
        AbstractDungeon.actionManager.addToBottom(new ExhaustSpecificCardAction(this, p.hand));
    }

    public AbstractCard makeCopy()
    {
        return new CS_Reisen();
    }

    public void upgrade() {}

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("CS_Reisen");
        NAME = CS_Reisen.cardStrings.NAME;
        DESCRIPTION = CS_Reisen.cardStrings.DESCRIPTION;
    }
}
