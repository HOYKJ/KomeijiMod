package Thmod.Cards.ColorlessCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Power.PreThgoughtPower;

public class PreThought extends AbstractColorlessCards {
    public static final String ID = "PreThought";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;

    public PreThought() {
        super("PreThought", PreThought.NAME,  1, PreThought.DESCRIPTION, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new PreThgoughtPower(p, this.magicNumber), this.magicNumber));
    }

    public AbstractCard makeCopy() {
        return new PreThought();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.upgradeBaseCost(0);
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("PreThought");
        NAME = PreThought.cardStrings.NAME;
        DESCRIPTION = PreThought.cardStrings.DESCRIPTION;
    }
}
