package Thmod.Cards.UncommonCards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.unique.ExhaustAllNonAttackAction;
import com.megacrit.cardcrawl.actions.unique.RemoveDebuffsAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FadingPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import java.util.ArrayList;
import java.util.List;

import Thmod.Actions.common.LaterDiscardAction;
import Thmod.Actions.common.LatterAction;
import Thmod.Actions.unique.UpgradeEffectChoose;
import Thmod.Cards.AbstractKomeijiCards;
import Thmod.ThMod;
import basemod.abstracts.CustomSavable;
import basemod.helpers.TooltipInfo;

public class SenseofElegance extends AbstractKomeijiCards implements CustomSavable<int[]> {
    public static final String ID = "SenseofElegance";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;
    private boolean chooseEffect = false;
    public boolean isChoosing = false;
    public boolean chooseActive = false;
    public boolean eventActive = false;
    public int upgradeHelper;
    public int[] extraEffect = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    public ArrayList<Integer> remainEffect = new ArrayList<>();
    public List<TooltipInfo> tips = new ArrayList<>();

    public SenseofElegance()
    {
        this(0);
    }

    public SenseofElegance(int upgrades) {
        super("SenseofElegance", SenseofElegance.NAME,  2, SenseofElegance.DESCRIPTION, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY, CardSet_k.YUYUKO);
        this.baseDamage = 12;
        this.baseBlock = (int) ((float)this.baseDamage / 4);
        this.baseMagicNumber = (int) ((float)this.baseDamage * 0.05);
        this.magicNumber = this.baseMagicNumber;
        this.timesUpgraded = upgrades;
        this.upgradeHelper = 0;
        this.extraEffect = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        this.remainEffect.clear();
        for(int i = 0; i < this.extraEffect.length; i++){
            remainEffect.add(i);
        }
    }

    public SenseofElegance(int upgrades, int[] extraEffect, ArrayList<Integer> remainEffect) {
        super("SenseofElegance", SenseofElegance.NAME,  2, SenseofElegance.DESCRIPTION, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY, CardSet_k.YUYUKO);
        this.baseDamage = 12;
        this.baseBlock = (int) ((float)this.baseDamage / 4);
        this.baseMagicNumber = (int) ((float)this.baseDamage * 0.05);
        this.magicNumber = this.baseMagicNumber;
        this.timesUpgraded = upgrades;
        this.upgradeHelper = (this.timesUpgraded / 2) * 2;
        this.extraEffect = extraEffect.clone();
        this.remainEffect = remainEffect;
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo){
        super.calculateCardDamage(mo);
        if(this.extraEffect[5] == 1){
            this.damage = (int) ((float)this.damage * 1.5);
        }
        if(this.extraEffect[6] == 1){
            this.damage = (int) ((float)this.damage * 1.5);
        }
        if(this.extraEffect[7] == 1){
            this.damage = (int) ((float)this.damage / 2);
        }
        if(this.extraEffect[8] == 1){
            this.block = (int) ((float)this.damage / 4);
        }
        if((this.extraEffect[5] == 1) || (this.extraEffect[9] == 1)){
            this.baseMagicNumber = (int) ((float)this.damage * 0.05);
            this.magicNumber = this.baseMagicNumber;
        }
        this.isDamageModified = true;
        this.isBlockModified = true;
        this.isMagicNumberModified = true;
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        if(this.extraEffect[5] == 1){
            this.damage = (int) ((float)this.damage * 1.5);
        }
        if(this.extraEffect[6] == 1){
            this.damage = (int) ((float)this.damage * 1.5);
        }
        if(this.extraEffect[7] == 1){
            this.damage = (int) ((float)this.damage / 2);
        }
        if(this.extraEffect[8] == 1){
            this.block = (int) ((float)this.damage / 4);
        }
        if((this.extraEffect[5] == 1) || (this.extraEffect[9] == 1)){
            this.baseMagicNumber = (int) ((float)this.damage * 0.05);
            this.magicNumber = this.baseMagicNumber;
        }
        this.isDamageModified = true;
        this.isBlockModified = true;
        this.isMagicNumberModified = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
        doExtraEffect(m);
    }

    public AbstractCard makeCopy() {
        SenseofElegance c = new SenseofElegance(this.timesUpgraded, this.extraEffect, this.remainEffect);
        c.addTips();
        return c;
    }

    public void upgrade() {
        this.upgradeDamage(4);
        this.baseBlock = (int) ((float)this.baseDamage / 4);
        this.upgradedBlock = true;
        this.baseMagicNumber = (int) ((float)this.baseDamage * 0.05);
        this.magicNumber = this.baseMagicNumber;
        this.upgradedMagicNumber = true;
        this.timesUpgraded += 1;
        this.upgraded = true;
        this.name = NAME + "+" + this.timesUpgraded;
        if(AbstractDungeon.currMapNode != null) {
            if (((this.chooseActive) || (this.eventActive))
                    && (!AbstractDungeon.isScreenUp) && (!this.isChoosing)) {
                ThMod.logger.info("TEST STEP");
                this.eventActive = false;
                if ((this.timesUpgraded - 2) >= this.upgradeHelper) {
                    if (this.upgradeHelper == 0) {
                        for (int b : this.extraEffect) {
                            if (b == 1) {
                                this.upgradeHelper += 2;
                            }
                        }
                    }
                }
                ThMod.logger.info("times Upgraded " + this.timesUpgraded);
                ThMod.logger.info("upgrade helper " + this.upgradeHelper);
                if ((this.timesUpgraded - 2) >= this.upgradeHelper) {
                    this.chooseEffect = true;
                }
                if (this.chooseEffect) {
                    AbstractDungeon.topLevelEffectsQueue.add(new UpgradeEffectChoose(this));
                    this.chooseEffect = false;
                    this.upgradeHelper = (this.timesUpgraded / 2) * 2;
                }
            }
        }
        this.rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
        addDescription();
        initializeTitle();
    }

    @Override
    public void triggerWhenDrawn() {
        super.triggerWhenDrawn();
        if(this.extraEffect[6] == 1) {
            AbstractDungeon.actionManager.addToTop(new LaterDiscardAction(this));
        }
    }

    public boolean canUpgrade()
    {
        return true;
    }

    @Override
    public int[] onSave() {
        return this.extraEffect;
    }

    @Override
    public void onLoad(int[] arg0)
    {
        if (arg0 == null) {
            return;
        }
        this.extraEffect = arg0;
        addTips();
    }

    @Override
    public List<TooltipInfo> getCustomTooltips()
    {
        return this.tips;
    }

    private void addTips(){
        for(int i = 0; i < this.extraEffect.length; i++){
            if(this.extraEffect[i] == 1) {
                this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[i * 2 + 2], EXTENDED_DESCRIPTION[i * 2 + 3]));
            }
            if(i <= 4){
                if(this.extraEffect[i] == 1) {
                    this.baseDamage += 8;
                }
            }
        }
        if(this.extraEffect[8] == 1){
            this.baseBlock = (int) ((float)this.baseDamage / 4);
        }
    }

    public void addDescription(){
        if(this.extraEffect[8] == 1){
            this.rawDescription += EXTENDED_DESCRIPTION[27];
            this.initializeDescription();
        }
        if(this.extraEffect[5] == 1){
            this.rawDescription += EXTENDED_DESCRIPTION[28];
            this.initializeDescription();
        }
        if(this.extraEffect[9] == 1){
            this.rawDescription += EXTENDED_DESCRIPTION[29];
            this.initializeDescription();
        }
    }

    private void doExtraEffect(AbstractMonster m){
        AbstractPlayer p = AbstractDungeon.player;

        if(this.extraEffect[0] == 1){
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new VulnerablePower(m, 2, false), 2));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new VulnerablePower(p, 2, false), 2));
        }
        if(this.extraEffect[1] == 1){
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new WeakPower(m, 2, false), 2));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new WeakPower(p, 2, false), 2));
        }
        if(this.extraEffect[2] == 1){
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new StrengthPower(m, -2), -2));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, -2), -2));
        }
        if(this.extraEffect[3] == 1){
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new StrengthPower(m, 2), 2));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, 2), 2));
        }
        if(this.extraEffect[4] == 1){
            AbstractDungeon.actionManager.addToBottom(new RemoveDebuffsAction(p));
            AbstractCard c = AbstractDungeon.getCard(AbstractCard.CardRarity.CURSE);
            UnlockTracker.markCardAsSeen(c.cardID);
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAction(c, 1));
        }
        if(this.extraEffect[5] == 1){
            AbstractDungeon.actionManager.addToBottom(new LoseHPAction(p, p, this.magicNumber));
        }
        if(this.extraEffect[8] == 1){
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
        }
        if(this.extraEffect[9] == 1){
            AbstractDungeon.actionManager.addToBottom(new HealAction(p, p, this.magicNumber));
        }
        if(this.extraEffect[10] == 1){
            if(m.currentHealth <= 100) {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new FadingPower(m, 1)));
            }
        }
        if(this.extraEffect[11] == 1){
            AbstractDungeon.actionManager.addToBottom(new ExhaustAllNonAttackAction());
        }
        if(this.extraEffect[7] == 1){
            AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(1));
            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, 2));
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("SenseofElegance");
        NAME = SenseofElegance.cardStrings.NAME;
        DESCRIPTION = SenseofElegance.cardStrings.DESCRIPTION;
        UPGRADE_DESCRIPTION = SenseofElegance.cardStrings.UPGRADE_DESCRIPTION;
        EXTENDED_DESCRIPTION = SenseofElegance.cardStrings.EXTENDED_DESCRIPTION;
    }
}
