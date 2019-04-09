package Thmod.Cards.RewardCards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Actions.common.NeedleMountainAttack;
import Thmod.Cards.AbstractKomeijiCards;

public class NeedleMountain extends AbstractKomeijiCards {
    public static final String ID = "NeedleMountain";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;

    public NeedleMountain() {
        super("NeedleMountain", NeedleMountain.NAME,  3, NeedleMountain.DESCRIPTION, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.NONE, CardSet_k.OTHER);
        this.exhaust = true;
        this.upgraded = true;
        this.baseDamage = 10;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new NeedleMountainAttack(AbstractDungeon.getMonsters().getRandomMonster(true), p, this.damage, AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    }

    public boolean canUpgrade()
    {
        return false;
    }

    public AbstractCard makeCopy() {
        return new NeedleMountain();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("NeedleMountain");
        NAME = NeedleMountain.cardStrings.NAME;
        DESCRIPTION = NeedleMountain.cardStrings.DESCRIPTION;
    }
}
