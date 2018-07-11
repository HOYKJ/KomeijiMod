package Thmod.Cards.UncommonCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Cards.AbstractKomeijiCards;
import Thmod.Power.MusuNoYumePower;

public class MusuNoYume extends AbstractKomeijiCards {
    public static final String ID = "MusuNoYume";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 1;

    public MusuNoYume() {
        super("MusuNoYume", MusuNoYume.NAME,  1, MusuNoYume.DESCRIPTION, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseMagicNumber = 4;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m,p,new MusuNoYumePower(m,this.magicNumber)));
    }

    public AbstractCard makeCopy() {
        return new MusuNoYume();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.upgradeMagicNumber(2);
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("MusuNoYume");
        NAME = MusuNoYume.cardStrings.NAME;
        DESCRIPTION = MusuNoYume.cardStrings.DESCRIPTION;
    }
}
