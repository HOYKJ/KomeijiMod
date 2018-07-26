package Thmod.Cards.RareCards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Actions.common.JinkouTaiyouAction;
import Thmod.Cards.AbstractKomeijiCards;

public class JigokuNoTaiyou extends AbstractKomeijiCards {
    public static final String ID = "JigokuNoTaiyou";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int BASE_DAMAGE = 5;
    private static final int COST = 1;

    public JigokuNoTaiyou() {
        super("JigokuNoTaiyou", JigokuNoTaiyou.NAME,  1, JigokuNoTaiyou.DESCRIPTION, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY);
        this.exhaust = true;
        this.baseDamage = 5;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new JinkouTaiyouAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn)));
    }

    public AbstractCard makeCopy() {
        return new JigokuNoTaiyou();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.upgradeDamage(3);
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("JigokuNoTaiyou");
        NAME = JigokuNoTaiyou.cardStrings.NAME;
        DESCRIPTION = JigokuNoTaiyou.cardStrings.DESCRIPTION;
    }
}
