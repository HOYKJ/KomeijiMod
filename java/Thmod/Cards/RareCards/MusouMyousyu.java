package Thmod.Cards.RareCards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Actions.common.RandomAttackAction;
import Thmod.Cards.AbstractKomeijiCards;

public class MusouMyousyu extends AbstractKomeijiCards {
    public static final String ID = "MusouMyousyu";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 3;
    private static final int ATTACK_DMG = 5;

    public MusouMyousyu() {
        super("MusouMyousyu", MusouMyousyu.NAME,  3, MusouMyousyu.DESCRIPTION, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY);
        this.baseDamage = 5;
        this.baseMagicNumber = 7;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(new RandomAttackAction(AbstractDungeon.getMonsters().getRandomMonster(true), new DamageInfo(p, this.baseDamage), this.magicNumber, AbstractGameAction.AttackEffect.BLUNT_HEAVY));
    }

    public AbstractCard makeCopy() {
        return new MusouMyousyu();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.name = "回忆「梦想封印」";
            this.initializeTitle();
            this.upgradeDamage(1);
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("MusouMyousyu");
        NAME = MusouMyousyu.cardStrings.NAME;
        DESCRIPTION = MusouMyousyu.cardStrings.DESCRIPTION;
    }
}
