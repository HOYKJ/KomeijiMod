package Thmod.Cards.ScarletCard.uncommonCards;

import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Cards.ScarletCard.AbstractRemiriaCards;
import Thmod.Characters.RemiriaScarlet;
import Thmod.Power.remiria.ScarletLordPower;
import basemod.helpers.TooltipInfo;

public class NightDance extends AbstractRemiriaCards {
    public static final String ID = "NightDance";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;

    public NightDance() {
        this(false);
    }

    public NightDance(boolean isPlus) {
        super("NightDance", NightDance.NAME,  1, NightDance.DESCRIPTION, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE, isPlus);
        this.baseBlock = 8;
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
        this.addTips();
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToTop(new GainBlockAction(p, p, this.block));
        super.use(p, m);
    }

    public void onUsedCard(AbstractCard card){
        if((card.type == CardType.ATTACK) && (!this.isPlus)){
            AbstractDungeon.actionManager.addToTop(new DiscardSpecificCardAction(this, AbstractDungeon.player.hand));
        }
        else {
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, this.magicNumber));
        }
    }

    public AbstractCard makeCopy() {
        if(AbstractDungeon.player != null){
            if((AbstractDungeon.player instanceof RemiriaScarlet) && (AbstractDungeon.player.hasPower(ScarletLordPower.POWER_ID))){
                return new NightDance(true);
            }
        }
        return new NightDance();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.upgradeBlock(2);
            this.upgradeMagicNumber(2);
        }
    }

    @Override
    public void addTips() {
        this.tips.clear();
        this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("NightDance");
        NAME = NightDance.cardStrings.NAME;
        DESCRIPTION = NightDance.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = NightDance.cardStrings.EXTENDED_DESCRIPTION;
    }
}
