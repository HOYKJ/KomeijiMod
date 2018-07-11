package Thmod.Cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Power.DashPower;

public class KochyouYume extends AbstractKomeijiCards{
    public static final String ID = "KochyouYume";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 2;
    private static final int ATTACK_DMG= 6;

    public KochyouYume() {
        super("KochyouYume", KochyouYume.NAME,  2, KochyouYume.DESCRIPTION, CardType.ATTACK, CardRarity.COMMON, CardTarget.SELF_AND_ENEMY);
        this.baseDamage = 6;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToTop(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DashPower(p, 1), 1));
    }

    public AbstractCard makeCopy() {
        return new KochyouYume();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.upgradeDamage(4);
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("KochyouYume");
        NAME = KochyouYume.cardStrings.NAME;
        DESCRIPTION = KochyouYume.cardStrings.DESCRIPTION;
    }
}
