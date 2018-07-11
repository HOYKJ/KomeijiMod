package Thmod.Cards.RareCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Cards.AbstractKomeijiCards;
import Thmod.Power.TimeLockPower;

public class LunaDial extends AbstractKomeijiCards {
    public static final String ID = "LunaDial";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION;
    private static final int COST = 3;

    public LunaDial() {
        super("LunaDial", LunaDial.NAME,  3, LunaDial.DESCRIPTION, CardType.SKILL, CardRarity.RARE, CardTarget.ENEMY);
        this.exhaust = true;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        m.useShakeAnimation(0.1F);
        if(this.upgraded){
            if(m.hasPower("Artifact"))
                AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(m,p,"Artifact"));
        }
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new TimeLockPower(m)));
    }

    public AbstractCard makeCopy() {
        return new LunaDial();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.rawDescription = UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("LunaDial");
        NAME = LunaDial.cardStrings.NAME;
        DESCRIPTION = LunaDial.cardStrings.DESCRIPTION;
        UPGRADE_DESCRIPTION = LunaDial.cardStrings.UPGRADE_DESCRIPTION;
    }
}
