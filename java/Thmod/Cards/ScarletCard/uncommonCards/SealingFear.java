package Thmod.Cards.ScarletCard.uncommonCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import java.util.List;

import Thmod.Actions.unique.ChooseAction;
import Thmod.Cards.ScarletCard.AbstractRemiriaCards;
import Thmod.Characters.RemiriaScarlet;
import Thmod.Power.remiria.ScarletLordPower;
import Thmod.ThMod;
import basemod.helpers.TooltipInfo;

public class SealingFear extends AbstractRemiriaCards {
    public static final String ID = "SealingFear";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;

    public SealingFear() {
        this(false);
    }

    public SealingFear(boolean isPlus) {
        super("SealingFear", SealingFear.NAME,  1, SealingFear.DESCRIPTION, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY, isPlus);
        this.baseMagicNumber = 6;
        this.magicNumber = this.baseMagicNumber;
        this.addTips();
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new VulnerablePower(m, this.magicNumber, false), this.magicNumber));
        boolean plus = this.isPlus;
        final ChooseAction choice = new ChooseAction(null, null, SealingFear.EXTENDED_DESCRIPTION[2], true, 1, true);
        for (AbstractCard card : p.hand.group) {
            if((card instanceof AbstractRemiriaCards) && (card != this))
                choice.add(card, () -> {
                    if(plus){
                        if (card.cost > 0) {
                            card.freeToPlayOnce = true;
                        }
                    }
                    ((AbstractRemiriaCards) card).plusCard();
                    p.hand.moveToDeck(card, false);
                    AbstractDungeon.player.hand.refreshHandLayout();
                });
        }
        AbstractDungeon.actionManager.addToBottom(choice);
        super.use(p, m);
    }

    public AbstractCard makeCopy() {
        if(AbstractDungeon.player != null){
            if((AbstractDungeon.player instanceof RemiriaScarlet) && (AbstractDungeon.player.hasPower(ScarletLordPower.POWER_ID))){
                return new SealingFear(true);
            }
        }
        return new SealingFear();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.upgradeBaseCost(0);
        }
    }

    @Override
    public void addTips() {
        this.tips.clear();
        this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("SealingFear");
        NAME = SealingFear.cardStrings.NAME;
        DESCRIPTION = SealingFear.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = SealingFear.cardStrings.EXTENDED_DESCRIPTION;
    }
}
