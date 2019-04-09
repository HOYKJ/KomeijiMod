package Thmod.Cards.ScarletCard.uncommonCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

import Thmod.Actions.common.LatterAction;
import Thmod.Cards.ScarletCard.AbstractRemiriaCards;
import Thmod.Characters.RemiriaScarlet;
import Thmod.Power.remiria.BloodBruisePower;
import Thmod.Power.remiria.ScarletLordPower;
import basemod.helpers.TooltipInfo;

public class ThirstForBlood extends AbstractRemiriaCards {
    public static final String ID = "ThirstForBlood";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;

    public ThirstForBlood() {
        this(false);
    }

    public ThirstForBlood(boolean isPlus) {
        super("ThirstForBlood", ThirstForBlood.NAME,  1, ThirstForBlood.DESCRIPTION, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ALL, isPlus);
        this.exhaust = true;
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.addTips();
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        if(p.hasPower(BloodBruisePower.POWER_ID)){
            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, this.magicNumber));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, this.magicNumber), this.magicNumber));
        }
        for (int i = 0; i < AbstractDungeon.getCurrRoom().monsters.monsters.size(); i++) {
            AbstractMonster target = AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
            if ((!(target.isDying)) && (target.currentHealth > 0) && (!(target.isEscaping))) {
                if(target.hasPower(BloodBruisePower.POWER_ID)){
                    AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, this.magicNumber));
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, this.magicNumber), this.magicNumber));
                }
            }
        }
        if(this.isPlus){
            this.exhaust = false;
            AbstractDungeon.actionManager.addToBottom(new LatterAction(()->{
                this.exhaust = true;
            }, 0.1f));
//            AbstractDungeon.actionManager.addToTop(new ExhaustSpecificCardAction(this, p.hand));
//            AbstractDungeon.actionManager.addToTop(new ExhaustSpecificCardAction(this, p.discardPile));
        }
        super.use(p, m);
    }

    public AbstractCard makeCopy() {
        if(AbstractDungeon.player != null){
            if((AbstractDungeon.player instanceof RemiriaScarlet) && (AbstractDungeon.player.hasPower(ScarletLordPower.POWER_ID))){
                return new ThirstForBlood(true);
            }
        }
        return new ThirstForBlood();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.upgradeBaseCost(0);
        }
    }

//    @Override
//    public void plusCard() {
//        super.plusCard();
//        this.exhaust = false;
//    }
//
//    @Override
//    public void normalCard() {
//        super.normalCard();
//        AbstractDungeon.actionManager.addToBottom(new LatterAction(()->{
//            this.exhaust = true;
//        }, 0.1f));
//    }

    @Override
    public void addTips() {
        this.tips.clear();
        this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("ThirstForBlood");
        NAME = ThirstForBlood.cardStrings.NAME;
        DESCRIPTION = ThirstForBlood.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = ThirstForBlood.cardStrings.EXTENDED_DESCRIPTION;
    }
}
