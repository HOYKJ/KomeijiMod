package Thmod.Cards.ScarletCard.uncommonCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Cards.ScarletCard.AbstractRemiriaCards;
import Thmod.Characters.RemiriaScarlet;
import Thmod.Power.remiria.BloodBruisePower;
import Thmod.Power.remiria.ScarletLordPower;
import basemod.helpers.TooltipInfo;

public class VampirishNight extends AbstractRemiriaCards {
    public static final String ID = "VampirishNight";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;

    public VampirishNight() {
        this(false);
    }

    public VampirishNight(boolean isPlus) {
        super("VampirishNight", VampirishNight.NAME,  2, VampirishNight.DESCRIPTION, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY, isPlus);
        this.baseMagicNumber = 5;
        this.magicNumber = this.baseMagicNumber;
        this.addTips();
        this.attackType = AttackType.CHAIN;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        for (int i = 0; i < AbstractDungeon.getCurrRoom().monsters.monsters.size(); i++) {
            AbstractMonster target = AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
            if ((!(target.isDying)) && (target.currentHealth > 0) && (!(target.isEscaping))) {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target, p, new BloodBruisePower(target, this.magicNumber), this.magicNumber));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target, p, new BloodBruisePower(target, this.magicNumber), this.magicNumber));
            }
        }
        super.use(p, m);
    }

    public AbstractCard makeCopy() {
        if(AbstractDungeon.player != null){
            if((AbstractDungeon.player instanceof RemiriaScarlet) && (AbstractDungeon.player.hasPower(ScarletLordPower.POWER_ID))){
                return new VampirishNight(true);
            }
        }
        return new VampirishNight();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.upgradeMagicNumber(2);
        }
    }

    @Override
    public void plusCard() {
        super.plusCard();
        this.modifyCostForCombat(-9);
    }

    @Override
    public void normalCard() {
        super.normalCard();
        if(this.cost < 2) {
            this.modifyCostForCombat(2 - this.cost);
        }
    }

    @Override
    public void addTips() {
        this.tips.clear();
        this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("VampirishNight");
        NAME = VampirishNight.cardStrings.NAME;
        DESCRIPTION = VampirishNight.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = VampirishNight.cardStrings.EXTENDED_DESCRIPTION;
    }
}
