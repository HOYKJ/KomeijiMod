package Thmod.Cards.ElementCards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
public class SummerFlame extends AbstractElementSweepCards {
    public static final String ID = "SummerFlame";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 1;

    public SummerFlame() {
        super("SummerFlame", SummerFlame.NAME,  1, SummerFlame.DESCRIPTION, CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL_ENEMY,ElementType.Fire);
        this.baseDamage = 8;
        this.isMultiDamage = true;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        super.use(p,m);
        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.FIRE));
    }

    @Override
    public ArrayList<AbstractElementSweepCards> getOpposite() {
        final ArrayList<AbstractElementSweepCards> opposite = new ArrayList<>();
        opposite.add(new SummerRed());
        opposite.add(new WipeMoisture());
        return opposite;
    }

    public AbstractCard makeCopy() {
        return new SummerFlame();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("SummerFlame");
        NAME = SummerFlame.cardStrings.NAME;
        DESCRIPTION = SummerFlame.cardStrings.DESCRIPTION;
    }
}
