package Thmod.Cards.UncommonCards;

import com.megacrit.cardcrawl.actions.unique.WhirlwindAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import Thmod.Cards.AbstractKomeijiCards;

public class HisouNoKen extends AbstractKomeijiCards {
    public static final String ID = "HisouNoKen";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;
    private static final int COST = -1;
    private static final int ATTACK_DMG = 5;

    public HisouNoKen() {
        super("HisouNoKen", HisouNoKen.NAME,  -1, HisouNoKen.DESCRIPTION, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        this.baseDamage = 5;
        this.isMultiDamage = true;
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
