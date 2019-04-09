package Thmod.Cards.ScarletCard.uncommonCards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.List;

import Thmod.Actions.common.VampireKissAction;
import Thmod.Cards.ScarletCard.AbstractRemiriaCards;
import Thmod.Characters.RemiriaScarlet;
import Thmod.Power.remiria.BloodBruisePower;
import Thmod.Power.remiria.ScarletLordPower;
import basemod.helpers.TooltipInfo;

public class VampireKiss_Remiria extends AbstractRemiriaCards {
    public static final String ID = "VampireKiss_Remiria";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;

    public VampireKiss_Remiria() {
        this(false);
    }

    public VampireKiss_Remiria(boolean isPlus) {
        super("VampireKiss_Remiria", VampireKiss_Remiria.NAME,  1, VampireKiss_Remiria.DESCRIPTION, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY, isPlus);
        this.baseDamage = 2;
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
        this.addTips();
        this.attackType = AttackType.LIGHT;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        if(this.isPlus){
            int num = 0;
            if (m.hasPower(BloodBruisePower.POWER_ID)) {
                num = m.getPower(BloodBruisePower.POWER_ID).amount;
            }
            if(num > 3) {
                AbstractDungeon.actionManager.addToTop(new VampireKissAction(m, new DamageInfo(p, this.damage), 3, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
                m.getPower(BloodBruisePower.POWER_ID).amount -= 3;
            }
            else if(num == 3){
                AbstractDungeon.actionManager.addToTop(new VampireKissAction(m, new DamageInfo(p, this.damage), 3, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
                AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(m, m, BloodBruisePower.POWER_ID));
            }
            else {
                AbstractDungeon.actionManager.addToTop(new VampireKissAction(m, new DamageInfo(p, this.damage), num, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
                for(int i = 0; i < 3 - num; i ++){
                    AbstractDungeon.actionManager.addToTop(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
                }
                AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(m, m, BloodBruisePower.POWER_ID));
            }
        }
        else {
            for (int i = 0; i < this.magicNumber; i++) {
                AbstractDungeon.actionManager.addToTop(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
                if (m.hasPower(BloodBruisePower.POWER_ID)) {
                    if (m.getPower(BloodBruisePower.POWER_ID).amount > 1) {
                        AbstractDungeon.actionManager.addToBottom(new HealAction(p, p, 2));
                        m.getPower(BloodBruisePower.POWER_ID).amount -= 1;
                    } else if (m.getPower(BloodBruisePower.POWER_ID).amount == 1) {
                        AbstractDungeon.actionManager.addToBottom(new HealAction(p, p, 2));
                        m.getPower(BloodBruisePower.POWER_ID).amount -= 1;
                        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(m, m, BloodBruisePower.POWER_ID));
                    }
                }
            }
        }
        super.use(p, m);
    }

    public AbstractCard makeCopy() {
        if(AbstractDungeon.player != null){
            if((AbstractDungeon.player instanceof RemiriaScarlet) && (AbstractDungeon.player.hasPower(ScarletLordPower.POWER_ID))){
                return new VampireKiss_Remiria(true);
            }
        }
        return new VampireKiss_Remiria();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.upgradeDamage(2);
        }
    }

    @Override
    public void addTips() {
        this.tips.clear();
        this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("VampireKiss_Remiria");
        NAME = VampireKiss_Remiria.cardStrings.NAME;
        DESCRIPTION = VampireKiss_Remiria.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = VampireKiss_Remiria.cardStrings.EXTENDED_DESCRIPTION;
    }
}
