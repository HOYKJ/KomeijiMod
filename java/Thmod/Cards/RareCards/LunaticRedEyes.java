package Thmod.Cards.RareCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Cards.AbstractKomeijiCards;
import Thmod.Power.LunaticRedEyesPower;

public class LunaticRedEyes extends AbstractKomeijiCards {
    public static final String ID = "LunaticRedEyes";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION;
    private static final int COST = 3;

    public LunaticRedEyes() {
        super("LunaticRedEyes", LunaticRedEyes.NAME,  3, LunaticRedEyes.DESCRIPTION, CardType.POWER, CardRarity.RARE, CardTarget.SELF, CardSet_k.REISEN);
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,new LunaticRedEyesPower(p)));
    }

    public AbstractCard makeCopy() {
        return new LunaticRedEyes();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.isInnate = true;
            this.rawDescription = UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("LunaticRedEyes");
        NAME = LunaticRedEyes.cardStrings.NAME;
        DESCRIPTION = LunaticRedEyes.cardStrings.DESCRIPTION;
        UPGRADE_DESCRIPTION = LunaticRedEyes.cardStrings.UPGRADE_DESCRIPTION;
    }
}
