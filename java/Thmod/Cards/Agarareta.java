package Thmod.Cards;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.Iterator;

import Thmod.Power.Abnormal.BlueAbnormity;
import Thmod.Power.Abnormal.GreenAbnormity;
import Thmod.Power.Abnormal.RedAbnormity;

public class Agarareta extends AbstractSweepCards {
    public static final String ID = "Agarareta";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 1;

    public Agarareta() {
        super("Agarareta", Agarareta.NAME,  1, Agarareta.DESCRIPTION, CardType.SKILL, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        this.exhaust = true;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        for (int i = 0; i < AbstractDungeon.getCurrRoom().monsters.monsters.size(); ++i) {
            AbstractMonster target = (AbstractMonster) AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
            if ((!(target.isDying)) && (target.currentHealth > 0) && (!(target.isEscaping))) {
                int roll = MathUtils.random(3);
                switch (roll) {
                    case 0:
                        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(target, p, new RedAbnormity(target, 3), 3));
                        break;
                    case 1:
                        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(target, p, new GreenAbnormity(target, 3), 3));
                        break;
                    case 2:
                        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(target, p, new BlueAbnormity(target, 3), 3));
                        break;
                    default:
                        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(target, p, new RedAbnormity(target, 3), 3));
                }
            }
        }
    }

    @Override
    public ArrayList<AbstractSweepCards> getOpposite() {
        final ArrayList<AbstractSweepCards> opposite = new ArrayList<>();
        opposite.add(new DochyakuKami());
        opposite.add(new Mishyaguji());
        return opposite;
    }

    public AbstractCard makeCopy() {
        return new Agarareta();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("Agarareta");
        NAME = Agarareta.cardStrings.NAME;
        DESCRIPTION = Agarareta.cardStrings.DESCRIPTION;
    }
}
