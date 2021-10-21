package Thmod.Relics.remiria;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import java.util.HashMap;

import Thmod.Actions.unique.TurnEndAction;

public class SpecialStopwatch extends AbstractRemiriaRelic {
    public static final String ID = "SpecialStopwatch";
    private AbstractPlayer p = AbstractDungeon.player;
    private boolean isUsed;
    private boolean active;
    private HashMap<AbstractCard, Integer> originalCost = new HashMap<>();

    public SpecialStopwatch()
    {
        super("SpecialStopwatch",  RelicTier.SPECIAL, LandingSound.FLAT);
    }

    @Override
    public void onEquip() {
        super.onEquip();
        this.isUsed = false;
        this.counter = 1;
        this.active = false;
        this.originalCost.clear();
        this.changeTips();
    }

    public void atBattleStart() {
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p,p,new StrengthPower(p,2),2));
        beginLongPulse();
        this.isUsed = false;
        this.pulse = true;
    }

    @Override
    public void atTurnStart() {
        super.atTurnStart();
        if(this.counter >= 12){
            if(AbstractDungeon.relicRng.randomBoolean()){
                this.active = true;
                changeImg();
            }
        }
    }

    @Override
    public void onPlayerEndTurn() {
        super.onPlayerEndTurn();
        this.active = false;
        changeImg();
    }

    public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {
        if(this.active) {
            if (this.counter < 12) {
                if (this.originalCost.containsKey(targetCard)) {
                    this.counter += this.originalCost.get(targetCard);
                    if (this.counter >= 12) {
                        this.counter = 12;
                        AbstractDungeon.actionManager.addToBottom(new TurnEndAction());
                        this.description = this.getUpdatedDescription();
                    }
                }
            } else {
                if (AbstractDungeon.relicRng.randomBoolean()) {
                    AbstractDungeon.actionManager.addToBottom(new TurnEndAction());
                    this.active = false;
                    changeImg();
                }
            }
            this.changeTips();
        }
    }

    protected  void onRightClick(){
        if(AbstractDungeon.currMapNode != null) {
            if ((AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT)) {
                if(!this.isUsed) {
                    this.isUsed = true;
                    this.active = true;
                    changeImg();
                }
                else if((this.active) && (this.counter < 12)){
                    this.active = false;
                    changeImg();
                }
            }
        }
    }

    @Override
    public void update() {
        super.update();
        if(this.active){
            for(AbstractCard card : AbstractDungeon.player.hand.group){
                if((card.costForTurn > 0)){
                    this.originalCost.put(card, card.costForTurn);
                    card.setCostForTurn(-9);
                }
            }
        }
        else if(this.originalCost.size() > 0) {
            for(AbstractCard card : AbstractDungeon.player.hand.group){
                if(this.originalCost.containsKey(card)){
                    card.costForTurn = this.originalCost.get(card);
                    if (card.costForTurn < 0) {
                        card.costForTurn = 0;
                    }

                    card.isCostModifiedForTurn = false;
                }
            }
            this.originalCost.clear();
        }
    }

    private void changeImg(){
        if (this.active) {
            this.img = ImageMaster.loadImage("images/relics/remiria/SpecialStopwatch_1.png");
        } else {
            this.img = ImageMaster.loadImage("images/relics/remiria/SpecialStopwatch.png");
        }
    }

    private void changeTips(){
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        this.tips.add(new PowerTip(this.DESCRIPTIONS[2], this.DESCRIPTIONS[3] + this.counter + this.DESCRIPTIONS[4]));
        this.initializeTips();
    }

    public String getUpdatedDescription() {
        if(this.counter >= 12) {
            return this.DESCRIPTIONS[1];
        }
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new SpecialStopwatch();
    }
}
