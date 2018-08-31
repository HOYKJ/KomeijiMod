package Thmod.Relics;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import java.util.ArrayList;

import Thmod.Actions.unique.BackInTimeAction;
import Thmod.Actions.unique.PlayerTalkAction;
import Thmod.vfx.TheWorld;
import basemod.DevConsole;

public class LinkosWocchi extends AbstractThRelic {
    public static final String ID = "LinkosWocchi";
    private AbstractPlayer p = AbstractDungeon.player;
    private int playerHP = 0;
    private ArrayList<Integer> monHP = new ArrayList<>();
    private int playerHPtobe = 0;
    private ArrayList<Integer> monHPtobe = new ArrayList<>();
    private int turns;
    private boolean playerturn;
    private boolean used;

    public LinkosWocchi()
    {
        super("LinkosWocchi",  RelicTier.SPECIAL, LandingSound.FLAT);
        this.playerturn = false;
        this.used = false;
    }

    public void atTurnStart()
    {
        this.playerHPtobe = 0;
        this.monHPtobe.clear();
        if(turns > 0){
            this.playerHPtobe = this.playerHP;
            this.monHPtobe.addAll(this.monHP);
            beginPulse();
            this.pulse = true;
        }
        this.playerHP = 0;
        this.playerHP = p.currentHealth;
        this.monHP.clear();
        for (int i = 0; i < AbstractDungeon.getCurrRoom().monsters.monsters.size(); i++) {
            AbstractMonster target = AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
            if ((target.halfDead) || ((!(target.isDying)) && (target.currentHealth > 0) && (!(target.isEscaping)) && (!(target.hasPower("Minion"))))) {
                if(target.halfDead)
                    this.monHP.add(0);
                else
                    this.monHP.add(target.currentHealth);
            }
        }
        this.playerturn = true;
        turns += 1;
    }

    protected  void onRightClick(){
        if ((AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT)) {
            if ((this.playerturn) && (playerHPtobe != 0) && (!(this.used))) {
                this.used = true;
                this.pulse = false;
                AbstractDungeon.actionManager.addToTop(new BackInTimeAction(this.playerHPtobe,this.monHPtobe));
                AbstractDungeon.actionManager.addToTop(new VFXAction(new TheWorld(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, true), 2F));
            } else {
                AbstractDungeon.actionManager.addToTop(new PlayerTalkAction(p, DESCRIPTIONS[1]));
            }
        }
    }

    public void onPlayerEndTurn() {
        this.playerturn = false;
        this.pulse = false;
    }

    public void atPreBattle() {
        this.used = false;
        this.turns = 0;
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new LinkosWocchi();
    }
}
