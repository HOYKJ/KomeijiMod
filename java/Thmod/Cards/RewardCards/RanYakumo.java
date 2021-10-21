package Thmod.Cards.RewardCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Actions.common.LatterAction;
import Thmod.Actions.unique.ChooseAction;
import Thmod.Cards.AbstractKomeijiCards;
import Thmod.Cards.NingyouFukuhei;
import Thmod.Power.LanPower;

public class RanYakumo extends AbstractKomeijiCards {
    public static final String ID = "RanYakumo";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 2;

    public RanYakumo() {
        super("RanYakumo", RanYakumo.NAME,  2, RanYakumo.DESCRIPTION, CardType.SKILL, CardRarity.SPECIAL, CardTarget.NONE, CardSet_k.OTHER);
        this.upgraded = true;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p, p, new LanPower(p,3),3));
        AbstractDungeon.actionManager.addToBottom(new LatterAction(()->{
            this.modifyCostForCombat(1);
        }, 0.1f));

        final ChooseAction choice2 = new ChooseAction(this, m, NingyouFukuhei.EXTENDED_DESCRIPTION[1],false, 1);
        choice2.add(NingyouFukuhei.EXTENDED_DESCRIPTION[2],NingyouFukuhei.EXTENDED_DESCRIPTION[3], () -> {
            AbstractDungeon.actionManager.addToBottom(new ExhaustSpecificCardAction(this, p.discardPile));
        });
        choice2.add(NingyouFukuhei.EXTENDED_DESCRIPTION[2],NingyouFukuhei.EXTENDED_DESCRIPTION[4], () -> {

        });
        AbstractDungeon.actionManager.addToBottom(choice2);
    }

    public boolean canUpgrade()
    {
        return false;
    }

    public AbstractCard makeCopy() {
        return new RanYakumo();
    }

    public void upgrade() {
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("RanYakumo");
        NAME = RanYakumo.cardStrings.NAME;
        DESCRIPTION = RanYakumo.cardStrings.DESCRIPTION;
    }
}
