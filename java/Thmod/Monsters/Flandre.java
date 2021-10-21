package Thmod.Monsters;

import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.ClearCardQueueAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.RollMoveAction;
import com.megacrit.cardcrawl.actions.common.SetMoveAction;
import com.megacrit.cardcrawl.actions.common.SpawnMonsterAction;
import com.megacrit.cardcrawl.actions.unique.CanLoseAction;
import com.megacrit.cardcrawl.actions.unique.RemoveDebuffsAction;
import com.megacrit.cardcrawl.actions.utility.HideHealthBarAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.ThornsPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.util.HashMap;

import Thmod.Actions.Remiria.ApplyKagomeAction;
import Thmod.Actions.common.LatterAction;
import Thmod.Relics.remiria.ThirstyCrossSpe;
import Thmod.ThMod;

public class Flandre extends AbstractMonster {
    public static final String ID = "Flandre";
    private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("Flandre");
    public static final String NAME = monsterStrings.NAME;
    public static final String[] MOVES = monsterStrings.MOVES;
    public static final String[] DIALOG = monsterStrings.DIALOG;
    private int turns;
    private int flanCounter;
    private boolean canFinal;
    public boolean first;

    public Flandre(float x, float y) {
        this(x, y, 0);
        this.first = true;
    }

    public Flandre(float x, float y, int turns) {
        super(NAME, "Flandre", 495, 0.0F, -40.0F, 350.0F, 400.0F, "images/monsters/Flandre/Main.png", x, y);
        loadAnimation("images/monsters/Flandre/Flandre.atlas", "images/monsters/Flandre/Flandre.json", 3.0F);
        AnimationState.TrackEntry e = this.state.setAnimation(0, "newAnimation", true);
        e.setTime(e.getEndTime() * MathUtils.random());
        this.turns = turns;
        this.damage.add(new DamageInfo(this, 18));
        this.damage.add(new DamageInfo(this, 12));
        this.damage.add(new DamageInfo(this, 2));
        this.damage.add(new DamageInfo(this, 6));
        this.damage.add(new DamageInfo(this, 20));
        this.damage.add(new DamageInfo(this, 30));
        this.damage.add(new DamageInfo(this, 1));
        this.flanCounter = 0;
        this.canFinal = false;
        this.first = false;
    }

    public void usePreBattleAction()
    {
        CardCrawlGame.music.silenceBGM();
        AbstractDungeon.scene.fadeOutAmbiance();
        this.turns = 0;
        AbstractDungeon.getCurrRoom().cannotLose = true;
    }

    protected void getMove(int num) {
        switch (this.turns){
            case 0:
                setMove(MOVES[0], (byte) 1, Intent.DEFEND_DEBUFF);
                break;
            case 1:
                setMove((byte) 2, Intent.ATTACK, this.damage.get(0).base);
                break;
            case 2:
                setMove(MOVES[1], (byte) 3, Intent.ATTACK_DEBUFF, this.damage.get(1).base);
                break;
            case 3:
                setMove(MOVES[2], (byte) 4, Intent.STRONG_DEBUFF);
                break;
            case 4:
                setMove(MOVES[3], (byte) 5, Intent.ATTACK_DEBUFF, this.damage.get(2).base, 6, true);
                break;
            case 5:
                setMove(MOVES[4], (byte) 6, Intent.MAGIC);
                break;
            case 10: case 11: case 12: case 13:
                if(AbstractDungeon.aiRng.randomBoolean()){
                    setMove(MOVES[5], (byte) 7, Intent.ATTACK_DEFEND, this.damage.get(3).base);
                }
                else {
                    setMove(MOVES[6], (byte) 8, Intent.ATTACK_BUFF, this.damage.get(3).base);
                }
                break;
            case 14:
                setMove(MOVES[10], (byte) 11, Intent.ATTACK, this.damage.get(6).base, 12, true);
                break;
            case 15:
                setMove(MOVES[11], (byte) 12, Intent.MAGIC);
                break;
            default:
                setMove(MOVES[10], (byte) 66, Intent.ATTACK, this.damage.get(6).base, 66, true);
        }
    }

    public void takeTurn() {
        switch (this.nextMove){
            case 1:
                if(this.first) {
                    this.first = false;
                    AbstractDungeon.actionManager.addToBottom(new TalkAction(this, DIALOG[0], 0.5F, 2.0F));
                    AbstractDungeon.actionManager.addToBottom(new WaitAction(0.4F));
                    AbstractDungeon.getCurrRoom().playBgmInstantly("unowen.mp3");
                }
                AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this, this, 18));
                AbstractDungeon.actionManager.addToBottom(new ApplyKagomeAction(this));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new StrengthPower(this, 3), 3));
                break;
            case 2:
                AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, this.damage.get(0), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                break;
            case 3:
                AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, this.damage.get(1), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, this,
                        new WeakPower(AbstractDungeon.player, 3, true), 3));
                break;
            case 4:
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, this,
                        new FrailPower(AbstractDungeon.player, 3, true), 3));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, this,
                        new VulnerablePower(AbstractDungeon.player, 3, true), 3));
                AbstractDungeon.actionManager.addToBottom(new HealAction(this, this, 66));
                break;
            case 5:
                for(int i = 0; i < 3; i++){
                    AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, this.damage.get(2), AbstractGameAction.AttackEffect.SLASH_HEAVY));
                    AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, this.damage.get(2), AbstractGameAction.AttackEffect.FIRE));
                }
                AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAction(new Burn(), 3));
                break;
            case 6:
                if(this.first) {
                    this.first = false;
                    AbstractDungeon.getCurrRoom().playBgmInstantly("unowen.mp3");
                }
                this.halfDead = false;
                this.turns = 10;
                this.die();
                AbstractDungeon.actionManager.addToBottom(new HideHealthBarAction(this));
                HashMap<AbstractPower, Integer> powerMap = new HashMap<>();
                for(AbstractPower power : this.powers){
                    int num = AbstractDungeon.monsterRng.random(0, 3);
                    powerMap.put(power, num);
                }
                for(int i = 0; i < 4; i++){
                    Flandre flan = new Flandre((-650f + i * 300f) * Settings.scale, 0f, this.turns);
                    flan.divide();
                    flan.updateHitbox(20.0F, 0.0F, 250.0F, 300.0F);
                    for(AbstractPower power : powerMap.keySet()){
                        if(powerMap.get(power) == i){
                            flan.powers.add(power);
                            power.owner = flan;
                        }
                    }
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(flan, flan, new StrengthPower(flan, this.currentHealth / 100 + 1), this.currentHealth / 100 + 1));
                    flan.canFinal = true;
                    AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(flan, false));
                }
                break;
            case 7:
                AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, this.damage.get(3), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
                for(AbstractMonster target : AbstractDungeon.getCurrRoom().monsters.monsters){
                    if(!target.isDying){
                        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(target, this, this.damage.get(3).base));
                    }
                }
                if(this.turns >= 13){
                    int counter = this.flanCounter;
                    Flandre flan = new Flandre(-100f, 0f, this.turns);
                    int sumHp = 195;
                    int total = 0;
                    for(AbstractMonster target : AbstractDungeon.getCurrRoom().monsters.monsters){
                        if(!target.isDying){
                            total ++;
                        }
                    }
                    total = AbstractDungeon.monsterRng.random(1, total);
                    for(AbstractMonster target : AbstractDungeon.getCurrRoom().monsters.monsters){
                        if(!target.isDying){
                            total --;
                            if(total == 0){
                                flan.powers.addAll(target.powers);
                                for(AbstractPower power : flan.powers){
                                    power.owner = flan;
                                }
                            }
                        }
                    }
                    for(AbstractMonster target : AbstractDungeon.getCurrRoom().monsters.monsters){
                        if(!target.isDying){
                            if(target instanceof Flandre){
                                ((Flandre) target).setCounter(-1);
                                ((Flandre) target).canFinal = false;
                            }
                            target.hideHealthBar();
                            target.die();
                            sumHp += target.currentHealth / 2;
                        }
                    }
                    flan.setCounter(counter);
                    flan.addHp(sumHp);
                    AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(flan, false));
                    AbstractDungeon.actionManager.addToBottom(new SetMoveAction(flan, MOVES[7], (byte)9, Intent.ATTACK_BUFF, this.damage.get(4).base, counter + 1, true));
                    AbstractDungeon.actionManager.addToBottom(new CanLoseAction());
                }
                break;
            case 8:
                AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, this.damage.get(3), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new ThornsPower(this, this.damage.get(3).base), this.damage.get(3).base));
                if(this.turns >= 13){
                    int counter = this.flanCounter;
                    Flandre flan = new Flandre(-100f, 0f, this.turns);
                    int sumHp = 195;
                    int total = 0;
                    for(AbstractMonster target : AbstractDungeon.getCurrRoom().monsters.monsters){
                        if(!target.isDying){
                            total ++;
                        }
                    }
                    total = AbstractDungeon.monsterRng.random(1, total);
                    for(AbstractMonster target : AbstractDungeon.getCurrRoom().monsters.monsters){
                        if(!target.isDying){
                            total --;
                            if(total == 0){
                                flan.powers.addAll(target.powers);
                                for(AbstractPower power : flan.powers){
                                    power.owner = flan;
                                }
                            }
                        }
                    }
                    for(AbstractMonster target : AbstractDungeon.getCurrRoom().monsters.monsters){
                        if(!target.isDying){
                            if(target instanceof Flandre){
                                ((Flandre) target).setCounter(-1);
                                ((Flandre) target).canFinal = false;
                            }
                            target.hideHealthBar();
                            target.die();
                            sumHp += target.currentHealth / 2;
                        }
                    }
                    flan.setCounter(counter);
                    flan.addHp(sumHp);
                    AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(flan, false));
                    AbstractDungeon.actionManager.addToBottom(new SetMoveAction(flan, MOVES[7], (byte)9, Intent.ATTACK_BUFF, this.damage.get(4).base, counter + 1, true));
                    AbstractDungeon.actionManager.addToBottom(new CanLoseAction());
                }
                break;
            case 9:
                for (int i = 0; i < this.flanCounter + 1; i++){
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new StrengthPower(this, 1), 1));
                    AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, this.damage.get(4), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                }
                break;
            case 10:
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new StrengthPower(this, 1), 1));
                AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, this.damage.get(5), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                break;
            case 11:
                for (int i = 0; i < 12; i++){
                    AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, this.damage.get(6), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                }
                break;
            case 12:
                AbstractDungeon.actionManager.addToBottom(new RemoveDebuffsAction(this));
                AbstractDungeon.actionManager.addToBottom(new HealAction(this, this, this.maxHealth));
                this.turns = -1;
                break;
            case 16:
                this.halfDead = false;
                this.die();
                Flandre flan = new Flandre(-100f, 0f, 13);
                flan.addHp(195);
                AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(flan, false));
                if(AbstractDungeon.player.hasRelic(ThirstyCrossSpe.ID)) {
                    AbstractDungeon.actionManager.addToBottom(new SetMoveAction(flan, MOVES[8], (byte) 10, Intent.ATTACK_BUFF, this.damage.get(5).base));
                }
                else {
                    AbstractDungeon.actionManager.addToBottom(new SetMoveAction(flan, MOVES[9], (byte) 10, Intent.ATTACK_BUFF, this.damage.get(5).base));
                }
                AbstractDungeon.actionManager.addToBottom(new LatterAction(()->{
                    flan.createIntent();
                    flan.applyPowers();
                }, 0f));
                AbstractDungeon.actionManager.addToBottom(new CanLoseAction());
                AbstractDungeon.actionManager.addToTop(new ClearCardQueueAction());
                break;
            default:
                for (int i = 0; i < 66; i++){
                    AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, this.damage.get(6), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                }
                break;
        }
        this.turns ++;
        AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
    }

    private void divide(){
        this.setHp(150);
        this.flanCounter = 4;
    }

    private void reduceCounter(){
        this.flanCounter --;
    }

    public void setCounter(int num){
        this.flanCounter = num;
    }

    private void addHp(int num){
        this.currentHealth = num;
        this.healthBarUpdatedEvent();
    }

    @Override
    public void damage(DamageInfo info) {
        super.damage(info);
        if ((AbstractDungeon.getCurrRoom().cannotLose) && (!this.canFinal)) {
            if ((this.currentHealth <= 0) && (!this.halfDead)) {

                this.halfDead = true;

                for (AbstractPower p : this.powers) {
                    p.onDeath();
                }
                for (AbstractRelic r : AbstractDungeon.player.relics) {
                    r.onMonsterDeath(this);
                }
                AbstractDungeon.actionManager.addToTop(new ClearCardQueueAction());
                setMove(MOVES[4], (byte) 6, Intent.MAGIC);
                createIntent();
                applyPowers();
            }
        }
    }

    public void die()
    {
        if ((!AbstractDungeon.getCurrRoom().cannotLose)) {
            super.die();
            useFastShakeAnimation(5.0F);
        }
        else if((turns > 9) && (this.flanCounter > 0)){
            ThMod.logger.info("Flan Counter : " + this.flanCounter);
            for(AbstractMonster target : AbstractDungeon.getCurrRoom().monsters.monsters){
                if(target instanceof Flandre){
                    ((Flandre) target).reduceCounter();
                }
            }
            if(this.flanCounter == 0) {
                ThMod.logger.info("Flan Counter Is 0");
                this.halfDead = true;

                for (AbstractPower p : this.powers) {
                    p.onDeath();
                }
                for (AbstractRelic r : AbstractDungeon.player.relics) {
                    r.onMonsterDeath(this);
                }
                AbstractDungeon.actionManager.addToTop(new ClearCardQueueAction());
                setMove((byte) 16, Intent.UNKNOWN);
                createIntent();
                applyPowers();
            }
            else {
                super.die();
            }
        }
        else if(turns > 9){
            super.die();
        }
    }
}
