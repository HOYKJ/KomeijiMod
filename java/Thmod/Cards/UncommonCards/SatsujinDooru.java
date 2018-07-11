package Thmod.Cards.UncommonCards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Cards.AbstractKomeijiCards;
import Thmod.Power.LagDamagePower;

public class SatsujinDooru extends AbstractKomeijiCards {
    public static final String ID = "SatsujinDooru";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 2;

    public SatsujinDooru() {
        super("SatsujinDooru", SatsujinDooru.NAME,  2, SatsujinDooru.DESCRIPTION, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = 10;
        this.magicNumber = this.baseMagicNumber;

    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new LagDamagePower(p, this.magicNumber), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
    }

    public AbstractCard makeCopy() {
        return new SatsujinDooru();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.name = "回忆「夜雾幻影杀人鬼」";
            this.initializeTitle();
            this.upgradeMagicNumber(5);
            this.upgraded = true;
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("SatsujinDooru");
        NAME = SatsujinDooru.cardStrings.NAME;
        DESCRIPTION = SatsujinDooru.cardStrings.DESCRIPTION;
    }
}
