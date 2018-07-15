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
import Thmod.ThMod;
import basemod.DevConsole;

public class PointPower extends AbstractPower {
    public static final String POWER_ID = "PointPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("PointPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private AbstractPlayer p = AbstractDungeon.player;
    private static ArrayList<AbstractCard> cardid = new ArrayList<>();
    private String Cardid;
    private int powercount;

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
                AbstractDungeon.actionManager.addToTop(new ReducePowerAction(this.owner,this.owner,"PointPower",(p.getPower("PointPower").amount - 5)));
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
                powercount = p.getPower("PointPower").amount;
                DevConsole.logger.info("beforeSelect"+powercount);
                if (powercount == 1)
                    AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new CardSelectAction(1, true, true, 1, cardid));
                else
                    AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new CardSelectAction(1, false, true, powercount, cardid));
            }
        }
    }

    public void isSeed(final AbstractCard c) {
        for (Iterator Iterator = ThMod.fightids.iterator(); Iterator.hasNext(); ) {
            Cardid = (String) Iterator.next();
            if (c.cardID.equals(Cardid)) {
                cardid.add(c);
            }
        }
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0];
    }

}
