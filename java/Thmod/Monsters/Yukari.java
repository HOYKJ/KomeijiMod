package Thmod.Monsters;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.common.RollMoveAction;
import com.megacrit.cardcrawl.actions.common.SpawnMonsterAction;
import com.megacrit.cardcrawl.actions.unique.RemoveDebuffsAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.GenericEventDialog;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.map.MapRoomNode;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ShiftingPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.VictoryRoom;
import com.megacrit.cardcrawl.screens.DeathScreen;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import java.io.IOException;

import Thmod.Actions.unique.PlotTalkAction;
import Thmod.Cards.DeriveCards.Boundaries;
import Thmod.Power.ActivationPower;
import Thmod.Power.BoundariesPower;
import Thmod.Room.NewVictoryRoom;
import Thmod.ThMod;

public class Yukari extends AbstractMonster {
    public static final String ID = "Yukari";
    private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("Yukari");
    public static final String NAME = monsterStrings.NAME;
    public static final String[] MOVES = monsterStrings.MOVES;
    public static final String[] DIALOG = monsterStrings.DIALOG;
    private AbstractPlayer p = AbstractDungeon.player;
    private boolean firstTurn;
    private boolean attacked;
    private int damages;
    private int cardsNeedUse;

    public Yukari(float x, float y) {
        super(NAME, "Yukari", 510, -10.0F, 0.0F, 280.0F, 350.0F, "images/monsters/Yukari/Main.png", x, y);
        this.firstTurn = true;
        this.attacked = false;
        this.damages = 17;
        this.cardsNeedUse = 2;
        this.dialogX = (-100.0F * Settings.scale);
        this.dialogY = (10.0F * Settings.scale);
    }

    public void usePreBattleAction(){
        CardCrawlGame.music.silenceBGM();
        AbstractDungeon.scene.fadeOutAmbiance();
        AbstractDungeon.getCurrRoom().playBgmInstantly("前奏.mp3");
        if(ThMod.firstAnswer)
            AbstractDungeon.actionManager.addToBottom(new PlotTalkAction(this,53,false));
        else
            AbstractDungeon.actionManager.addToBottom(new PlotTalkAction(this,0,false));
//        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new ShiftingPower(this)));
    }

    protected void getMove(int num) {
        boolean hasBoundaries = false;
        boolean hasLan = false;
        if(firstTurn){
            setMove(MOVES[0], (byte) 1, Intent.UNKNOWN);
        }
        else if(lastMove((byte)6)){
            if(!(attacked)){
                setMove(MOVES[2],(byte) 3, Intent.ATTACK, this.damages, 3, true);
                attacked = true;
            }
            else {
                setMove(MOVES[1], (byte) 2, Intent.UNKNOWN);
                attacked = false;
            }
        }
        else {
            for (AbstractPower power : this.powers) {
                if (power instanceof BoundariesPower) {
                    hasBoundaries = true;
                }
            }
            for (int i = 0; i < AbstractDungeon.getCurrRoom().monsters.monsters.size(); i++) {
                AbstractMonster target = AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
                if ((!(target.isDying)) && (target.currentHealth > 0) && (!(target.isEscaping))) {
                    if (target.id.equals(Lan.ID))
                        hasLan = true;
                }
            }
            if (!(hasBoundaries)) {
                if (hasLan) {
                    setMove(MOVES[1], (byte) 2, Intent.UNKNOWN);
                }
                else {
                    setMove((byte) 6, Intent.DEFEND_DEBUFF);
                }
            }
            else {
                if(hasLan){
                    setMove((byte)5, Intent.ATTACK_BUFF,this.damages);
                }
                else
                    setMove((byte)4, Intent.BUFF);
            }
        }
    }

    public void takeTurn() {
        if (this.firstTurn) {
//            AbstractDungeon.actionManager.addToBottom(new TalkAction(this, DIALOG[0], 0.5F, 2.0F));
//            AbstractDungeon.actionManager.addToBottom(new WaitAction(0.4F));
            this.firstTurn = false;
        }
        switch (this.nextMove) {
            case 1:
                AbstractDungeon.actionManager.addToTop(new SpawnMonsterAction(new Lan(-200.0F, -10.0F), false));
                break;
            case 2:
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new BoundariesPower(this)));
                AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAction(new Boundaries(), 1));
                for (AbstractPower power : p.powers) {
                    if (power instanceof ActivationPower) {
                        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, this, power));
                    }
                }
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, this, new ActivationPower(p, this.cardsNeedUse)));
                this.cardsNeedUse += 2;
                this.img = ImageMaster.loadImage("images/monsters/Yukari/Boundaries.png");
                this.attacked = false;
                break;
            case 3:
                for (int i = 0; i < 3; i++) {
                    AbstractDungeon.actionManager.addToBottom(new DamageAction(p, new DamageInfo(this, this.damages, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
                }
                this.damages += 3;
                this.attacked = true;
                break;
            case 4:
                for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
                    if ((!m.isDying) && (!m.isEscaping)) {
                        AbstractDungeon.actionManager.addToBottom(new HealAction(m, this, 17));
                    }
                }
                AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this,this,17));
                AbstractDungeon.actionManager.addToBottom(new RemoveDebuffsAction(this));
                break;
            case 5:
                for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
                    if ((!m.isDying) && (!m.isEscaping)) {
                        AbstractDungeon.actionManager.addToBottom(new HealAction(m, this, 17));
                    }
                }
                AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this,this,17));
                AbstractDungeon.actionManager.addToBottom(new RemoveDebuffsAction(this));
                AbstractDungeon.actionManager.addToBottom(new DamageAction(p, new DamageInfo(this, this.damages, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
                this.damages += 3;
                break;
            case 6:
                AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this,this,17));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, this, new WeakPower(p, 2, true), 2));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, this, new VulnerablePower(p, 2, true), 2));
                break;
            default:
                for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
                    if ((!m.isDying) && (!m.isEscaping)) {
                        AbstractDungeon.actionManager.addToBottom(new HealAction(m, this, 18));
                    }
                    AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this,this,18));
                }
                AbstractDungeon.actionManager.addToBottom(new RemoveDebuffsAction(this));
        }
        AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
    }

    public void changeIntend(){
        boolean hasLan = false;
        for (int i = 0; i < AbstractDungeon.getCurrRoom().monsters.monsters.size(); i++) {
            AbstractMonster target = AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
            if ((!(target.isDying)) && (target.currentHealth > 0) && (!(target.isEscaping))) {
                if (target.id.equals(Lan.ID))
                    hasLan = true;
            }
        }
        if (hasLan) {
            setMove(MOVES[1], (byte) 2, Intent.UNKNOWN);
        }
        else {
            if(!(attacked)){
                setMove(MOVES[2],(byte) 3, Intent.ATTACK, this.damages, 3, true);
                attacked = true;
            }
            else {
                setMove(MOVES[1], (byte) 2, Intent.UNKNOWN);
                attacked = false;
            }
        }
    }

    public void changeImg(){
        this.img = ImageMaster.loadImage("images/monsters/Yukari/Main.png");
    }

    public void damage(DamageInfo info)
    {
        if(this.hasPower(BoundariesPower.POWER_ID)){
            info.base = 0;
        }
        super.damage(info);
    }

    public void die() {
        UnlockTracker.unlockCard("Peep");
        MapRoomNode node = new MapRoomNode(-1, 15);
        node.room = new NewVictoryRoom();
        AbstractDungeon.nextRoom = node;
        ThMod.defeatYukari = true;
        try {
            ThMod.SavePointPower();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        AbstractRoom.waitTimer = 0.1F;
//        GenericEventDialog.hide();
//        CardCrawlGame.fadeIn(1.5F);
//        AbstractDungeon.closeCurrentScreen();
        AbstractDungeon.nextRoomTransitionStart();
    }
}
