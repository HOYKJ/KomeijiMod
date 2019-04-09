package Thmod.Cards.UncommonCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Cards.AbstractKomeijiCards;
import Thmod.Power.TokamakPower;

public class SelfTokamak extends AbstractKomeijiCards {
    public static final String ID = "SelfTokamak";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 0;
    private static final int HP_LOSS = 3;
    private static final int ENERGY_AMT = 2;

    public SelfTokamak() {
        super("SelfTokamak", SelfTokamak.NAME,  0, SelfTokamak.DESCRIPTION, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF, CardSet_k.UTSUHO);
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(1));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,new TokamakPower(p,this.magicNumber),this.magicNumber));
        AbstractDungeon.actionManager.addToBottom(new LoseHPAction(p, p, 3));
    }

    public AbstractCard makeCopy() {
        return new SelfTokamak();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("SelfTokamak");
        NAME = SelfTokamak.cardStrings.NAME;
        DESCRIPTION = SelfTokamak.cardStrings.DESCRIPTION;
    }
}
