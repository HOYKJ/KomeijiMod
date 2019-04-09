package Thmod.Cards.ScarletCard.rewardCards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Actions.Remiria.BloodyKnifeAction;
import Thmod.Cards.ScarletCard.AbstractRemiriaCards;
import Thmod.Characters.RemiriaScarlet;
import Thmod.Power.remiria.BloodBruisePower;
import Thmod.Power.remiria.ScarletLordPower;
import basemod.helpers.TooltipInfo;

public class SuperhumanBloodyKnife extends AbstractRemiriaCards {
    public static final String ID = "SuperhumanBloodyKnife";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;

    public SuperhumanBloodyKnife() {
        this(false);
    }

    public SuperhumanBloodyKnife(boolean isPlus) {
        super("SuperhumanBloodyKnife", SuperhumanBloodyKnife.NAME,  3, SuperhumanBloodyKnife.DESCRIPTION, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY, isPlus);
        this.baseDamage = 6;
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
        this.addTips();
        this.attackType = AttackType.HEAVY;
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        if(AbstractDungeon.player.hasPower(BloodBruisePower.POWER_ID)){
            this.damage += AbstractDungeon.player.getPower(BloodBruisePower.POWER_ID).amount * this.magicNumber;
        }
        for (int i = 0; i < AbstractDungeon.getCurrRoom().monsters.monsters.size(); i++) {
            AbstractMonster target = AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
            if ((!(target.isDying)) && (target.currentHealth > 0) && (!(target.isEscaping))) {
                if(target.hasPower(BloodBruisePower.POWER_ID)){
                    this.damage += target.getPower(BloodBruisePower.POWER_ID).amount * this.magicNumber;
                }
            }
        }
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
        if(AbstractDungeon.player.hasPower(BloodBruisePower.POWER_ID)){
            this.damage += AbstractDungeon.player.getPower(BloodBruisePower.POWER_ID).amount * this.magicNumber;
        }
        for (int i = 0; i < AbstractDungeon.getCurrRoom().monsters.monsters.size(); i++) {
            AbstractMonster target = AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
            if ((!(target.isDying)) && (target.currentHealth > 0) && (!(target.isEscaping))) {
                if(target.hasPower(BloodBruisePower.POWER_ID)){
                    this.damage += target.getPower(BloodBruisePower.POWER_ID).amount * this.magicNumber;
                }
            }
        }
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        if(m.hasPower(BloodBruisePower.POWER_ID)){
            AbstractDungeon.actionManager.addToBottom(new BloodyKnifeAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn),
                    AbstractGameAction.AttackEffect.SLASH_HEAVY, m.getPower(BloodBruisePower.POWER_ID).amount, this.isPlus));
        }
        else {
            AbstractDungeon.actionManager.addToBottom(new BloodyKnifeAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn),
                    AbstractGameAction.AttackEffect.SLASH_HEAVY, 0, this.isPlus));
        }
        super.use(p, m);
    }

    public AbstractCard makeCopy() {
        if(AbstractDungeon.player != null){
            if((AbstractDungeon.player instanceof RemiriaScarlet) && (AbstractDungeon.player.hasPower(ScarletLordPower.POWER_ID))){
                return new SuperhumanBloodyKnife(true);
            }
        }
        return new SuperhumanBloodyKnife();
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
        cardStrings = CardCrawlGame.languagePack.getCardStrings("SuperhumanBloodyKnife");
        NAME = SuperhumanBloodyKnife.cardStrings.NAME;
        DESCRIPTION = SuperhumanBloodyKnife.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = SuperhumanBloodyKnife.cardStrings.EXTENDED_DESCRIPTION;
    }
}
