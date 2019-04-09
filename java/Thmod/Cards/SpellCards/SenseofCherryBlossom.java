package Thmod.Cards.SpellCards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
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
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import java.util.ArrayList;
import java.util.List;

import Thmod.Cards.AbstractSetCards;
import Thmod.Cards.UncommonCards.SenseofElegance;
import Thmod.ThMod;
import basemod.helpers.TooltipInfo;

public class SenseofCherryBlossom extends AbstractSpellCards {
    public static final String ID = "SenseofCherryBlossom";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private int pointcost;
    public int[] extraEffect = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    public ArrayList<Integer> remainEffect = new ArrayList<>();
    public List<TooltipInfo> tips = new ArrayList<>();

    public SenseofCherryBlossom(int times) {
        super("SenseofCherryBlossom", SenseofCherryBlossom.NAME,  2, SenseofCherryBlossom.DESCRIPTION, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ALL_ENEMY);
        this.baseDamage = 12;
        this.pointcost = 5;
        this.timesUpgraded = times;
        this.name = NAME + "+" + this.timesUpgraded;
        initializeTitle();
        for(int i = 0;i < this.timesUpgraded;i++)
            this.baseDamage += 6;
        this.isMultiDamage = true;
    }

    public SenseofCherryBlossom(int times, int[] extraEffect, ArrayList<Integer> remainEffect) {
        super("SenseofCherryBlossom", SenseofCherryBlossom.NAME,  2, SenseofCherryBlossom.DESCRIPTION, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ALL_ENEMY);
        this.baseDamage = 12;
        this.pointcost = 5;
        this.timesUpgraded = times;
        this.extraEffect = extraEffect;
        this.remainEffect = remainEffect;
        this.name = NAME + "+" + this.timesUpgraded;
        initializeTitle();
        for(int i = 0;i < this.timesUpgraded;i++)
            this.baseDamage += 6;
        this.isMultiDamage = true;
        addTips();
        addDescription();
        initializeDescription();
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo){
        super.calculateCardDamage(mo);
        if(this.extraEffect[5] == 1){
            for (int i = 0; i < this.multiDamage.length; i++) {
                this.multiDamage[i] = (int) ((float)this.multiDamage[i] * 1.5);
            }
        }
        if(this.extraEffect[6] == 1){
            for (int i = 0; i < this.multiDamage.length; i++) {
                this.multiDamage[i] = (int) ((float)this.multiDamage[i] * 1.5);
            }
        }
        if(this.extraEffect[7] == 1){
            for (int i = 0; i < this.multiDamage.length; i++) {
                this.multiDamage[i] = (int) ((float)this.multiDamage[i] / 1.5);
            }
        }
        this.damage = this.multiDamage[0];
        if(this.extraEffect[8] == 1){
            float totalDamage = 0;
            for (int aMultiDamage : this.multiDamage) {
                totalDamage += (int) ((float) aMultiDamage / 1.5);
            }
            this.block = (int) (totalDamage / 4);
        }
        if((this.extraEffect[5] == 1) || (this.extraEffect[9] == 1)){
            float totalDamage = 0;
            for (int aMultiDamage : this.multiDamage) {
                totalDamage += (int) ((float) aMultiDamage / 1.5);
            }
            this.baseMagicNumber = (int) (totalDamage * 0.05);
            this.magicNumber = this.baseMagicNumber;
        }
        this.isDamageModified = true;
        this.isBlockModified = true;
        this.isMagicNumberModified = true;
        addDescription();
        initializeDescription();
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        if(this.extraEffect[5] == 1){
            for (int i = 0; i < this.multiDamage.length; i++) {
                this.multiDamage[i] = (int) ((float)this.multiDamage[i] * 1.5);
            }
        }
        if(this.extraEffect[6] == 1){
            for (int i = 0; i < this.multiDamage.length; i++) {
                this.multiDamage[i] = (int) ((float)this.multiDamage[i] * 1.5);
            }
        }
        if(this.extraEffect[7] == 1){
            for (int i = 0; i < this.multiDamage.length; i++) {
                this.multiDamage[i] = (int) ((float)this.multiDamage[i] / 1.5);
            }
        }
        this.damage = this.multiDamage[0];
        if(this.extraEffect[8] == 1){
            float totalDamage = 0;
            for (int aMultiDamage : this.multiDamage) {
                totalDamage += (int) ((float) aMultiDamage / 1.5);
            }
            this.block = (int) (totalDamage / 4);
        }
        if((this.extraEffect[5] == 1) || (this.extraEffect[9] == 1)){
            float totalDamage = 0;
            for (int aMultiDamage : this.multiDamage) {
                totalDamage += (int) ((float) aMultiDamage / 1.5);
            }
            this.baseMagicNumber = (int) (totalDamage * 0.05);
            this.magicNumber = this.baseMagicNumber;
        }
        this.isDamageModified = true;
        this.isBlockModified = true;
        this.isMagicNumberModified = true;
        addDescription();
        initializeDescription();
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        if (p.hasPower("PointPower")) {
            if (p.getPower("PointPower").amount >= this.pointcost) {
                AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                AbstractDungeon.actionManager.addToTop(new ReducePowerAction(p,p,"PointPower",this.pointcost));
                doExtraEffect();
            }
        }
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m){
        super.canUse(p,m);
        if (p.hasPower("PointPower")) {
            if (p.getPower("PointPower").amount >= this.pointcost) {
                return true;
            }
        }
        this.cantUseMessage = AbstractSpellCards.EXTENDED_DESCRIPTION[4];
        return false;
    }

    public AbstractCard makeCopy() {
        return new SenseofCherryBlossom(0);
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    @Override
    public List<TooltipInfo> getCustomTooltips()
    {
        return this.tips;
    }

    private void addTips(){
        for(int i = 0; i < extraEffect.length; i++){
            if(extraEffect[i] == 1) {
                this.tips.add(new TooltipInfo(SenseofElegance.EXTENDED_DESCRIPTION[i * 2 + 2], SenseofElegance.EXTENDED_DESCRIPTION[i * 2 + 3]));
            }
            if(i <= 4){
                if(extraEffect[i] == 1) {
                    this.baseDamage += 8;
                }
            }
        }
    }

    private void addDescription(){
        if(this.extraEffect[8] == 1){
            this.rawDescription += SenseofElegance.EXTENDED_DESCRIPTION[27];
            this.initializeDescription();
        }
        if(this.extraEffect[5] == 1){
            this.rawDescription += SenseofElegance.EXTENDED_DESCRIPTION[28];
            this.initializeDescription();
        }
        if(this.extraEffect[9] == 1){
            this.rawDescription += SenseofElegance.EXTENDED_DESCRIPTION[29];
            this.initializeDescription();
        }
    }

    private void doExtraEffect(){
        AbstractPlayer p = AbstractDungeon.player;

        if(this.extraEffect[0] == 1){
            for (int i = 0; i < AbstractDungeon.getCurrRoom().monsters.monsters.size(); i++) {
                AbstractMonster m = AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
                if ((!(m.isDying)) && (m.currentHealth > 0) && (!(m.isEscaping))) {
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new VulnerablePower(m, 2, false), 2));
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new VulnerablePower(p, 2, false), 2));
                }
            }
        }
        if(this.extraEffect[1] == 1){
            for (int i = 0; i < AbstractDungeon.getCurrRoom().monsters.monsters.size(); i++) {
                AbstractMonster m = AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
                if ((!(m.isDying)) && (m.currentHealth > 0) && (!(m.isEscaping))) {
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new WeakPower(m, 2, false), 2));
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new WeakPower(p, 2, false), 2));
                }
            }
        }
        if(this.extraEffect[2] == 1){
            for (int i = 0; i < AbstractDungeon.getCurrRoom().monsters.monsters.size(); i++) {
                AbstractMonster m = AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
                if ((!(m.isDying)) && (m.currentHealth > 0) && (!(m.isEscaping))) {
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new StrengthPower(m, -2), -2));
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, -2), -2));
                }
            }
        }
        if(this.extraEffect[3] == 1){
            for (int i = 0; i < AbstractDungeon.getCurrRoom().monsters.monsters.size(); i++) {
                AbstractMonster m = AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
                if ((!(m.isDying)) && (m.currentHealth > 0) && (!(m.isEscaping))) {
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new StrengthPower(m, 2), 2));
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, 2), 2));
                }
            }
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
            for (int i = 0; i < AbstractDungeon.getCurrRoom().monsters.monsters.size(); i++) {
                AbstractMonster m = AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
                if ((!(m.isDying)) && (m.currentHealth > 0) && (!(m.isEscaping))) {
                    if (m.currentHealth <= 100) {
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new FadingPower(m, 1)));
                    }
                }
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
        cardStrings = CardCrawlGame.languagePack.getCardStrings("SenseofCherryBlossom");
        NAME = SenseofCherryBlossom.cardStrings.NAME;
        DESCRIPTION = SenseofCherryBlossom.cardStrings.DESCRIPTION;
    }
}
