package Thmod.Cards.RewardCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Cards.AbstractKomeijiCards;
import Thmod.Power.LanPower;

public class RanYakumo extends AbstractKomeijiCards {
    public static final String ID = "RanYakumo";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 3;

    public RanYakumo() {
        super("RanYakumo", RanYakumo.NAME,  3, RanYakumo.DESCRIPTION, CardType.POWER, CardRarity.SPECIAL, CardTarget.NONE);
        this.upgraded = true;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p, p, new LanPower(p,3)));
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
