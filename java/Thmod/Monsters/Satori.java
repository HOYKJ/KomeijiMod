package Thmod.Monsters;

import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.EscapeAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.common.RollMoveAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

import Thmod.Actions.common.LatterAction;
import Thmod.Actions.unique.ChooseAndObtainAction;
import Thmod.Cards.ScarletCard.rewardCards.BloodyCatastrophe;
import Thmod.Cards.ScarletCard.rewardCards.BloodyLaserofSeventeenArticles;
import Thmod.Cards.ScarletCard.rewardCards.SuperhumanBloodyKnife;
import Thmod.Power.Abnormal.BlueAbnormity;
import Thmod.Power.Abnormal.GreenAbnormity;
import Thmod.Power.Abnormal.RedAbnormity;
import Thmod.Power.DashPower;
import Thmod.Power.FetterPower;
import Thmod.Power.JyouchiRei;
import Thmod.Power.KyoufuSaiminPower;
import Thmod.Power.RokkonShyoujyouDefend;
import Thmod.Power.SoulPower;
import Thmod.Power.Weather.DaiyamondoDasuto;
import Thmod.Power.Weather.DonTen;
import Thmod.Power.Weather.Fuuu;
import Thmod.Power.Weather.Haku;
import Thmod.Power.Weather.HanaGumo;
import Thmod.Power.Weather.KaiSei;
import Thmod.Power.Weather.KawaGiri;
import Thmod.Power.Weather.KiriSame;
import Thmod.Power.Weather.Kousa;
import Thmod.Power.Weather.KyoKkou;
import Thmod.Power.Weather.Nagi;
import Thmod.Power.Weather.NouMu;
import Thmod.Power.Weather.RetsuJitsu;
import Thmod.Power.Weather.SeiRan;
import Thmod.Power.Weather.SouTen;
import Thmod.Power.Weather.Soyuki;
import Thmod.Power.Weather.TaiFuu;
import Thmod.Power.Weather.TenkiYume;
import Thmod.Power.Weather.Tsume;
import Thmod.Power.Weather.Yuki;
import Thmod.Power.satoriEnemy.PointPowerSpe;
import Thmod.Power.satoriEnemy.Weather.AbstractWeatherPowers;
import Thmod.Power.satoriEnemy.Weather.FuuuSpe;
import Thmod.Power.satoriEnemy.Weather.HakuSpe;
import Thmod.Power.satoriEnemy.Weather.KawaGiriSpe;
import Thmod.Power.satoriEnemy.Weather.KiriSameSpe;
import Thmod.Power.satoriEnemy.Weather.KyoKkouSpe;
import Thmod.Power.satoriEnemy.Weather.NouMuSpe;
import Thmod.Power.satoriEnemy.Weather.SoyukiSpe;
import Thmod.Power.satoriEnemy.Weather.TenkiYumeSpe;
import Thmod.Power.satoriEnemy.Weather.TsumeSpe;
import Thmod.Power.satoriEnemy.Weather.YukiSpe;
import Thmod.Relics.SpellCardsRule;
import Thmod.Relics.SteinsOfFate;
import Thmod.Relics.remiria.SpellCardsRuleRemi;
import Thmod.Relics.remiria.ThirstyCrossSpe;
import Thmod.ThMod;
import Thmod.vfx.SparkEffect;

public class Satori extends AbstractMonster {
    public static final String ID = "Satori";
    private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("Satori");
    public static final String NAME = monsterStrings.NAME;
    public static final String[] MOVES = monsterStrings.MOVES;
    public static final String[] DIALOG = monsterStrings.DIALOG;
    private AbstractPlayer p;
    private int pointCounter;
    private boolean singleTurn;

    public Satori(float x, float y) {
        super(NAME, "Satori", 100, 10.0F, 0.0F, 240.0F, 300.0F, null, x, y);
        loadAnimation("images/characters/komeiji/Komeiji.atlas", "images/characters/komeiji/Komeiji.json", 1.0F);
        AnimationState.TrackEntry e = this.state.setAnimation(0, "Normal", true);
        e.setTime(e.getEndTime() * MathUtils.random());
        e.setTimeScale(1.0F);
        this.singleTurn = true;
        this.pointCounter = 0;
        this.p = AbstractDungeon.player;
        this.dialogX = this.drawX + 0.0f * Settings.scale;
        this.dialogY = this.drawY + 240.0f * Settings.scale;
        this.damage.add(new DamageInfo(this, 0));
        this.damage.add(new DamageInfo(this, 6));
        this.damage.add(new DamageInfo(this, 16, DamageInfo.DamageType.THORNS));
        this.damage.add(new DamageInfo(this, 5));
        this.damage.add(new DamageInfo(this, 4));
        this.damage.add(new DamageInfo(this, 8));
        this.damage.add(new DamageInfo(this, 80));
        this.damage.add(new DamageInfo(this, 2));
    }

    public void usePreBattleAction()
    {

        AbstractDungeon.player.flipHorizontal = true;
        AbstractDungeon.player.drawX += 860.0f * Settings.scale;
        CardCrawlGame.music.silenceBGM();
        AbstractDungeon.scene.fadeOutAmbiance();
        AbstractDungeon.getCurrRoom().playBgmInstantly("Satori.mp3");
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new StrengthPower(this, 1), 1));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new DexterityPower(this, 1), 1));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new DashPower(this, 1), 1));
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this, this, 10));
        AbstractDungeon.actionManager.addToTop(new LatterAction(()->{
            for(int i = 0; i < 3; i ++) {
                CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
                tmp.addToTop(AbstractDungeon.returnTrulyRandomCardInCombat().makeStatEquivalentCopy());
                tmp.addToTop(AbstractDungeon.returnTrulyRandomCardInCombat().makeStatEquivalentCopy());
                tmp.addToTop(AbstractDungeon.returnTrulyRandomCardInCombat().makeStatEquivalentCopy());
                AbstractDungeon.actionManager.addToTop(new ChooseAndObtainAction(tmp, AbstractDungeon.player.discardPile, DIALOG[0]));
            }
        }, 2.5f));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new PointPowerSpe(this, 1), 1));
        this.singleTurn = true;
        AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
        this.pointCounter = 0;
    }

    protected void getMove(int num) {
        if(this.singleTurn){
            if((this.hasPower(PointPowerSpe.POWER_ID)) && (this.getPower(PointPowerSpe.POWER_ID).amount > this.pointCounter)){
                this.pointCounter ++;
                switch (this.pointCounter){
                    case 1:
                        switch (MathUtils.random(3)){
                            case 0:
                                setMove(MOVES[0], (byte) 10,Intent.ATTACK_BUFF, this.damage.get(1).base);
                                break;
                            case 1:
                                setMove(MOVES[1], (byte) 11,Intent.ATTACK, this.damage.get(2).base);
                                break;
                            case 2:
                                setMove(MOVES[2], (byte) 12,Intent.ATTACK_DEBUFF, this.damage.get(1).base);
                                break;
                            case 3:
                                setMove(MOVES[3], (byte) 13,Intent.ATTACK_BUFF, this.damage.get(1).base);
                                break;
                        }
                        break;
                    case 2:
                        setMove(MOVES[4], (byte) 20,Intent.ATTACK_DEBUFF, this.damage.get(3).base);
                        break;
                    case 3:
                        switch (MathUtils.random(3)){
                            case 0:
                                setMove(MOVES[5], (byte) 30,Intent.DEFEND_BUFF);
                                break;
                            case 1:
                                setMove(MOVES[6], (byte) 31,Intent.ATTACK_DEBUFF, this.damage.get(1).base);
                                break;
                            case 2:
                                this.damage.set(0, new DamageInfo(this, 12));
                                if(this.hasPower(StrengthPower.POWER_ID)){
                                    this.damage.set(0, new DamageInfo(this, this.damage.get(0).base + this.getPower(StrengthPower.POWER_ID).amount * 6));
                                }
                                setMove(MOVES[7], (byte) 32,Intent.ATTACK_DEFEND, damage.get(0).base);
                                break;
                            case 3:
                                setMove(MOVES[8], (byte) 33,Intent.ATTACK_DEFEND, this.damage.get(4).base, 4, true);
                                break;
                        }
                        break;
                    case 4:
                        switch (MathUtils.random(2)){
                            case 0:
                                setMove(MOVES[9], (byte) 40,Intent.ATTACK_DEBUFF, this.damage.get(5).base);
                                break;
                            case 1:
                                this.damage.set(0, new DamageInfo(this, 12));
                                if(AbstractDungeon.player.hasPower(JyouchiRei.POWER_ID)){
                                    this.damage.set(0, new DamageInfo(this, this.damage.get(0).base +  (int) Math.pow(2, (double) this.getPower(StrengthPower.POWER_ID).amount) / 2));
                                }
                                setMove(MOVES[10], (byte) 41,Intent.ATTACK, damage.get(0).base);
                                break;
                            case 2:
                                this.damage.set(0, new DamageInfo(this, 15));
                                for(AbstractPower power : this.powers) {
                                    if (power instanceof AbstractWeatherPowers) {
                                        this.damage.set(0, new DamageInfo(this, this.damage.get(0).base + 45));
                                        break;
                                    }
                                }
                                setMove(MOVES[11], (byte) 42,Intent.ATTACK_DEBUFF, damage.get(0).base);
                                break;
                        }
                        break;
                    case 5:
                        switch (MathUtils.random(8)){
                            case 0:
                                setMove(MOVES[12], (byte) 50,Intent.STRONG_DEBUFF);
                                break;
                            case 1:
                                setMove(MOVES[13], (byte) 51,Intent.STRONG_DEBUFF);
                                break;
                            case 2:
                                setMove(MOVES[14], (byte) 52,Intent.DEFEND_BUFF);
                                break;
                            case 3:
                                setMove(MOVES[15], (byte) 53,Intent.ATTACK, this.damage.get(5).base, 5, true);
                                break;
                            case 4:
                                setMove(MOVES[16], (byte) 54,Intent.ATTACK, this.damage.get(6).base);
                                break;
                            case 5:
                                setMove(MOVES[17], (byte) 55,Intent.ATTACK, this.damage.get(7).base, 10, true);
                                break;
                            case 6:
                                this.damage.set(0, new DamageInfo(this, 16));
                                if(this.hasPower(StrengthPower.POWER_ID)){
                                    this.damage.set(0, new DamageInfo(this, this.damage.get(0).base + this.getPower(StrengthPower.POWER_ID).amount * 8));
                                }
                                setMove(MOVES[18], (byte) 56,Intent.ATTACK_DEFEND, this.damage.get(0).base);
                                break;
                            case 7:
                                setMove(MOVES[19], (byte) 57,Intent.STRONG_DEBUFF);
                                break;
                            case 8:
                                setMove(MOVES[20], (byte) 58,Intent.DEFEND_DEBUFF);
                                break;
                        }
                        break;
                }
            }
            else {
                setMove((byte) 0,Intent.ATTACK_DEFEND, this.damage.get(1).base);
            }
        }
        else {
            setMove((byte) 1,Intent.DEFEND_BUFF);
        }
        this.singleTurn = !this.singleTurn;
    }

    public void takeTurn() {
        switch (this.nextMove){
            case 0:
                AbstractDungeon.actionManager.addToBottom(new DamageAction(this.p, this.damage.get(1), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
                if(this.hasPower(DexterityPower.POWER_ID)) {
                    AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this, this, 10 + this.getPower(DexterityPower.POWER_ID).amount * 2));
                }
                else {
                    AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this, this, 10));
                }
                //AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new DashPower(this, 1), 1));
                break;
            case 1:
                //AbstractDungeon.actionManager.addToBottom(new DamageAction(this.p, this.damage.get(1), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
                if(this.hasPower(DexterityPower.POWER_ID)) {
                    AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this, this, 10 + this.getPower(DexterityPower.POWER_ID).amount * 2));
                }
                else {
                    AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this, this, 10));
                }
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new DashPower(this, 1), 1));
                break;
            case 10:
                AbstractDungeon.actionManager.addToBottom(new DamageAction(this.p, this.damage.get(1), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
                switch (MathUtils.random(9)){
                    case 0:
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new FuuuSpe(p)));
                        break;
                    case 1:
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new HakuSpe(p)));
                        break;
                    case 2:
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new KawaGiriSpe(p)));
                        break;
                    case 3:
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new KiriSameSpe(p)));
                        break;
                    case 4:
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new KyoKkouSpe(p)));
                        break;
                    case 5:
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new NouMuSpe(p)));
                        break;
                    case 6:
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new SoyukiSpe(p)));
                        break;
                    case 7:
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new TenkiYumeSpe(p)));
                        break;
                    case 8:
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new TsumeSpe(p)));
                        break;
                    case 9:
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new YukiSpe(p)));
                        break;
                    default:
                        break;
                }
                if(this.hasPower(DexterityPower.POWER_ID)) {
                    AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this, this, 10 + this.getPower(DexterityPower.POWER_ID).amount * 2));
                }
                else {
                    AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this, this, 10));
                }
                break;
            case 11:
                if(this.hasPower(DexterityPower.POWER_ID)) {
                    AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this, this, 10 + this.getPower(DexterityPower.POWER_ID).amount * 2));
                }
                else {
                    AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this, this, 10));
                }
                AbstractDungeon.actionManager.addToBottom(new DamageAction(this.p, this.damage.get(2), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                AbstractDungeon.actionManager.addToBottom(new DamageAction(this, new DamageInfo(this, 3), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                break;
            case 12:
                p.currentBlock = 0;
                AbstractDungeon.actionManager.addToBottom(new DamageAction(this.p, this.damage.get(1), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                if(this.hasPower(DexterityPower.POWER_ID)) {
                    AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this, this, 10 + this.getPower(DexterityPower.POWER_ID).amount * 2));
                }
                else {
                    AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this, this, 10));
                }
                break;
            case 13:
                AbstractDungeon.actionManager.addToBottom(new DamageAction(this.p, this.damage.get(1), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                if(this.hasPower(DexterityPower.POWER_ID)) {
                    AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this, this, 10 + this.getPower(DexterityPower.POWER_ID).amount * 2));
                }
                else {
                    AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this, this, 10));
                }
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new StrengthPower(this, 3), 3));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new DexterityPower(this, -1), -1));
                break;
            case 20:
                AbstractDungeon.actionManager.addToBottom(new DamageAction(this.p, this.damage.get(3), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                if(this.hasPower(DexterityPower.POWER_ID)) {
                    AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this, this, 10 + this.getPower(DexterityPower.POWER_ID).amount * 2));
                }
                else {
                    AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this, this, 10));
                }
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.p, this.p, new WeakPower(this.p, 3, true), 3));
                break;
            case 30:
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new StrengthPower(this, 2), 2));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new DexterityPower(this, 2), 2));
                if(this.hasPower(DexterityPower.POWER_ID)) {
                    AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this, this, 10 + this.getPower(DexterityPower.POWER_ID).amount * 2));
                }
                else {
                    AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this, this, 10));
                }
                break;
            case 31:
                AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(this.p, this, new JyouchiRei(this.p,6),6));
                if(this.hasPower(DexterityPower.POWER_ID)) {
                    AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this, this, 10 + this.getPower(DexterityPower.POWER_ID).amount * 2));
                }
                else {
                    AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this, this, 10));
                }
                break;
            case 32:
                AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_HEAVY"));
                AbstractDungeon.actionManager.addToBottom(new DamageAction(this.p, this.damage.get(0), AbstractGameAction.AttackEffect.NONE));
                AbstractDungeon.actionManager.addToTop(new VFXAction(this, new SparkEffect((this.dialogX + 5.0f), (this.dialogY - 10.0f), false,2.0F,1), 0.1F));
                if(this.hasPower(DexterityPower.POWER_ID)) {
                    AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this, this, 5 + this.getPower(DexterityPower.POWER_ID).amount));
                }
                else {
                    AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this, this, 5));
                }
                break;
            case 33:
                AbstractDungeon.actionManager.addToBottom(new DamageAction(this.p, this.damage.get(4), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
                AbstractDungeon.actionManager.addToBottom(new DamageAction(this.p, this.damage.get(4), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
                AbstractDungeon.actionManager.addToBottom(new DamageAction(this.p, this.damage.get(4), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
                AbstractDungeon.actionManager.addToBottom(new DamageAction(this.p, this.damage.get(4), AbstractGameAction.AttackEffect.SLASH_HEAVY));
                if(this.hasPower(DexterityPower.POWER_ID)) {
                    AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this, this, 10 + this.getPower(DexterityPower.POWER_ID).amount * 2));
                }
                else {
                    AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this, this, 10));
                }
                break;
            case 40:
                AbstractDungeon.actionManager.addToBottom(new DamageAction(this.p, this.damage.get(5), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.p, this, new WeakPower(this.p, 3, true), 3));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new IntangiblePlayerPower(this, 2), 2));
                if(this.hasPower(DexterityPower.POWER_ID)) {
                    AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this, this, 10 + this.getPower(DexterityPower.POWER_ID).amount * 2));
                }
                else {
                    AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this, this, 10));
                }
                break;
            case 41:
                AbstractDungeon.actionManager.addToBottom(new DamageAction(this.p, this.damage.get(0), AbstractGameAction.AttackEffect.SLASH_HEAVY));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new SoulPower(p, 4, 3), 4));
                AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.p, this, JyouchiRei.POWER_ID));
                if(this.hasPower(DexterityPower.POWER_ID)) {
                    AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this, this, 10 + this.getPower(DexterityPower.POWER_ID).amount * 2));
                }
                else {
                    AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this, this, 10));
                }
                break;
            case 42:
                AbstractDungeon.actionManager.addToBottom(new DamageAction(this.p, this.damage.get(0), AbstractGameAction.AttackEffect.SLASH_HEAVY));
                for(AbstractPower power : this.powers) {
                    if (power instanceof AbstractWeatherPowers) {
                        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this, this, power));
                    }
                }
                if(this.hasPower(DexterityPower.POWER_ID)) {
                    AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this, this, 10 + this.getPower(DexterityPower.POWER_ID).amount * 2));
                }
                else {
                    AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this, this, 10));
                }
                break;
            case  50:
                this.p.currentHealth = (this.p.currentHealth/2);
                this.p.healthBarUpdatedEvent();
                break;
            case 51:
                float moper = ((float) this.currentHealth / (float) this.maxHealth);
                float plper = ((float) this.p.currentHealth / (float) this.p.maxHealth);
                this.p.currentHealth = (int)(this.p.maxHealth * moper);
                this.p.healthBarUpdatedEvent();
                int moNewHealth = (int)(this.maxHealth * plper);
                if(moNewHealth <= 0){
                    moNewHealth = 1;
                }
                this.currentHealth = moNewHealth;

                this.healthBarUpdatedEvent();
                break;
            case 52:
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new RokkonShyoujyouDefend(this, 18)));
                if(this.hasPower(DexterityPower.POWER_ID)) {
                    AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this, this, 5 + this.getPower(DexterityPower.POWER_ID).amount));
                }
                else {
                    AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this, this, 5));
                }
                break;
            case 53:
                AbstractDungeon.actionManager.addToBottom(new DamageAction(this.p, this.damage.get(5), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                AbstractDungeon.actionManager.addToBottom(new DamageAction(this.p, this.damage.get(5), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                AbstractDungeon.actionManager.addToBottom(new DamageAction(this.p, this.damage.get(5), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                AbstractDungeon.actionManager.addToBottom(new DamageAction(this.p, this.damage.get(5), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                AbstractDungeon.actionManager.addToBottom(new DamageAction(this.p, this.damage.get(5), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                break;
            case 54:
                AbstractDungeon.actionManager.addToBottom(new DamageAction(this.p, this.damage.get(6), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                break;
            case 55:
                for(int i = 0; i < 10; i++) {
                    AbstractDungeon.actionManager.addToBottom(new DamageAction(this.p, this.damage.get(7), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
                }
                if(this.hasPower(DexterityPower.POWER_ID)) {
                    AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this, this, 10 + this.getPower(DexterityPower.POWER_ID).amount * 2));
                }
                else {
                    AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this, this, 10));
                }
                break;
            case 56:
                AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_HEAVY"));
                AbstractDungeon.actionManager.addToBottom(new DamageAction(this.p, this.damage.get(0), AbstractGameAction.AttackEffect.NONE));
                AbstractDungeon.actionManager.addToTop(new VFXAction(this, new SparkEffect((this.dialogX + 5.0f), (this.dialogY - 10.0f), false,2.0F,2), 0.1F));
                if(this.hasPower(DexterityPower.POWER_ID)) {
                    AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this, this, 5 + this.getPower(DexterityPower.POWER_ID).amount));
                }
                else {
                    AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this, this, 5));
                }
                break;
            case 57:
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.p, this, new WeakPower(this.p, 5,true), 5));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.p, this, new VulnerablePower(this.p, 5,true), 5));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.p, this, new FrailPower(this.p, 5,true), 5));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.p, this, new StrengthPower(this.p, -5), -5));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.p, this, new DexterityPower(this.p, -5), -5));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.p, this, new KyoufuSaiminPower(this.p)));
                break;
            case 58:
                AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(this.p, this, new RedAbnormity(this.p, 3), 3));
                AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(this.p, this, new GreenAbnormity(this.p, 3), 3));
                AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(this.p, this, new BlueAbnormity(this.p, 3), 3));
                if(this.hasPower(DexterityPower.POWER_ID)) {
                    AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this, this, 5 + this.getPower(DexterityPower.POWER_ID).amount));
                }
                else {
                    AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this, this, 5));
                }
                break;
        }
        AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
    }

    public void die()
    {
        super.die();
        if (!AbstractDungeon.getCurrRoom().cannotLose) {
            AbstractDungeon.getCurrRoom().addRelicToRewards(new SpellCardsRuleRemi());
            AbstractDungeon.getCurrRoom().addRelicToRewards(new ThirstyCrossSpe());
            useFastShakeAnimation(5.0F);
            CardCrawlGame.screenShake.rumble(4.0F);
            this.deathTimer += 1.5F;
            onBossVictoryLogic();
        }
    }
}
