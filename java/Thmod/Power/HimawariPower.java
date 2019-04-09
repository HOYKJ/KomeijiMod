package Thmod.Power;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;

import Thmod.Cards.ElementCards.CondensedBubble;
import Thmod.Cards.ElementCards.RareCards.ElementInvoke;
import Thmod.Cards.ElementCards.RareCards.ElementMix;
import Thmod.Cards.ElementCards.RareCards.RoyalFlare;
import Thmod.Cards.ElementCards.RareCards.SilentSelene;
import Thmod.Cards.ElementCards.StickyBubble;
import Thmod.Cards.ElementCards.SummerFlame;
import Thmod.Cards.ElementCards.SummerRed;
import Thmod.Cards.ElementCards.UncommonCards.AutumnBlades;
import Thmod.Cards.ElementCards.UncommonCards.AutumnEdge;
import Thmod.Cards.ElementCards.UncommonCards.DiamondHardness;
import Thmod.Cards.ElementCards.UncommonCards.EmeraldCity;
import Thmod.Cards.ElementCards.UncommonCards.FallThrasher;
import Thmod.Cards.ElementCards.UncommonCards.FlashOfSpring;
import Thmod.Cards.ElementCards.UncommonCards.MidautumnSpear;
import Thmod.Cards.ElementCards.UncommonCards.SpringWind;
import Thmod.Cards.ElementCards.UncommonCards.StaticGreen;
import Thmod.Cards.ElementCards.WinterElement;
import Thmod.Cards.ElementCards.WipeMoisture;
import Thmod.ThMod;

public class HimawariPower extends AbstractPower {
    public static final String POWER_ID = "HimawariPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("HimawariPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static CardGroup elementCardPool = new CardGroup(CardGroup.CardGroupType.CARD_POOL);

    public HimawariPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = "HimawariPower";
        this.owner = owner;
        this.amount = amount;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/HimawariPower.png");
        this.type = PowerType.BUFF;
    }

    public void atStartOfTurn()
    {
        elementCardPool.addToTop(new ElementInvoke());
        elementCardPool.addToTop(new ElementMix());
        elementCardPool.addToTop(new RoyalFlare());
        elementCardPool.addToTop(new SilentSelene());

        elementCardPool.addToTop(new AutumnBlades());
        elementCardPool.addToTop(new AutumnEdge());
        elementCardPool.addToTop(new DiamondHardness());
        elementCardPool.addToTop(new EmeraldCity());
        elementCardPool.addToTop(new FallThrasher());
        elementCardPool.addToTop(new FlashOfSpring());
        elementCardPool.addToTop(new MidautumnSpear(0));
        elementCardPool.addToTop(new SpringWind());
        elementCardPool.addToTop(new StaticGreen());

        elementCardPool.addToTop(new CondensedBubble());
        elementCardPool.addToTop(new StickyBubble());
        elementCardPool.addToTop(new SummerFlame());
        elementCardPool.addToTop(new SummerRed());
        elementCardPool.addToTop(new WinterElement());
        elementCardPool.addToTop(new WipeMoisture());
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead())
        {
            flash();
            for (int i = 0; i < this.amount; i++) {
                AbstractCard giveCard = elementCardPool.getRandomCard(AbstractDungeon.cardRandomRng).makeCopy();
                giveCard.setCostForTurn(-9);
                if(!(giveCard.exhaust))
                    giveCard.rawDescription = giveCard.rawDescription + " 消耗 .";
                if(!(giveCard.isEthereal))
                    giveCard.rawDescription = giveCard.rawDescription + " 虚无 .";
                giveCard.isEthereal = true;
                giveCard.purgeOnUse = true;
                giveCard.initializeDescription();
                AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(giveCard));
            }
        }
    }

    public void updateDescription()
    {
        this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
    }
}
