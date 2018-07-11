package Thmod.Cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.Iterator;

import Thmod.Power.Abnormal.HauntPower;

public class Mishyaguji extends AbstractSweepCards {
    public static final String ID = "Mishyaguji";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 1;

    public Mishyaguji() {
        super("Mishyaguji", Mishyaguji.NAME,  1, Mishyaguji.DESCRIPTION, CardType.SKILL, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        this.exhaust = true;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        for (int i = 0; i < AbstractDungeon.getCurrRoom().monsters.monsters.size(); ++i) {
            AbstractMonster target = (AbstractMonster) AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
            if ((!(target.isDying)) && (target.currentHealth > 0) && (!(target.isEscaping))) {
                AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(target, p, new HauntPower(target)));
            }
        }
    }

    @Override
    public ArrayList<AbstractSweepCards> getOpposite() {
        final ArrayList<AbstractSweepCards> opposite = new ArrayList<>();
        opposite.add(new DochyakuKami());
        opposite.add(new Agarareta());
        return opposite;
    }

    public AbstractCard makeCopy() {
        return new Mishyaguji();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("Mishyaguji");
        NAME = Mishyaguji.cardStrings.NAME;
        DESCRIPTION = Mishyaguji.cardStrings.DESCRIPTION;
    }
}
