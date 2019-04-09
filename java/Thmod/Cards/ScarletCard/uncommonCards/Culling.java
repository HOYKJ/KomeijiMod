package Thmod.Cards.ScarletCard.uncommonCards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Actions.Remiria.CullingAction;
import Thmod.Cards.ScarletCard.AbstractRemiriaCards;
import Thmod.Characters.RemiriaScarlet;
import Thmod.Power.remiria.BloodBruisePower;
import Thmod.Power.remiria.ScarletLordPower;
import basemod.helpers.TooltipInfo;

public class Culling extends AbstractRemiriaCards {
    public static final String ID = "Culling";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;

    public Culling() {
        this(false);
    }

    public Culling(boolean isPlus) {
        super("Culling", Culling.NAME,  3, Culling.DESCRIPTION, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY, isPlus);
        this.baseDamage = 0;
        this.baseMagicNumber = 6;
        this.magicNumber = this.baseMagicNumber;
        this.addTips();
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
        if(mo.hasPower(BloodBruisePower.POWER_ID)) {
            this.damage += mo.getPower(BloodBruisePower.POWER_ID).amount * this.magicNumber;
        }
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToTop(new CullingAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), 3, this.isPlus));
        super.use(p, m);
    }

    public AbstractCard makeCopy() {
        if(AbstractDungeon.player != null){
            if((AbstractDungeon.player instanceof RemiriaScarlet) && (AbstractDungeon.player.hasPower(ScarletLordPower.POWER_ID))){
                return new Culling(true);
            }
        }
        return new Culling();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.upgradeMagicNumber(2);
        }
    }

    @Override
    public void addTips() {
        this.tips.clear();
        this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("Culling");
        NAME = Culling.cardStrings.NAME;
        DESCRIPTION = Culling.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = Culling.cardStrings.EXTENDED_DESCRIPTION;
    }
}
