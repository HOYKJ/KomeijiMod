package Thmod.Relics.remiria;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.util.ArrayList;

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
        ArrayList<AbstractCard> tmp = new ArrayList<>();
        //CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        tmp.add(new BloodyCatastrophe());
        tmp.add(new BloodyLaserofSeventeenArticles());
        tmp.add(new SuperhumanBloodyKnife());
        AbstractDungeon.cardRewardScreen.open(tmp, null, this.DESCRIPTIONS[1]);
        //AbstractDungeon.effectsQueue.add(new ChooseAndObtainEffect(tmp, AbstractDungeon.player.masterDeck, DESCRIPTIONS[1]));
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
