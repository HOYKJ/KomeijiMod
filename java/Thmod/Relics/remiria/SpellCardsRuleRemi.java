package Thmod.Relics.remiria;

import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import Thmod.Actions.unique.ChooseAndObtainAction;
import Thmod.Cards.ScarletCard.rewardCards.BloodyCatastrophe;
import Thmod.Cards.ScarletCard.rewardCards.BloodyLaserofSeventeenArticles;
import Thmod.Cards.ScarletCard.rewardCards.SuperhumanBloodyKnife;
import Thmod.vfx.action.ChooseAndObtainEffect;

public class SpellCardsRuleRemi extends AbstractRemiriaRelic {
    public static final String ID = "SpellCardsRuleRemi";

    public SpellCardsRuleRemi()
    {
        super("SpellCardsRuleRemi",  RelicTier.SPECIAL, LandingSound.HEAVY);
    }

    @Override
    public void onEquip() {
        super.onEquip();
        CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        tmp.addToTop(new BloodyCatastrophe());
        tmp.addToTop(new BloodyLaserofSeventeenArticles());
        tmp.addToTop(new SuperhumanBloodyKnife());
        AbstractDungeon.effectsQueue.add(new ChooseAndObtainEffect(tmp, AbstractDungeon.player.masterDeck, DESCRIPTIONS[1]));
    }

    protected  void onRightClick(){
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new SpellCardsRuleRemi();
    }
}
