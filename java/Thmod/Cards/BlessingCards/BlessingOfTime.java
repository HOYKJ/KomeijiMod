package Thmod.Cards.BlessingCards;

import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import Thmod.Actions.common.EndBattleAction;
import Thmod.Monsters.Remiria;
import Thmod.ThMod;


public class BlessingOfTime extends AbstractBlessingCard
{
    public static final String ID = "BlessingOfTime";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;

    public BlessingOfTime()
    {
        super("BlessingOfTime", BlessingOfTime.NAME, BlessingOfTime.DESCRIPTION);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        boolean remove = false;
        AbstractCard card = null;
        RewardItem specialCard = new RewardItem();
        specialCard.type = RewardItem.RewardType.CARD;
        specialCard.cards.clear();
        specialCard.cards.add(new BlessingOfScarlet());
        if(ThMod.blessingOfTime < 3)
            ThMod.blessingOfTime += 1;
        String text;
        if(Settings.language == Settings.GameLanguage.ENG)
            text = "Stop this meaningless fight...";
        else
            text = "停止这无意义的争斗吧...";
        AbstractDungeon.actionManager.addToBottom(new TalkAction(AbstractDungeon.getCurrRoom().monsters.monsters.get(0), text, 0.5F, 2.0F));
        AbstractDungeon.getCurrRoom().rewards.clear();
        AbstractDungeon.getCurrRoom().addCardReward(specialCard);
        for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
            if (c instanceof BlessingOfTime) {
                remove = true;
                card = c;
            }
        }
        if(remove)
            AbstractDungeon.player.masterDeck.group.remove(card);
        UnlockTracker.unlockCard("ScarletsBlessing");
        AbstractDungeon.actionManager.addToBottom(new EndBattleAction());
    }

    public void triggerWhenDrawn()
    {
        if((!(AbstractDungeon.getCurrRoom().monsters.monsters.get(0) instanceof Remiria))) {
            AbstractDungeon.actionManager.addToTop(new ExhaustSpecificCardAction(this, AbstractDungeon.player.hand));
            AbstractDungeon.actionManager.addToTop(new DrawCardAction(AbstractDungeon.player, this.magicNumber));
        }
        else if(ThMod.blessingOfTime < 2){
            AbstractDungeon.actionManager.addToTop(new ExhaustSpecificCardAction(this, AbstractDungeon.player.hand));
            AbstractDungeon.actionManager.addToTop(new DrawCardAction(AbstractDungeon.player, this.magicNumber));
        }
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m){
        return ((ThMod.blessingOfTime >= 2) && ((AbstractDungeon.getCurrRoom().monsters.monsters.get(0) instanceof Remiria)));
    }

    public AbstractCard makeCopy()
    {
        return new BlessingOfTime();
    }

    public void upgrade() {}

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("BlessingOfTime");
        NAME = BlessingOfTime.cardStrings.NAME;
        DESCRIPTION = BlessingOfTime.cardStrings.DESCRIPTION;
    }
}
