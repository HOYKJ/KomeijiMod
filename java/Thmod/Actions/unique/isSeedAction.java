package Thmod.Actions.unique;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import Thmod.Power.PointPower;
import basemod.DevConsole;

public class isSeedAction extends AbstractGameAction {
    private static final float startingDuration = 0.5f;

    public isSeedAction (){
        this.duration = 0.5f;
    }

    public void update() {
        AbstractPlayer p = AbstractDungeon.player;
        PointPower pointPower = new PointPower(p,0);
        if (this.duration == 0.5f) {
            for (final AbstractCard c : AbstractDungeon.player.hand.group) {
                pointPower.isSeed(c);
            }
            pointPower.cardSelect();
//            if (p.hasPower("PointPower")) {
//                if (p.getPower("PointPower").amount > 0) {
//                    int powercount = p.getPower("PointPower").amount;
//                    DevConsole.logger.info("beforeSelect"+powercount);
//                    if (powercount == 1)
//                        AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new CardSelectAction(1, true, true, 1, cardid));
//                    else
//                        AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new CardSelectAction(1, false, true, powercount, cardid));
//                }
//            }
        }
        this.tickDuration();
    }
}
