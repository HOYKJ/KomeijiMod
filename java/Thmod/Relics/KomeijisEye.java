package Thmod.Relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import Thmod.Actions.unique.PlayerTalkAction;
import Thmod.Power.MindReadingPower;
import basemod.DevConsole;

public class KomeijisEye extends AbstractThRelic {
    public static final String ID = "komeijisEye";
    private int clicknum;

    public KomeijisEye()
    {
        super("KomeijisEye",  RelicTier.STARTER, LandingSound.FLAT);
    }

    public void atBattleStartPreDraw() {
        if(Settings.hideCombatElements)
            Settings.hideCombatElements = false;
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

    protected  void onRightClick(){
        AbstractPlayer p = AbstractDungeon.player;
        if (this.clicknum < 20)
            this.clicknum += 1;
        else if (this.clicknum == 20){
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,new DexterityPower(p,10),10));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,new StrengthPower(p,10),10));
//            this.clicknum = 100;
            AbstractDungeon.actionManager.addToTop(new PlayerTalkAction(p, "test mode start"));
        }
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new KomeijisEye();
    }
}
