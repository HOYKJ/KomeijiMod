package Thmod.Cards.ScarletCard.uncommonCards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Actions.common.LatterAction;
import Thmod.Actions.unique.ChooseAction;
import Thmod.Cards.ScarletCard.Absorbed;
import Thmod.Cards.ScarletCard.AbstractRemiriaCards;
import Thmod.Characters.RemiriaScarlet;
import Thmod.Power.remiria.BloodBruisePower;
import Thmod.Power.remiria.ScarletLordPower;
import basemod.helpers.TooltipInfo;

public class Exsanguinate extends AbstractRemiriaCards {
    public static final String ID = "Exsanguinate";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;

    public Exsanguinate() {
        this(false);
    }

    public Exsanguinate(boolean isPlus) {
        super("Exsanguinate", Exsanguinate.NAME,  1, Exsanguinate.DESCRIPTION, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY, isPlus);
        this.baseMagicNumber = 6;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
        this.addTips();
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        final ChooseAction choice = new ChooseAction(this, m, Absorbed.EXTENDED_DESCRIPTION[2], false, 1);
        choice.add(this.name, EXTENDED_DESCRIPTION[2], () -> {
            if(p.hasPower(BloodBruisePower.POWER_ID)) {
                int amount = p.getPower(BloodBruisePower.POWER_ID).amount;
                if(amount <= 6) {
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new BloodBruisePower(m, amount), amount));
                    AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, BloodBruisePower.POWER_ID));
                }
                else {
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new BloodBruisePower(m, 6), 6));
                    AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(p, p, BloodBruisePower.POWER_ID, 6));
                }
            }
            AbstractDungeon.actionManager.addToBottom(new LatterAction(()->{
                if(m.hasPower(BloodBruisePower.POWER_ID)){
                    AbstractDungeon.actionManager.addToTop(new DamageAction(m, new DamageInfo(p, m.getPower(BloodBruisePower.POWER_ID).amount * 3
                            , this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
                }
            }, 0.1f));
        });
        choice.add(this.name, EXTENDED_DESCRIPTION[3], () -> {
            if(m.hasPower(BloodBruisePower.POWER_ID)) {
                int amount = m.getPower(BloodBruisePower.POWER_ID).amount;
                if(amount <= 6) {
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new BloodBruisePower(p, amount), amount));
                    AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(m, p, BloodBruisePower.POWER_ID));
                }
                else {
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new BloodBruisePower(p, 6), 6));
                    AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(m, p, BloodBruisePower.POWER_ID, 6));
                }
            }
            AbstractDungeon.actionManager.addToBottom(new LatterAction(()->{
                if(p.hasPower(BloodBruisePower.POWER_ID)){
                    AbstractDungeon.actionManager.addToTop(new HealAction(p, p, (int)(p.getPower(BloodBruisePower.POWER_ID).amount * 1.5f)));
                }
            }, 0.1f));
        });
        AbstractDungeon.actionManager.addToBottom(choice);
        super.use(p, m);
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if(this.isPlus){
            return super.canUse(p, m);
        }
        if(p.hasPower(BloodBruisePower.POWER_ID)){
            if(p.getPower(BloodBruisePower.POWER_ID).amount >= this.magicNumber){
                return super.canUse(p, m);
            }
        }
        return false;
    }

    public AbstractCard makeCopy() {
        if(AbstractDungeon.player != null){
            if((AbstractDungeon.player instanceof RemiriaScarlet) && (AbstractDungeon.player.hasPower(ScarletLordPower.POWER_ID))){
                return new Exsanguinate(true);
            }
        }
        return new Exsanguinate();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.upgradeBaseCost(0);
        }
    }

    @Override
    public void addTips() {
        this.tips.clear();
        this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
        this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[4], EXTENDED_DESCRIPTION[2]));
        this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[4], EXTENDED_DESCRIPTION[3]));
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("Exsanguinate");
        NAME = Exsanguinate.cardStrings.NAME;
        DESCRIPTION = Exsanguinate.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = Exsanguinate.cardStrings.EXTENDED_DESCRIPTION;
    }
}
