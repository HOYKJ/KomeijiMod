package Thmod.Cards.UncommonCards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

import Thmod.Actions.common.ChangeOrbAction;
import Thmod.Actions.common.ChooseAction;
import Thmod.Cards.AbstractSweepCards;
import Thmod.Cards.NingyouShinki;
import Thmod.Orbs.NingyouOrb;

public class NingyouYunhei extends AbstractSweepCards {
    public static final String ID = "NingyouYunhei";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;
    private static final int COST = 1;

    public NingyouYunhei() {
        super("NingyouYunhei", NingyouYunhei.NAME,  1, NingyouYunhei.DESCRIPTION, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        final ChooseAction choice = new ChooseAction(this, m, NingyouYunhei.EXTENDED_DESCRIPTION[0], true, 3);
        for (int i = (AbstractDungeon.player.orbs.size() - 1); i >= 0; i--) {
            final ArrayList<Integer> orbnum = new ArrayList<>();
            orbnum.clear();
            orbnum.add(i);
            if (AbstractDungeon.player.orbs.get(i) instanceof NingyouOrb) {
                choice.add(NingyouShinki.EXTENDED_DESCRIPTION[1], NingyouShinki.EXTENDED_DESCRIPTION[2], () -> {
                    AbstractDungeon.actionManager.addToBottom(new ChangeOrbAction(orbnum.get(0),3));
                });
            }
        }
        AbstractDungeon.actionManager.addToBottom(choice);
    }

    @Override
    public ArrayList<AbstractSweepCards> getOpposite() {
        final ArrayList<AbstractSweepCards> opposite = new ArrayList<>();
        opposite.add(new NingyouOkisou());
        opposite.add(new NingyouChiyari());
        return opposite;
    }

    public AbstractCard makeCopy() {
        return new NingyouYunhei();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("NingyouYunhei");
        NAME = NingyouYunhei.cardStrings.NAME;
        DESCRIPTION = NingyouYunhei.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = NingyouYunhei.cardStrings.EXTENDED_DESCRIPTION;
    }
}
