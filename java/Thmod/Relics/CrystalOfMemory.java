package Thmod.Relics;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.unique.ArmamentsAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.TutorialStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import java.util.ArrayList;
import java.util.List;

import Thmod.Actions.common.ChangeOrbAction;
import Thmod.Actions.common.LatterAction;
import Thmod.Actions.unique.ChooseAction;
import Thmod.Actions.unique.ResonateAction;
import Thmod.Cards.AbstractSetCards;
import Thmod.Cards.DeriveCards.AbstractDeriveCards;
import Thmod.Cards.DeriveCards.ArcherDoll;
import Thmod.Cards.DeriveCards.ShieldDoll;
import Thmod.Cards.DeriveCards.SpearDoll;
import Thmod.Cards.ElementCards.AbstractElementCards;
import Thmod.Cards.ElementCards.SpellCards.AbstractElementSpellCards;
import Thmod.Cards.NingyouShinki;
import Thmod.Cards.SpellCards.AbstractSpellCards;
import Thmod.Cards.UncommonCards.LittleLegion;
import Thmod.Orbs.ElementOrb.EarthOrb;
import Thmod.Orbs.ElementOrb.FireOrb;
import Thmod.Orbs.ElementOrb.MetalOrb;
import Thmod.Orbs.ElementOrb.WaterOrb;
import Thmod.Orbs.ElementOrb.WoodOrb;
import Thmod.Orbs.Helan;
import Thmod.Orbs.NingyouOrb;
import Thmod.Orbs.Penglai;
import Thmod.Orbs.Shanghai;
import Thmod.Orbs.TateNingyou;
import Thmod.Orbs.YariNingyou;
import Thmod.Orbs.YumiNingyou;
import Thmod.Power.Abnormal.BlueAbnormity;
import Thmod.Power.Abnormal.GreenAbnormity;
import Thmod.Power.Abnormal.RedAbnormity;
import Thmod.Power.JyouchiRei;
import Thmod.ThMod;
import Thmod.vfx.action.ChooseEffect;
import basemod.abstracts.CustomSavable;
import basemod.helpers.TooltipInfo;

public class CrystalOfMemory extends AbstractThRelic implements CustomSavable<int[]> {
    public static final String ID = "CrystalOfMemory";
    public int[] number = {0};
    private int desNum;
    private int blockCounter;
    private AbstractElementCards.ElementType recorder;
    private boolean used;

    public CrystalOfMemory()
    {
        super("CrystalOfMemory",  RelicTier.SPECIAL, LandingSound.CLINK);
        this.number[0] = 0;
        this.desNum = 1;
        this.blockCounter = 0;
        this.recorder = null;
        this.used = true;
        reloadDes();
    }

    @Override
    public void onEquip() {
        super.onEquip();
        final ChooseEffect choice = new ChooseEffect(null, null, DESCRIPTIONS[0], false, 1);
        for(AbstractCard card : AbstractDungeon.player.masterDeck.group){
            if((card instanceof AbstractSetCards) && (((AbstractSetCards) card).cardSetK != AbstractSetCards.CardSet_k.OTHER)){
                choice.add(card, ()->{
                    this.init(((AbstractSetCards) card).cardSetK);
                });
            }
        }
        AbstractDungeon.effectsQueue.add(choice);
    }

    @Override
    public void atPreBattle() {
        super.atPreBattle();
        if(this.number[0] == 12){
            this.used = false;
            beginPulse();
            this.pulse = true;
        }
        if(this.number[0] == 13){
            this.used = false;
            beginPulse();
            this.pulse = true;
        }
        if(this.number[0] == 14){
            this.used = false;
            beginPulse();
            this.pulse = true;
        }
        if(this.number[0] == 19){
            this.counter = 0;
        }
        if(this.number[0] == 20){
            this.counter = 0;
        }
        if(this.number[0] == 22){
            this.counter = 0;
        }
    }

    @Override
    public void atTurnStart() {
        super.atTurnStart();
        AbstractPlayer p = AbstractDungeon.player;
//        if(this.number[0] == 3){
//            boolean spear = false, shield = false, archer = false;
//            for(AbstractOrb orb : p.orbs){
//                if((orb instanceof YariNingyou) || (orb instanceof Shanghai) || (orb instanceof Penglai)){
//                    spear = true;
//                }
//                else if((orb instanceof TateNingyou) || (orb instanceof Helan)){
//                    shield = true;
//                }
//                else if(orb instanceof YumiNingyou){
//                    archer = true;
//                }
//            }
//            if(spear && shield && archer){
//                flash();
//                AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
//                AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(1));
//            }
//        }
        if(this.number[0] == 5){
            if(this.blockCounter > 0){
                flash();
                AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, 3 * this.blockCounter));
                this.blockCounter = 0;
            }
        }
        if(this.number[0] == 7){
            flash();
            AbstractMonster m = AbstractDungeon.getMonsters().getRandomMonster(true);
            if(m != null) {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new JyouchiRei(m, 1), 1));
            }
        }
        if(this.number[0] == 9){
            this.counter = 8;
        }
        if(this.number[0] == 13){
            this.used = false;
            beginPulse();
            this.pulse = true;
        }
        if(this.number[0] == 14){
            if(!this.used){
                this.flash();
                AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, 2));
            }
            else {
                this.used = false;
                beginPulse();
                this.pulse = true;
            }
        }
        if(this.number[0] == 15){
            this.flash();
            if(MathUtils.random.nextBoolean()){
                if(MathUtils.random.nextBoolean()){
                    AbstractDungeon.actionManager.addToBottom(new HealAction(p, p, 2));
                }
                else {
                    AbstractDungeon.actionManager.addToBottom(new HealAction(AbstractDungeon.getMonsters().getRandomMonster(true), p, 2));
                }
            }
            else {
                if(MathUtils.random.nextBoolean()){
                    AbstractDungeon.actionManager.addToBottom(new DamageAction(p, new DamageInfo(p, 2), AbstractGameAction.AttackEffect.FIRE));
                }
                else {
                    AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.getMonsters().getRandomMonster(true),
                            new DamageInfo(p, 2), AbstractGameAction.AttackEffect.FIRE));
                }
            }
        }
        if(this.number[0] == 16){
            if(!this.used){
                this.flash();
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, 1), 1));
            }
            else {
                this.used = false;
                beginPulse();
                this.pulse = true;
            }
        }
    }

    @Override
    public void onCardDraw(AbstractCard drawnCard) {
        super.onCardDraw(drawnCard);
        if(this.number[0] == 4){
            flash();
            AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.getMonsters().getRandomMonster(true),
                    new DamageInfo(AbstractDungeon.player, 3, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        }
    }

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        if(this.number[0] == 9){
            if(c.type == AbstractCard.CardType.ATTACK){
                if(this.counter > 0) {
                    this.counter -= 1;
                }
            }
        }
        if(this.number[0] == 10){
            if((c instanceof AbstractElementCards) && (((AbstractElementCards) c).mixElementType == null) && !(((AbstractElementCards) c).allElements)
                    && (((AbstractElementCards) c).elementType != AbstractElementCards.ElementType.Sun) && (((AbstractElementCards) c).elementType != AbstractElementCards.ElementType.Luna)){
                if(this.recorder != null) {
                    switch (this.recorder){
                        case Water:
                            if(((AbstractElementCards) c).elementType == AbstractElementCards.ElementType.Wood){
                                this.flash();
                                AbstractDungeon.actionManager.addToBottom(new ResonateAction(new WoodOrb(), true, true));
                                this.recorder = ((AbstractElementCards) c).elementType;
                            }
                            if(((AbstractElementCards) c).elementType == AbstractElementCards.ElementType.Fire){
                                this.recorder = null;
                            }
                            break;
                        case Wood:
                            if(((AbstractElementCards) c).elementType == AbstractElementCards.ElementType.Fire){
                                this.flash();
                                AbstractDungeon.actionManager.addToBottom(new ResonateAction(new FireOrb(), true, true));
                                this.recorder = ((AbstractElementCards) c).elementType;
                            }
                            if(((AbstractElementCards) c).elementType == AbstractElementCards.ElementType.Earth){
                                this.recorder = null;
                            }
                            break;
                        case Fire:
                            if(((AbstractElementCards) c).elementType == AbstractElementCards.ElementType.Earth){
                                this.flash();
                                AbstractDungeon.actionManager.addToBottom(new ResonateAction(new EarthOrb(), true, true));
                                this.recorder = ((AbstractElementCards) c).elementType;
                            }
                            if(((AbstractElementCards) c).elementType == AbstractElementCards.ElementType.Metal){
                                this.recorder = null;
                            }
                            break;
                        case Earth:
                            if(((AbstractElementCards) c).elementType == AbstractElementCards.ElementType.Metal){
                                this.flash();
                                AbstractDungeon.actionManager.addToBottom(new ResonateAction(new MetalOrb(), true, true));
                                this.recorder = ((AbstractElementCards) c).elementType;
                            }
                            if(((AbstractElementCards) c).elementType == AbstractElementCards.ElementType.Water){
                                this.recorder = null;
                            }
                            break;
                        case Metal:
                            if(((AbstractElementCards) c).elementType == AbstractElementCards.ElementType.Water){
                                this.flash();
                                AbstractDungeon.actionManager.addToBottom(new ResonateAction(new WaterOrb(), true, true));
                                this.recorder = ((AbstractElementCards) c).elementType;
                            }
                            if(((AbstractElementCards) c).elementType == AbstractElementCards.ElementType.Wood){
                                this.recorder = null;
                            }
                            break;
                            default:
                    }
                }
                else {
                    this.recorder = ((AbstractElementCards) c).elementType;
                }
                reloadDes();
                loadElementImg();
            }
        }
        if(this.number[0] == 16){
            if(c.type == AbstractCard.CardType.ATTACK){
                this.used = true;
                this.pulse = false;
            }
        }
        if(this.number[0] == 20){
            if(c.type == AbstractCard.CardType.ATTACK){
                if(this.counter < 4){
                    this.counter += 1;
                }
                else {
                    this.flash();
                    this.counter = 0;
                    for(AbstractCard card : AbstractDungeon.player.drawPile.group){
                        if (card.baseDamage > 0) {
                            card.baseDamage += 1;
                            card.isDamageModified = true;
                        }
                    }
                    for(AbstractCard card : AbstractDungeon.player.hand.group){
                        if (card.baseDamage > 0) {
                            card.baseDamage += 1;
                            card.isDamageModified = true;
                        }
                    }
                    for(AbstractCard card : AbstractDungeon.player.discardPile.group){
                        if (card.baseDamage > 0) {
                            card.baseDamage += 1;
                            card.isDamageModified = true;
                        }
                    }
                }
            }
        }
        if(this.number[0] == 22){
            if(c.type == AbstractCard.CardType.SKILL){
                if(this.counter < 3){
                    this.counter += 1;
                }
                else {
                    flash();
                    this.counter = 0;
                    AbstractDungeon.actionManager.addToBottom(new LatterAction(()-> {
                        ArrayList<AbstractCard> cards = new ArrayList<>();
                        for (AbstractCard card : AbstractDungeon.player.hand.group) {
                            if (card.canUpgrade()) {
                                cards.add(card);
                            }
                        }
                        if (cards.size() > 0) {
                            int i = cards.size() - 1;
                            int roll = MathUtils.random(i);
                            cards.get(roll).upgrade();
                        }
                    }, 0.1f));
                }
            }
        }
        super.onPlayCard(c, m);
    }

    @Override
    public int onPlayerGainedBlock(float blockAmount) {
        if(this.number[0] == 6){
            if(!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
                flash();
                AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.getMonsters().getRandomMonster(true),
                        new DamageInfo(AbstractDungeon.player, 2, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
            }
        }
        return super.onPlayerGainedBlock(blockAmount);
    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {

        super.onAttack(info, damageAmount, target);
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if(this.number[0] == 5){
            if(damageAmount <= 0){
                this.blockCounter += 1;
            }
        }
        if(this.number[0] == 14){
            this.used = true;
            this.pulse = false;
        }
        if(this.number[0] == 17){
            this.flash();
            if((info.type != DamageInfo.DamageType.THORNS) && ((info.owner.hasPower(BlueAbnormity.POWER_ID)) || (info.owner.hasPower(RedAbnormity.POWER_ID))
                    || (info.owner.hasPower(GreenAbnormity.POWER_ID)))){
                AbstractDungeon.actionManager.addToBottom(new HealAction(AbstractDungeon.player, AbstractDungeon.player, 1));
            }
        }
        return super.onAttacked(info, damageAmount);
    }

    @Override
    public void onExhaust(AbstractCard card) {
        if(this.number[0] == 19){
            if(this.counter < 3){
                this.counter += 1;
            }
            else {
                this.flash();
                this.counter = 0;
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                        new StrengthPower(AbstractDungeon.player, 1), 1));
            }
        }
        super.onExhaust(card);
    }

    @Override
    public void onShuffle() {
        if(this.number[0] == 21){
            final ChooseAction choice = new ChooseAction(null, null, DESCRIPTIONS[0], true, 1);
            for (AbstractCard card : AbstractDungeon.player.exhaustPile.group) {
                if(!(card instanceof AbstractDeriveCards) && !(card instanceof AbstractSpellCards) && !(card instanceof AbstractElementSpellCards)) {
                    choice.add(card, () -> {
                        AbstractDungeon.player.exhaustPile.removeCard(card);
                        AbstractCard card1 = card.makeCopy();
                        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAction(card1, 1));
                    });
                }
            }
            AbstractDungeon.actionManager.addToBottom(choice);
        }
        super.onShuffle();
    }

    @Override
    public void onPlayerEndTurn() {
        if(this.number[0] == 9){
            if(this.counter > 0){
                this.flash();
                AbstractDungeon.actionManager.addToBottom(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, this.counter));
            }
        }
        super.onPlayerEndTurn();
    }

    @Override
    public void onChannelOrb(AbstractOrb orb) {
        super.onChannelOrb(orb);
        if(this.number[0] == 3) {
            if (orb instanceof NingyouOrb) {
                this.counter++;
                if (this.counter == 3) {
                    this.counter = 0;

                    final ChooseAction choice = new ChooseAction(null, null, LittleLegion.EXTENDED_DESCRIPTION[0], false, 1);
                    choice.add(new SpearDoll(), () -> {
                        for (int i = (AbstractDungeon.player.orbs.size() - 1); i >= 0; i--) {
                            if (AbstractDungeon.player.orbs.get(i) instanceof NingyouOrb) {
                                AbstractDungeon.actionManager.addToBottom(new ChangeOrbAction(i, 1));
                                break;
                            }
                        }
                    });
                    choice.add(new ShieldDoll(), () -> {
                        for (int i = (AbstractDungeon.player.orbs.size() - 1); i >= 0; i--) {
                            if (AbstractDungeon.player.orbs.get(i) instanceof NingyouOrb) {
                                AbstractDungeon.actionManager.addToBottom(new ChangeOrbAction(i, 2));
                                break;
                            }
                        }
                    });
                    choice.add(new ArcherDoll(), () -> {
                        for (int i = (AbstractDungeon.player.orbs.size() - 1); i >= 0; i--) {
                            if (AbstractDungeon.player.orbs.get(i) instanceof NingyouOrb) {
                                AbstractDungeon.actionManager.addToBottom(new ChangeOrbAction(i, 3));
                                break;
                            }
                        }
                    });
                    AbstractDungeon.actionManager.addToBottom(choice);
                }
            }
        }
    }

    @Override
    public void onVictory() {
        super.onVictory();
        if(this.number[0] == 5){
            if(this.blockCounter > 0){
                this.blockCounter = 0;
            }
        }
        if(this.number[0] == 8){
            flash();
            AbstractDungeon.player.gainGold(12);
        }
        if(this.number[0] == 10){
            this.recorder = null;
            reloadDes();
            loadElementImg();
        }
        if(this.number[0] == 12){
            this.used = false;
            this.pulse = false;
        }
        if(this.number[0] == 13){
            this.used = false;
            this.pulse = false;
        }
        if(this.number[0] == 14){
            this.used = true;
            this.pulse = false;
        }
        if(this.number[0] == 16){
            this.used = true;
            this.pulse = false;
        }
        if(this.number[0] == 19){
            this.counter = 0;
        }
        if(this.number[0] == 20){
            this.counter = 0;
        }
        if(this.number[0] == 22){
            this.counter = 0;
        }
    }

    @Override
    public int changeNumberOfCardsInReward(int numberOfCards) {
        if(this.number[0] == 1) {
            return super.changeNumberOfCardsInReward(numberOfCards) + 1;
        }
        return super.changeNumberOfCardsInReward(numberOfCards);
    }

    protected  void onRightClick(){
        if(this.number[0] == 12){
            if(!this.used){
                if(AbstractDungeon.currMapNode != null) {
                    if ((AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT)) {
                        this.used = true;
                        AbstractPlayer p = AbstractDungeon.player;
                        final ChooseAction choice = new ChooseAction(null, null, DESCRIPTIONS[0], true, 1);
                        for (AbstractCard card : p.hand.group) {
                            choice.add(card, ()->{
                                AbstractCard card1 = card.makeStatEquivalentCopy();
                                AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAction(card1, 1));
                            });
                        }
                        AbstractDungeon.actionManager.addToBottom(choice);
                    }
                }
            }
        }
        if(this.number[0] == 13){
            if(!this.used){
                if(AbstractDungeon.currMapNode != null) {
                    if ((AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT)) {
                        this.used = true;
                        AbstractCard card = AbstractDungeon.returnTrulyRandomCardInCombat().makeCopy();
                        card.setCostForTurn(-99);
                        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(card, 1));
                    }
                }
            }
        }
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[this.desNum];
    }

    public AbstractRelic makeCopy() {
        return new CrystalOfMemory();
    }

    private void init(AbstractSetCards.CardSet_k cardSet_k){
        switch (cardSet_k){
            case SATORI:
                this.number[0] = 1;
                break;
            case KOISHI:
                this.number[0] = 2;
                Settings.hideCombatElements = false;
                AbstractDungeon.player.state.setAnimation(0,"Normal",true);
                break;
            case ALICE:
                this.number[0] = 3;
                this.counter = 0;
                break;
            case AYA:
                this.number[0] = 4;
                break;
            case CIRNO:
                this.number[0] = 5;
                break;
            case IKU:
                this.number[0] = 6;
                break;
            case KOMACHI:
                this.number[0] = 7;
                break;
            case MARISA:
                this.number[0] = 8;
                break;
            case MEIRIN:
                this.number[0] = 9;
                this.counter = 0;
                break;
            case PATCHOULI:
                this.number[0] = 10;
                break;
            case REIMU:
                this.number[0] = 11;
                break;
            case REISEN:
                this.number[0] = 12;
                break;
            case REMIRIA:
                this.number[0] = 13;
                break;
            case SAKUYA:
                this.number[0] = 14;
                break;
            case SANAE:
                this.number[0] = 15;
                break;
            case SUIKA:
                this.number[0] = 16;
                break;
            case SUWAKO:
                this.number[0] = 17;
                break;
            case TENSHI:
                this.number[0] = 18;
                break;
            case UTSUHO:
                this.number[0] = 19;
                this.counter = 0;
                break;
            case YOUMU:
                this.number[0] = 20;
                this.counter = 0;
                break;
            case YUKARI:
                this.number[0] = 21;
                break;
            case YUYUKO:
                this.number[0] = 22;
                this.counter = 0;
                break;
            default:
        }
        this.desNum = 1 + this.number[0];
        reloadDes();
        reloadIMG();
    }

    private void reloadDes(){
        this.description = getUpdatedDescription();
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        if(this.number[0] == 10){
            this.tips.add(new PowerTip(this.DESCRIPTIONS[24], this.DESCRIPTIONS[25]));
            this.tips.add(new PowerTip(this.DESCRIPTIONS[26], this.DESCRIPTIONS[27]));
            int i = 29;
            if(this.recorder != null){
                switch (this.recorder){
                    case Metal:
                        i = 30;
                        break;
                    case Wood:
                        i = 31;
                        break;
                    case Water:
                        i = 32;
                        break;
                    case Fire:
                        i = 33;
                        break;
                    case Earth:
                        i = 34;
                        break;
                        default:
                }
            }
            this.tips.add(new PowerTip(this.DESCRIPTIONS[28],this.DESCRIPTIONS[i]));
        }
        initializeTips();
    }

    private void reloadIMG(){
        String tmp = "images/relics/CrystalOfMemory/CrystalOfMemory_" + this.number[0] + ".png";
        this.img = ImageMaster.loadImage(tmp);
    }

    private void loadElementImg(){
        String tmp;
        if(this.recorder == null) {
            tmp = "images/relics/CrystalOfMemory/CrystalOfMemory_" + 10 + ".png";
        }
        else {
            tmp = "images/relics/CrystalOfMemory/Element/CrystalOfMemory_" + this.recorder.name() + ".png";
        }
        this.img = ImageMaster.loadImage(tmp);
    }

    @Override
    public int[] onSave() {
        return this.number;
    }

    @Override
    public void onLoad(int[] arg0)
    {
        if (arg0 == null) {
            return;
        }
        this.number = arg0.clone();
        ThMod.logger.info("Load number is :" + this.number[0]);
        this.desNum = 1 + this.number[0];
        reloadDes();
        reloadIMG();
    }
}
