package Thmod.Cards.ScarletCard;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.List;

import Thmod.Characters.RemiriaScarlet;
import Thmod.Power.remiria.BloodBruisePower;
import Thmod.Power.remiria.ScarletLordPower;
import basemod.helpers.TooltipInfo;

public class Scratch extends AbstractRemiriaCards {
    public static final String ID = "Scratch";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;

    public Scratch() {
        this(false);
    }

    public Scratch(boolean isPlus) {
        super("Scratch", Scratch.NAME,  0, Scratch.DESCRIPTION, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY, isPlus);
        this.baseDamage = 4;
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.addTips();
        this.attackType = AttackType.CHAIN;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToTop(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new BloodBruisePower(m, this.magicNumber), this.magicNumber));
        if(this.isPlus){
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new BloodBruisePower(m, this.magicNumber), this.magicNumber));
        }
        super.use(p, m);
    }

    public AbstractCard makeCopy() {
        if(AbstractDungeon.player != null){
            if((AbstractDungeon.player instanceof RemiriaScarlet) && (AbstractDungeon.player.hasPower(ScarletLordPower.POWER_ID))){
                return new Scratch(true);
            }
        }
        return new Scratch();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.upgradeDamage(2);
            this.upgradeMagicNumber(1);
        }
    }

    @Override
    public void addTips() {
        this.tips.clear();
        this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("Scratch");
        NAME = Scratch.cardStrings.NAME;
        DESCRIPTION = Scratch.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = Scratch.cardStrings.EXTENDED_DESCRIPTION;
    }
}
