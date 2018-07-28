package Thmod.Cards.ElementCards.UncommonCards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

import Thmod.Actions.common.RandomAttackAction;
import Thmod.Cards.ElementCards.AbstractElementSweepCards;

public class AutumnEdge extends AbstractElementSweepCards {
    public static final String ID = "AutumnEdge";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 2;

    public AutumnEdge() {
        super("AutumnEdge", AutumnEdge.NAME,  2, AutumnEdge.DESCRIPTION, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.NONE,ElementType.Metal);
        this.baseDamage = 5;
        this.baseMagicNumber = 5;
        this.magicNumber = baseMagicNumber;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        super.use(p,m);
        AbstractDungeon.actionManager.addToBottom(new RandomAttackAction(AbstractDungeon.getMonsters().getRandomMonster(true), new DamageInfo(p, this.baseDamage), this.magicNumber, AbstractGameAction.AttackEffect.SLASH_HEAVY));
    }

    @Override
    public ArrayList<AbstractElementSweepCards> getOpposite() {
        final ArrayList<AbstractElementSweepCards> opposite = new ArrayList<>();
        opposite.add(new AutumnBlades());
        opposite.add(new FallThrasher());
        return opposite;
    }

    public AbstractCard makeCopy() {
        return new AutumnEdge();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("AutumnEdge");
        NAME = AutumnEdge.cardStrings.NAME;
        DESCRIPTION = AutumnEdge.cardStrings.DESCRIPTION;
    }
}
