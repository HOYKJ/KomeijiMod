package Thmod.Monsters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.esotericsoftware.spine.Bone;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ChangeStateAction;
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
import com.megacrit.cardcrawl.vfx.NemesisFireParticle;
import com.megacrit.cardcrawl.vfx.SpeechBubble;
import com.megacrit.cardcrawl.vfx.combat.ClawEffect;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;

import java.io.IOException;
import java.util.ArrayList;

import Thmod.Actions.common.EndBattleAction;
import Thmod.Actions.common.LatterAction;
import Thmod.Cards.BlessingCards.BlessingOfScarlet;
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
import Thmod.ThMod;
import Thmod.vfx.RemiriaFireParticle;
import Thmod.vfx.StartPetalEffect;

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
    private enum combatState{
        START, NORMAL, GUNGNIR, OVER
    }
    private combatState nowState;
    private float fireTimer = 0.0F;
    private ArrayList<Bone> fires = new ArrayList<>();
    private float petalTimerTotal = 0.0F;
    private float petalTimer = 0.0F;
    private ArrayList<Bone> petals = new ArrayList<>();


   public Remiria(float x, float y){
       super(NAME, "Remiria", 999, 0.0F, -300.0F, 450.0F, 550.0F, null, x, y);
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
       loadAnimation("images/monsters/Remiria/begin/begin.atlas", "images/monsters/Remiria/begin/begin.json", 1.1F);
       AnimationState.TrackEntry e = this.state.setAnimation(0, "normal", true);
       e.setTime(e.getEndTime() * MathUtils.random());
       this.stateData.setMix("hit", "normal", 0.2F);
       this.stateData.setMix("normal", "start", 0.9F);
       //this.stateData.setMix("start", "normal", 0.5F);
       this.state.setTimeScale(1.0F);
       this.nowState = combatState.START;
   }

    public void usePreBattleAction()
    {
        CardCrawlGame.music.silenceBGM();
        AbstractDungeon.scene.fadeOutAmbiance();
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new FetterPower(AbstractDungeon.player)));
        this.nowState = combatState.START;
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
                AbstractDungeon.actionManager.addToTop(new ChangeStateAction(this, "START"));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,this,new StrengthPower(p,-6),-6));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this,this,new StrengthPower(this,6),6));
                AbstractDungeon.actionManager.addToBottom(new WaitAction(0.3F));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,this,new WeakPower(p,99,true),99));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,this,new VulnerablePower(p,99,true),99));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,this,new FrailPower(p,99,true),99));
                AbstractDungeon.actionManager.addToBottom(new WaitAction(0.3F));
                AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAction(new Torch(), 1));
                //this.img = ImageMaster.loadImage("images/monsters/Remiria/01.png");
                break;
            case 2:
                AbstractDungeon.actionManager.addToBottom(new DamageAction(p, this.damage.get(0), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this,this,new MetallicizePower(this,10),10));
                //AbstractDungeon.actionManager.addToBottom(new ChangeStateAction(this, "GUNGNIR"));
                //this.img = ImageMaster.loadImage("images/monsters/Remiria/02.png");
                break;
            case 3:
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this,this,new IntangiblePlayerPower(this,99),99));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this,this,new Breakthrough(this)));
                AbstractDungeon.actionManager.addToBottom(new ChangeStateAction(this, "GUNGNIR"));
                //this.img = ImageMaster.loadImage("images/monsters/Remiria/03.png");
                break;
            case 4:
                AbstractDungeon.actionManager.addToBottom(new ChangeStateAction(this, "N_ATTACK"));
                AbstractDungeon.actionManager.addToBottom(new WaitAction(0.5F));
                AbstractDungeon.actionManager.addToBottom(new DamageAction(p, this.damage.get(2), AbstractGameAction.AttackEffect.SLASH_HEAVY));
                AbstractDungeon.actionManager.addToBottom(new LatterAction(()->{
                    AbstractDungeon.actionManager.addToTop(new ChangeStateAction(this, "NORMAL"));
                }, 1f));
                //this.img = ImageMaster.loadImage("images/monsters/Remiria/02.png");
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
                AbstractDungeon.actionManager.addToBottom(new ChangeStateAction(this, "GUNGNIR"));
                //this.img = ImageMaster.loadImage("images/monsters/Remiria/03.png");
                break;
            case 8:
                AbstractDungeon.actionManager.addToBottom(new ChangeStateAction(this, "N_ATTACK"));
                AbstractDungeon.actionManager.addToBottom(new WaitAction(0.5F));
                AbstractDungeon.actionManager.addToBottom(new DamageAction(p, this.damage.get(3), AbstractGameAction.AttackEffect.SLASH_HEAVY));
                //this.img = ImageMaster.loadImage("images/monsters/Remiria/02.png");
                break;
            case 9:
                AbstractDungeon.actionManager.addToBottom(new ChangeStateAction(this, "C_ATTACK"));
                AbstractDungeon.actionManager.addToBottom(new WaitAction(0.5F));
                AbstractDungeon.actionManager.addToBottom(new VFXAction(new ClawEffect(this.skeleton
                        .getX() + this.fires.get(0).getWorldX(), this.skeleton
                        .getY() + this.fires.get(0).getWorldY(), Color.SCARLET, Color.ORANGE), 0.1F));
                AbstractDungeon.actionManager.addToBottom(new DamageAction(p, this.damage.get(1), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, this, new DrawReductionPower(p, 99)));
                AbstractDungeon.actionManager.addToBottom(new LatterAction(()->{
                    AbstractDungeon.actionManager.addToTop(new ChangeStateAction(this, "NORMAL"));
                }, 1f));
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
                AbstractDungeon.actionManager.addToBottom(new ChangeStateAction(this, "GUNGNIR"));
                break;
            case 13:
                AbstractDungeon.actionManager.addToBottom(new ChangeStateAction(this, "H_ATTACK"));
                AbstractDungeon.actionManager.addToBottom(new VFXAction(new WeightyImpactEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, new Color(1.0F, 0.1F, 0.1F, 0.0F))));
                AbstractDungeon.actionManager.addToBottom(new WaitAction(0.8F));
                AbstractDungeon.actionManager.addToBottom(new DamageAction(p, this.damage.get(4), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                break;
            case 14:
                AbstractDungeon.actionManager.addToBottom(new ChangeStateAction(this, "H_ATTACK"));
                AbstractDungeon.actionManager.addToBottom(new VFXAction(new WeightyImpactEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, new Color(1.0F, 0.1F, 0.1F, 0.0F))));
                AbstractDungeon.actionManager.addToBottom(new WaitAction(0.8F));
                AbstractDungeon.actionManager.addToBottom(new DamageAction(p, this.damage.get(5), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                break;
            case 15:
                AbstractDungeon.actionManager.addToBottom(new ChangeStateAction(this, "H_ATTACK"));
                AbstractDungeon.actionManager.addToBottom(new VFXAction(new WeightyImpactEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, new Color(1.0F, 0.1F, 0.1F, 0.0F))));
                AbstractDungeon.actionManager.addToBottom(new WaitAction(0.8F));
                AbstractDungeon.actionManager.addToBottom(new DamageAction(p, this.damage.get(6), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                break;
            default:
                AbstractDungeon.actionManager.addToBottom(new ChangeStateAction(this, "H_ATTACK"));
                AbstractDungeon.actionManager.addToBottom(new VFXAction(new WeightyImpactEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, new Color(1.0F, 0.1F, 0.1F, 0.0F))));
                AbstractDungeon.actionManager.addToBottom(new WaitAction(0.8F));
                AbstractDungeon.actionManager.addToBottom(new DamageAction(p, this.damage.get(7), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        }
        AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
    }

    public void damage(DamageInfo info)
    {
        super.damage(info);
        if ((info.owner != null) && (info.type != DamageInfo.DamageType.THORNS) && (info.output > 0))
        {
            if((!this.hasPower(DashPower.POWER_ID)) && (this.nowState != combatState.OVER)) {
                this.state.setAnimation(0, "hit", false);
                this.state.setTimeScale(1.0F);
                this.state.addAnimation(0, "normal", true, 0.0F);
            }
        }
    }

    public void changeState(String key)
    {
        switch (key)
        {
            case "START":
                this.state.setAnimation(0, "start", false);
                this.state.setTimeScale(1.0F);
                this.petals.add(this.skeleton.findBone("wingR4"));
                this.petals.add(this.skeleton.findBone("wingR8"));
                this.petals.add(this.skeleton.findBone("wingR6"));
                this.petals.add(this.skeleton.findBone("wingL4"));
                this.petals.add(this.skeleton.findBone("wingL8"));
                this.petals.add(this.skeleton.findBone("wingL6"));
                this.petalTimerTotal = 0.9f;
                AbstractDungeon.actionManager.addToTop(new LatterAction(()->{
                    loadAnimation("images/monsters/Remiria/normal/normal.atlas", "images/monsters/Remiria/normal/normal.json", 1.1F);
                    AnimationState.TrackEntry e = this.state.setAnimation(0, "normal", true);
                    e.setTime(e.getEndTime() * MathUtils.random());
                    this.stateData.setMix("hit", "normal", 0.2F);
                    this.state.setTimeScale(1.0F);
                    this.nowState = combatState.NORMAL;
                }, 0.75f));
                break;
            case "NORMAL":
                this.petals.clear();
                this.fires.clear();
                loadAnimation("images/monsters/Remiria/normal/normal.atlas", "images/monsters/Remiria/normal/normal.json", 1.1F);
                AnimationState.TrackEntry e = this.state.setAnimation(0, "normal", true);
                e.setTime(e.getEndTime() * MathUtils.random());
                this.stateData.setMix("hit", "normal", 0.2F);
                this.updateHitbox(0.0F, -300.0F, 450.0F, 550.0F);
                this.healthBarUpdatedEvent();
                this.nowState = combatState.NORMAL;
                break;
            case "GUNGNIR":
                loadAnimation("images/monsters/Remiria/gungnir/gungnir.atlas", "images/monsters/Remiria/gungnir/gungnir.json", 1.1F);
                AnimationState.TrackEntry e2 = this.state.setAnimation(0, "normal", true);
                e2.setTime(e2.getEndTime() * MathUtils.random());
                this.stateData.setMix("hit", "normal", 0.2F);
                this.stateData.setMix("normal", "normalAttack", 0.2F);
                this.stateData.setMix("normal", "chainAttack", 0.2F);
                this.stateData.setMix("normal", "heavyAttack", 0.2F);
                this.updateHitbox(0.0F, -300.0F, 600.0F, 550.0F);
                this.healthBarUpdatedEvent();
                this.nowState = combatState.GUNGNIR;
                this.fires.add(this.skeleton.findBone("fire"));
                this.fires.add(this.skeleton.findBone("fire2"));
                this.fires.add(this.skeleton.findBone("fire3"));
                this.fires.add(this.skeleton.findBone("fire4"));
                this.fires.add(this.skeleton.findBone("fire5"));
                this.fires.add(this.skeleton.findBone("fire6"));
                this.fires.add(this.skeleton.findBone("fire7"));
                this.fires.add(this.skeleton.findBone("fire8"));
                this.fires.add(this.skeleton.findBone("fire9"));
                this.fires.add(this.skeleton.findBone("fire10"));
                this.fires.add(this.skeleton.findBone("fire11"));
                this.fires.add(this.skeleton.findBone("fire12"));
                break;
            case "N_ATTACK":
                if(this.nowState == combatState.GUNGNIR) {
                    this.state.setAnimation(0, "normalAttack", false);
                    this.state.setTimeScale(1F);
                    this.state.addAnimation(0, "normal", true, 0.0F);
                }
                break;
            case "C_ATTACK":
                if(this.nowState == combatState.GUNGNIR) {
                    this.state.setAnimation(0, "chainAttack", false);
                    this.state.setTimeScale(1F);
                    this.state.addAnimation(0, "normal", true, 0.0F);
                }
                break;
            case "H_ATTACK":
                if(this.nowState == combatState.GUNGNIR) {
                    this.state.setAnimation(0, "heavyAttack", false);
                    this.state.setTimeScale(1F);
                    this.state.addAnimation(0, "normal", true, 0.0F);
                }
                break;
        }
    }

//    private void attackAnimation(String key){
//       switch (key){
//
//       }
//    }

    public void die()
    {
        super.die();
        if (!AbstractDungeon.getCurrRoom().cannotLose)
        {
            this.fires.clear();
            loadAnimation("images/monsters/Remiria/over/over.atlas", "images/monsters/Remiria/over/over.json", 1.0F);
            AnimationState.TrackEntry e = this.state.setAnimation(0, "hurt", true);
            e.setTime(e.getEndTime() * MathUtils.random());
            this.nowState = combatState.OVER;
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
                ThMod.defeatRemiria = true;
                try {
                    ThMod.SavePointPower();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
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

    public void overByTime(){
        loadAnimation("images/monsters/Remiria/begin/begin.atlas", "images/monsters/Remiria/begin/begin.json", 1.0F);
        AnimationState.TrackEntry e = this.state.setAnimation(0, "over", true);
        e.setTime(e.getEndTime() * MathUtils.random());
        this.nowState = combatState.OVER;
        RewardItem specialCard = new RewardItem();
        specialCard.type = RewardItem.RewardType.CARD;
        specialCard.cards.clear();
        specialCard.cards.add(new BlessingOfScarlet());
        if(ThMod.blessingOfTime < 3)
            ThMod.blessingOfTime += 1;
        AbstractDungeon.actionManager.addToBottom(new TalkAction(this, DIALOG[3], 0.5F, 2.0F));
        AbstractDungeon.getCurrRoom().rewards.clear();
        AbstractDungeon.getCurrRoom().addCardReward(specialCard);
        UnlockTracker.unlockCard("ScarletsBlessing");
        AbstractDungeon.actionManager.addToBottom(new EndBattleAction());
    }

    public void update()
    {
        super.update();
        if (!this.isDying)
        {
            if(this.nowState == combatState.GUNGNIR) {
                this.fireTimer -= Gdx.graphics.getDeltaTime();
                if (this.fireTimer < 0.0F) {
                    this.fireTimer = 0.05F;
                    for(Bone fire : this.fires) {
                        AbstractDungeon.effectList.add(new RemiriaFireParticle(this.skeleton
                                .getX() + fire.getWorldX(), this.skeleton.getY() + fire.getWorldY()));
                    }
                }
            }
            else if(this.nowState == combatState.START){
                if((this.petalTimerTotal > 0) && (this.petals.size() > 0))
                this.petalTimer -= Gdx.graphics.getDeltaTime();
                if (this.petalTimer < 0.0F) {
                    this.petalTimer = 0.01F;
                    this.petalTimerTotal -= 0.01F;
                    int i = this.petals.size() - 1;
                    int roll = MathUtils.random(i);
                    Bone petal = this.petals.get(roll);
                    AbstractDungeon.effectList.add(new StartPetalEffect(this.skeleton
                            .getX() + petal.getWorldX(), this.skeleton.getY() + petal.getWorldY()));
                }
            }
        }
    }
}
