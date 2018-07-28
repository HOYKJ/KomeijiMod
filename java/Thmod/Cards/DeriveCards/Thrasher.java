package Thmod.Cards.DeriveCards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Actions.common.RandomAttackAction;
import basemod.DevConsole;

public class Thrasher extends AbstractDeriveCards {
    public static final String ID = "Thrasher";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 0;
    private int offset ;

    public Thrasher(int magicNum) {
        super("Thrasher", Thrasher.NAME,  0, Thrasher.DESCRIPTION, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ALL_ENEMY);
        this.baseDamage = 12;
        this.baseMagicNumber = magicNum;
        this.magicNumber = this.baseMagicNumber;
        DevConsole.logger.info("basemagicNum"+this.baseMagicNumber);
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        if (AbstractDungeon.player.hasPower("ThrasherAccumulate")) {
            AbstractDungeon.actionManager.addToBottom(new RandomAttackAction(AbstractDungeon.getMonsters().getRandomMonster(true), new DamageInfo(p, this.baseDamage), this.magicNumber, AbstractGameAction.AttackEffect.SLASH_HEAVY));
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, "ThrasherAccumulate"));
        }
    }

    public AbstractCard makeCopy() {
        return new Thrasher(0);
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("Thrasher");
        NAME = Thrasher.cardStrings.NAME;
        DESCRIPTION = Thrasher.cardStrings.DESCRIPTION;
    }
}
