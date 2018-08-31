package Thmod.Relics;

import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
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

    public void atTurnStart() {
        AbstractPlayer p = AbstractDungeon.player;
        if(p.maxOrbs < 10) {
            flash();
            AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            CardCrawlGame.sound.play("MAP_OPEN_2", 0.1F);
            p.maxOrbs += 1;
            for (int i = 0; i < 1; i++) {
                p.orbs.add(new EmptyOrbSlot());
            }
            for (int i = 0; i < p.orbs.size(); i++) {
                (p.orbs.get(i)).setSlot(i, p.maxOrbs);
            }
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
