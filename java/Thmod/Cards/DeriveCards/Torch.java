package Thmod.Cards.DeriveCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import Thmod.Events.RoomOfDemon;
import Thmod.Monsters.Remiria;

public class Torch extends AbstractDeriveCards {
    public static final String ID = "Torch";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;
    private int useTimes = 0;

    public Torch() {
        super("Torch", Torch.NAME,  0, Torch.DESCRIPTION, CardType.SKILL, CardRarity.SPECIAL, CardTarget.NONE);
        this.exhaust = false;
        this.isEthereal = false;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractMonster mo = AbstractDungeon.getCurrRoom().monsters.monsters.get(0);
        if(this.useTimes < 5) {
            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, 2));
            RoomOfDemon.torchDone = false;
            RoomOfDemon.torchNum = this.useTimes;
            Remiria.torchNum = (this.useTimes + 1);
        }
        switch (this.useTimes){
            case 0:
                for (AbstractPower power : p.powers) {
                    if (power.ID.equals("Vulnerable"))
                        AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(p, p, power.ID));
                    if (power.ID.equals("Frail"))
                        AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(p, p, power.ID));
                    if (power.ID.equals("Weakened"))
                        AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(p, p, power.ID));
                }
                this.rawDescription = EXTENDED_DESCRIPTION[0];
                initializeDescription();
                break;
            case 1:
                AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p,p,new StrengthPower(p,6),6));
                AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(mo,p,new StrengthPower(mo,-6),-6));
                this.rawDescription = EXTENDED_DESCRIPTION[1];
                initializeDescription();
                break;
            case 2:
                for (AbstractPower power : mo.powers) {
                    if (power.ID.equals("Metallicize"))
                        AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(mo, mo, power.ID));
                }
                this.rawDescription = EXTENDED_DESCRIPTION[2];
                initializeDescription();
                break;
            case 3:
                mo.currentHealth = 20;
                mo.healthBarUpdatedEvent();
//                this.exhaust = true;
                this.rawDescription = EXTENDED_DESCRIPTION[3];
                initializeDescription();
                break;
            case 4:
                for (AbstractPower power : mo.powers) {
                    if (power.ID.equals("IntangiblePlayer"))
                        AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(mo, mo, power.ID));
                }
                this.exhaust = true;
                break;
            default:
                this.exhaust = true;
        }
        this.useTimes += 1;
    }

//    public boolean canUse(AbstractPlayer p, AbstractMonster m){
//        super.canUse(p,m);
//        AbstractMonster mo = AbstractDungeon.getCurrRoom().monsters.monsters.get(0);
//        switch (this.useTimes) {
//            case 0:
//                boolean num1 = false;
//                boolean num2 = false;
//                for (AbstractPower power : p.powers) {
//                    if (power.ID.equals("Vulnerable"))
//                        num1 = true;
//                    if (power.ID.equals("Frail"))
//                        num2 = true;
//                    if((num1) && (num2))
//                        return true;
//                }
//                break;
//            case 1:
//                return true;
//            case 2:
//                for (AbstractPower power : mo.powers) {
//                    if (power.ID.equals("Barricade"))
//                        return true;
//                }
//                break;
//            case 3:
//                return true;
//            case 4:
//                for (AbstractPower power : mo.powers) {
//                    if (power.ID.equals("IntangiblePlayer"))
//                        return true;
//                }
//                break;
//            default:
//                return true;
//        }
//        this.cantUseMessage = "还不是时候...";
//        return false;
//    }

    public AbstractCard makeCopy() {
        return new Torch();
    }

    public void upgrade() {

    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("Torch");
        NAME = Torch.cardStrings.NAME;
        DESCRIPTION = Torch.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = Torch.cardStrings.EXTENDED_DESCRIPTION;
    }
}
