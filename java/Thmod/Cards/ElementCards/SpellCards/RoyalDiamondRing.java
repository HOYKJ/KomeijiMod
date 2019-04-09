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
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;

import Thmod.Actions.common.ChangeOrbAction;
import Thmod.Orbs.ElementOrb.AbstractElementOrb;
import basemod.DevConsole;

public class RoyalDiamondRing extends AbstractElementSpellCards {
    public static final String ID = "RoyalDiamondRing";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;

    public RoyalDiamondRing() {
        super("RoyalDiamondRing", RoyalDiamondRing.NAME,  3, RoyalDiamondRing.DESCRIPTION, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ALL_ENEMY,ElementType.Sun,ElementType.Luna);
        this.baseDamage = 12;
        this.baseMagicNumber = 0;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        super.use(p,m);
        this.baseMagicNumber = 0;
        for(int i = 0;i < p.orbs.size();i++){
            if(p.orbs.get(i) instanceof AbstractElementOrb){
                this.baseMagicNumber += 2;
                AbstractDungeon.actionManager.addToBottom(new ChangeOrbAction(i,true));
            }
        }
        this.magicNumber = this.baseMagicNumber;
        for(int i1 = 0;i1 < this.magicNumber;i1++) {
            for (int i = 0; i < AbstractDungeon.getCurrRoom().monsters.monsters.size(); i++) {
                AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.getCurrRoom().monsters.monsters.get(i), new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
            }
        }
    }

    public void applyPowers()
    {
        super.applyPowers();

        this.baseMagicNumber = 0;
        this.magicNumber = 0;
        for(int i = 0;i < AbstractDungeon.player.orbs.size();i++){
            if(AbstractDungeon.player.orbs.get(i) instanceof AbstractElementOrb){
                this.baseMagicNumber += 2;
            }
        }
        if (this.baseMagicNumber > 0)
        {
            this.rawDescription = (DESCRIPTION + EXTENDED_DESCRIPTION[0]);
            initializeDescription();
        }
    }

    public void onMoveToDiscard()
    {
        this.rawDescription = DESCRIPTION;
        initializeDescription();
    }

    public void calculateCardDamage(AbstractMonster mo)
    {
        super.calculateCardDamage(mo);
        if (this.baseMagicNumber > 0) {
            this.rawDescription = (DESCRIPTION + EXTENDED_DESCRIPTION[0]);
        }
        initializeDescription();
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
        EXTENDED_DESCRIPTION = RoyalDiamondRing.cardStrings.EXTENDED_DESCRIPTION;
    }
}
