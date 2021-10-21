package Thmod.Cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.helpers.BaseModCardTags;

public class Defend_Koishi extends AbstractKomeijiCards{
    public static final String ID = "Defend_Koishi";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 1;
    private static final int BLOCK_AMT = 5;

    public Defend_Koishi() {
        super("Defend_Koishi", Defend_Koishi.NAME,  1, Defend_Koishi.DESCRIPTION, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF, CardSet_k.KOISHI);
        this.baseBlock = 5;
        tags.add(CardTags.STARTER_DEFEND);
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToTop(new GainBlockAction(p, p, this.block));
    }

    public AbstractCard makeCopy() {
        return new Defend_Koishi();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.upgradeBlock(3);
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("Defend_Koishi");
        NAME = Defend_Koishi.cardStrings.NAME;
        DESCRIPTION = Defend_Koishi.cardStrings.DESCRIPTION;
    }
}
