package Thmod.Cards.ScarletCard.uncommonCards;

import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
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
import Thmod.Power.remiria.ScarletLordPower;
import basemod.helpers.TooltipInfo;

public class FateCollect extends AbstractRemiriaCards {
    public static final String ID = "FateCollect";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;
    private int count = 0;

    public FateCollect() {
        this(false);
    }

    public FateCollect(boolean isPlus) {
        super("FateCollect", FateCollect.NAME,  0, FateCollect.DESCRIPTION, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF, isPlus);
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
        this.addTips();
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        this.count = 0;
        final ChooseAction choice = new ChooseAction(null, null, Absorbed.EXTENDED_DESCRIPTION[2], true, Math.min(this.magicNumber, p.hand.size()));
        for (AbstractCard card : p.hand.group) {
            if(card == this){
                continue;
            }
            choice.add(card, () -> {
                p.hand.moveToDeck(card, true);
                AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, 1));
            });
        }
        AbstractDungeon.actionManager.addToBottom(choice);
        if(!this.isPlus){
            this.exhaust = true;
            AbstractDungeon.actionManager.addToBottom(new LatterAction(()->{
                this.exhaust = false;
            }, 0.1f));
        }
        super.use(p, m);
    }

    public AbstractCard makeCopy() {
        if(AbstractDungeon.player != null){
            if((AbstractDungeon.player instanceof RemiriaScarlet) && (AbstractDungeon.player.hasPower(ScarletLordPower.POWER_ID))){
                return new FateCollect(true);
            }
        }
        return new FateCollect();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            //this.upgradeBaseCost(0);
            this.upgradeMagicNumber(1);
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
        cardStrings = CardCrawlGame.languagePack.getCardStrings("FateCollect");
        NAME = FateCollect.cardStrings.NAME;
        DESCRIPTION = FateCollect.cardStrings.DESCRIPTION;
        UPGRADE_DESCRIPTION = FateCollect.cardStrings.UPGRADE_DESCRIPTION;
        EXTENDED_DESCRIPTION = FateCollect.cardStrings.EXTENDED_DESCRIPTION;
    }
}
