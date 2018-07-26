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
import com.megacrit.cardcrawl.vfx.combat.MindblastEffect;

import Thmod.Cards.AbstractKomeijiCards;

import static Thmod.ThMod.AllzhsOpen;

public class NarrowSpark extends AbstractKomeijiCards {
    public static final String ID = "NarrowSpark";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 1;
    private static final int ATTACK_DMG = 3;
    private boolean addDone;

    public NarrowSpark() {
        super("NarrowSpark", NarrowSpark.NAME,  1, NarrowSpark.DESCRIPTION, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        this.baseDamage = 3;
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.isMultiDamage = true;
        this.addDone = false;
    }

    public void calculateCardDamage(AbstractMonster mo){
        super.calculateCardDamage(mo);
        AbstractPlayer p = AbstractDungeon.player;
        if (p.hasPower("Strength")) {
            if (p.getPower("Strength").amount > 0) {
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
        AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new MindblastEffect(p.dialogX, (p.dialogY - 100F)), 0.10000000149011612F));
        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));
        this.addDone = false;
    }

    public AbstractCard makeCopy() {
        return new NarrowSpark();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            if(AllzhsOpen)
                this.name = "回忆「极限火花」";
            else
                this.name = "回忆「Master Spark」";
            this.initializeTitle();
            this.upgradeMagicNumber(1);
            this.upgradeDamage(5);
            this.upgradeBaseCost(2);
            this.timesUpgraded += 1;
            this.upgraded = true;
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("NarrowSpark");
        NAME = NarrowSpark.cardStrings.NAME;
        DESCRIPTION = NarrowSpark.cardStrings.DESCRIPTION;
    }
}
