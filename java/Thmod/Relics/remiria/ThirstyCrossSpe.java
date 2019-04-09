package Thmod.Relics.remiria;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import Thmod.Power.remiria.BloodBruisePower;
import Thmod.Relics.AbstractThRelic;

public class ThirstyCrossSpe extends AbstractRemiriaRelic {
    public static final String ID = "ThirstyCrossSpe";
    private AbstractPlayer p;

    public ThirstyCrossSpe()
    {
        super("ThirstyCrossSpe",  RelicTier.STARTER, LandingSound.HEAVY);
        this.p = AbstractDungeon.player;
    }

    @Override
    public void onEquip() {
        super.onEquip();
        this.p = AbstractDungeon.player;
    }

    public void atBattleStart() {
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p,p,new StrengthPower(p,1),1));
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p,p,new DexterityPower(p,1),1));
        beginLongPulse();
        this.pulse = true;
    }

    public void atTurnStart() {
        beginLongPulse();
        this.pulse = true;
    }

    public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {
        if(targetCard.type == AbstractCard.CardType.ATTACK){
            this.pulse = false;
            stopPulse();
        }
    }

    public void onPlayerEndTurn() {
        if(this.pulse){
            AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p, p, new BloodBruisePower(p, 2), 2));
        }
    }

    protected  void onRightClick(){
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new ThirstyCrossSpe();
    }
}
