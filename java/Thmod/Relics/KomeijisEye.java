package Thmod.Relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import Thmod.Power.MindReadingPower;
import basemod.DevConsole;

public class KomeijisEye extends AbstractThRelic {
    public static final String ID = "komeijisEye";

    public KomeijisEye()
    {
        super("KomeijisEye",  RelicTier.STARTER, LandingSound.FLAT);
    }

    public void atBattleStartPreDraw() {
        AbstractPlayer p = AbstractDungeon.player;
        flash();
        if(!(p.hasPower("MindReadingPower"))) {
            AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p, p, new MindReadingPower(p)));
            DevConsole.logger.info("had power mindReading");
        }
    }

//    public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {
//        if(!(AbstractDungeon.player.hasPower("MindReadingPower")))
//            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.p, this.p, new MindReadingPower(this.p)));
//    }

    protected  void onRightClick(){}

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new KomeijisEye();
    }
}
