package Thmod.Cards.ScarletCard.uncommonCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Actions.common.LatterAction;
import Thmod.Cards.ScarletCard.AbstractRemiriaCards;
import Thmod.Characters.RemiriaScarlet;
import Thmod.Power.remiria.BloodyMagicSquarePower;
import Thmod.Power.remiria.ScarletLordPower;
import basemod.helpers.TooltipInfo;

public class BloodyMagicSquare extends AbstractRemiriaCards {
    public static final String ID = "BloodyMagicSquare";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;

    public BloodyMagicSquare() {
        this(false);
    }

    public BloodyMagicSquare(boolean isPlus) {
        super("BloodyMagicSquare", BloodyMagicSquare.NAME,  1, BloodyMagicSquare.DESCRIPTION, CardType.POWER, CardRarity.UNCOMMON, CardTarget.NONE, isPlus);
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
        this.addTips();
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new BloodyMagicSquarePower(p, this.magicNumber), this.magicNumber));

        if(this.isPlus){
            AbstractDungeon.actionManager.addToBottom(new LatterAction(()->{
                if(p.hasPower(BloodyMagicSquarePower.POWER_ID)){
                    ((BloodyMagicSquarePower)p.getPower(BloodyMagicSquarePower.POWER_ID)).trigger();
                }
            }, 0.1f));
        }
        super.use(p, m);
    }

    public AbstractCard makeCopy() {
        if(AbstractDungeon.player != null){
            if((AbstractDungeon.player instanceof RemiriaScarlet) && (AbstractDungeon.player.hasPower(ScarletLordPower.POWER_ID))){
                return new BloodyMagicSquare(true);
            }
        }
        return new BloodyMagicSquare();
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
        cardStrings = CardCrawlGame.languagePack.getCardStrings("BloodyMagicSquare");
        NAME = BloodyMagicSquare.cardStrings.NAME;
        DESCRIPTION = BloodyMagicSquare.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = BloodyMagicSquare.cardStrings.EXTENDED_DESCRIPTION;
    }
}
