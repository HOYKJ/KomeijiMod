package Thmod.Cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

import java.util.ArrayList;

import Thmod.Power.JyouchiPower;

public class JyouchiJin extends AbstractSweepCards{
    public static final String ID = "JyouchiJin";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 1;

    public JyouchiJin() {
        super("JyouchiJin", JyouchiJin.NAME,  1, JyouchiJin.DESCRIPTION, CardType.SKILL, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new JyouchiPower(p,this.magicNumber)));
    }

    @Override
    public ArrayList<AbstractSweepCards> getOpposite() {
        final ArrayList<AbstractSweepCards> opposite = new ArrayList<>();
        opposite.add(new KeiseiJin());
        opposite.add(new KinbakuJin());
        return opposite;
    }

    public AbstractCard makeCopy() {
        return new JyouchiJin();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("JyouchiJin");
        NAME = JyouchiJin.cardStrings.NAME;
        DESCRIPTION = JyouchiJin.cardStrings.DESCRIPTION;
    }
}
