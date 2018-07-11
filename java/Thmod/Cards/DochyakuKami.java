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

import Thmod.Power.Abnormal.BlueAbnormity;
import Thmod.Power.Abnormal.GreenAbnormity;
import Thmod.Power.Abnormal.RedAbnormity;

public class DochyakuKami extends AbstractSweepCards {
    public static final String ID = "DochyakuKami";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 0;

    public DochyakuKami() {
        super("DochyakuKami", DochyakuKami.NAME,  0, DochyakuKami.DESCRIPTION, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
        this.exhaust = true;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        int roll = MathUtils.random(3);
        switch (roll){
            case 0:
                AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(m, p, new RedAbnormity(m, 3), 3));
                break;
            case 1:
                AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(m, p, new GreenAbnormity(m, 3), 3));
                break;
            case 2:
                AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(m, p, new BlueAbnormity(m, 3), 3));
                break;
        }
    }

    @Override
    public ArrayList<AbstractSweepCards> getOpposite() {
        final ArrayList<AbstractSweepCards> opposite = new ArrayList<>();
        opposite.add(new Agarareta());
        opposite.add(new Mishyaguji());
        return opposite;
    }

    public AbstractCard makeCopy() {
        return new DochyakuKami();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("DochyakuKami");
        NAME = DochyakuKami.cardStrings.NAME;
        DESCRIPTION = DochyakuKami.cardStrings.DESCRIPTION;
    }
}
