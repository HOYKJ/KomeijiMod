package Thmod.Cards.ScarletCard.uncommonCards;

import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Actions.Remiria.PlusCardAction;
import Thmod.Actions.common.LatterAction;
import Thmod.Actions.unique.ChooseAction;
import Thmod.Cards.ScarletCard.Absorbed;
import Thmod.Cards.ScarletCard.AbstractRemiriaCards;
import Thmod.Characters.RemiriaScarlet;
import Thmod.Power.remiria.ScarletLordPower;
import basemod.helpers.TooltipInfo;

public class ThePrice extends AbstractRemiriaCards {
    public static final String ID = "ThePrice";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;

    public ThePrice() {
        this(false);
    }

    public ThePrice(boolean isPlus) {
        super("ThePrice", ThePrice.NAME,  0, ThePrice.DESCRIPTION, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF, isPlus);
        this.exhaust = true;
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.addTips();
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        final ChooseAction choice = new ChooseAction(null, null, Absorbed.EXTENDED_DESCRIPTION[2], true, this.magicNumber);
        if(this.isPlus){
            for (AbstractCard card : p.drawPile.group) {
                choice.add(card, () -> {
                    AbstractDungeon.actionManager.addToTop(new ExhaustSpecificCardAction(card, p.drawPile));
                    AbstractDungeon.actionManager.addToBottom(new LatterAction(()->{
                        AbstractDungeon.actionManager.addToBottom(new PlusCardAction(p.hand, this));
                    }, 0.1f));
                });
            }
            for (AbstractCard card : p.hand.group) {
                if(card != this) {
                    choice.add(card, () -> {
                        AbstractDungeon.actionManager.addToTop(new ExhaustSpecificCardAction(card, p.hand));
                        AbstractDungeon.actionManager.addToBottom(new LatterAction(()->{
                            AbstractDungeon.actionManager.addToBottom(new PlusCardAction(p.hand, this));
                        }, 0.1f));
                    });
                }
            }
            for (AbstractCard card : p.discardPile.group) {
                if(card != this) {
                    choice.add(card, () -> {
                        AbstractDungeon.actionManager.addToTop(new ExhaustSpecificCardAction(card, p.discardPile));
                        AbstractDungeon.actionManager.addToBottom(new LatterAction(()->{
                            AbstractDungeon.actionManager.addToBottom(new PlusCardAction(p.hand, this));
                        }, 0.1f));
                    });
                }
            }
        }
        else {
            for (AbstractCard card : p.hand.group) {
                if(card != this) {
                    choice.add(card, () -> {
                        AbstractDungeon.actionManager.addToTop(new ExhaustSpecificCardAction(card, p.hand));
                        AbstractDungeon.actionManager.addToBottom(new LatterAction(()->{
                            AbstractDungeon.actionManager.addToBottom(new PlusCardAction(p.hand, this));
                        }, 0.1f));
                    });
                }
            }
        }
        AbstractDungeon.actionManager.addToBottom(choice);
        super.use(p, m);
    }

    public AbstractCard makeCopy() {
        if(AbstractDungeon.player != null){
            if((AbstractDungeon.player instanceof RemiriaScarlet) && (AbstractDungeon.player.hasPower(ScarletLordPower.POWER_ID))){
                return new ThePrice(true);
            }
        }
        return new ThePrice();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
        }
    }

    @Override
    public void addTips() {
        this.tips.clear();
        this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("ThePrice");
        NAME = ThePrice.cardStrings.NAME;
        DESCRIPTION = ThePrice.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = ThePrice.cardStrings.EXTENDED_DESCRIPTION;
    }
}
