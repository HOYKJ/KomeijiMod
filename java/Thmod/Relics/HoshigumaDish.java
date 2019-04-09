package Thmod.Relics;

import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;

import Thmod.Actions.unique.ChooseAction;

public class HoshigumaDish extends AbstractThRelic {
    public static final String ID = "HoshigumaDish";
    private boolean used;
    private AbstractCard givenCard;
    private float baseNum;
    private float numCounter;
    private enum NT{
        DAMAGE, BLOCK, MAGICNUMBER
    }
    private NT nt;

    public HoshigumaDish()
    {
        super("HoshigumaDish",  RelicTier.COMMON, LandingSound.FLAT);
        this.used = false;
        this.baseNum = 0;
        this.numCounter = 0;
    }

    public void atPreBattle() {
        this.used = false;
        beginPulse();
        this.pulse = true;
        this.nt = null;
    }

    protected  void onRightClick(){
        if(!used){
            if(AbstractDungeon.currMapNode != null) {
                if ((AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT)) {
                    AbstractPlayer p = AbstractDungeon.player;
                    final ChooseAction choice = new ChooseAction(null, null, DESCRIPTIONS[1], true, 1);
                    for (AbstractCard card : p.hand.group) {
                        addChoice(choice, card);
                    }
                    AbstractDungeon.actionManager.addToBottom(choice);
                }
            }
        }
    }

    private void addChoice(ChooseAction chooseAction, AbstractCard card){
        AbstractPlayer p = AbstractDungeon.player;
        chooseAction.add(card, ()->{
            AbstractDungeon.actionManager.addToBottom(new ExhaustSpecificCardAction(card, p.hand));
            this.givenCard = AbstractDungeon.returnTrulyRandomCardInCombat().makeCopy();
            while (true){
                if(this.givenCard.baseDamage > 0){
                    this.baseNum = this.givenCard.baseDamage;
                    this.givenCard.baseDamage += this.baseNum * 2;
                    this.numCounter = this.givenCard.baseDamage;
                    this.nt = NT.DAMAGE;
                    break;
                }
                if(this.givenCard.baseBlock > 0){
                    this.baseNum = this.givenCard.baseBlock;
                    this.givenCard.baseBlock += this.baseNum * 2;
                    this.numCounter = this.givenCard.baseBlock;
                    this.nt = NT.BLOCK;
                    break;
                }
                if(this.givenCard.baseMagicNumber > 0){
                    this.baseNum = this.givenCard.baseMagicNumber;
                    this.givenCard.baseMagicNumber += this.baseNum * 2;
                    this.givenCard.magicNumber = this.givenCard.baseMagicNumber;
                    this.numCounter = this.givenCard.baseMagicNumber;
                    this.nt = NT.MAGICNUMBER;
                    break;
                }
                this.givenCard = AbstractDungeon.returnTrulyRandomCardInCombat().makeCopy();
            }
            AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(this.givenCard));
            this.img = ImageMaster.loadImage("images/relics/HoshigumaDish_1.png");
            this.used = true;
        });
    }

    @Override
    public void onPlayerEndTurn() {
        super.onPlayerEndTurn();
        if(this.numCounter < 0){
            this.numCounter = 0;
        }
        else {
            this.numCounter -= this.baseNum / 3;
        }
        if(this.nt != null) {
            switch (this.nt) {
                case DAMAGE:
                    this.givenCard.baseDamage = (int) this.numCounter;
                    break;
                case BLOCK:
                    this.givenCard.baseBlock = (int) this.numCounter;
                    break;
                case MAGICNUMBER:
                    this.givenCard.baseMagicNumber = (int) this.numCounter;
                    break;
            }
        }
    }

    @Override
    public void onVictory() {
        super.onVictory();
        this.nt = null;
        this.used = false;
        this.img = ImageMaster.loadImage("images/relics/HoshigumaDish.png");
        this.pulse = false;
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new HoshigumaDish();
    }
}
