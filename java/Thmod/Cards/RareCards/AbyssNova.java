package Thmod.Cards.RareCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

import Thmod.Cards.AbstractKomeijiCards;
import Thmod.Power.AbyssNovaPower;

public class AbyssNova extends AbstractKomeijiCards {
    public static final String ID = "AbyssNova";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 3;

    public AbyssNova() {
        super("AbyssNova", AbyssNova.NAME,  3, AbyssNova.DESCRIPTION, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY, CardSet_k.UTSUHO);
        this.baseMagicNumber = 50;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new AbyssNovaPower(p, this.magicNumber)));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, 2), 2));
    }

    public AbstractCard makeCopy() {
        return new AbyssNova();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.upgradeBaseCost(2);
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("AbyssNova");
        NAME = AbyssNova.cardStrings.NAME;
        DESCRIPTION = AbyssNova.cardStrings.DESCRIPTION;
    }
}
