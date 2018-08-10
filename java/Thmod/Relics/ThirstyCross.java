package Thmod.Relics;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class ThirstyCross extends AbstractThRelic {
    public static final String ID = "ThirstyCross";
    private AbstractPlayer p = AbstractDungeon.player;

    public ThirstyCross()
    {
        super("ThirstyCross",  RelicTier.SPECIAL, LandingSound.HEAVY);
    }

    public void atBattleStart() {
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p,p,new StrengthPower(p,2),2));
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
            AbstractDungeon.actionManager.addToTop(new DamageAction(p, new DamageInfo(p, 3, DamageInfo.DamageType.HP_LOSS), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        }
    }

    protected  void onRightClick(){
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new ThirstyCross();
    }
}
