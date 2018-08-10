package Thmod.Power;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import Thmod.Cards.Curses.Fetter;
import Thmod.Cards.DeriveCards.AbstractDeriveCards;
import Thmod.Cards.ElementCards.SpellCards.AbstractElementSpellCards;
import Thmod.Cards.ItemCards.AbstractItemCards;
import Thmod.Cards.SpellCards.AbstractSpellCards;

public class FetterPower extends AbstractPower {
    public static final String POWER_ID = "FetterPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("FetterPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private AbstractPlayer p = AbstractDungeon.player;

    public FetterPower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = "FetterPower";
        this.owner = owner;
        this.amount = -1;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/FetterPower.png");
        this.type = PowerType.BUFF;
    }

    public void onExhaust(AbstractCard card) {
        if((!(card instanceof AbstractSpellCards)) && (!(card instanceof AbstractDeriveCards)) && (!(card instanceof AbstractElementSpellCards)) && (!(card instanceof AbstractItemCards))){
            AbstractDungeon.actionManager.addToTop(new MakeTempCardInHandAction(new Fetter(),false));
        }
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0];
    }
}
