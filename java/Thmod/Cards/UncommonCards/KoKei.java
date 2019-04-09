package Thmod.Cards.UncommonCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

import Thmod.Cards.AbstractSweepCards;
import Thmod.Power.KoKeiPower;

public class KoKei extends AbstractSweepCards {
    public static final String ID = "KoKei";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 1;

    public KoKei() {
        super("KoKei", KoKei.NAME,  1, KoKei.DESCRIPTION, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE, CardSet_k.MEIRIN);
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p,p,new KoKeiPower(p,false)));
    }

    @Override
    public ArrayList<AbstractSweepCards> getOpposite() {
        final ArrayList<AbstractSweepCards> opposite = new ArrayList<>();
        opposite.add(new KouPou());
        return opposite;
    }

    public AbstractCard makeCopy() {
        return new KoKei();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("KoKei");
        NAME = KoKei.cardStrings.NAME;
        DESCRIPTION = KoKei.cardStrings.DESCRIPTION;
    }
}
