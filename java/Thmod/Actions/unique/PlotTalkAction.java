package Thmod.Actions.unique;

import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.io.IOException;
import java.util.HashMap;

import Thmod.Cards.BlessingCards.BlessingOfScarlet;
import Thmod.Cards.BlessingCards.Determination;
import Thmod.Cards.BlessingCards.Remission;
import Thmod.ThMod;
import Thmod.vfx.Talk.TalkEffectNew;
import basemod.DevConsole;

public class PlotTalkAction extends AbstractGameAction
{
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("PlotTalkAction");
    public static final String[] TEXT = uiStrings.TEXT;
    private String msg;
    private boolean used;
    private boolean LclickStart;
    private boolean Lclick;
    private boolean RclickStart;
    private boolean Rclick;
    private boolean canChoose;
    private int textNum;
    private AbstractPlayer p = AbstractDungeon.player;
    private AbstractMonster zi = AbstractDungeon.getCurrRoom().monsters.monsters.get(0);
    private HashMap<Integer,Integer> LeftMap = new HashMap<>();
    private HashMap<Integer,Integer> RightMap = new HashMap<>();

    public PlotTalkAction(final AbstractCreature source, final int textNum, boolean canChoose) {
        this.used = false;
        this.setValues(source, source);
        this.textNum = textNum;
        this.actionType = AbstractGameAction.ActionType.TEXT;
        this.canChoose = canChoose;
        this.duration = 0.3F;

        this.Lclick = false;
        this.LclickStart = false;
        this.Rclick = false;
        this.RclickStart = false;

        this.LeftMap.put(0,1);
        this.LeftMap.put(2,6);
        this.LeftMap.put(4,5);
        this.LeftMap.put(3,4);
        this.LeftMap.put(6,7);
        this.LeftMap.put(5,8);
        this.LeftMap.put(7,8);
        this.LeftMap.put(8,9);
        this.LeftMap.put(10,14);
        this.LeftMap.put(12,13);
        this.LeftMap.put(11,12);
        this.LeftMap.put(14,15);
        this.LeftMap.put(13,16);
        this.LeftMap.put(15,21);
        this.LeftMap.put(17,19);
        this.LeftMap.put(18,20);
        this.LeftMap.put(22,24);
        this.LeftMap.put(23,25);
        this.LeftMap.put(19,26);
        this.LeftMap.put(20,26);
        this.LeftMap.put(24,26);
        this.LeftMap.put(25,26);
        this.LeftMap.put(27,29);
        this.LeftMap.put(28,30);
        this.LeftMap.put(29,31);
        this.LeftMap.put(30,36);
        this.LeftMap.put(32,35);
        this.LeftMap.put(33,34);
        this.LeftMap.put(37,39);
        this.LeftMap.put(38,40);
        this.LeftMap.put(34,41);
        this.LeftMap.put(35,41);
        this.LeftMap.put(39,41);
        this.LeftMap.put(40,41);
        this.LeftMap.put(42,44);
        this.LeftMap.put(43,45);
        this.LeftMap.put(44,52);
        this.LeftMap.put(45,52);

        this.LeftMap.put(53,46);
        this.LeftMap.put(46,47);
        this.LeftMap.put(48,50);
        this.LeftMap.put(49,51);
        this.LeftMap.put(50,1);

        this.LeftMap.put(1,2);
        this.LeftMap.put(9,10);
        this.LeftMap.put(16,17);
        this.LeftMap.put(21,22);
        this.LeftMap.put(26,27);
        this.LeftMap.put(31,32);
        this.LeftMap.put(36,37);
        this.LeftMap.put(41,42);
        this.LeftMap.put(47,48);

        this.RightMap.put(0,1);
        this.RightMap.put(2,6);
        this.RightMap.put(4,5);
        this.RightMap.put(3,4);
        this.RightMap.put(6,7);
        this.RightMap.put(5,8);
        this.RightMap.put(7,8);
        this.RightMap.put(8,9);
        this.RightMap.put(10,14);
        this.RightMap.put(12,13);
        this.RightMap.put(11,12);
        this.RightMap.put(14,15);
        this.RightMap.put(13,16);
        this.RightMap.put(15,21);
        this.RightMap.put(17,19);
        this.RightMap.put(18,20);
        this.RightMap.put(22,24);
        this.RightMap.put(23,25);
        this.RightMap.put(19,26);
        this.RightMap.put(20,26);
        this.RightMap.put(24,26);
        this.RightMap.put(25,26);
        this.RightMap.put(27,29);
        this.RightMap.put(28,30);
        this.RightMap.put(29,31);
        this.RightMap.put(30,36);
        this.RightMap.put(32,35);
        this.RightMap.put(33,34);
        this.RightMap.put(37,39);
        this.RightMap.put(38,40);
        this.RightMap.put(34,41);
        this.RightMap.put(35,41);
        this.RightMap.put(39,41);
        this.RightMap.put(40,41);
        this.RightMap.put(42,44);
        this.RightMap.put(43,45);
        this.RightMap.put(44,52);
        this.RightMap.put(45,52);

        this.RightMap.put(53,46);
        this.RightMap.put(46,47);
        this.RightMap.put(48,50);
        this.RightMap.put(49,51);
        this.RightMap.put(50,1);

        this.RightMap.put(1,3);
        this.RightMap.put(9,11);
        this.RightMap.put(16,18);
        this.RightMap.put(21,23);
        this.RightMap.put(26,28);
        this.RightMap.put(31,33);
        this.RightMap.put(36,38);
        this.RightMap.put(41,43);
        this.RightMap.put(47,49);
    }

    public void update() {
        if (!this.used) {
            if(source instanceof AbstractPlayer) {
                AbstractDungeon.effectList.add(new TalkEffectNew(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, TEXT[this.textNum], true, this.canChoose));
            }
            else{
                AbstractDungeon.effectList.add(new TalkEffectNew(this.source.hb.cX + this.source.dialogX, this.source.hb.cY + this.source.dialogY, TEXT[this.textNum], false, this.canChoose));
            }
            this.used = true;
        }

        if(InputHelper.justClickedLeft){
            this.LclickStart = true;
        }
        if((this.LclickStart) && (InputHelper.justReleasedClickLeft) && (!(this.Rclick))){
            this.Lclick = true;
        }

        if ((this.Lclick)) {
            DevConsole.logger.info("done");
            switch (this.textNum){
                case 0: case 8: case 13: case 15: case 29: case 30: case 50: case 53:
                    AbstractDungeon.actionManager.addToTop(new PlotTalkAction(this.zi,this.LeftMap.get(this.textNum),true));
                    break;
                case 1: case 9: case 16: case 21: case 26: case 31: case 36: case 41: case 47:
                    AbstractDungeon.actionManager.addToTop(new PlotTalkAction(this.p,this.LeftMap.get(this.textNum),false));
                    break;
                case 2: case 3: case 4: case 5: case 6: case 7: case 10: case 11: case 12: case 14: case 17: case 18:
                case 22: case 23: case 27: case 28: case 32: case 33: case 37: case 38: case 42: case 43: case 46: case 48: case 49:
                    AbstractDungeon.actionManager.addToTop(new PlotTalkAction(this.zi,this.LeftMap.get(this.textNum),false));
                    break;

                case 19:
                    AbstractDungeon.actionManager.addToTop(new PlotTalkAction(this.zi,this.LeftMap.get(this.textNum),true));
                    ThMod.canDetBle = true;
                    break;
                case 34:
                    AbstractDungeon.actionManager.addToTop(new PlotTalkAction(this.zi,this.LeftMap.get(this.textNum),true));
                    ThMod.canScaBle = true;
                    break;
                case 45:
                    AbstractDungeon.actionManager.addToTop(new PlotTalkAction(this.zi,this.LeftMap.get(this.textNum),false));
                    ThMod.canRemBle = true;
                    break;

                case 20: case 24: case 25:
                    AbstractDungeon.actionManager.addToTop(new PlotTalkAction(this.zi,this.LeftMap.get(this.textNum),true));
                    ThMod.canDetBle = false;
                    break;
                case 35: case 39: case 40:
                    AbstractDungeon.actionManager.addToTop(new PlotTalkAction(this.zi,this.LeftMap.get(this.textNum),true));
                    ThMod.canScaBle = false;
                    break;
                case 44:
                    AbstractDungeon.actionManager.addToTop(new PlotTalkAction(this.zi,this.LeftMap.get(this.textNum),false));
                    ThMod.canRemBle = false;
                    break;

                case 52:
                    ThMod.firstAnswer = true;
                    if(ThMod.canDetBle){
                        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(new Determination(),1,true,false));
                    }
                    if(ThMod.canRemBle){
                        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(new BlessingOfScarlet(),1,true,false));
                    }
                    if(ThMod.canRemBle){
                        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(new Remission(),1,true,false));
                    }
                    try {
                        ThMod.SavePointPower();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    CardCrawlGame.music.silenceTempBgmInstantly();
                    AbstractDungeon.getCurrRoom().playBgmInstantly("夜降.mp3");
                    break;
                case 51:
                    if(ThMod.canDetBle){
                        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(new Determination(),1,true,false));
                    }
                    if(ThMod.canRemBle){
                        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(new BlessingOfScarlet(),1,true,false));
                    }
                    if(ThMod.canRemBle){
                        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(new Remission(),1,true,false));
                    }
                    try {
                        ThMod.SavePointPower();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    CardCrawlGame.music.silenceTempBgmInstantly();
                    AbstractDungeon.getCurrRoom().playBgmInstantly("夜降.mp3");
                    break;
            }
            this.isDone = true;
            this.Lclick = false;
        }

        if(InputHelper.justClickedRight){
            this.RclickStart = true;
        }
        if((this.RclickStart) && (InputHelper.justReleasedClickRight) && (!(this.Lclick))){
            this.Rclick = true;
        }

        if ((this.Rclick)) {
            DevConsole.logger.info("done");
            switch (this.textNum){
                case 0: case 8: case 13: case 15: case 29: case 30: case 50: case 53:
                    AbstractDungeon.actionManager.addToTop(new PlotTalkAction(this.zi,this.RightMap.get(this.textNum),true));
                    break;
                case 1: case 9: case 16: case 21: case 26: case 31: case 36: case 41: case 47:
                    AbstractDungeon.actionManager.addToTop(new PlotTalkAction(this.p,this.RightMap.get(this.textNum),false));
                    break;
                case 2: case 3: case 4: case 5: case 6: case 7: case 10: case 11: case 12: case 14: case 17: case 18:
                case 22: case 23: case 27: case 28: case 32: case 33: case 37: case 38: case 42: case 43: case 46: case 48: case 49:
                    AbstractDungeon.actionManager.addToTop(new PlotTalkAction(this.zi,this.RightMap.get(this.textNum),false));
                    break;

                case 19:
                    AbstractDungeon.actionManager.addToTop(new PlotTalkAction(this.zi,this.RightMap.get(this.textNum),true));
                    ThMod.canDetBle = true;
                    break;
                case 34:
                    AbstractDungeon.actionManager.addToTop(new PlotTalkAction(this.zi,this.RightMap.get(this.textNum),true));
                    ThMod.canScaBle = true;
                    break;
                case 45:
                    AbstractDungeon.actionManager.addToTop(new PlotTalkAction(this.zi,this.RightMap.get(this.textNum),false));
                    ThMod.canRemBle = true;
                    break;

                case 20: case 24: case 25:
                    AbstractDungeon.actionManager.addToTop(new PlotTalkAction(this.zi,this.RightMap.get(this.textNum),true));
                    ThMod.canDetBle = false;
                    break;
                case 35: case 39: case 40:
                    AbstractDungeon.actionManager.addToTop(new PlotTalkAction(this.zi,this.RightMap.get(this.textNum),true));
                    ThMod.canScaBle = false;
                    break;
                case 44:
                    AbstractDungeon.actionManager.addToTop(new PlotTalkAction(this.zi,this.RightMap.get(this.textNum),false));
                    ThMod.canRemBle = false;
                    break;

                case 52:
                    ThMod.firstAnswer = true;
                    if(ThMod.canDetBle){
                        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(new Determination(),1,true,false));
                    }
                    if(ThMod.canRemBle){
                        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(new BlessingOfScarlet(),1,true,false));
                    }
                    if(ThMod.canRemBle){
                        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(new Remission(),1,true,false));
                    }
                    try {
                        ThMod.SavePointPower();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    CardCrawlGame.music.silenceTempBgmInstantly();
                    AbstractDungeon.getCurrRoom().playBgmInstantly("夜降.mp3");
                    break;
                case 51:
                    if(ThMod.canDetBle){
                        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(new Determination(),1,true,false));
                    }
                    if(ThMod.canRemBle){
                        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(new BlessingOfScarlet(),1,true,false));
                    }
                    if(ThMod.canRemBle){
                        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(new Remission(),1,true,false));
                    }
                    try {
                        ThMod.SavePointPower();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    CardCrawlGame.music.silenceTempBgmInstantly();
                    AbstractDungeon.getCurrRoom().playBgmInstantly("夜降.mp3");
                    break;
            }
            this.isDone = true;
            this.Rclick = false;
        }

        if((this.Lclick) || (this.Rclick)) {
            this.duration -= Gdx.graphics.getDeltaTime();
        }
    }
}
