package Thmod.Cards.DeriveCards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.DevConsole;

public class Stardust extends AbstractDeriveCards {
    public static final String ID = "Stardust";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;
    private static final int COST = 0;
    private int magicNum;

    public Stardust(int magicNum) {
        super("Stardust", Stardust.NAME,  0, Stardust.DESCRIPTION, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ALL_ENEMY);
        this.baseMagicNumber = (8 + 4 * magicNum);
        this.magicNumber = this.baseMagicNumber;
        this.upgraded = true;
        this.magicNum = magicNum;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        if(AbstractDungeon.player.hasPower("StardustAccumulate")) {
            DevConsole.logger.info("magicNumber"+this.magicNumber);
            for (int i = 0; i < AbstractDungeon.getCurrRoom().monsters.monsters.size(); ++i) {
                AbstractMonster target = AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
                if ((!(target.isDying)) && (target.currentHealth > 0) && (!(target.isEscaping))) {
                    AbstractDungeon.actionManager.addToTop(new DamageAction(target, new DamageInfo(p, this.magicNumber, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
                }
            }
            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(p, p, "StardustAccumulate"));
        }
    }

    public void calculateCardDamage(AbstractMonster mo)
    {
//        super.calculateCardDamage(mo);
        this.magicNumber = (8 + 4 * magicNum);
        this.rawDescription = DESCRIPTION;
        this.rawDescription += EXTENDED_DESCRIPTION[0];
        initializeDescription();
    }

    public AbstractCard makeCopy() {
        return new Stardust(0);
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("Stardust");
        NAME = Stardust.cardStrings.NAME;
        DESCRIPTION = Stardust.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = Stardust.cardStrings.EXTENDED_DESCRIPTION;
    }
}
