package Thmod.Cards.ScarletCard;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Characters.RemiriaScarlet;
import Thmod.Power.remiria.ScarletLordPower;
import basemod.helpers.TooltipInfo;

public class Warpath extends AbstractRemiriaCards {
    public static final String ID = "Warpath";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;

    public Warpath() {
        this(false);
    }

    public Warpath(boolean isPlus) {
        super("Warpath", Warpath.NAME,  0, Warpath.DESCRIPTION, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE, isPlus);
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
        this.addTips();
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        for(AbstractCard card : p.hand.group){
            if(card.baseDamage > 0){
                card.baseDamage += this.magicNumber;
            }
        }
        if(this.isPlus){
            for(AbstractCard card : p.drawPile.group){
                if(card.baseDamage > 0){
                    card.baseDamage += this.magicNumber;
                }
            }
            for(AbstractCard card : p.discardPile.group){
                if(card.baseDamage > 0){
                    card.baseDamage += this.magicNumber;
                }
            }
        }
        super.use(p, m);
    }

    public AbstractCard makeCopy() {
        if(AbstractDungeon.player != null){
            if((AbstractDungeon.player instanceof RemiriaScarlet) && (AbstractDungeon.player.hasPower(ScarletLordPower.POWER_ID))){
                return new Warpath(true);
            }
        }
        return new Warpath();
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        for(AbstractCard card : AbstractDungeon.actionManager.cardsPlayedThisTurn){
            if(card.type == CardType.ATTACK){
                return false;
            }
        }
        return super.canUse(p, m);
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
        }
    }

    @Override
    public void addTips() {
        this.tips.clear();
        this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("Warpath");
        NAME = Warpath.cardStrings.NAME;
        DESCRIPTION = Warpath.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = Warpath.cardStrings.EXTENDED_DESCRIPTION;
    }
}
