package Thmod.Cards.ElementCards.UncommonCards;

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

import java.util.ArrayList;

import Thmod.Cards.ElementCards.AbstractElementSweepCards;
import Thmod.Power.NextSpear;

public class MidautumnSpear extends AbstractElementSweepCards {
    public static final String ID = "MidautumnSpear";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 2;

    public MidautumnSpear() {
        super("MidautumnSpear", MidautumnSpear.NAME,  2, MidautumnSpear.DESCRIPTION, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY,ElementType.Earth);
        this.baseDamage = 12;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        super.use(p,m);
        AbstractDungeon.actionManager.addToTop(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m,m,new NextSpear(m,this.damage),this.damage));
    }

    @Override
    public ArrayList<AbstractElementSweepCards> getOpposite() {
        final ArrayList<AbstractElementSweepCards> opposite = new ArrayList<>();
        opposite.add(new EmeraldCity());
        opposite.add(new DiamondHardness());
        return opposite;
    }

    public AbstractCard makeCopy() {
        return new MidautumnSpear();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("MidautumnSpear");
        NAME = MidautumnSpear.cardStrings.NAME;
        DESCRIPTION = MidautumnSpear.cardStrings.DESCRIPTION;
    }
}
