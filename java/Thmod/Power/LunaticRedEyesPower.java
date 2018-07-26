package Thmod.Power;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;

import Thmod.Actions.unique.PlayerTalkAction;
import Thmod.Cards.DeriveCards.AbstractDeriveCards;
import Thmod.Cards.ElementCards.SpellCards.AbstractElementSpellCards;
import Thmod.Cards.ItemCards.AbstractItemCards;
import Thmod.Cards.SpellCards.AbstractSpellCards;
import basemod.DevConsole;

public class LunaticRedEyesPower extends AbstractPower {
    public static final String POWER_ID = "LunaticRedEyesPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("LunaticRedEyesPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private boolean canback;
    private AbstractPlayer p = AbstractDungeon.player;

    public LunaticRedEyesPower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = "LunaticRedEyesPower";
        this.owner = owner;
        this.amount = -1;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/LunaticRedEyesPower.png");
        this.type = PowerType.BUFF;
        this.canback = false;
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if(!(card.purgeOnUse)) {
            AbstractCard c = card.makeStatEquivalentCopy();
            this.canback = true;
//            for (Iterator localIterator = ThMod.itemorspell.iterator(); localIterator.hasNext(); ) {
//                String id = (String) localIterator.next();
//                if (c.cardID.equals(id))
//                    this.canback = false;
//            }
            boolean result = card instanceof AbstractSpellCards;
            boolean result2 = card instanceof AbstractItemCards;
            boolean result3 = card instanceof AbstractDeriveCards;
            boolean result4 = card instanceof AbstractElementSpellCards;
            if ((result) || (result2) || (result3) || (result4))
                this.canback = false;
            if (this.canback) {
                if(!(c.exhaust))
                    c.rawDescription = c.rawDescription + " 消耗 .";
                if(!(c.isEthereal))
                    c.rawDescription = c.rawDescription + " 虚无 .";
//                c.exhaust = true;
                c.isEthereal = true;
                c.purgeOnUse = true;
                c.initializeDescription();
                if(AbstractDungeon.player.hand.size() == 10)
                    AbstractDungeon.actionManager.addToTop(new PlayerTalkAction(p,"我的手牌满了"));
                else
                    AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(c));
            }
        }
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0];
    }
}
