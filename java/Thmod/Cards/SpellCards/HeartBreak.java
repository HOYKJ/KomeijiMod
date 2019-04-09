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
import com.megacrit.cardcrawl.monsters.ending.CorruptHeart;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import Thmod.Cards.DeriveCards.EasterEgg.RealHeartBreak;

public class HeartBreak extends AbstractSpellCards {
    public static final String ID = "HeartBreak";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private int pointcost;

    public HeartBreak() {
        super("HeartBreak", HeartBreak.NAME,  1, HeartBreak.DESCRIPTION, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ALL_ENEMY);
        this.baseDamage = 16;
        this.baseMagicNumber = 6;
        this.magicNumber = this.baseMagicNumber;
        this.pointcost = 2;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        if (p.hasPower("PointPower")) {
            if (p.getPower("PointPower").amount >= this.pointcost) {
                if(AbstractDungeon.getCurrRoom().monsters.monsters.get(0) instanceof CorruptHeart){
                    AbstractMonster target = AbstractDungeon.getCurrRoom().monsters.monsters.get(0);
                    AbstractDungeon.actionManager.addToBottom(new DamageAction(target, new DamageInfo(p, 999, DamageInfo.DamageType.HP_LOSS), AbstractGameAction.AttackEffect.SMASH));
                    UnlockTracker.unlockCard(RealHeartBreak.ID);
                }
                else {
                    for (int i = 0; i < AbstractDungeon.getCurrRoom().monsters.monsters.size(); i++) {
                        AbstractMonster target = AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
                        if ((!(target.isDying)) && (target.currentHealth > 0) && (!(target.isEscaping))) {
                            AbstractDungeon.actionManager.addToBottom(new DamageAction(target, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                            this.damage += this.magicNumber;
                        }
                    }
                }
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
        this.cantUseMessage = AbstractSpellCards.EXTENDED_DESCRIPTION[4];
        return false;
    }

    public AbstractCard makeCopy() {
        return new HeartBreak();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("HeartBreak");
        NAME = HeartBreak.cardStrings.NAME;
        DESCRIPTION = HeartBreak.cardStrings.DESCRIPTION;
    }
}
