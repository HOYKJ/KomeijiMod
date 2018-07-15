package Thmod.Cards.UncommonCards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Cards.AbstractKomeijiCards;

public class SenceofElegance extends AbstractKomeijiCards {
    public static final String ID = "SenceofElegance";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 2;
    private static final int ATTACK_DMG = 12;

    public SenceofElegance()
    {
        this(0);
    }

    public SenceofElegance(int upgrades) {
        super("SenceofElegance", SenceofElegance.NAME,  2, SenceofElegance.DESCRIPTION, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = 12;
        this.timesUpgraded = upgrades;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
    }

    public AbstractCard makeCopy() {
        return new SenceofElegance(this.timesUpgraded);
    }

    public void upgrade() {
        this.upgradeDamage(4 + this.timesUpgraded);
        this.timesUpgraded += 1;
        this.upgraded = true;
        this.name = NAME + "+" + this.timesUpgraded;
        initializeTitle();
    }

    public boolean canUpgrade()
    {
        return true;
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("SenceofElegance");
        NAME = SenceofElegance.cardStrings.NAME;
        DESCRIPTION = SenceofElegance.cardStrings.DESCRIPTION;
    }
}
