package Thmod.Relics;

import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.defect.IncreaseMaxOrbAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class Grimoire extends AbstractThRelic {
    public static final String ID = "Grimoire";

    public Grimoire()
    {
        super("Grimoire",  RelicTier.BOSS, LandingSound.MAGICAL);
    }

    public void onEquip()
    {
        this.counter = 0;
    }

    public void atTurnStart()
    {
        if (this.counter == -1)
            this.counter += 2;
        else {
            this.counter += 1;
        }

        if (this.counter == 2) {
            this.counter = 0;
            flash();
            AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            AbstractDungeon.actionManager.addToBottom(new IncreaseMaxOrbAction(1));
        }
    }

    protected  void onRightClick(){
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new Grimoire();
    }
}
