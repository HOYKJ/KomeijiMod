package Thmod.Cards.ElementCards.SpellCards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.unique.RemoveDebuffsAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import Thmod.Orbs.ElementOrb.AbstractElementOrb;
import Thmod.ThMod;

public class StElmoPillar extends AbstractElementSpellCards {
    public static final String ID = "StElmoPillar";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;

    public StElmoPillar() {
        super("StElmoPillar", StElmoPillar.NAME,  2, StElmoPillar.DESCRIPTION, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY,ElementType.Metal,ElementType.Fire);
        this.baseDamage = 24;
        this.baseMagicNumber = 6;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        super.use(p,m);
        AbstractDungeon.actionManager.addToTop(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
        AbstractDungeon.actionManager.addToBottom(new RemoveDebuffsAction(m));
    }

    public void applyPowers()
    {
        super.applyPowers();
    }

    public void calculateCardDamage(AbstractMonster mo)
    {
        super.calculateCardDamage(mo);
        for(AbstractPower power : mo.powers){
            if(power.type == AbstractPower.PowerType.DEBUFF){
                this.damage += this.magicNumber;
                this.isDamageModified = true;
                ThMod.logger.info("Modified damage");
            }
        }
    }

    public AbstractCard makeCopy() {
        return new StElmoPillar();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("StElmoPillar");
        NAME = StElmoPillar.cardStrings.NAME;
        DESCRIPTION = StElmoPillar.cardStrings.DESCRIPTION;
    }
}
