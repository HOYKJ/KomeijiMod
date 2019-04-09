package Thmod.Cards.ScarletCard.uncommonCards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import Thmod.Actions.Remiria.RemoveRandomBuffAction;
import Thmod.Cards.ScarletCard.AbstractRemiriaCards;
import Thmod.Characters.RemiriaScarlet;
import Thmod.Power.remiria.BloodBruisePower;
import Thmod.Power.remiria.ScarletLordPower;
import basemod.helpers.TooltipInfo;

public class SeparateFleshBlood extends AbstractRemiriaCards {
    public static final String ID = "SeparateFleshBlood";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;

    public SeparateFleshBlood() {
        this(false);
    }

    public SeparateFleshBlood(boolean isPlus) {
        super("SeparateFleshBlood", SeparateFleshBlood.NAME,  1, SeparateFleshBlood.DESCRIPTION, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY, isPlus);
        this.baseDamage = 8;
        this.baseMagicNumber = 4;
        this.magicNumber = this.baseMagicNumber;
        this.addTips();
        this.attackType = AttackType.LIGHT;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToTop(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
        if(this.isPlus){
            AbstractDungeon.actionManager.addToBottom(new RemoveRandomBuffAction(m));
        }
        else {
            if (m.hasPower(BloodBruisePower.POWER_ID)) {
                AbstractPower blood = m.getPower(BloodBruisePower.POWER_ID);
                if (blood.amount > 4) {
                    blood.amount -= 4;
                    AbstractDungeon.actionManager.addToBottom(new RemoveRandomBuffAction(m));
                } else if (blood.amount == 4) {
                    AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(m, m, blood));
                    AbstractDungeon.actionManager.addToBottom(new RemoveRandomBuffAction(m));
                }
            }
        }
        super.use(p, m);
    }

    public AbstractCard makeCopy() {
        if(AbstractDungeon.player != null){
            if((AbstractDungeon.player instanceof RemiriaScarlet) && (AbstractDungeon.player.hasPower(ScarletLordPower.POWER_ID))){
                return new SeparateFleshBlood(true);
            }
        }
        return new SeparateFleshBlood();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.upgradeDamage(4);
        }
    }

    @Override
    public void addTips() {
        this.tips.clear();
        this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("SeparateFleshBlood");
        NAME = SeparateFleshBlood.cardStrings.NAME;
        DESCRIPTION = SeparateFleshBlood.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = SeparateFleshBlood.cardStrings.EXTENDED_DESCRIPTION;
    }
}
