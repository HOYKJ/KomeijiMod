package Thmod.Cards.ScarletCard;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import Thmod.Actions.Remiria.PlusCardAction;
import Thmod.Actions.unique.ChooseAction;
import Thmod.Characters.RemiriaScarlet;
import Thmod.Power.remiria.BloodBruisePower;
import Thmod.Power.remiria.ScarletLordPower;
import basemod.helpers.TooltipInfo;

public class FastHealing extends AbstractRemiriaCards {
    public static final String ID = "FastHealing";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;

    public FastHealing() {
        this(false);
    }

    public FastHealing(boolean isPlus) {
        super("FastHealing", FastHealing.NAME,  0, FastHealing.DESCRIPTION, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF, isPlus);
        this.baseMagicNumber = 4;
        this.magicNumber = this.baseMagicNumber;
        this.addTips();
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        //AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, 1));
        if(p.hasPower(BloodBruisePower.POWER_ID)){
            AbstractPower blood = p.getPower(BloodBruisePower.POWER_ID);
            if(blood.amount > this.magicNumber){
                blood.amount -= this.magicNumber;
                AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.magicNumber));
                if(this.isPlus){
                    AbstractDungeon.actionManager.addToBottom(new HealAction(p, p, this.magicNumber));
                }
            }
            else {
                AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, blood.amount));
                AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, blood));
                if(this.isPlus){
                    AbstractDungeon.actionManager.addToBottom(new HealAction(p, p, blood.amount));
                }
            }
        }
        if(this.upgraded){
            final ChooseAction choice = new ChooseAction(null, null, Absorbed.EXTENDED_DESCRIPTION[2], true, 1);
            for (AbstractCard card : p.hand.group) {
                choice.add(card, () -> {
                    AbstractDungeon.actionManager.addToBottom(new ExhaustSpecificCardAction(card, p.hand));
                });
            }
            AbstractDungeon.actionManager.addToBottom(choice);
        }
        super.use(p, m);
    }

    public AbstractCard makeCopy() {
        if(AbstractDungeon.player != null){
            if((AbstractDungeon.player instanceof RemiriaScarlet) && (AbstractDungeon.player.hasPower(ScarletLordPower.POWER_ID))){
                return new FastHealing(true);
            }
        }
        return new FastHealing();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.upgradeMagicNumber(2);
            this.rawDescription = UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public void addTips() {
        this.tips.clear();
        this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("FastHealing");
        NAME = FastHealing.cardStrings.NAME;
        DESCRIPTION = FastHealing.cardStrings.DESCRIPTION;
        UPGRADE_DESCRIPTION = FastHealing.cardStrings.UPGRADE_DESCRIPTION;
        EXTENDED_DESCRIPTION = FastHealing.cardStrings.EXTENDED_DESCRIPTION;
    }
}
