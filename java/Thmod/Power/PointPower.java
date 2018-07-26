package Thmod.Power;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;
import java.util.Iterator;

import Thmod.Actions.unique.CardSelectAction;
import Thmod.Actions.unique.isSeedAction;
import Thmod.Cards.SpellCards.CuteOchiyari;
import Thmod.Cards.SpellCards.DollofRoundTable;
import Thmod.Cards.SpellCards.DollsWar;
import Thmod.Cards.SpellCards.LemmingsParade;
import Thmod.Cards.SpellCards.TripWire;
import Thmod.ThMod;
import basemod.DevConsole;

public class PointPower extends AbstractPower {
    public static final String POWER_ID = "PointPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("PointPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private AbstractPlayer p = AbstractDungeon.player;
    private static ArrayList<AbstractCard> cardid = new ArrayList<>();

    public PointPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = "PointPower";
        this.owner = owner;
        this.amount = amount;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/PointPower.png");
        this.type = PowerType.BUFF;
    }

    public void atStartOfTurn() {
        if (p.hasPower("PointPower")) {
            if (p.getPower("PointPower").amount > 5)
                this.amount = 5;
        }
        if(ThMod.StartSelectOpen)
            Start();
    }

    public static void Start() {
        cardid.clear();
        AbstractDungeon.actionManager.addToBottom(new isSeedAction());
    }

    public void cardSelect(){
        if (p.hasPower("PointPower")) {
            if (p.getPower("PointPower").amount > 0) {
                int powercount = p.getPower("PointPower").amount;
                if (powercount > 5)
                    powercount = 5;
                DevConsole.logger.info("beforeSelect"+ powercount);
                if (powercount == 1)
                    AbstractDungeon.actionManager.addToBottom( new CardSelectAction(1, true, true, 1, cardid));
                else
                    AbstractDungeon.actionManager.addToBottom( new CardSelectAction(1, false, true, powercount, cardid));
            }
        }
    }

    public void isSeed(final AbstractCard c) {
        for (Iterator Iterator = ThMod.fightids.iterator(); Iterator.hasNext(); ) {
            String cardid1 = (String) Iterator.next();
            if (c.cardID.equals(cardid1)) {
                cardid.add(c);
            }
        }
    }

    public void addNingyou(int NingyouNum,int YariNum,int TateNum,int YumiNum){
        DevConsole.logger.info("NingyouNum"+NingyouNum);
        DevConsole.logger.info("YariNum"+YariNum);
        DevConsole.logger.info("TateNum"+TateNum);
        DevConsole.logger.info("YumiNum"+YumiNum);
        if(NingyouNum > 2){
            cardid.add(new TripWire());
            cardid.add(new LemmingsParade());
        }
        if(YariNum > 2)
            cardid.add(new CuteOchiyari());
        if((TateNum > 2) && ((!p.hasPower("RoundTable"))))
            cardid.add(new DollofRoundTable());
        if(YumiNum > 2)
            cardid.add(new DollsWar());
    }

    public void elementMix(boolean earth,boolean fire,boolean luna,boolean metal,boolean sun,boolean water,boolean wood,boolean isMix){
        if(isMix){
            cardid.clear();
        }

    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0];
    }

}
