package Thmod.Cards.UncommonCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Cards.AbstractKomeijiCards;
import Thmod.Power.HenyouMirumePower;

public class HenyouMirume extends AbstractKomeijiCards {
    public static final String ID = "HenyouMirume";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 1;

    public HenyouMirume() {
        super("HenyouMirume", HenyouMirume.NAME,  1, HenyouMirume.DESCRIPTION, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE, CardSet_k.YUKARI);
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,new HenyouMirumePower(p,1),1));
    }

    public AbstractCard makeCopy() {
        return new HenyouMirume();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.upgradeBaseCost(0);
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("HenyouMirume");
        NAME = HenyouMirume.cardStrings.NAME;
        DESCRIPTION = HenyouMirume.cardStrings.DESCRIPTION;
    }
}
