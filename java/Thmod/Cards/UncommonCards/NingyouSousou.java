package Thmod.Cards.UncommonCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

import Thmod.Cards.AbstractSweepCards;
import Thmod.Cards.PerfectMaid;
import Thmod.Power.NingyouPower;

public class NingyouSousou extends AbstractSweepCards {
    public static final String ID = "NingyouSousou";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 1;
    private static final int BLOCK_AMT = 3;
    private static final int ATTACK_DMG= 3;

    public NingyouSousou() {
        super("NingyouSousou", NingyouSousou.NAME,  1, NingyouSousou.DESCRIPTION, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseBlock = 3;
        this.baseDamage = 3;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new NingyouPower(p, 1), 1));
        AbstractDungeon.actionManager.addToTop(new GainBlockAction(p, p, this.block));
    }

    @Override
    public ArrayList<AbstractSweepCards> getOpposite() {
        final ArrayList<AbstractSweepCards> opposite = new ArrayList<>();
        opposite.add(new PerfectMaid());
        return opposite;
    }

    public AbstractCard makeCopy() {
        return new NingyouSousou();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("NingyouSousou");
        NAME = NingyouSousou.cardStrings.NAME;
        DESCRIPTION = NingyouSousou.cardStrings.DESCRIPTION;
    }
}
