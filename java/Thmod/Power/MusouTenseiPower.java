package Thmod.Power;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;

import Thmod.Actions.common.TenseiAttackAction;
import Thmod.Actions.vfx.MusouStartAction;
import Thmod.vfx.MusouTenseiEffect;

public class MusouTenseiPower extends AbstractPower {
    public static final String POWER_ID = "MusouTenseiPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("MusouTenseiPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private AbstractPlayer p = AbstractDungeon.player;
    private int num;
    private ArrayList<MusouTenseiEffect> effects = new ArrayList<>();
    private boolean done;
    private int damage;
    private int turns;

    public MusouTenseiPower(AbstractCreature owner,int damage) {
        this.name = NAME;
        this.ID = "MusouTenseiPower";
        this.owner = owner;
        this.amount = 7;
        this.img = ImageMaster.loadImage("images/power/32/MusouTenseiPower.png");
        this.type = PowerType.BUFF;
        this.num = 0;
        this.done = false;
        this.damage = damage;
        this.turns = 3;
        updateDescription();
    }

    @Override
    public void update(int slot) {
        super.update(slot);
        if(!this.done){
            for(int i = 0; i < 7; i ++){
                this.effects.add(new MusouTenseiEffect(this.owner.hb, i));
                AbstractDungeon.effectList.add(this.effects.get(i));
            }
            this.done = true;
        }
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        super.onUseCard(card, action);
        if(this.amount > 0) {
            if (card.type == AbstractCard.CardType.ATTACK) {
                this.effects.get(this.num).setActive();
                this.num += 1;
                this.amount -= 1;
            }
        }
        if(this.amount == 0){
            for(int i = 0; i < 7; i ++){
                this.effects.get(i).setStart();
            }
            AbstractDungeon.actionManager.addToBottom(new TenseiAttackAction(this.damage, this.owner.hb));
        }
        updateDescription();
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        super.atEndOfTurn(isPlayer);
        if(isPlayer){
            if(this.turns > 0){
                this.turns -= 1;
            }
            else{
                AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, this));
            }
            updateDescription();
        }
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0] + this.turns + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2] + this.damage + DESCRIPTIONS[3];
    }
}
