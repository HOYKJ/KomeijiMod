package Thmod.Cards.ElementCards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

import Thmod.ThMod;

public class SummerRed extends AbstractElementSweepCards {
    public static final String ID = "SummerRed";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 1;
    private boolean canDraw;

    public SummerRed() {
        super("SummerRed", SummerRed.NAME,  1, SummerRed.DESCRIPTION, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY,ElementType.Fire);
        this.baseDamage = 15;
        this.isInnate = true;
        this.exhaust = true;
        this.canDraw = true;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        super.use(p,m);
        AbstractDungeon.actionManager.addToTop(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
    }

    @Override
    public void atTurnStart() {
        super.atTurnStart();

    }

    @Override
    public void triggerWhenDrawn() {
        super.triggerWhenDrawn();
        if(this.canDraw){
            AbstractDungeon.actionManager.addToTop(new DrawCardAction(AbstractDungeon.player, 1));
            this.canDraw = false;
        }
    }

    @Override
    public ArrayList<AbstractElementSweepCards> getOpposite() {
        final ArrayList<AbstractElementSweepCards> opposite = new ArrayList<>();
        opposite.add(new SummerFlame());
        opposite.add(new WipeMoisture());
        return opposite;
    }

    public AbstractCard makeCopy() {
        return new SummerRed();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("SummerRed");
        NAME = SummerRed.cardStrings.NAME;
        DESCRIPTION = SummerRed.cardStrings.DESCRIPTION;
    }
}
