package Thmod.Cards.ScarletCard.uncommonCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.MetallicizePower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import Thmod.Actions.Remiria.PlusCardAction;
import Thmod.Actions.unique.ChooseAction;
import Thmod.Cards.ScarletCard.Absorbed;
import Thmod.Cards.ScarletCard.AbstractRemiriaCards;
import Thmod.Characters.RemiriaScarlet;
import Thmod.Power.remiria.ScarletLordPower;
import basemod.helpers.TooltipInfo;

public class PartStrengthen extends AbstractRemiriaCards {
    public static final String ID = "PartStrengthen";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;

    public PartStrengthen() {
        this(false);
    }

    public PartStrengthen(boolean isPlus) {
        super("PartStrengthen", PartStrengthen.NAME,  1, PartStrengthen.DESCRIPTION, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF, isPlus);
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.addTips();
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        final ChooseAction choice;
        if(this.isPlus){
            choice = new ChooseAction(this, null, Absorbed.EXTENDED_DESCRIPTION[2], true, 2);
        }
        else {
            choice = new ChooseAction(this, null, Absorbed.EXTENDED_DESCRIPTION[2], true, 1);
        }
        choice.add(EXTENDED_DESCRIPTION[5], EXTENDED_DESCRIPTION[2], () -> {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, this.magicNumber), this.magicNumber));
        });
        choice.add(EXTENDED_DESCRIPTION[6], EXTENDED_DESCRIPTION[3], () -> {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DexterityPower(p, this.magicNumber), this.magicNumber));
        });
        if(this.upgraded){
            choice.add(EXTENDED_DESCRIPTION[7], EXTENDED_DESCRIPTION[4], () -> {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new MetallicizePower(p, 5), this.magicNumber + 1));
            });
        }
        else {
            choice.add(EXTENDED_DESCRIPTION[7], EXTENDED_DESCRIPTION[4], () -> {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new MetallicizePower(p, 3), this.magicNumber + 1));
            });
        }
        AbstractDungeon.actionManager.addToBottom(choice);
        super.use(p, m);
    }

    public AbstractCard makeCopy() {
        if(AbstractDungeon.player != null){
            if((AbstractDungeon.player instanceof RemiriaScarlet) && (AbstractDungeon.player.hasPower(ScarletLordPower.POWER_ID))){
                return new PartStrengthen(true);
            }
        }
        return new PartStrengthen();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
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
        cardStrings = CardCrawlGame.languagePack.getCardStrings("PartStrengthen");
        NAME = PartStrengthen.cardStrings.NAME;
        DESCRIPTION = PartStrengthen.cardStrings.DESCRIPTION;
        UPGRADE_DESCRIPTION = PartStrengthen.cardStrings.UPGRADE_DESCRIPTION;
        EXTENDED_DESCRIPTION = PartStrengthen.cardStrings.EXTENDED_DESCRIPTION;
    }
}
