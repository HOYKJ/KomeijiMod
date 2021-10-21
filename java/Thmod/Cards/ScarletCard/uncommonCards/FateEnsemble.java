package Thmod.Cards.ScarletCard.uncommonCards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Actions.Remiria.PlusCardAction;
import Thmod.Cards.ScarletCard.AbstractRemiriaFate;
import Thmod.Characters.RemiriaScarlet;
import Thmod.Power.remiria.ScarletLordPower;
import basemod.helpers.TooltipInfo;

public class FateEnsemble extends AbstractRemiriaFate {
    public static final String ID = "FateEnsemble";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;

    public FateEnsemble() {
        this(false);
    }

    public FateEnsemble(boolean isPlus) {
        super("FateEnsemble", FateEnsemble.NAME,  -2, FateEnsemble.DESCRIPTION, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY, isPlus);
        this.baseBlock = 4;
        this.addTips();
        this.attackType = AttackType.CHAIN;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        this.applyPowers();
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
        if(p instanceof RemiriaScarlet) {
            for (AbstractCard card : p.hand.group) {
                if(this.isPlus){
                    card.upgrade();
                }
                else if (!((RemiriaScarlet) p).masterGroupCopy.contains(card)){
                    card.upgrade();
                }
                if(((RemiriaScarlet) p).masterGroupCopy.contains(card)){
                    ((RemiriaScarlet) p).masterGroupCopy.remove(card);
                }
            }
        }
        super.use(p, m);
    }

    @Override
    public void triggerOnExhaust() {
        super.triggerOnExhaust();
        AbstractDungeon.actionManager.addToBottom(new PlusCardAction(AbstractDungeon.player.drawPile, true, this, true));
        AbstractDungeon.actionManager.addToBottom(new PlusCardAction(AbstractDungeon.player.hand, true, this, true));
        AbstractDungeon.actionManager.addToBottom(new PlusCardAction(AbstractDungeon.player.discardPile, true, this, true));
    }

    public AbstractCard makeCopy() {
        if(AbstractDungeon.player != null){
            if((AbstractDungeon.player instanceof RemiriaScarlet) && (AbstractDungeon.player.hasPower(ScarletLordPower.POWER_ID))){
                return new FateEnsemble(true);
            }
        }
        return new FateEnsemble();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.upgradeBlock(4);
        }
    }

    @Override
    public void addTips() {
        this.tips.clear();
        this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("FateEnsemble");
        NAME = FateEnsemble.cardStrings.NAME;
        DESCRIPTION = FateEnsemble.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = FateEnsemble.cardStrings.EXTENDED_DESCRIPTION;
    }
}
