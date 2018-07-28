package Thmod.Cards.ElementCards.UncommonCards;

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

import Thmod.Cards.ElementCards.AbstractElementSweepCards;
import Thmod.Cards.ElementCards.SummerFlame;
import Thmod.Cards.ElementCards.WipeMoisture;

public class FlashOfSpring extends AbstractElementSweepCards {
    public static final String ID = "FlashOfSpring";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 1;

    public FlashOfSpring() {
        super("FlashOfSpring", FlashOfSpring.NAME,  1, FlashOfSpring.DESCRIPTION, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY,ElementType.Wood);
        this.baseDamage = 6;
        this.baseMagicNumber = 1;
        this.magicNumber = baseMagicNumber;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        super.use(p,m);
        AbstractDungeon.actionManager.addToTop(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, this.magicNumber));
    }

    @Override
    public ArrayList<AbstractElementSweepCards> getOpposite() {
        final ArrayList<AbstractElementSweepCards> opposite = new ArrayList<>();
        opposite.add(new SpringWind());
        opposite.add(new StaticGreen());
        return opposite;
    }

    public AbstractCard makeCopy() {
        return new FlashOfSpring();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("FlashOfSpring");
        NAME = FlashOfSpring.cardStrings.NAME;
        DESCRIPTION = FlashOfSpring.cardStrings.DESCRIPTION;
    }
}
