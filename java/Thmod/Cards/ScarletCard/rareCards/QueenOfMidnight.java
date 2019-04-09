package Thmod.Cards.ScarletCard.rareCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

import Thmod.Cards.ScarletCard.AbstractRemiriaCards;
import Thmod.Cards.ScarletCard.AbstractRemiriaFate;
import Thmod.Characters.RemiriaScarlet;
import Thmod.Power.remiria.ScarletLordPower;
import basemod.helpers.TooltipInfo;

public class QueenOfMidnight extends AbstractRemiriaFate {
    public static final String ID = "QueenOfMidnight";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;

    public QueenOfMidnight() {
        this(false);
    }

    public QueenOfMidnight(boolean isPlus) {
        super("QueenOfMidnight", QueenOfMidnight.NAME,  -2, QueenOfMidnight.DESCRIPTION, CardType.POWER, CardRarity.RARE, CardTarget.NONE, isPlus);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
        this.addTips();
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        boolean tem = false;
        if(this.isPlus){
            tem = true;
        }
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new ScarletLordPower(p, this.magicNumber), this.magicNumber));
        if(tem){
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, 3), 3));
        }
        super.use(p, m);
    }

    @Override
    public void triggerWhenDrawn() {
        super.triggerWhenDrawn();
        this.use(AbstractDungeon.player, null);
    }

    public AbstractCard makeCopy() {
        if(AbstractDungeon.player != null){
            if((AbstractDungeon.player instanceof RemiriaScarlet) && (AbstractDungeon.player.hasPower(ScarletLordPower.POWER_ID))){
                return new QueenOfMidnight(true);
            }
        }
        return new QueenOfMidnight();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.exhaust = false;
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
        cardStrings = CardCrawlGame.languagePack.getCardStrings("QueenOfMidnight");
        NAME = QueenOfMidnight.cardStrings.NAME;
        DESCRIPTION = QueenOfMidnight.cardStrings.DESCRIPTION;
        UPGRADE_DESCRIPTION = QueenOfMidnight.cardStrings.UPGRADE_DESCRIPTION;
        EXTENDED_DESCRIPTION = QueenOfMidnight.cardStrings.EXTENDED_DESCRIPTION;
    }
}
