package Thmod.Cards.DeriveCards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class FuubiStrike extends AbstractDeriveCards {
    public static final String ID = "FuubiStrike";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 0;

    public FuubiStrike() {
        super("FuubiStrike", FuubiStrike.NAME,  0, FuubiStrike.DESCRIPTION, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY);
        this.baseDamage = 2;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        if(p.hasPower("FuubiPower")){
            if(p.getPower("FuubiPower").amount > 0){
                AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(this, 1));
                AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(p,p,"FuubiPower",1));
            }
            else
                AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p,p,"FuubiPower"));
            AbstractDungeon.actionManager.addToTop(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        }
    }

    public AbstractCard makeCopy() {
        return new FuubiStrike();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("FuubiStrike");
        NAME = FuubiStrike.cardStrings.NAME;
        DESCRIPTION = FuubiStrike.cardStrings.DESCRIPTION;
    }
}
