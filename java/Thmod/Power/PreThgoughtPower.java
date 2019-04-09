package Thmod.Power;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
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

import java.util.ArrayList;

import Thmod.Cards.AbstractSweepCards;
import Thmod.Cards.ElementCards.AbstractElementSweepCards;

public class PreThgoughtPower extends AbstractPower {
    public static final String POWER_ID = "PreThgoughtPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("PreThgoughtPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private AbstractPlayer p = AbstractDungeon.player;

    public PreThgoughtPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = "PreThgoughtPower";
        this.owner = owner;
        this.amount = amount;
        this.img = ImageMaster.loadImage("images/power/32/PreThgoughtPower.png");
        this.type = PowerType.BUFF;
        updateDescription();
    }

    @Override
    public void onAfterCardPlayed(AbstractCard usedCard) {
        super.onAfterCardPlayed(usedCard);
        AbstractCard cardToPlay = null;
        if(usedCard instanceof AbstractSweepCards){
            ArrayList<AbstractSweepCards> cards =((AbstractSweepCards) usedCard).getOpposite();
            if(cards.size() > 1){
                if(MathUtils.randomBoolean()){
                    cardToPlay = cards.get(1);
                }
                else {
                    cardToPlay = cards.get(0);
                }
            }
            else {
                cardToPlay = cards.get(0);
            }
        }
        else if(usedCard instanceof AbstractElementSweepCards){
            ArrayList<AbstractElementSweepCards> cards =((AbstractElementSweepCards) usedCard).getOpposite();
            if(cards.size() > 1){
                if(MathUtils.randomBoolean()){
                    cardToPlay = cards.get(1);
                }
                else {
                    cardToPlay = cards.get(0);
                }
            }
            else {
                cardToPlay = cards.get(0);
            }
        }
        if(cardToPlay != null){
            if(this.amount > 0) {
                AbstractMonster m = AbstractDungeon.getMonsters().getRandomMonster(true);
                AbstractDungeon.player.limbo.addToBottom(cardToPlay);
                cardToPlay.target_x = (Settings.WIDTH / 2.0F - 300.0F * Settings.scale);
                cardToPlay.target_y = (Settings.HEIGHT / 2.0F);
                cardToPlay.freeToPlayOnce = true;
                if (m != null) {
                    cardToPlay.calculateCardDamage(m);
                }
                cardToPlay.purgeOnUse = true;
                AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(cardToPlay, m, usedCard.energyOnUse));
                this.amount -= 1;
            }
            if(this.amount == 0) {
                AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, this));
            }
            updateDescription();
        }
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
