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
    public static final String[] EXTENDED_DESCRIPTION;
    private int count = 0;

    public FateCollect() {
        this(false);
    }

    public FateCollect(boolean isPlus) {
        super("FateCollect", FateCollect.NAME,  1, FateCollect.DESCRIPTION, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF, isPlus);
        this.isEthereal = true;
        this.addTips();
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        boolean attack = true, skill = true, power = true, status = true, curse = true;
        this.count = 0;
        final ChooseAction choice = new ChooseAction(null, null, Absorbed.EXTENDED_DESCRIPTION[2], true, 1);
        for (AbstractCard card : p.hand.group) {
            switch (card.type){
                case ATTACK:
                    if(attack){
                        choice.add(card, EXTENDED_DESCRIPTION[2], () -> {
                            for(AbstractCard card1 : p.hand.group) {
                                if(card1.type != card.type) {
                                    this.count ++;
                                    AbstractDungeon.actionManager.addToBottom(new DiscardSpecificCardAction(card1, p.hand));
                                }
                            }
                            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, (int) (this.count * 1.5f)));
                        });
                        attack = false;
                    }
                    break;
                case SKILL:
                    if(skill){
                        choice.add(card, EXTENDED_DESCRIPTION[3], () -> {
                            for(AbstractCard card1 : p.hand.group) {
                                if(card1.type != card.type) {
                                    this.count ++;
                                    AbstractDungeon.actionManager.addToBottom(new DiscardSpecificCardAction(card1, p.hand));
                                }
                            }
                            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, (int) (this.count * 1.5f)));
                        });
                        skill = false;
                    }
                    break;
                case POWER:
                    if(power){
                        choice.add(card, EXTENDED_DESCRIPTION[4], () -> {
                            for(AbstractCard card1 : p.hand.group) {
                                if(card1.type != card.type) {
                                    this.count ++;
                                    AbstractDungeon.actionManager.addToBottom(new DiscardSpecificCardAction(card1, p.hand));
                                }
                            }
                            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, (int) (this.count * 1.5f)));
                        });
                        power = false;
                    }
                    break;
                case STATUS:
                    if(status){
                        choice.add(card, EXTENDED_DESCRIPTION[5], () -> {
                            for(AbstractCard card1 : p.hand.group) {
                                if(card1.type != card.type) {
                                    this.count ++;
                                    AbstractDungeon.actionManager.addToBottom(new DiscardSpecificCardAction(card1, p.hand));
                                }
                            }
                            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, (int) (this.count * 1.5f)));
                        });
                        status = false;
                    }
                    break;
                case CURSE:
                    if(curse){
                        choice.add(card, EXTENDED_DESCRIPTION[6], () -> {
                            for(AbstractCard card1 : p.hand.group) {
                                if(card1.type != card.type) {
                                    this.count ++;
                                    AbstractDungeon.actionManager.addToBottom(new DiscardSpecificCardAction(card1, p.hand));
                                }
                            }
                            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, (int) (this.count * 1.5f)));
                        });
                        curse = false;
                    }
                    break;
            }
        }
        AbstractDungeon.actionManager.addToBottom(choice);
        if(!this.isPlus){
            this.exhaust = true;
            AbstractDungeon.actionManager.addToBottom(new LatterAction(()->{
                this.exhaust = false;
            }, 0.1f));
//            AbstractDungeon.actionManager.addToTop(new ExhaustSpecificCardAction(this, p.hand));
//            AbstractDungeon.actionManager.addToTop(new ExhaustSpecificCardAction(this, p.discardPile));
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
        cardStrings = CardCrawlGame.languagePack.getCardStrings("FateCollect");
        NAME = FateCollect.cardStrings.NAME;
        DESCRIPTION = FateCollect.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = FateCollect.cardStrings.EXTENDED_DESCRIPTION;
    }
}
