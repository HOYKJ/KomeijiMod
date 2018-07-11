package Thmod.Cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Power.MakuraSekiPower;

public class MakuraSeki extends AbstractKomeijiCards{
    public static final String ID = "MakuraSeki";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 1;
    private static final int BLOCK_AMT = 8;

    public MakuraSeki() {
        super("MakuraSeki", MakuraSeki.NAME,  1, MakuraSeki.DESCRIPTION, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.baseBlock = 8;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToTop(new GainBlockAction(p, p, this.block));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new MakuraSekiPower(p,this.upgraded)));
    }

    public AbstractCard makeCopy() {
        return new MakuraSeki();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.upgradeBlock(4);
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("MakuraSeki");
        NAME = MakuraSeki.cardStrings.NAME;
        DESCRIPTION = MakuraSeki.cardStrings.DESCRIPTION;
    }
}
