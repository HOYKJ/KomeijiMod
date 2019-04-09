package Thmod.Relics.remiria;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import Thmod.Power.remiria.BloodBruisePower;

public class TepesBloodVial extends AbstractRemiriaRelic {
    public static final String ID = "TepesBloodVial";
    private AbstractPlayer p = AbstractDungeon.player;

    public TepesBloodVial()
    {
        super("TepesBloodVial",  RelicTier.SPECIAL, LandingSound.CLINK);
    }

    @Override
    public void onEquip() {
        super.onEquip();
        this.counter = 0;
        this.img = ImageMaster.loadImage("images/relics/remiria/TepesBloodVial_0.png");
    }

    @Override
    public void onMonsterDeath(AbstractMonster m) {
        if(m.hasPower(BloodBruisePower.POWER_ID)){
            if(this.counter == 0){
                this.img = ImageMaster.loadImage("images/relics/remiria/TepesBloodVial.png");
            }
            this.counter += 1;
        }
        super.onMonsterDeath(m);
    }

    protected  void onRightClick(){
        if(this.counter > 0){
            p.heal(2);
            this.counter -= 1;
            if(this.counter == 0){
                this.img = ImageMaster.loadImage("images/relics/remiria/TepesBloodVial_0.png");
            }
        }
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new TepesBloodVial();
    }
}
