package Thmod.Cards.ScarletCard.uncommonCards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveAllBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import Thmod.Actions.Remiria.RemoveDebuffsActionChange;
import Thmod.Cards.ScarletCard.AbstractRemiriaCards;
import Thmod.Characters.RemiriaScarlet;
import Thmod.Power.remiria.BloodBruisePower;
import Thmod.Power.remiria.ScarletLordPower;
import Thmod.Power.remiria.TepesPower;
import basemod.helpers.TooltipInfo;

public class Tepes extends AbstractRemiriaCards {
    public static final String ID = "Tepes";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;

    public Tepes() {
        this(false);
    }

    public Tepes(boolean isPlus) {
        super("Tepes", Tepes.NAME,  1, Tepes.DESCRIPTION, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY, isPlus);
        this.baseDamage = 12;
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.addTips();
        this.attackType = AttackType.LIGHT;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToTop(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.HP_LOSS), AbstractGameAction.AttackEffect.SLASH_HEAVY));
        for(AbstractPower power : m.powers){
            if((power.type == AbstractPower.PowerType.DEBUFF) && (power.amount > 0)){
                power.amount ++;
            }
        }
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new TepesPower(m, this.magicNumber), this.magicNumber));
        if(this.isPlus){
            AbstractDungeon.actionManager.addToBottom(new RemoveAllBlockAction(m, p));
        }
        super.use(p, m);
    }

    public AbstractCard makeCopy() {
        if(AbstractDungeon.player != null){
            if((AbstractDungeon.player instanceof RemiriaScarlet) && (AbstractDungeon.player.hasPower(ScarletLordPower.POWER_ID))){
                return new Tepes(true);
            }
        }
        return new Tepes();
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
        cardStrings = CardCrawlGame.languagePack.getCardStrings("Tepes");
        NAME = Tepes.cardStrings.NAME;
        DESCRIPTION = Tepes.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = Tepes.cardStrings.EXTENDED_DESCRIPTION;
    }
}
