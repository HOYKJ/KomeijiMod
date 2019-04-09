package Thmod.Cards.ScarletCard.rareCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

import Thmod.Cards.ScarletCard.AbstractRemiriaCards;
import Thmod.Characters.RemiriaScarlet;
import Thmod.Power.remiria.RemiliaStretchPower;
import Thmod.Power.remiria.ScarletLordPower;
import basemod.helpers.TooltipInfo;

public class RemiliaStretch_Remiria extends AbstractRemiriaCards {
    public static final String ID = "RemiliaStretch_Remiria";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;

    public RemiliaStretch_Remiria() {
        this(false);
    }

    public RemiliaStretch_Remiria(boolean isPlus) {
        super("RemiliaStretch_Remiria", RemiliaStretch_Remiria.NAME,  2, RemiliaStretch_Remiria.DESCRIPTION, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY, isPlus);
        this.baseDamage = 18;
        this.baseMagicNumber = 6;
        this.magicNumber = this.baseMagicNumber;
        this.addTips();
        this.attackType = null;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
//        int realDamage = this.baseDamage;
//        if(p.hasPower(StrengthPower.POWER_ID)){
//            realDamage += p.getPower(StrengthPower.POWER_ID).amount * this.magicNumber;
//        }
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new RemiliaStretchPower(p, this.damage, this.isPlus), this.damage));
        super.use(p, m);
    }

    public void calculateCardDamage(AbstractMonster mo){
        super.calculateCardDamage(mo);
        AbstractPlayer p = AbstractDungeon.player;
        if (p.hasPower(StrengthPower.POWER_ID)) {
            if (p.getPower(StrengthPower.POWER_ID).amount > 0) {
                this.damage += (p.getPower("Strength").amount * (this.magicNumber - 1));
                this.isDamageModified = true;
            }
        }
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        AbstractPlayer p = AbstractDungeon.player;
        if (p.hasPower(StrengthPower.POWER_ID)) {
            if (p.getPower(StrengthPower.POWER_ID).amount > 0) {
                this.damage += (p.getPower("Strength").amount * (this.magicNumber - 1));
                this.isDamageModified = true;
            }
        }
    }

    public AbstractCard makeCopy() {
        if(AbstractDungeon.player != null){
            if((AbstractDungeon.player instanceof RemiriaScarlet) && (AbstractDungeon.player.hasPower(ScarletLordPower.POWER_ID))){
                return new RemiliaStretch_Remiria(true);
            }
        }
        return new RemiliaStretch_Remiria();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.upgradeDamage(6);
            this.upgradeMagicNumber(2);
        }
    }

    @Override
    public void addTips() {
        this.tips.clear();
        this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("RemiliaStretch_Remiria");
        NAME = RemiliaStretch_Remiria.cardStrings.NAME;
        DESCRIPTION = RemiliaStretch_Remiria.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = RemiliaStretch_Remiria.cardStrings.EXTENDED_DESCRIPTION;
    }
}
