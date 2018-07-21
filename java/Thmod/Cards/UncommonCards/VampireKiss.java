package Thmod.Cards.UncommonCards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Cards.AbstractKomeijiCards;

public class VampireKiss extends AbstractKomeijiCards {
    public static final String ID = "VampireKiss";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 1;
    private static final int ATTACK_DMG = 2;

    public VampireKiss() {
        super("VampireKiss", VampireKiss.NAME,  1, VampireKiss.DESCRIPTION, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = 2;
        this.exhaust = true;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        for(int i = 0; i < 3; i++) {
            AbstractDungeon.actionManager.addToTop(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
            int tmp = this.damage;
            tmp -= m.currentBlock;
            if (tmp > m.currentHealth) {
                tmp = m.currentHealth;
            }
            if (tmp > 0)
                p.heal((tmp / 2));
            AbstractDungeon.actionManager.addToTop(new WaitAction(0.1F));
        }
//        this.m = m;
    }

//    public void update(){
//        int i = 0;
//        if(i != 3) {
//            AbstractDungeon.actionManager.addToTop(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
//            int tmp = this.damage;
//            tmp -= m.currentBlock;
//            if (tmp > m.currentHealth) {
//                tmp = m.currentHealth;
//            }
//            if (tmp > 0)
//                p.heal((tmp / 2));
//            i += 1;
//            AbstractDungeon.actionManager.addToTop(new WaitAction(0.1F));
//        }
//        else if (i == 3)
//            tickDuration();
//    }

    public AbstractCard makeCopy() {
        return new VampireKiss();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.upgradeDamage(1);
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("VampireKiss");
        NAME = VampireKiss.cardStrings.NAME;
        DESCRIPTION = VampireKiss.cardStrings.DESCRIPTION;
    }
}
