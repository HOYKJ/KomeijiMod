package Thmod.Cards.ElementCards.SpellCards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Actions.common.RandomAttackAction;

public class NoachianDeluge extends AbstractElementSpellCards {
    public static final String ID = "NoachianDeluge";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 2;

    public NoachianDeluge() {
        super("NoachianDeluge", NoachianDeluge.NAME,  2, NoachianDeluge.DESCRIPTION, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ALL_ENEMY,ElementType.Earth,ElementType.Water);
        this.baseDamage = 4;
        this.isMultiDamage = true;
        this.baseMagicNumber = 8;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        super.use(p,m);
        AbstractDungeon.actionManager.addToBottom(new RandomAttackAction(AbstractDungeon.getMonsters().getRandomMonster(true), new DamageInfo(p, this.baseDamage), this.magicNumber, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
    }

    public AbstractCard makeCopy() {
        return new NoachianDeluge();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("NoachianDeluge");
        NAME = NoachianDeluge.cardStrings.NAME;
        DESCRIPTION = NoachianDeluge.cardStrings.DESCRIPTION;
    }
}
