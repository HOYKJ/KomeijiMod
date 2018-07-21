package Thmod.Cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;

import Thmod.Power.DashPower;

public class Dash_Komeiji extends AbstractKomeijiCards{
    public static final String ID = "Dash_Komeiji";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 2;

    public Dash_Komeiji() {
        super("Dash_Komeiji", Dash_Komeiji.NAME,  2, Dash_Komeiji.DESCRIPTION, CardType.SKILL, CardRarity.BASIC, CardTarget.NONE);
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DashPower(p, 1), 1));
    }

    public AbstractCard makeCopy() {
        return new Dash_Komeiji();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.upgradeBaseCost(1);
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("Dash_Komeiji");
        NAME = Dash_Komeiji.cardStrings.NAME;
        DESCRIPTION = Dash_Komeiji.cardStrings.DESCRIPTION;
    }
}
