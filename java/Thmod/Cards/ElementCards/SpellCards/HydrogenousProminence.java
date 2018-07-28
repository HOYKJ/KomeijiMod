package Thmod.Cards.ElementCards.SpellCards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;

import Thmod.Actions.common.RandomAttackAction;
import Thmod.Orbs.ElementOrb.AbstractElementOrb;

public class HydrogenousProminence extends AbstractElementSpellCards {
    public static final String ID = "HydrogenousProminence";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;
    private static final int COST = 2;

    public HydrogenousProminence() {
        super("HydrogenousProminence", HydrogenousProminence.NAME,  2, HydrogenousProminence.DESCRIPTION, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ALL_ENEMY,ElementType.Sun,ElementType.Water);
        this.baseDamage = 3;
        this.isMultiDamage = true;
        this.baseMagicNumber = 0;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        super.use(p,m);
        this.damage = 3;
        this.baseMagicNumber = 0;
        for (AbstractOrb o : AbstractDungeon.actionManager.orbsChanneledThisCombat) {
            if (o instanceof AbstractElementOrb) {
                this.baseMagicNumber += 1;
            }
        }
        this.magicNumber = this.baseMagicNumber;
        AbstractDungeon.actionManager.addToBottom(new RandomAttackAction(AbstractDungeon.getMonsters().getRandomMonster(true), new DamageInfo(p, this.baseDamage), this.magicNumber, AbstractGameAction.AttackEffect.FIRE));
    }

    public void applyPowers()
    {
        super.applyPowers();

        this.baseMagicNumber = 0;
        this.magicNumber = 0;
        for (AbstractOrb o : AbstractDungeon.actionManager.orbsChanneledThisCombat) {
            if (o instanceof AbstractElementOrb) {
                this.baseMagicNumber += 1;
            }
        }
        if (this.baseMagicNumber > 0)
        {
            this.rawDescription = (DESCRIPTION + EXTENDED_DESCRIPTION[0]);
            initializeDescription();
        }
    }

    public void onMoveToDiscard()
    {
        this.rawDescription = DESCRIPTION;
        initializeDescription();
    }

    public void calculateCardDamage(AbstractMonster mo)
    {
        super.calculateCardDamage(mo);
        if (this.baseMagicNumber > 0) {
            this.rawDescription = (DESCRIPTION + EXTENDED_DESCRIPTION[0]);
        }
        initializeDescription();
    }

    public AbstractCard makeCopy() {
        return new HydrogenousProminence();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("HydrogenousProminence");
        NAME = HydrogenousProminence.cardStrings.NAME;
        DESCRIPTION = HydrogenousProminence.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = HydrogenousProminence.cardStrings.EXTENDED_DESCRIPTION;
    }
}
