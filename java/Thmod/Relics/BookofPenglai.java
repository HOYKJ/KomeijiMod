package Thmod.Relics;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import Thmod.Actions.unique.ChooseAction;
import Thmod.Actions.unique.PlayerTalkAction;
import Thmod.Cards.DeriveCards.EasterEgg.CheckPenglai;
import Thmod.ThMod;

public class BookofPenglai extends AbstractThRelic {
    public static final String ID = "BookofPenglai";
    private boolean playerturn;
    private boolean clicked;

    public BookofPenglai()
    {
        super("BookofPenglai",  RelicTier.SPECIAL, LandingSound.FLAT);
        this.playerturn = false;
    }

    protected  void onRightClick(){
        AbstractPlayer p = AbstractDungeon.player;
        if ((AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT)) {
            if (this.playerturn) {
                if (!(this.clicked)) {
                    this.clicked = true;
                    final ChooseAction choice = new ChooseAction(new CheckPenglai(), null, this.DESCRIPTIONS[1], true, 1);
                    choice.add(this.DESCRIPTIONS[2], this.DESCRIPTIONS[3], () -> {
                        if ((!ThMod.Hexaghost) && (!ThMod.SlimeBoss) && (!ThMod.TheGuardian) && (!ThMod.BronzeAutomaton) && (!ThMod.Champ) && (!ThMod.TheCollector) && (!ThMod.AwakenedOne) && (!ThMod.TimeEater))
                            AbstractDungeon.actionManager.addToTop(new PlayerTalkAction(AbstractDungeon.player, DESCRIPTIONS[22]));
                        else
                            AbstractDungeon.actionManager.addToTop(new PlayerTalkAction(AbstractDungeon.player, DESCRIPTIONS[23]));
                        AbstractDungeon.actionManager.addToTop(new PlayerTalkAction(AbstractDungeon.player, DESCRIPTIONS[24]));
                        AbstractDungeon.actionManager.addToTop(new PlayerTalkAction(AbstractDungeon.player, DESCRIPTIONS[25]));
                    });
                    if (ThMod.Hexaghost)
                        choice.add(this.DESCRIPTIONS[6], this.DESCRIPTIONS[7], () -> {
                            AbstractDungeon.actionManager.addToTop(new PlayerTalkAction(AbstractDungeon.player, DESCRIPTIONS[26]));
                        });
                    if (ThMod.SlimeBoss)
                        choice.add(this.DESCRIPTIONS[8], this.DESCRIPTIONS[9], () -> {
                            AbstractDungeon.actionManager.addToTop(new PlayerTalkAction(AbstractDungeon.player, DESCRIPTIONS[27]));
                        });
                    if (ThMod.TheGuardian)
                        choice.add(this.DESCRIPTIONS[10], this.DESCRIPTIONS[11], () -> {
                            AbstractDungeon.actionManager.addToTop(new PlayerTalkAction(AbstractDungeon.player, DESCRIPTIONS[28]));
                        });
                    if (ThMod.BronzeAutomaton)
                        choice.add(this.DESCRIPTIONS[16], this.DESCRIPTIONS[17], () -> {
                            AbstractDungeon.actionManager.addToTop(new PlayerTalkAction(AbstractDungeon.player, DESCRIPTIONS[29]));
                        });
                    if (ThMod.Champ)
                        choice.add(this.DESCRIPTIONS[18], this.DESCRIPTIONS[19], () -> {
                            AbstractDungeon.actionManager.addToTop(new PlayerTalkAction(AbstractDungeon.player, DESCRIPTIONS[30]));
                        });
                    if (ThMod.TheCollector)
                        choice.add(this.DESCRIPTIONS[12], this.DESCRIPTIONS[13], () -> {
                            AbstractDungeon.actionManager.addToTop(new PlayerTalkAction(AbstractDungeon.player, DESCRIPTIONS[31]));
                        });
                    if (ThMod.AwakenedOne)
                        choice.add(this.DESCRIPTIONS[4], this.DESCRIPTIONS[5], () -> {
                            AbstractDungeon.actionManager.addToTop(new PlayerTalkAction(AbstractDungeon.player, DESCRIPTIONS[32]));
                        });
                    if (ThMod.TimeEater)
                        choice.add(this.DESCRIPTIONS[14], this.DESCRIPTIONS[15], () -> {
                            AbstractDungeon.actionManager.addToTop(new PlayerTalkAction(AbstractDungeon.player, DESCRIPTIONS[33]));
                        });
                    if ((ThMod.Hexaghost) && (ThMod.SlimeBoss) && (ThMod.TheGuardian) && (ThMod.BronzeAutomaton) && (ThMod.Champ) && (ThMod.TheCollector) && (ThMod.AwakenedOne) && (ThMod.TimeEater)) {
                        choice.add(this.DESCRIPTIONS[20], this.DESCRIPTIONS[21], () -> {
                            AbstractDungeon.actionManager.addToTop(new PlayerTalkAction(AbstractDungeon.player, DESCRIPTIONS[34]));
                            AbstractDungeon.actionManager.addToTop(new PlayerTalkAction(AbstractDungeon.player, DESCRIPTIONS[35]));
                        });
                        UnlockTracker.unlockCard("CheckPenglai");
                        if (ThMod.blessingOfDetermination == 2) {
                            ThMod.blessingOfDetermination += 1;
                            this.description = DESCRIPTIONS[36];
                            this.updateDescription(AbstractDungeon.player.chosenClass);
                        }
                    }
                    AbstractDungeon.actionManager.addToBottom(choice);
                }
            }
        }
    }

    public void atTurnStart() {
        this.playerturn = true;
        this.clicked = false;
    }

    public void onPlayerEndTurn() {
        this.playerturn = false;
    }

    public void onMonsterDeath(AbstractMonster m) {
        if(m.id.equals("Hexaghost"))
            ThMod.Hexaghost = true;
        if(m.id.equals("SlimeBoss"))
            ThMod.SlimeBoss = true;
        if(m.id.equals("TheGuardian"))
            ThMod.TheGuardian = true;
        if(m.id.equals("BronzeAutomaton"))
            ThMod.BronzeAutomaton = true;
        if(m.id.equals("Champ"))
            ThMod.Champ = true;
        if(m.id.equals("TheCollector"))
            ThMod.TheCollector = true;
        if(m.id.equals("AwakenedOne"))
            ThMod.AwakenedOne = true;
        if(m.id.equals("TimeEater"))
            ThMod.TimeEater = true;
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new BookofPenglai();
    }
}
