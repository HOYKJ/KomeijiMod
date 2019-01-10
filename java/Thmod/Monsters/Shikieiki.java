package Thmod.Monsters;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.RollMoveAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BarricadePower;
import com.megacrit.cardcrawl.rooms.MonsterRoom;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import Thmod.Actions.unique.HPlossAction;
import Thmod.Cards.BlessingCards.Remission;
import Thmod.Characters.KomeijiSatori;
import Thmod.Power.AtonePower;
import Thmod.Power.FinalJudgement;
import Thmod.Power.Judgement;
import Thmod.Relics.JyouHari;
import Thmod.ThMod;
import basemod.DevConsole;

public class Shikieiki extends AbstractMonster {
    public static final String ID = "Shikieiki";
    private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("Shikieiki");
    public static final String NAME = monsterStrings.NAME;
    public static final String[] MOVES = monsterStrings.MOVES;
    public static final String[] DIALOG = monsterStrings.DIALOG;
    private AbstractPlayer p = AbstractDungeon.player;
    private boolean firstTurn = true;
    private int turns = 1;
    private double nums1 = 0.4;
    private double nums2 = 1.0;
    private int damage1;
    private int damage2;
    private int cursesNum = 0;
    private double loseHealth = 0;

    public Shikieiki(float x, float y) {
        super(NAME, "Shikieiki", 345, 25.0F, -15.0F, 350.0F, 350.0F, "images/monsters/Shikieiki/Main.png", x, y);
    }

    public void usePreBattleAction() {
        CardCrawlGame.music.silenceBGM();
        AbstractDungeon.scene.fadeOutAmbiance();
        AbstractDungeon.getCurrRoom().playBgmInstantly("审判.mp3");
        UnlockTracker.unlockCard("RakuenSaibancyou");
    }

    protected void getMove(int num) {
        DevConsole.logger.info("turns" + this.turns);
        if (this.firstTurn) {
            setMove(MOVES[0],(byte)1, Intent.DEBUFF);
            for(AbstractCard c : AbstractDungeon.player.masterDeck.group){
                if(c.type == AbstractCard.CardType.CURSE)
                    this.cursesNum += 1;
            }
        }
        else if (lastMove((byte)1)){
            this.damage1 = ((int)(this.loseHealth * this.nums1) + this.cursesNum);
            setMove(MOVES[1],(byte)2, Intent.ATTACK,this.damage1);
        }
        else if ((lastMove((byte)2)) && (this.turns != 10)){
            this.damage2 = ((int)((p.getPower("Judgement").amount) * this.nums2) + this.cursesNum);
            if(this.damage2 < 0)
                this.damage2 = 0;
            setMove(MOVES[2],(byte)3, Intent.ATTACK,this.damage2);
            DevConsole.logger.info("damage2" + this.damage2);
        }
        else if ((lastMove((byte)3)) && (this.turns != 10)){
            setMove((byte)4, Intent.BUFF);
        }
        else if((lastMove((byte)4)) && (this.turns != 10)){
            this.damage1 = ((int)(this.loseHealth * this.nums1) + this.cursesNum);
            setMove(MOVES[1],(byte)2, Intent.ATTACK,this.damage1);
            DevConsole.logger.info("nums1  " + this.nums1);
            DevConsole.logger.info("damage1  " + this.damage1);
        }
        else if(this.turns == 10){
            setMove(MOVES[3],(byte)5, Intent.DEBUFF);
        }
        else if((lastMove((byte)5)) || (lastMove((byte)6))){
            setMove((byte)6, Intent.ATTACK,(6 + this.cursesNum));
        }
        else{
            setMove(MOVES[4],(byte)2, Intent.ATTACK,999);
        }
    }

    public void takeTurn() {
        if (this.firstTurn) {
            AbstractDungeon.actionManager.addToBottom(new TalkAction(this, DIALOG[0], 0.5F, 2.0F));
            AbstractDungeon.actionManager.addToBottom(new WaitAction(0.4F));
            this.firstTurn = false;
        }
        switch (this.nextMove) {
            case 1:
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new Judgement(p,0),0));
                break;
            case 2:
                if (this.damage1 < 10)
                    AbstractDungeon.actionManager.addToBottom(new DamageAction(p, new DamageInfo(this, this.damage1, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
                else
                    AbstractDungeon.actionManager.addToBottom(new DamageAction(p, new DamageInfo(this, this.damage1, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                if ((this.turns == 8) && (this.loseHealth == 0))
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new BarricadePower(p)));
                this.nums1 -= 0.15;
                break;
            case 3:
                if (this.damage2 < 10)
                    AbstractDungeon.actionManager.addToBottom(new DamageAction(p, new DamageInfo(this, this.damage2, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
                else
                    AbstractDungeon.actionManager.addToBottom(new DamageAction(p, new DamageInfo(this, this.damage2, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                if (this.turns == 3) {
                    for (AbstractCard c : p.hand.group) {
                        if (c.type == AbstractCard.CardType.POWER) {
                            AbstractDungeon.actionManager.addToTop(new ExhaustSpecificCardAction(c, AbstractDungeon.player.hand));
                        }
                    }
                    for (AbstractCard c : p.discardPile.group) {
                        if (c.type == AbstractCard.CardType.POWER) {
                            AbstractDungeon.actionManager.addToTop(new ExhaustSpecificCardAction(c, AbstractDungeon.player.discardPile));
                        }
                    }
                    for (AbstractCard c : p.drawPile.group) {
                        if (c.type == AbstractCard.CardType.POWER) {
                            AbstractDungeon.actionManager.addToTop(new ExhaustSpecificCardAction(c, AbstractDungeon.player.drawPile));
                        }
                    }
                }
                this.nums2 += 0.25;
                break;
            case 4:
                if (this.turns == 4)
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new AtonePower(p, true)));
                else
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new AtonePower(p, false)));
                AbstractDungeon.actionManager.addToBottom(new HealAction(this, this, 30));
                break;
            case 5:
                for (AbstractCard c : p.hand.group) {
                    AbstractDungeon.actionManager.addToBottom(new ExhaustSpecificCardAction(c, AbstractDungeon.player.hand));
                }
                for (AbstractCard c : p.discardPile.group) {
                    AbstractDungeon.actionManager.addToBottom(new ExhaustSpecificCardAction(c, AbstractDungeon.player.discardPile));
                }
                for (AbstractCard c : p.drawPile.group) {
                    AbstractDungeon.actionManager.addToBottom(new ExhaustSpecificCardAction(c, AbstractDungeon.player.drawPile));
                }
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new FinalJudgement(p)));
                break;
            case 6:
                if (this.cursesNum < 4)
                    AbstractDungeon.actionManager.addToBottom(new DamageAction(p, new DamageInfo(this, (2 + this.cursesNum), DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
                else
                    AbstractDungeon.actionManager.addToBottom(new DamageAction(p, new DamageInfo(this, (2 + this.cursesNum), DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                if(AbstractDungeon.player instanceof KomeijiSatori) {
                    if ((this.loseHealth == 0) && (ThMod.blessingOfRemission < 2)) {
                        if (this.currentHealth > 70)
                            AbstractDungeon.actionManager.addToBottom(new HPlossAction(this, 70));
                        else
                            this.die();
                    }
                }
                break;
            default:
                AbstractDungeon.actionManager.addToBottom(new DamageAction(p, new DamageInfo(this, 999, DamageInfo.DamageType.HP_LOSS), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                break;
        }
        this.turns += 1;
        AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
    }

    public void damage(DamageInfo info)
    {
        super.damage(info);
        if(info.output > 0)
            this.loseHealth += info.output;
        DevConsole.logger.info("loseHealth" + this.loseHealth);
    }

    public void die()
    {
        super.die();
        if (!AbstractDungeon.getCurrRoom().cannotLose)
        {
            AbstractDungeon.getCurrRoom().rewards.clear();
            AbstractDungeon.getCurrRoom().addRelicToRewards(new JyouHari());
            if(this.loseHealth == 0){
                if(AbstractDungeon.player instanceof KomeijiSatori) {
                    AbstractDungeon.player.masterDeck.group.add(new Remission());
                    UnlockTracker.unlockCard("Innocent");
                    if(ThMod.blessingOfRemission == 1)
                        ThMod.blessingOfRemission += 1;
                }
            }
            //AbstractDungeon.currMapNode.room = new MonsterRoom();
            useFastShakeAnimation(5.0F);
            CardCrawlGame.screenShake.rumble(4.0F);
            this.deathTimer += 1.5F;
            onBossVictoryLogic();
        }
    }
}
