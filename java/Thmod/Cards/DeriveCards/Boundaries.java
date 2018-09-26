package Thmod.Cards.DeriveCards;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import Thmod.Monsters.Yukari;
import Thmod.Power.ActivationPower;
import Thmod.Power.BoundariesPower;

public class Boundaries extends AbstractDeriveCards {
    public static final String ID = "Boundaries";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;

    public Boundaries() {
        super("Boundaries", Boundaries.NAME,  0, Boundaries.DESCRIPTION, CardType.SKILL, CardRarity.SPECIAL, CardTarget.NONE);
        this.exhaust = false;
        this.isEthereal = false;
        this.purgeOnUse = true;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        for(AbstractMonster monster : AbstractDungeon.getCurrRoom().monsters.monsters){
            if ((!(monster.isDying)) && (monster.currentHealth > 0) && (!(monster.isEscaping))) {
                if(monster instanceof Yukari){
                    for(AbstractPower power : ((Yukari) monster).powers){
                        if(power instanceof BoundariesPower){
                            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(monster,p,power));
                        }
                    }
//                    ((Yukari) monster).changeImg();
                }
            }
        }
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        for(AbstractPower power:p.powers){
            if(power instanceof ActivationPower){
                if(power.amount == 0){
                    return true;
                }
            }
        }
        return false;
    }

    public AbstractCard makeCopy() {
        return new Boundaries();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("Boundaries");
        NAME = Boundaries.cardStrings.NAME;
        DESCRIPTION = Boundaries.cardStrings.DESCRIPTION;
    }
}
