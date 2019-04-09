package Thmod.Cards.ScarletCard.uncommonCards;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Actions.Remiria.DrawFromDiscardAction;
import Thmod.Actions.common.DrawSpeCardAction;
import Thmod.Actions.common.LatterAction;
import Thmod.Actions.unique.ChooseAction;
import Thmod.Cards.ScarletCard.Absorbed;
import Thmod.Cards.ScarletCard.AbstractRemiriaCards;
import Thmod.Characters.RemiriaScarlet;
import Thmod.Power.remiria.ScarletLordPower;
import basemod.helpers.TooltipInfo;

public class Rewrite extends AbstractRemiriaCards {
    public static final String ID = "Rewrite";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;

    public Rewrite() {
        this(false);
    }

    public Rewrite(boolean isPlus) {
        super("Rewrite", Rewrite.NAME,  0, Rewrite.DESCRIPTION, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF, isPlus);
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.addTips();
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        final boolean[] needDiscard = {false};
        final int[] otherCards = {0};
        final ChooseAction choice = new ChooseAction(null, null, Absorbed.EXTENDED_DESCRIPTION[2], true, this.magicNumber, true);
        for (AbstractCard card : p.drawPile.group) {
            if((p instanceof RemiriaScarlet) && (!((RemiriaScarlet) p).masterGroupCopy.contains(card))) {
                choice.add(card, () -> {
                    AbstractDungeon.actionManager.addToBottom(new DrawSpeCardAction(card));
                    needDiscard[0] = true;
                    otherCards[0]++;
                });
            }
        }
        for (AbstractCard card : p.discardPile.group) {
            if((p instanceof RemiriaScarlet) && (!((RemiriaScarlet) p).masterGroupCopy.contains(card))) {
                choice.add(card, () -> {
                    AbstractDungeon.actionManager.addToBottom(new DrawFromDiscardAction(card));
                    needDiscard[0] = true;
                    otherCards[0]++;
                });
            }
        }
        AbstractDungeon.actionManager.addToBottom(choice);

        AbstractDungeon.actionManager.addToBottom(new LatterAction(()->{
            if((!this.isPlus) && (needDiscard[0])) {
                if(otherCards[0] < 2){
                    AbstractDungeon.actionManager.addToBottom(new DiscardAction(p, p, 1, false));
                }
                else {
                    AbstractDungeon.actionManager.addToBottom(new DiscardAction(p, p, 2, false));
                }
            }
        }, 0f));
        super.use(p, m);
    }

    public AbstractCard makeCopy() {
        if(AbstractDungeon.player != null){
            if((AbstractDungeon.player instanceof RemiriaScarlet) && (AbstractDungeon.player.hasPower(ScarletLordPower.POWER_ID))){
                return new Rewrite(true);
            }
        }
        return new Rewrite();
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
        cardStrings = CardCrawlGame.languagePack.getCardStrings("Rewrite");
        NAME = Rewrite.cardStrings.NAME;
        DESCRIPTION = Rewrite.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = Rewrite.cardStrings.EXTENDED_DESCRIPTION;
    }
}
