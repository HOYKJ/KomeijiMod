package Thmod.Actions.common;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.Iterator;

import Thmod.Cards.SpellCards.HagoromoToki;

public class DiscardAndDamageAction extends AbstractGameAction
{
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("DiscardAndDamageAction");
    public static final String[] TEXT = uiStrings.TEXT;
    private AbstractMonster target;
    private DamageInfo info;

    public DiscardAndDamageAction(AbstractCreature source, AbstractMonster target,DamageInfo info)
    {
        setValues(AbstractDungeon.player, source);
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.target = target;
        this.info = info;
    }

    public void update()
    {
        if (this.duration == 0.5F) {
            AbstractDungeon.handCardSelectScreen.open(TEXT[0], 1, false, true);

            AbstractDungeon.actionManager.addToBottom(new WaitAction(0.25F));
            tickDuration();
            return;
        }

        if (!(AbstractDungeon.handCardSelectScreen.wereCardsRetrieved))
        {
            if (!(AbstractDungeon.handCardSelectScreen.selectedCards.group.isEmpty())) {
                for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                    AbstractDungeon.player.hand.moveToDiscardPile(c);
                    GameActionManager.incrementDiscard(false);
                    c.triggerOnManualDiscard();
                }
                AbstractDungeon.actionManager.addToBottom(new DamageAction(target, info, AbstractGameAction.AttackEffect.BLUNT_HEAVY));
            }

            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
        }

        tickDuration();
    }
}
