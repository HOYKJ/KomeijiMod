package Thmod.Cards.ScarletCard.uncommonCards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

import Thmod.Cards.ScarletCard.AbstractRemiriaCards;
import Thmod.Characters.RemiriaScarlet;
import Thmod.Power.remiria.BloodBruisePower;
import Thmod.Power.remiria.ScarletLordPower;
import basemod.helpers.TooltipInfo;

public class Impregnable extends AbstractRemiriaCards {
    public static final String ID = "Impregnable";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;

    public Impregnable() {
        this(false);
    }

    public Impregnable(boolean isPlus) {
        super("Impregnable", Impregnable.NAME,  2, Impregnable.DESCRIPTION, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF, isPlus);
        this.baseBlock = 14;
        this.addTips();
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        this.block += AbstractDungeon.player.hand.group.size();
        if(this.isPlus){
            if(AbstractDungeon.player.hasPower(StrengthPower.POWER_ID)){
                this.block += AbstractDungeon.player.getPower(StrengthPower.POWER_ID).amount;
            }
        }
        this.isBlockModified = true;
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
        this.block += AbstractDungeon.player.hand.group.size();
        if(this.isPlus){
            if(AbstractDungeon.player.hasPower(StrengthPower.POWER_ID)){
                this.block += AbstractDungeon.player.getPower(StrengthPower.POWER_ID).amount;
            }
        }
        this.isBlockModified = true;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));

        super.use(p, m);
    }

    public AbstractCard makeCopy() {
        if(AbstractDungeon.player != null){
            if((AbstractDungeon.player instanceof RemiriaScarlet) && (AbstractDungeon.player.hasPower(ScarletLordPower.POWER_ID))){
                return new Impregnable(true);
            }
        }
        return new Impregnable();
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
        cardStrings = CardCrawlGame.languagePack.getCardStrings("Impregnable");
        NAME = Impregnable.cardStrings.NAME;
        DESCRIPTION = Impregnable.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = Impregnable.cardStrings.EXTENDED_DESCRIPTION;
    }
}
