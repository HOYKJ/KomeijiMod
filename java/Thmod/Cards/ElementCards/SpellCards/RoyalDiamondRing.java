package Thmod.Cards.ElementCards.SpellCards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;

import Thmod.Actions.common.ChangeOrbAction;

public class RoyalDiamondRing extends AbstractElementSpellCards {
    public static final String ID = "RoyalDiamondRing";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 2;

    public RoyalDiamondRing() {
        super("RoyalDiamondRing", RoyalDiamondRing.NAME,  2, RoyalDiamondRing.DESCRIPTION, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ALL_ENEMY,ElementType.Sun,ElementType.Luna);
        this.baseDamage = 0;
        this.baseMagicNumber = 10;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        super.use(p,m);
        for(int i = 0;i < p.orbs.size();i++){
            if(!(p.orbs.get(i) instanceof EmptyOrbSlot)){
                this.damage += 1;
                AbstractDungeon.actionManager.addToBottom(new ChangeOrbAction(i,true));
            }
        }
        for(int i1 = 0;i1 < this.magicNumber;i1++) {
            for (int i = (AbstractDungeon.getCurrRoom().monsters.monsters.size() - 1); i >= 0; i--) {
                AbstractDungeon.actionManager.addToTop(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
            }
        }
    }

    public AbstractCard makeCopy() {
        return new RoyalDiamondRing();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("RoyalDiamondRing");
        NAME = RoyalDiamondRing.cardStrings.NAME;
        DESCRIPTION = RoyalDiamondRing.cardStrings.DESCRIPTION;
    }
}
