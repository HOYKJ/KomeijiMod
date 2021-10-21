package Thmod.Relics;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import Thmod.ThMod;
import Thmod.vfx.ClueEffect;
import Thmod.vfx.LatterEffect;

public class Clue extends AbstractThRelic {
    public static final String ID = "Clue";
    private boolean remove = false;
    private Clue relicAdd;

    public Clue()
    {
        super("Clue",  RelicTier.SPECIAL, LandingSound.FLAT);
    }

    @Override
    public void onEquip() {
        for(AbstractRelic relic:AbstractDungeon.player.relics){
            if((relic instanceof Clue) && (relic != this)){
                //remove = true;
                AbstractDungeon.effectList.add(new LatterEffect(()->{
                    relicAdd.addClue();
                    AbstractDungeon.player.relics.remove(this);
                    AbstractDungeon.player.reorganizeRelics();
                }, 0));
                relicAdd = ((Clue) relic);
            }
            else
                this.counter = ThMod.numOfClue;
        }
        if(this.counter != 3)
            this.description = DESCRIPTIONS[0] + "纸条似乎是残缺的.";
    }

    @Override
    public void renderCounter(SpriteBatch sb, boolean inTopPanel) {

    }

    @Override
    public void update() {
        super.update();
        if(remove) {


        }
    }

    protected  void onRightClick(){
        AbstractDungeon.effectList.add(new ClueEffect(this.counter));
    }

    public void addClue(){
        if(this.counter < 3) {
            this.counter += 1;
            ThMod.numOfClue += 1;
        }
        if(this.counter != 3)
            this.description = DESCRIPTIONS[0] + "纸条似乎是残缺的.";
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new Clue();
    }
}
