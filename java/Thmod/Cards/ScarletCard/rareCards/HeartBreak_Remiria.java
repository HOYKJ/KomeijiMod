package Thmod.Cards.ScarletCard.rareCards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.ending.CorruptHeart;
import com.megacrit.cardcrawl.powers.StrengthPower;

import Thmod.Actions.unique.ChooseAction;
import Thmod.Cards.ScarletCard.Absorbed;
import Thmod.Cards.ScarletCard.AbstractRemiriaCards;
import Thmod.Characters.RemiriaScarlet;
import Thmod.Power.remiria.BloodBruisePower;
import Thmod.Power.remiria.ScarletLordPower;
import basemod.helpers.TooltipInfo;

public class HeartBreak_Remiria extends AbstractRemiriaCards {
    public static final String ID = "HeartBreak_Remiria";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;

    public HeartBreak_Remiria() {
        this(false);
    }

    public HeartBreak_Remiria(boolean isPlus) {
        super("HeartBreak_Remiria", HeartBreak_Remiria.NAME,  2, HeartBreak_Remiria.DESCRIPTION, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY, isPlus);
        this.baseDamage = 12;
        this.baseMagicNumber = 4;
        this.magicNumber = this.baseMagicNumber;
        this.isMultiDamage = true;
        this.addTips();
        this.attackType = AttackType.LIGHT;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        if(AbstractDungeon.getCurrRoom().monsters.monsters.get(0) instanceof CorruptHeart){
            this.multiDamage[0] += 999;
        }
        if(this.isPlus){
            if (p.hasPower(StrengthPower.POWER_ID)) {
                for (int i = 0; i < this.multiDamage.length; i++) {
                    this.multiDamage[i] += p.getPower(StrengthPower.POWER_ID).amount * (this.magicNumber - 1);
                }
            }
            AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_HEAVY));
            if (p.hasPower(StrengthPower.POWER_ID)) {
                for (int i = 0; i < AbstractDungeon.getCurrRoom().monsters.monsters.size(); i++) {
                    AbstractMonster target = AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
                    if ((!(target.isDying)) && (target.currentHealth > 0) && (!(target.isEscaping))) {
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target, p, new BloodBruisePower(target,
                                (int) ((float) this.magicNumber / 3 * p.getPower(StrengthPower.POWER_ID).amount)),
                                (int) ((float) this.magicNumber / 3 * p.getPower(StrengthPower.POWER_ID).amount)));
                    }
                }
            }
        }
        else {
            final ChooseAction choice = new ChooseAction(this, null, Absorbed.EXTENDED_DESCRIPTION[2], false, 1);
            choice.add(this.name, EXTENDED_DESCRIPTION[3], () -> {
                if (p.hasPower(StrengthPower.POWER_ID)) {
                    for (int i = 0; i < this.multiDamage.length; i++) {
                        this.multiDamage[i] += p.getPower(StrengthPower.POWER_ID).amount * (this.magicNumber - 1);
                    }
                }
                AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_HEAVY));
            });
            choice.add(this.name, EXTENDED_DESCRIPTION[4], () -> {
                AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_HEAVY));
                if (p.hasPower(StrengthPower.POWER_ID)) {
                    for (int i = 0; i < AbstractDungeon.getCurrRoom().monsters.monsters.size(); i++) {
                        AbstractMonster target = AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
                        if ((!(target.isDying)) && (target.currentHealth > 0) && (!(target.isEscaping))) {
                            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target, p, new BloodBruisePower(target,
                                    (int) ((float) this.magicNumber / 3 * p.getPower(StrengthPower.POWER_ID).amount)),
                                    (int) ((float) this.magicNumber / 3 * p.getPower(StrengthPower.POWER_ID).amount)));
                        }
                    }
                }
            });
            AbstractDungeon.actionManager.addToBottom(choice);
        }
        super.use(p, m);
    }

    public AbstractCard makeCopy() {
        if(AbstractDungeon.player != null){
            if((AbstractDungeon.player instanceof RemiriaScarlet) && (AbstractDungeon.player.hasPower(ScarletLordPower.POWER_ID))){
                return new HeartBreak_Remiria(true);
            }
        }
        return new HeartBreak_Remiria();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            //this.upgradeDamage(4);
            this.upgradeMagicNumber(2);
            this.rawDescription = UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public void plusCard() {
        super.plusCard();
        this.name = EXTENDED_DESCRIPTION[2];
        this.initializeTitle();
    }

    @Override
    public void normalCard() {
        super.normalCard();
        this.name = NAME;
        this.initializeTitle();
    }

    @Override
    public void addTips() {
        this.tips.clear();
        this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("HeartBreak_Remiria");
        NAME = HeartBreak_Remiria.cardStrings.NAME;
        DESCRIPTION = HeartBreak_Remiria.cardStrings.DESCRIPTION;
        UPGRADE_DESCRIPTION = HeartBreak_Remiria.cardStrings.UPGRADE_DESCRIPTION;
        EXTENDED_DESCRIPTION = HeartBreak_Remiria.cardStrings.EXTENDED_DESCRIPTION;
    }
}
