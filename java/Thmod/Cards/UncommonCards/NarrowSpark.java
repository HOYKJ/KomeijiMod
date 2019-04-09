package Thmod.Cards.UncommonCards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.combat.MindblastEffect;

import Thmod.Cards.AbstractKomeijiCards;
import Thmod.ThMod;
import Thmod.vfx.SparkEffect;

public class NarrowSpark extends AbstractKomeijiCards {
    public static final String ID = "NarrowSpark";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;
    private static final int COST = 1;
    private static final int ATTACK_DMG = 3;
    private boolean addDone;

    public NarrowSpark() {
        super("NarrowSpark", NarrowSpark.NAME,  1, NarrowSpark.DESCRIPTION, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY, CardSet_k.MARISA);
        this.baseDamage = 6;
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
        this.isMultiDamage = true;
        this.addDone = false;
    }

    public void calculateCardDamage(AbstractMonster mo){
        super.calculateCardDamage(mo);
        AbstractPlayer p = AbstractDungeon.player;
        if (p.hasPower(StrengthPower.POWER_ID)) {
            if (p.getPower(StrengthPower.POWER_ID).amount > 0) {
                for (int i = 0; i < this.multiDamage.length; i++) {
                    this.multiDamage[i] += (p.getPower("Strength").amount * (this.magicNumber - 1));
                }
                this.damage = this.multiDamage[0];
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
                for (int i = 0; i < this.multiDamage.length; i++) {
                    this.multiDamage[i] += (p.getPower("Strength").amount * (this.magicNumber - 1));
                }
                this.damage = this.multiDamage[0];
                this.isDamageModified = true;
            }
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_HEAVY"));
        if(this.upgraded)
            AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new SparkEffect((p.dialogX + 5.0f), (p.dialogY - 10.0f),p.flipHorizontal,2.0F,0), 0.1F));
        else
            AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new SparkEffect((p.dialogX + 5.0f), (p.dialogY - 10.0f),p.flipHorizontal,2.0F,-1), 0.1F));
        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));
        this.addDone = false;
    }

    public AbstractCard makeCopy() {
        return new NarrowSpark();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.name = EXTENDED_DESCRIPTION[0];
            this.initializeTitle();
            this.upgradeMagicNumber(2);
            this.upgradeDamage(6);
            this.upgradeBaseCost(2);
            this.timesUpgraded += 1;
            this.upgraded = true;
            this.textureImg = ThMod.komeijiCardImage(this.cardID,true);
            loadCardImage(this.textureImg);
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("NarrowSpark");
        NAME = NarrowSpark.cardStrings.NAME;
        DESCRIPTION = NarrowSpark.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = NarrowSpark.cardStrings.EXTENDED_DESCRIPTION;
    }
}
