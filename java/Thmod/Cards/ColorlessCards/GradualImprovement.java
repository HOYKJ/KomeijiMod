package Thmod.Cards.ColorlessCards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class GradualImprovement extends AbstractColorlessCards {
    public static final String ID = "GradualImprovement";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 0;

    public GradualImprovement() {
        super("GradualImprovement", GradualImprovement.NAME,  0, GradualImprovement.DESCRIPTION, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = 0;
        this.isEthereal = true;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(p,this.damage,this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAction(this,1));
    }

    public void applyPowers()
    {
        AbstractPlayer p = AbstractDungeon.player;
        if(this.upgraded)
            this.baseDamage = 3;
        else
            this.baseDamage = 0;

        for(AbstractCard c:p.hand.group){
            if(c.cardID.equals(this.cardID))
                this.baseDamage += 1;
        }

        for (AbstractCard c : p.discardPile.group) {
            if(c.cardID.equals(this.cardID))
                this.baseDamage += 1;
        }

        for (AbstractCard c : p.drawPile.group) {
            if(c.cardID.equals(this.cardID))
                this.baseDamage += 1;
        }

        super.applyPowers();

        initializeDescription();
    }

    public void onMoveToDiscard()
    {
        this.rawDescription = DESCRIPTION;
        initializeDescription();
    }

    public void calculateCardDamage(AbstractMonster mo)
    {
        super.calculateCardDamage(mo);
        initializeDescription();
    }

    public AbstractCard makeCopy() {
        return new GradualImprovement();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.upgradeDamage(3);
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("GradualImprovement");
        NAME = GradualImprovement.cardStrings.NAME;
        DESCRIPTION = GradualImprovement.cardStrings.DESCRIPTION;
    }
}
