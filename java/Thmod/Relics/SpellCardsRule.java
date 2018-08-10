package Thmod.Relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import java.io.IOException;
import java.util.ArrayList;

import Thmod.Actions.common.MoveOrbAction;
import Thmod.Actions.unique.PlayerTalkAction;
import Thmod.Cards.BlessingCards.BlessingOfTime;
import Thmod.Cards.DeriveCards.FuubiStrike;
import Thmod.Orbs.ElementOrb.AbstractElementOrb;
import Thmod.Power.PointPower;
import Thmod.Power.TenmizuPower;
import Thmod.ThMod;

import static Thmod.ThMod.blessingOfTime;


public class SpellCardsRule extends AbstractThRelic {
    public static final String ID = "SpellCardsRule";
    public static ArrayList<Integer> pointcount;
    public static int SeishiRotenNum;
    public static int Hangongnum;
    public static int Kokushinum;
    public static boolean HangongUsed;
    public static boolean selected;
    public static boolean newCards;
    public static boolean clicked;
    public static ArrayList<AbstractCard> cardsToSelect = new ArrayList<>();
    public static ArrayList<AbstractElementOrb> orbToMix = new ArrayList<>();
    private boolean playerturn;
    public static ArrayList<Boolean> torchLight = new ArrayList<>();
    public static boolean ceremonied = false;

    public SpellCardsRule()
    {
        super("SpellCardsRule",  RelicTier.STARTER, LandingSound.HEAVY);
        this.counter = 0;
        this.playerturn = false;
        selected = false;
        newCards = true;
        HangongUsed = false;
    }

    public void atPreBattle() {
        AbstractPlayer p = AbstractDungeon.player;
        if (!(pointcount.get(0) == 0))
            AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p, p, new PointPower(p, pointcount.get(0)), pointcount.get(0)));
        if (!(pointcount.get(1) == 0))
            AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p, p, new TenmizuPower(p, pointcount.get(1)), pointcount.get(1)));
        if (blessingOfTime > 0){
            if (blessingOfTime < 3) {
                boolean giveBlessing = true;
                for (AbstractCard c : AbstractDungeon.player.masterDeck.group){
                    if(c instanceof BlessingOfTime)
                        giveBlessing = false;
                }
                if (giveBlessing)
                    AbstractDungeon.player.masterDeck.group.add(new BlessingOfTime());
            }
        }

        try {
            ThMod.SavePointPower();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (AbstractDungeon.player.hasRelic("Strange Spoon"))
            AbstractDungeon.player.loseRelic("Strange Spoon");
        HangongUsed = false;
    }

    public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {
        AbstractPlayer p = AbstractDungeon.player;
        if ((targetCard.type == AbstractCard.CardType.ATTACK) && (!(targetCard instanceof FuubiStrike))) {
            if (!(p.hasPower("PointPower"))) {
                this.counter += 1;
                if (this.counter == 4) {
                    AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p, p, new PointPower(p, 1), 1));
                    this.counter = 0;
                }
            }
            else if (p.getPower("PointPower").amount < 5) {
                this.counter += 1;
                if (this.counter == 4) {
                    AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p, p, new PointPower(p, 1), 1));
                    this.counter = 0;
                }
            }
        }
        if (p.hasPower("PointPower")) {
            if(p.getPower("PointPower").amount == 1)
                this.img = ImageMaster.loadImage("images/relics/SpellCardRule_1.png");
            if(p.getPower("PointPower").amount == 2)
                this.img = ImageMaster.loadImage("images/relics/SpellCardRule_2.png");
            if(p.getPower("PointPower").amount == 3)
                this.img = ImageMaster.loadImage("images/relics/SpellCardRule_3.png");
            if(p.getPower("PointPower").amount == 4)
                this.img = ImageMaster.loadImage("images/relics/SpellCardRule_4.png");
            if(p.getPower("PointPower").amount == 5)
                this.img = ImageMaster.loadImage("images/relics/SpellCardRule_5.png");
        }
    }

//    public void onMonsterDeath(AbstractMonster m) {
//        AbstractPlayer p = AbstractDungeon.player;
//        if(!(p.hasPower("PointPower"))) {
//            AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p, p, new PointPower(p, 1), 1));
//        }
//        else if (p.getPower("PointPower").amount < 5) {
//            AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p, p, new PointPower(p, 1), 1));
//        }
//    }

    public void onVictory() {
        AbstractPlayer p = AbstractDungeon.player;

        if (p.hasPower("PointPower")) {
            if (p.getPower("PointPower").amount > 0)
                pointcount.set(0,p.getPower("PointPower").amount);
        }
        else
            pointcount.set(0,0);

        if (p.hasPower("TenmizuPower")) {
            if (p.getPower("TenmizuPower").amount > 0)
                pointcount.set(1,p.getPower("TenmizuPower").amount);
        }
        else
            pointcount.set(1,0);

        try {
            ThMod.SavePointPower();
        } catch (IOException e) {
            e.printStackTrace();
        }

        selected = false;
        newCards = true;
        this.pulse = false;
        SeishiRotenNum = 0;
        Kokushinum = 0;
    }

    protected void onRightClick() {
        AbstractPlayer p = AbstractDungeon.player;
        if ((AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT)) {
            if (!(selected)) {
                if (this.playerturn) {
                    if (p.hasPower("PointPower")) {
                        if (p.getPower("PointPower").amount > 0) {
                            if (!(clicked)) {
                                clicked = true;
                                PointPower.Start();
                                this.pulse = false;
                            } else {
                                AbstractDungeon.actionManager.addToTop(new PlayerTalkAction(p, "请不要连续点击"));
                            }
                        } else {
                            AbstractDungeon.actionManager.addToTop(new PlayerTalkAction(p, "我没有足够的P点"));
                        }
                    } else {
                        AbstractDungeon.actionManager.addToTop(new PlayerTalkAction(p, "我没有足够的P点"));
                    }
                } else {
                    AbstractDungeon.actionManager.addToTop(new PlayerTalkAction(p, "这不是我的回合"));
                }
            } else {
                AbstractDungeon.actionManager.addToTop(new PlayerTalkAction(p, "我获得过符卡了"));
            }
        }
    }

    public void atTurnStart() {
        AbstractPlayer p = AbstractDungeon.player;
        this.playerturn = true;
        clicked = false;
        newCards = true;
        cardsToSelect.clear();
        if (p.hasPower("PointPower")) {
            if (p.getPower("PointPower").amount > 1) {
                beginPulse();
                this.pulse = true;
            }
            if(p.getPower("PointPower").amount == 1)
                this.img = ImageMaster.loadImage("images/relics/SpellCardRule_1.png");
            if(p.getPower("PointPower").amount == 2)
                this.img = ImageMaster.loadImage("images/relics/SpellCardRule_2.png");
            if(p.getPower("PointPower").amount == 3)
                this.img = ImageMaster.loadImage("images/relics/SpellCardRule_3.png");
            if(p.getPower("PointPower").amount == 4)
                this.img = ImageMaster.loadImage("images/relics/SpellCardRule_4.png");
            if(p.getPower("PointPower").amount == 5)
                this.img = ImageMaster.loadImage("images/relics/SpellCardRule_5.png");
        }
        AbstractDungeon.actionManager.addToTop(new MoveOrbAction());
    }

    public void onLoseHp(int damageAmount) {
        AbstractPlayer p = AbstractDungeon.player;
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            if ((!(p.hasPower("CounterAttackPower"))) && (!(p.hasPower("DashPower"))) && (!(p.hasPower("HardnessPower")))) {
                if (p.hasPower("HeiyuPower"))
                    AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, "HeiyuPower"));
                for (int i = 0; i < AbstractDungeon.getCurrRoom().monsters.monsters.size(); i++) {
                    AbstractMonster target = AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
                    if ((!(target.isDying)) && (target.currentHealth > 0) && (!(target.isEscaping))) {
                        if (target.hasPower("MusuNoYumePower"))
                            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(target, target, "MusuNoYumePower"));
                        if (target.hasPower("MusuNoTegataPower"))
                            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(target, target, "MusuNoTegataPower"));
                    }
                }
            }
        }
    }

    public void onPlayerEndTurn() {
        selected = false;
        this.playerturn = false;
        this.pulse = false;
        SeishiRotenNum = 0;
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new SpellCardsRule();
    }

    static {
        SpellCardsRule.pointcount = new ArrayList<>();
        SeishiRotenNum = 0;
        Hangongnum = 0;
        Kokushinum = 0;
    }
}
