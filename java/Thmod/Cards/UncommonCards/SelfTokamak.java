package Thmod.Cards.UncommonCards;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Cards.AbstractKomeijiCards;

public class SelfTokamak extends AbstractKomeijiCards {
    public static final String ID = "SelfTokamak";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION;
    private static final int COST = 0;
    private static final int HP_LOSS = 3;
    private static final int ENERGY_AMT = 2;

    public SelfTokamak() {
        super("SelfTokamak", SelfTokamak.NAME,  1, SelfTokamak.DESCRIPTION, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(this.magicNumber));
        AbstractDungeon.actionManager.addToBottom(new LoseHPAction(p, p, 3));
    }

    public AbstractCard makeCopy() {
        return new SelfTokamak();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
            this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("SelfTokamak");
        NAME = SelfTokamak.cardStrings.NAME;
        DESCRIPTION = SelfTokamak.cardStrings.DESCRIPTION;
        UPGRADE_DESCRIPTION = SelfTokamak.cardStrings.UPGRADE_DESCRIPTION;
    }
}
