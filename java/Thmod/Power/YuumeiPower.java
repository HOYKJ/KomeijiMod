package Thmod.Power;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class YuumeiPower extends AbstractPower {
    public static final String POWER_ID = "YuumeiPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("YuumeiPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public YuumeiPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = "YuumeiPower";
        this.owner = owner;
        this.amount = amount;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/YuumeiPower.png");
        this.type = PowerType.BUFF;
    }

    public void onUseCard(AbstractCard card, UseCardAction action)
    {
        if ((!(card.purgeOnUse)) && (card.type == AbstractCard.CardType.ATTACK) && (this.amount > 0)) {
            flash();
            AbstractMonster m = null;

            if (action.target != null) {
                m = (AbstractMonster)action.target;
            }

            AbstractCard tmp = card.makeStatEquivalentCopy();
            AbstractDungeon.player.limbo.addToBottom(tmp);
            tmp.current_x = card.current_x;
            tmp.current_y = card.current_y;
            tmp.target_x = (Settings.WIDTH / 2F - 300.0F * Settings.scale);
            tmp.target_y = (Settings.HEIGHT / 2F);
            tmp.freeToPlayOnce = true;

            if (m != null) {
                tmp.calculateCardDamage(m);
            }

            tmp.purgeOnUse = true;
            AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(tmp, m, card.energyOnUse));
        }
    }

    public void atEndOfTurn(boolean isPlayer)
    {
        if (isPlayer){
            if (this.amount <= 1)
                AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, this));
            else
                this.amount -= 1;
        }
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0];
    }
}
