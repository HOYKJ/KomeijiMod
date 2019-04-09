package Thmod.Relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class JyouHari extends AbstractThRelic {
    public static final String ID = "JyouHari";

    public JyouHari()
    {
        super("JyouHari",  RelicTier.SPECIAL, LandingSound.MAGICAL);
    }

    public void atTurnStart() {

    }

    @Override
    public void atTurnStartPostDraw() {
        super.atTurnStartPostDraw();
        flash();
        for(AbstractMonster m:AbstractDungeon.getCurrRoom().monsters.monsters){
            if (!(m.isDeadOrEscaped())) {
                if (m.intent == AbstractMonster.Intent.ATTACK || m.intent == AbstractMonster.Intent.ATTACK_BUFF || m.intent == AbstractMonster.Intent.ATTACK_DEBUFF || m.intent == AbstractMonster.Intent.ATTACK_DEFEND){
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m,m,new VulnerablePower(m,1,false),1));
                }
                else {
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m,m,new WeakPower(m,1,false),1));
                }
            }
        }
    }

    protected  void onRightClick(){
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new JyouHari();
    }
}
