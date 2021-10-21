package Thmod.Cards.ScarletCard.uncommonCards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ModifyDamageAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.List;

import Thmod.Actions.Remiria.IncreaseDamageAction;
import Thmod.Actions.common.LatterAction;
import Thmod.Cards.ScarletCard.AbstractRemiriaCards;
import Thmod.Characters.RemiriaScarlet;
import Thmod.Power.remiria.BloodBruisePower;
import Thmod.Power.remiria.ScarletLordPower;
import basemod.helpers.TooltipInfo;

public class VampireClaw extends AbstractRemiriaCards {
    public static final String ID = "VampireClaw";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;
    private int extraDamage;
    private boolean upgradedMis = false;

    public VampireClaw() {
        this(false);
    }

    public VampireClaw(boolean isPlus) {
        super("VampireClaw", VampireClaw.NAME,  0, VampireClaw.DESCRIPTION, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY, isPlus);
        this.misc = 0;
        this.baseDamage = 6;
        this.baseMagicNumber = 4;
        this.magicNumber = this.baseMagicNumber;
        this.extraDamage = 0;
        this.addTips();
        this.attackType = AttackType.CHAIN;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        if(!this.isPlus) {
            AbstractDungeon.actionManager.addToTop(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        }
        if(m.hasPower(BloodBruisePower.POWER_ID)){
//            AbstractDungeon.actionManager.addToBottom(new ModifyDamageAction(this.uuid, this.magicNumber));
            AbstractDungeon.actionManager.addToBottom(new LatterAction(()->{
                this.extraDamage += this.magicNumber;
            }, 0f));
            AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(m, p, BloodBruisePower.POWER_ID, 1));
        }
        if(this.isPlus){
            if(m.hasPower(BloodBruisePower.POWER_ID)){
                AbstractDungeon.actionManager.addToBottom(new IncreaseDamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn),
                        AbstractGameAction.AttackEffect.SLASH_HORIZONTAL, this.magicNumber, this.uuid));
            }
            else {
                AbstractDungeon.actionManager.addToTop(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
            }
        }
        super.use(p, m);
    }

    public void applyPowers()
    {
        int tmp = this.baseDamage;
        this.baseDamage += this.misc;
        this.baseDamage += this.extraDamage;
        super.applyPowers();
        this.baseDamage = tmp;
        //initializeDescription();
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int tmp = this.baseDamage;
        this.baseDamage += this.misc;
        this.baseDamage += this.extraDamage;
        super.calculateCardDamage(mo);
        this.baseDamage = tmp;
    }

    public AbstractCard makeCopy() {
        if(AbstractDungeon.player != null){
            if((AbstractDungeon.player instanceof RemiriaScarlet) && (AbstractDungeon.player.hasPower(ScarletLordPower.POWER_ID))){
                return new VampireClaw(true);
            }
        }
        return new VampireClaw();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.misc += 3;
            this.upgradedMis = true;
            this.baseDamage = this.misc + 6;
            this.upgradedDamage = true;
            this.upgradeMagicNumber(1);
        }
    }

    @Override
    public void displayUpgrades() {
        super.displayUpgrades();
        if(this.upgradedMis){
            this.misc -= 4;
        }
    }

    @Override
    public void addTips() {
        this.tips.clear();
        this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("VampireClaw");
        NAME = VampireClaw.cardStrings.NAME;
        DESCRIPTION = VampireClaw.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = VampireClaw.cardStrings.EXTENDED_DESCRIPTION;
    }
}
