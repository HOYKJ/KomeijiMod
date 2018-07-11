package Thmod.Cards.DeriveCards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DemonsFork extends AbstractDeriveCards {
    public static final String ID = "DemonsFork";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 0;

    public DemonsFork(int magicNum) {
        super("DemonsFork", DemonsFork.NAME,  0, DemonsFork.DESCRIPTION, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ALL_ENEMY);
        if(this.upgraded)
            this.baseDamage = (7 * magicNum);
        else
            this.baseDamage = (5 * magicNum);
        this.isMultiDamage = true;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));
        if(AbstractDungeon.player.hasPower("DemonsForkAccumulate"))
            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(p,p,"DemonsForkAccumulate"));
    }

    public AbstractCard makeCopy() {
        return new DemonsFork(0);
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("DemonsFork");
        NAME = DemonsFork.cardStrings.NAME;
        DESCRIPTION = DemonsFork.cardStrings.DESCRIPTION;
    }
}
