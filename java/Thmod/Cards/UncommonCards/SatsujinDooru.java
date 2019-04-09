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
import Thmod.ThMod;

public class SatsujinDooru extends AbstractKomeijiCards {
    public static final String ID = "SatsujinDooru";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;
    private static final int COST = 1;

    public SatsujinDooru() {
        super("SatsujinDooru", SatsujinDooru.NAME,  1, SatsujinDooru.DESCRIPTION, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF, CardSet_k.SAKUYA);
        this.baseMagicNumber = 12;
        this.magicNumber = this.baseMagicNumber;

    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new LagDamagePower(p, this.magicNumber,this.upgraded), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
    }

    public AbstractCard makeCopy() {
        return new SatsujinDooru();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.name = EXTENDED_DESCRIPTION[0];
            this.initializeTitle();
            this.upgradeMagicNumber(6);
            this.timesUpgraded += 1;
            this.upgraded = true;
            this.textureImg = ThMod.komeijiCardImage(this.cardID,true);
            loadCardImage(this.textureImg);
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("SatsujinDooru");
        NAME = SatsujinDooru.cardStrings.NAME;
        DESCRIPTION = SatsujinDooru.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = SatsujinDooru.cardStrings.EXTENDED_DESCRIPTION;
    }
}
