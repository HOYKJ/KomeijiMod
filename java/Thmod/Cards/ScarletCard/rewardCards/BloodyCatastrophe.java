package Thmod.Cards.ScarletCard.rewardCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

import Thmod.Cards.ScarletCard.AbstractRemiriaCards;
import Thmod.Characters.RemiriaScarlet;
import Thmod.Power.remiria.BloodBruisePower;
import Thmod.Power.remiria.ScarletLordPower;
import basemod.helpers.TooltipInfo;

public class BloodyCatastrophe extends AbstractRemiriaCards {
    public static final String ID = "BloodyCatastrophe";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;

    public BloodyCatastrophe() {
        this(false);
    }

    public BloodyCatastrophe(boolean isPlus) {
        super("BloodyCatastrophe", BloodyCatastrophe.NAME,  2, BloodyCatastrophe.DESCRIPTION, CardType.SKILL, CardRarity.SPECIAL, CardTarget.NONE, isPlus);
        this.addTips();
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        int counter = 0;
        ArrayList<AbstractCard> cards = new ArrayList<>();
        for(AbstractCard c : p.drawPile.group){
            counter ++;
            AbstractCard card = AbstractDungeon.returnTrulyRandomCardInCombat().makeCopy();
            if(this.upgraded){
                card.upgrade();
            }
            if((card instanceof AbstractRemiriaCards) && (this.isPlus)){
                ((AbstractRemiriaCards) card).plusCard();
            }
            cards.add(card);

            p.exhaustPile.addToTop(c);
        }
        p.drawPile.group.clear();
        p.drawPile.group.addAll(cards);

        for(AbstractCard c : p.hand.group){
            counter ++;
            AbstractDungeon.actionManager.addToTop(new ExhaustSpecificCardAction(c, p.hand));
            AbstractCard card = AbstractDungeon.returnTrulyRandomCardInCombat().makeCopy();
            if(this.upgraded){
                card.upgrade();
            }
            if((card instanceof AbstractRemiriaCards) && (this.isPlus)){
                ((AbstractRemiriaCards) card).plusCard();
            }
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(card));
        }

        cards.clear();
        for(AbstractCard c : p.discardPile.group){
            counter ++;
            AbstractCard card = AbstractDungeon.returnTrulyRandomCardInCombat().makeCopy();
            if(this.upgraded){
                card.upgrade();
            }
            if((card instanceof AbstractRemiriaCards) && (this.isPlus)){
                ((AbstractRemiriaCards) card).plusCard();
            }
            cards.add(card);

            p.exhaustPile.addToTop(c);
        }
        p.discardPile.group.clear();
        p.discardPile.group.addAll(cards);

        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new BloodBruisePower(p, counter), counter));
        super.use(p, m);
    }

    public AbstractCard makeCopy() {
        if(AbstractDungeon.player != null){
            if((AbstractDungeon.player instanceof RemiriaScarlet) && (AbstractDungeon.player.hasPower(ScarletLordPower.POWER_ID))){
                return new BloodyCatastrophe(true);
            }
        }
        return new BloodyCatastrophe();
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
        cardStrings = CardCrawlGame.languagePack.getCardStrings("BloodyCatastrophe");
        NAME = BloodyCatastrophe.cardStrings.NAME;
        DESCRIPTION = BloodyCatastrophe.cardStrings.DESCRIPTION;
        UPGRADE_DESCRIPTION = BloodyCatastrophe.cardStrings.UPGRADE_DESCRIPTION;
        EXTENDED_DESCRIPTION = BloodyCatastrophe.cardStrings.EXTENDED_DESCRIPTION;
    }
}
