package Thmod.Cards.ScarletCard;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Actions.Remiria.PlusCardAction;
import Thmod.Actions.common.LatterAction;
import Thmod.Actions.unique.ChooseAction;
import Thmod.Characters.RemiriaScarlet;
import Thmod.Power.remiria.ScarletLordPower;
import basemod.helpers.TooltipInfo;

public class Absorbed extends AbstractRemiriaCards {
    public static final String ID = "Absorbed";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;

    public Absorbed() {
        this(false);
    }

    public Absorbed(boolean isPlus) {
        super("Absorbed", Absorbed.NAME,  1, Absorbed.DESCRIPTION, CardType.SKILL, CardRarity.BASIC, CardTarget.NONE, isPlus);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.addTips();
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        if (this.isPlus) {
            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, this.magicNumber));
        }
        AbstractDungeon.actionManager.addToBottom(new LatterAction(() -> {
            if (this.upgraded) {
                final ChooseAction choice = new ChooseAction(null, null, Absorbed.EXTENDED_DESCRIPTION[2], true, 2);
                for (AbstractCard card : p.hand.group) {
                    if ((card instanceof AbstractRemiriaCards) && (card != this) && (!((AbstractRemiriaCards) card).isPlus)) {
                        choice.add(card, () -> {
                            AbstractDungeon.actionManager.addToBottom(new PlusCardAction((AbstractRemiriaCards) card));
                        });
                    }
                }
                AbstractDungeon.actionManager.addToBottom(choice);
            } else {
                AbstractDungeon.actionManager.addToBottom(new PlusCardAction(p.hand, 2, this));
            }
        }, 0.1f));
        super.use(p, m);
    }

    public AbstractCard makeCopy() {
        if(AbstractDungeon.player != null){
            if((AbstractDungeon.player instanceof RemiriaScarlet) && (AbstractDungeon.player.hasPower(ScarletLordPower.POWER_ID))){
                return new Absorbed(true);
            }
        }
        return new Absorbed();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
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
        cardStrings = CardCrawlGame.languagePack.getCardStrings("Absorbed");
        NAME = Absorbed.cardStrings.NAME;
        DESCRIPTION = Absorbed.cardStrings.DESCRIPTION;
        UPGRADE_DESCRIPTION = Absorbed.cardStrings.UPGRADE_DESCRIPTION;
        EXTENDED_DESCRIPTION = Absorbed.cardStrings.EXTENDED_DESCRIPTION;
    }
}
