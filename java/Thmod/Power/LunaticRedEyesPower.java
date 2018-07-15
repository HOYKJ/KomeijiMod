package Thmod.Power;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
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

import java.util.Iterator;

import Thmod.Actions.common.PlayerTalkAction;
import Thmod.Cards.ItemCards.AbstractItemCards;
import Thmod.Cards.SpellCards.AbstractSpellCards;
import Thmod.ThMod;
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
            if ((result) || (result2))
                this.canback = false;
            if (this.canback) {
                if(!(c.exhaust == true))
                    c.rawDescription = c.rawDescription + " 消耗 .";
                if(!(c.isEthereal == true))
                    c.rawDescription = c.rawDescription + " 虚无 .";
//                c.exhaust = true;
                c.isEthereal = true;
                c.purgeOnUse = true;
                DevConsole.logger.info("purge?"+c.purgeOnUse);
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
