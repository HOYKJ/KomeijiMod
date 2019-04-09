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

import java.util.ArrayList;

import Thmod.Cards.AbstractSweepCards;

public class SenyouGoraku extends AbstractSweepCards {
    public static final String ID = "SenyouGoraku";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 6;
    private static final int DAMAGE_AMT = 30;

    public SenyouGoraku() {
        super("SenyouGoraku", SenyouGoraku.NAME,  5, SenyouGoraku.DESCRIPTION, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY, CardSet_k.TENSHI);
        this.baseDamage = 30;
    }

    public void tookDamage()
    {
        updateCost(-1);
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        for (int i = (AbstractDungeon.getCurrRoom().monsters.monsters.size() - 1); i >= 0; i--) {
            AbstractMonster target = AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
            if ((!(target.isDying)) && (target.currentHealth > 0) && (!(target.isEscaping)) && (!(target.hasPower("Flight")))) {
                AbstractDungeon.actionManager.addToTop(new DamageAction(target, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
            }
        }
    }

    @Override
    public ArrayList<AbstractSweepCards> getOpposite() {
        final ArrayList<AbstractSweepCards> opposite = new ArrayList<>();
        opposite.add(new RoshinSou());
        return opposite;
    }

    public AbstractCard makeCopy() {
        return new SenyouGoraku();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("SenyouGoraku");
        NAME = SenyouGoraku.cardStrings.NAME;
        DESCRIPTION = SenyouGoraku.cardStrings.DESCRIPTION;
    }
}
