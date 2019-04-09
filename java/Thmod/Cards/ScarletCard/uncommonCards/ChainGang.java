package Thmod.Cards.ScarletCard.uncommonCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

import java.util.List;

import Thmod.Cards.ScarletCard.AbstractRemiriaCards;
import Thmod.Characters.RemiriaScarlet;
import Thmod.Power.remiria.BloodBruisePower;
import Thmod.Power.remiria.ScarletLordPower;
import basemod.helpers.TooltipInfo;

public class ChainGang extends AbstractRemiriaCards {
    public static final String ID = "ChainGang";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;

    public ChainGang() {
        this(false);
    }

    public ChainGang(boolean isPlus) {
        super("ChainGang", ChainGang.NAME,  1, ChainGang.DESCRIPTION, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY, isPlus);
        this.baseMagicNumber = 6;
        this.magicNumber = this.baseMagicNumber;
        this.addTips();
        this.attackType = AttackType.CHAIN;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        if(this.isPlus){
            for (int i = 0; i < AbstractDungeon.getCurrRoom().monsters.monsters.size(); i++) {
                AbstractMonster target = AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
                if ((!(target.isDying)) && (target.currentHealth > 0) && (!(target.isEscaping))) {
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target, p, new WeakPower(target, this.magicNumber,false), this.magicNumber));
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target, p, new BloodBruisePower(target, 3), 3));
                }
            }
        }
        else {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new WeakPower(m, this.magicNumber, false), this.magicNumber));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new BloodBruisePower(m, 3), 3));
        }
        super.use(p, m);
    }

    public AbstractCard makeCopy() {
        if(AbstractDungeon.player != null){
            if((AbstractDungeon.player instanceof RemiriaScarlet) && (AbstractDungeon.player.hasPower(ScarletLordPower.POWER_ID))){
                return new ChainGang(true);
            }
        }
        return new ChainGang();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.upgradeMagicNumber(4);
        }
    }

    @Override
    public void plusCard() {
        super.plusCard();
        this.target = CardTarget.ALL_ENEMY;
    }

    @Override
    public void normalCard() {
        super.normalCard();
        this.target = CardTarget.ENEMY;
    }

    @Override
    public void addTips() {
        this.tips.clear();
        this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("ChainGang");
        NAME = ChainGang.cardStrings.NAME;
        DESCRIPTION = ChainGang.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = ChainGang.cardStrings.EXTENDED_DESCRIPTION;
    }
}
