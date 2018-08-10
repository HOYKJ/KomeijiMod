package Thmod.Cards.ElementCards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

public class WipeMoisture extends AbstractElementSweepCards {
    public static final String ID = "WipeMoisture";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 1;

    public WipeMoisture() {
        super("WipeMoisture", WipeMoisture.NAME,  1, WipeMoisture.DESCRIPTION, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY,ElementType.Fire);
        this.baseDamage = 8;
        this.exhaust = true;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        super.use(p,m);
        AbstractDungeon.actionManager.addToTop(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
    }

    public void triggerOnExhaust(){
        if(!(AbstractDungeon.player.hasPower("ForestBlazePower"))) {
            if(!(this.purgeOnUse)) {
                if(AbstractDungeon.player.hand.size() >= 10)
                    AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAction(new WipeMoisture(), 1));
                else
                    AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new WipeMoisture(), 1));
            }
        }
    }

    @Override
    public ArrayList<AbstractElementSweepCards> getOpposite() {
        final ArrayList<AbstractElementSweepCards> opposite = new ArrayList<>();
        opposite.add(new SummerRed());
        opposite.add(new SummerFlame());
        return opposite;
    }

    public AbstractCard makeCopy() {
        return new WipeMoisture();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("WipeMoisture");
        NAME = WipeMoisture.cardStrings.NAME;
        DESCRIPTION = WipeMoisture.cardStrings.DESCRIPTION;
    }
}
