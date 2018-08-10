package Thmod.Cards.ElementCards.RareCards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Cards.ElementCards.AbstractElementCards;

public class RoyalFlare extends AbstractElementCards {
    public static final String ID = "RoyalFlare";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 3;

    public RoyalFlare() {
        super("RoyalFlare", RoyalFlare.NAME,  3, RoyalFlare.DESCRIPTION, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY,ElementType.Sun);
        this.baseDamage = 30;
        this.isMultiDamage = true;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        super.use(p,m);
        if(AbstractDungeon.getCurrRoom().monsters.monsters.get(0).id.equals("Remiria")) {
            AbstractDungeon.getCurrRoom().monsters.monsters.get(0).currentHealth = 0;
            AbstractDungeon.getCurrRoom().monsters.monsters.get(0).healthBarUpdatedEvent();
        }
        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.FIRE));
    }

    public AbstractCard makeCopy() {
        return new RoyalFlare();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.upgradeDamage(15);
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("RoyalFlare");
        NAME = RoyalFlare.cardStrings.NAME;
        DESCRIPTION = RoyalFlare.cardStrings.DESCRIPTION;
    }
}
