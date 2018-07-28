package Thmod.Cards.ElementCards;

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
import com.megacrit.cardcrawl.powers.WeakPower;

import java.util.ArrayList;

public class WinterElement extends AbstractElementSweepCards {
    public static final String ID = "WinterElement";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 1;

    public WinterElement() {
        super("WinterElement", WinterElement.NAME,  1, WinterElement.DESCRIPTION, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY,ElementType.Water);
        this.baseDamage = 6;
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        super.use(p,m);
        AbstractDungeon.actionManager.addToTop(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SMASH));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new WeakPower(m, this.magicNumber,false), this.magicNumber));
    }

    @Override
    public ArrayList<AbstractElementSweepCards> getOpposite() {
        final ArrayList<AbstractElementSweepCards> opposite = new ArrayList<>();
        opposite.add(new CondensedBubble());
        opposite.add(new StickyBubble());
        return opposite;
    }

    public AbstractCard makeCopy() {
        return new WinterElement();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("WinterElement");
        NAME = WinterElement.cardStrings.NAME;
        DESCRIPTION = WinterElement.cardStrings.DESCRIPTION;
    }
}
