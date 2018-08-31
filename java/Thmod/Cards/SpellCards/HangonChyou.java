package Thmod.Cards.SpellCards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import Thmod.Relics.SpellCardsRule;

public class HangonChyou extends AbstractSpellCards {
    public static final String ID = "HangonChyou";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;
    private static final int COST = 1;
    private int pointcost;

    public HangonChyou(int Times) {
        super("HangonChyou", HangonChyou.NAME,  1, HangonChyou.DESCRIPTION, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ALL_ENEMY);
        this.baseDamage = (8 + 3*Times);
        this.isMultiDamage = true;
        this.pointcost = 3;
        switch (Times){
            case 0:
                this.name = EXTENDED_DESCRIPTION[0];
                break;
            case 1:
                this.name = EXTENDED_DESCRIPTION[1];
                break;
            case 2:
                this.name = EXTENDED_DESCRIPTION[2];
                break;
            case 3:
                this.name = EXTENDED_DESCRIPTION[3];
                break;
            case 4:
                this.name = EXTENDED_DESCRIPTION[4];
                break;
            case 5:
                this.name = EXTENDED_DESCRIPTION[5];
                break;
            case 6:
                this.name = EXTENDED_DESCRIPTION[6];
                break;
            case 7:
                this.name = EXTENDED_DESCRIPTION[7];
                break;
            case 8:
                this.name = EXTENDED_DESCRIPTION[8];
                break;
            default:
                this.name = EXTENDED_DESCRIPTION[0];
                break;
        }
        initializeTitle();
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        if (p.hasPower("PointPower")) {
            if (p.getPower("PointPower").amount >= this.pointcost) {
                AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                AbstractDungeon.actionManager.addToTop(new ReducePowerAction(p,p,"PointPower",this.pointcost));
                if (SpellCardsRule.Hangongnum < 8)
                    SpellCardsRule.Hangongnum += 1;
                SpellCardsRule.HangongUsed = true;
                UnlockTracker.unlockCard("MuyoNehan");
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
        return new HangonChyou(0);
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("HangonChyou");
        NAME = HangonChyou.cardStrings.NAME;
        DESCRIPTION = HangonChyou.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = HangonChyou.cardStrings.EXTENDED_DESCRIPTION;
    }
}
