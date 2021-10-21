package Thmod.Cards.ScarletCard.uncommonCards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Cards.ScarletCard.AbstractRemiriaCards;
import Thmod.Characters.RemiriaScarlet;
import Thmod.Power.remiria.BloodBruisePower;
import Thmod.Power.remiria.ScarletLordPower;
import basemod.helpers.TooltipInfo;

public class PainBurst extends AbstractRemiriaCards {
    public static final String ID = "PainBurst";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;

    public PainBurst() {
        this(false);
    }

    public PainBurst(boolean isPlus) {
        super("PainBurst", PainBurst.NAME,  4, PainBurst.DESCRIPTION, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY, isPlus);
        this.baseDamage = 24;
        this.isMultiDamage = true;
        this.addTips();
        this.attackType = AttackType.LIGHT;
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
        if(this.isPlus){
            if(AbstractDungeon.player.hasPower(BloodBruisePower.POWER_ID)) {
                for (int i = 0; i < this.multiDamage.length; i++) {
                    this.multiDamage[i] += AbstractDungeon.player.getPower(BloodBruisePower.POWER_ID).amount * 2;
                }
                this.damage = this.multiDamage[0];
                this.isDamageModified = true;
            }
        }
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        super.use(p, m);
    }

    public void reduceCost(){
        updateCost(-1);
    }

    public AbstractCard makeCopy() {
        if(AbstractDungeon.player != null){
            if((AbstractDungeon.player instanceof RemiriaScarlet) && (AbstractDungeon.player.hasPower(ScarletLordPower.POWER_ID))){
                return new PainBurst(true);
            }
        }
        return new PainBurst();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.upgradeBaseCost(3);
        }
    }

    @Override
    public void addTips() {
        this.tips.clear();
        this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("PainBurst");
        NAME = PainBurst.cardStrings.NAME;
        DESCRIPTION = PainBurst.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = PainBurst.cardStrings.EXTENDED_DESCRIPTION;
    }
}
