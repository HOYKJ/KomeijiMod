package Thmod.Cards.ScarletCard;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Actions.common.LatterAction;
import Thmod.Characters.RemiriaScarlet;
import Thmod.Power.remiria.ScarletLordPower;
import basemod.helpers.TooltipInfo;

public class Warpath extends AbstractRemiriaFate {
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
        AbstractDungeon.actionManager.addToBottom(new LatterAction(()->{
            for(AbstractCard card : p.hand.group){
                if(card.baseDamage > 0){
                    card.baseDamage += this.magicNumber;
                }
            }
            if(this.isPlus){
                AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, 1));
            }
            super.use(p, m);
        },0.1f));
    }

    @Override
    public void triggerOnExhaust() {
        super.triggerOnExhaust();
        for(AbstractCard card : AbstractDungeon.player.hand.group){
            if(card.baseDamage > 0){
                card.baseDamage += this.magicNumber;
            }
        }

            for(AbstractCard card : AbstractDungeon.player.drawPile.group){
                if(card.baseDamage > 0){
                    card.baseDamage += this.magicNumber;
                }
            }
            for(AbstractCard card : AbstractDungeon.player.discardPile.group){
                if(card.baseDamage > 0){
                    card.baseDamage += this.magicNumber;
                }
            }

    }

    public AbstractCard makeCopy() {
        if(AbstractDungeon.player != null){
            if((AbstractDungeon.player instanceof RemiriaScarlet) && (AbstractDungeon.player.hasPower(ScarletLordPower.POWER_ID))){
                return new Warpath(true);
            }
        }
        return new Warpath();
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
