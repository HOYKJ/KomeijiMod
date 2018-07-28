package Thmod.Cards.SpellCards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DaiesanShikkaiKoroshi extends AbstractSpellCards {
    public static final String ID = "DaiesanShikkaiKoroshi";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 3;
    private static final int ATTACK_DMG = 80;
    private int pointcost;

    public DaiesanShikkaiKoroshi() {
        super("DaiesanShikkaiKoroshi", DaiesanShikkaiKoroshi.NAME,  3, DaiesanShikkaiKoroshi.DESCRIPTION, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY);
        this.baseDamage = 80;
        this.pointcost = 5;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        if (p.hasPower("PointPower")) {
            if (p.getPower("PointPower").amount >= this.pointcost) {
                AbstractDungeon.actionManager.addToTop(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                AbstractDungeon.actionManager.addToTop(new ReducePowerAction(p,p,"PointPower",this.pointcost));
            }
        }
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m){
        super.canUse(p,m);
        if (p.hasPower("PointPower")) {
            if (p.getPower("PointPower").amount >= this.pointcost) {
                return true;
            }
        }
        this.cantUseMessage = "我没有足够的P点";
        return false;
    }

    public AbstractCard makeCopy() {
        return new DaiesanShikkaiKoroshi();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("DaiesanShikkaiKoroshi");
        NAME = DaiesanShikkaiKoroshi.cardStrings.NAME;
        DESCRIPTION = DaiesanShikkaiKoroshi.cardStrings.DESCRIPTION;
    }
}
