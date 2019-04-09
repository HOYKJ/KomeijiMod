package Thmod.Cards.UncommonCards;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.unique.WhirlwindAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import Thmod.Cards.AbstractKomeijiCards;
import Thmod.ThMod;

public class HisouNoKen extends AbstractKomeijiCards {
    public static final String ID = "HisouNoKen";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;
    private static final int COST = -1;
    private static final int ATTACK_DMG = 5;

    public HisouNoKen() {
        super("HisouNoKen", HisouNoKen.NAME,  -1, HisouNoKen.DESCRIPTION, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY, CardSet_k.TENSHI);
        this.baseDamage = 6;
        this.isMultiDamage = true;
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
    }

    public void calculateCardDamage(AbstractMonster mo){
        super.calculateCardDamage(mo);
        AbstractPlayer p = AbstractDungeon.player;
        for(int i = 0;i < 20;i++){
            String weatherid = ThMod.weathers.get(i);
            if(p.hasPower(weatherid)) {
                for (int i1 = 0; i1 < this.multiDamage.length; i1++) {
                    this.multiDamage[i1] += this.magicNumber;
                }
                this.damage = this.multiDamage[0];
                this.isDamageModified = true;
                break;
            }
        }
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        AbstractPlayer p = AbstractDungeon.player;
        for(int i = 0;i < 20;i++){
            String weatherid = ThMod.weathers.get(i);
            if(p.hasPower(weatherid)) {
                for (int i1 = 0; i1 < this.multiDamage.length; i1++) {
                    this.multiDamage[i1] += this.magicNumber;
                }
                this.damage = this.multiDamage[0];
                this.isDamageModified = true;
                break;
            }
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        if (this.energyOnUse < EnergyPanel.totalCount) {
            this.energyOnUse = EnergyPanel.totalCount;
        }

        AbstractDungeon.actionManager.addToBottom(new WhirlwindAction(p, this.multiDamage, this.damageTypeForTurn, this.freeToPlayOnce, this.energyOnUse));
    }

    public AbstractCard makeCopy() {
        return new HisouNoKen();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.name = EXTENDED_DESCRIPTION[0];
            this.initializeTitle();
            this.upgradeDamage(3);
            this.timesUpgraded += 1;
            this.upgraded = true;
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("HisouNoKen");
        NAME = HisouNoKen.cardStrings.NAME;
        DESCRIPTION = HisouNoKen.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = HisouNoKen.cardStrings.EXTENDED_DESCRIPTION;
    }
}
