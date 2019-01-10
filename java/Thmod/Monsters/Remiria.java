package Thmod.Monsters;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.RollMoveAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BarricadePower;
import com.megacrit.cardcrawl.powers.DrawReductionPower;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import com.megacrit.cardcrawl.powers.MetallicizePower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.SpeechBubble;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;

import Thmod.Cards.DeriveCards.Torch;
import Thmod.Cards.RewardCards.MiserableFate;
import Thmod.Cards.RewardCards.RemiliaStretch;
import Thmod.Cards.RewardCards.ScarletDevil;
import Thmod.Characters.KomeijiSatori;
import Thmod.Events.RoomOfDemon;
import Thmod.Power.Breakthrough;
import Thmod.Power.DashPower;
import Thmod.Power.FetterPower;
import Thmod.Relics.SteinsOfFate;

public class Remiria extends AbstractMonster {
    public static final String ID = "Remiria";
    private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("Remiria");
    public static final String NAME = monsterStrings.NAME;
    public static final String[] MOVES = monsterStrings.MOVES;
    public static final String[] DIALOG = monsterStrings.DIALOG;
    private AbstractPlayer p = AbstractDungeon.player;
    private boolean firstTurn = true;
    public static int torchNum;
    private RewardItem specialCard = new RewardItem();


   public Remiria(float x, float y){
       super(NAME, "Remiria", 999, 25.0F, -15.0F, 400.0F, 350.0F, "images/monsters/Remiria/Main.png", x, y);
       setHp(999);
       this.dialogX = (-100.0F * Settings.scale);
       this.dialogY = (10.0F * Settings.scale);
       int lessbDmg = 0;
       this.damage.add(new DamageInfo(this, lessbDmg, DamageInfo.DamageType.NORMAL));
       this.damage.add(new DamageInfo(this, 6, DamageInfo.DamageType.NORMAL));
       this.damage.add(new DamageInfo(this, 8, DamageInfo.DamageType.NORMAL));
       this.damage.add(new DamageInfo(this, 16, DamageInfo.DamageType.NORMAL));
       this.damage.add(new DamageInfo(this, 50, DamageInfo.DamageType.HP_LOSS));
       this.damage.add(new DamageInfo(this, 60, DamageInfo.DamageType.HP_LOSS));
       this.damage.add(new DamageInfo(this, 70, DamageInfo.DamageType.HP_LOSS));
       this.damage.add(new DamageInfo(this, 999, DamageInfo.DamageType.HP_LOSS));
       this.specialCard.type = RewardItem.RewardType.CARD;
       this.specialCard.cards.clear();
       this.specialCard.cards.add(new MiserableFate());
       this.specialCard.cards.add(new ScarletDevil());
       this.specialCard.cards.add(new RemiliaStretch());
   }

    public void usePreBattleAction()
    {
        CardCrawlGame.music.silenceBGM();
        AbstractDungeon.scene.fadeOutAmbiance();
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new FetterPower(AbstractDungeon.player)));
    }

    protected void getMove(int num)
    {
        if (this.firstTurn)
        {
            setMove(MOVES[0],(byte)1, Intent.DEBUFF);
        }
        else if (lastMove((byte)1)){
            setMove((byte)2, Intent.ATTACK_BUFF,(this.damage.get(0)).base);
        }
        else if (lastMove((byte)2)){
            setMove((byte)3, Intent.BUFF);
        }
        else if (lastMove((byte)3)){
            setMove(MOVES[1],(byte)4, Intent.ATTACK,(this.damage.get(2)).base);
        }
        else if (lastMove((byte)4)){
            setMove((byte)5, Intent.ATTACK_BUFF,(this.damage.get(1)).base);
        }
        else if (lastMove((byte)5)){
            setMove((byte)6, Intent.BUFF);
        }
        else if (lastMove((byte)6)){
            setMove((byte)7, Intent.ATTACK_BUFF,(this.damage.get(1)).base);
        }
        else if (lastMove((byte)7)){
            setMove(MOVES[2],(byte)8, Intent.ATTACK,(this.damage.get(3)).base);
        }
        else if (lastMove((byte)8)){
            setMove(MOVES[3],(byte)9, Intent.ATTACK_DEBUFF,(this.damage.get(1)).base);
        }
        else if (lastMove((byte)9)){
            setMove((byte)10, Intent.BUFF);
        }
        else if (lastMove((byte)10)){
            setMove(MOVES[4],(byte)11, Intent.ATTACK,(this.damage.get(2)).base,3,true);
        }
        else if (lastMove((byte)11)){
            setMove(MOVES[5],(byte)12, Intent.ATTACK,(this.damage.get(2)).base,6,true);
        }
        else if (lastMove((byte)12)){
            setMove(MOVES[6],(byte)13, Intent.ATTACK,(this.damage.get(4)).base);
        }
        else if (lastMove((byte)13)){
            setMove(MOVES[6],(byte)14, Intent.ATTACK,(this.damage.get(5)).base);
        }
        else if (lastMove((byte)14)){
            setMove(MOVES[6],(byte)15, Intent.ATTACK,(this.damage.get(6)).base);
        }
        else {
            setMove(MOVES[6],(byte)99, Intent.ATTACK,(this.damage.get(7)).base);
        }
    }

    public void takeTurn()
    {
        if (this.firstTurn)
        {
            AbstractDungeon.actionManager.addToBottom(new TalkAction(this, DIALOG[0], 0.5F, 2.0F));
            AbstractDungeon.actionManager.addToBottom(new WaitAction(0.4F));
            AbstractDungeon.getCurrRoom().playBgmInstantly("七重奏.mp3");
            this.firstTurn = false;
        }
        switch (this.nextMove)
        {
            case 1:
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,this,new StrengthPower(p,-6),-6));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this,this,new StrengthPower(this,6),6));
                AbstractDungeon.actionManager.addToBottom(new WaitAction(0.3F));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,this,new WeakPower(p,99,true),99));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,this,new VulnerablePower(p,99,true),99));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,this,new FrailPower(p,99,true),99));
                AbstractDungeon.actionManager.addToBottom(new WaitAction(0.3F));
                AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAction(new Torch(), 1));
                this.img = ImageMaster.loadImage("images/monsters/Remiria/01.png");
                break;
            case 2:
                AbstractDungeon.actionManager.addToBottom(new DamageAction(p, this.damage.get(0), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this,this,new MetallicizePower(this,10),10));
                this.img = ImageMaster.loadImage("images/monsters/Remiria/02.png");
                break;
            case 3:
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this,this,new IntangiblePlayerPower(this,99),99));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this,this,new Breakthrough(this)));
                this.img = ImageMaster.loadImage("images/monsters/Remiria/03.png");
                break;
            case 4:
                AbstractDungeon.actionManager.addToBottom(new DamageAction(p, this.damage.get(2), AbstractGameAction.AttackEffect.SLASH_HEAVY));
                this.img = ImageMaster.loadImage("images/monsters/Remiria/02.png");
                break;
            case 5:
                AbstractDungeon.actionManager.addToBottom(new DamageAction(p, this.damage.get(1), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this,this,new DashPower(this,1),1));
                break;
            case 6:
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this,this,new BarricadePower(this)));
                break;
            case 7:
                AbstractDungeon.actionManager.addToBottom(new DamageAction(p, this.damage.get(1), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this,this,new Breakthrough(this)));
                this.img = ImageMaster.loadImage("images/monsters/Remiria/03.png");
                break;
            case 8:
                AbstractDungeon.actionManager.addToBottom(new DamageAction(p, this.damage.get(3), AbstractGameAction.AttackEffect.SLASH_HEAVY));
                this.img = ImageMaster.loadImage("images/monsters/Remiria/02.png");
                break;
            case 9:
                AbstractDungeon.actionManager.addToBottom(new DamageAction(p, this.damage.get(1), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, this, new DrawReductionPower(p, 99)));
                break;
            case 10:
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this,this,new DashPower(this,2),2));
                break;
            case 11:
                AbstractDungeon.actionManager.addToBottom(new DamageAction(p, this.damage.get(2), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
                AbstractDungeon.actionManager.addToBottom(new DamageAction(p, this.damage.get(2), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
                AbstractDungeon.actionManager.addToBottom(new DamageAction(p, this.damage.get(2), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                break;
            case 12:
                AbstractDungeon.actionManager.addToBottom(new DamageAction(p, this.damage.get(2), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
                AbstractDungeon.actionManager.addToBottom(new DamageAction(p, this.damage.get(2), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
                AbstractDungeon.actionManager.addToBottom(new DamageAction(p, this.damage.get(2), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
                AbstractDungeon.actionManager.addToBottom(new DamageAction(p, this.damage.get(2), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                AbstractDungeon.actionManager.addToBottom(new DamageAction(p, this.damage.get(2), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                AbstractDungeon.actionManager.addToBottom(new DamageAction(p, this.damage.get(2), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                break;
            case 13:
                AbstractDungeon.actionManager.addToBottom(new VFXAction(new WeightyImpactEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, new Color(1.0F, 0.1F, 0.1F, 0.0F))));
                AbstractDungeon.actionManager.addToBottom(new DamageAction(p, this.damage.get(4), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                break;
            case 14:
                AbstractDungeon.actionManager.addToBottom(new VFXAction(new WeightyImpactEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, new Color(1.0F, 0.1F, 0.1F, 0.0F))));
                AbstractDungeon.actionManager.addToBottom(new DamageAction(p, this.damage.get(5), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                break;
            case 15:
                AbstractDungeon.actionManager.addToBottom(new VFXAction(new WeightyImpactEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, new Color(1.0F, 0.1F, 0.1F, 0.0F))));
                AbstractDungeon.actionManager.addToBottom(new DamageAction(p, this.damage.get(6), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                break;
            default:
                AbstractDungeon.actionManager.addToBottom(new VFXAction(new WeightyImpactEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, new Color(1.0F, 0.1F, 0.1F, 0.0F))));
                AbstractDungeon.actionManager.addToBottom(new DamageAction(p, this.damage.get(7), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        }
        AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
    }

    public void damage(DamageInfo info)
    {
        super.damage(info);

    }

    public void die()
    {
        super.die();
        if (!AbstractDungeon.getCurrRoom().cannotLose)
        {
            RoomOfDemon.torchDone = false;
            RoomOfDemon.torchNum = 99;
            AbstractDungeon.getCurrRoom().rewards.clear();
            AbstractDungeon.getCurrRoom().addRelicToRewards(new SteinsOfFate());
            if (!(AbstractDungeon.player.hasRelic("NeowsBlessing"))) {
//                if (AbstractDungeon.player instanceof KomeijiSatori) {
                    if (torchNum <= 4) {
                        AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX, this.hb.cY + this.dialogY, 2.5F, DIALOG[2], false));
                        AbstractDungeon.getCurrRoom().addCardToRewards();
                        if (torchNum <= 3) {
                            AbstractDungeon.getCurrRoom().addGoldToRewards(100);
                        }
                        if (torchNum <= 2) {
                            AbstractDungeon.getCurrRoom().addRelicToRewards(AbstractRelic.RelicTier.RARE);
                        }
                        if (torchNum <= 1) {
                            AbstractDungeon.getCurrRoom().addGoldToRewards(300);
                        }
                        if (torchNum == 0) {
                            AbstractDungeon.getCurrRoom().addCardReward(this.specialCard);
                        }
                    }
//                }
            }
            else
                AbstractDungeon.effectList.add(new SpeechBubble(this.hb.cX + this.dialogX, this.hb.cY + this.dialogY, 2.5F, DIALOG[1], false));
            UnlockTracker.unlockCard("Scarlet");
            useFastShakeAnimation(5.0F);
            CardCrawlGame.screenShake.rumble(4.0F);
            this.deathTimer += 1.5F;
            onBossVictoryLogic();
        }
    }
}
