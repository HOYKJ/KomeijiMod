package Thmod.Cards.ScarletCard;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

import Thmod.Actions.common.LatterAction;
import Thmod.Characters.RemiriaScarlet;
import Thmod.Power.remiria.BloodBruisePower;
import Thmod.Power.remiria.ScarletLordPower;
import basemod.helpers.TooltipInfo;

public class DoubleEdge extends AbstractRemiriaCards {
    public static final String ID = "DoubleEdge";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;

    public DoubleEdge() {
        this(false);
    }

    public DoubleEdge(boolean isPlus) {
        super("DoubleEdge", DoubleEdge.NAME,  0, DoubleEdge.DESCRIPTION, CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL, isPlus);
        this.baseMagicNumber = 4;
        this.magicNumber = this.baseMagicNumber;
        this.addTips();
        this.attackType = AttackType.LIGHT;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new BloodBruisePower(p, this.magicNumber), this.magicNumber));
        for (int i = 0; i < AbstractDungeon.getCurrRoom().monsters.monsters.size(); i++) {
            AbstractMonster target = AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
            if ((!(target.isDying)) && (target.currentHealth > 0) && (!(target.isEscaping))) {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target, p, new BloodBruisePower(target, this.magicNumber), this.magicNumber));
            }
        }
        if(this.isPlus){
//            AbstractDungeon.actionManager.addToTop(new ExhaustSpecificCardAction(this, p.hand));
//            AbstractDungeon.actionManager.addToTop(new ExhaustSpecificCardAction(this, p.discardPile));
            this.exhaust = true;
            AbstractDungeon.actionManager.addToBottom(new LatterAction(()->{
                this.exhaust = false;
            }, 0.1f));
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(this));
        }
        super.use(p, m);
    }

    public AbstractCard makeCopy() {
        if(AbstractDungeon.player != null){
            if((AbstractDungeon.player instanceof RemiriaScarlet) && (AbstractDungeon.player.hasPower(ScarletLordPower.POWER_ID))){
                return new DoubleEdge(true);
            }
        }
        return new DoubleEdge();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.upgradeMagicNumber(2);
        }
    }

//    @Override
//    public void plusCard() {
//        super.plusCard();
//        this.exhaust = true;
//    }
//
//    @Override
//    public void normalCard() {
//        super.normalCard();
//
//    }

    @Override
    public void addTips() {
        this.tips.clear();
        this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("DoubleEdge");
        NAME = DoubleEdge.cardStrings.NAME;
        DESCRIPTION = DoubleEdge.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = DoubleEdge.cardStrings.EXTENDED_DESCRIPTION;
    }
}
