package Thmod.Relics.remiria;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.RestRoom;

import Thmod.Power.remiria.BloodBruisePower;

public class RedTeaWithBlood extends AbstractRemiriaRelic {
    public static final String ID = "RedTeaWithBlood";
    private AbstractPlayer p;

    public RedTeaWithBlood()
    {
        super("RedTeaWithBlood",  RelicTier.RARE, LandingSound.CLINK);
        this.p = AbstractDungeon.player;
    }

    @Override
    public void onEquip() {
        super.onEquip();
        this.counter = 1;
        this.p = AbstractDungeon.player;
        this.description = this.getUpdatedDescription();
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        this.initializeTips();
    }

    @Override
    public void onMonsterDeath(AbstractMonster m) {
        super.onMonsterDeath(m);
        if(m.hasPower(BloodBruisePower.POWER_ID)){
            this.counter ++;
            this.description = this.getUpdatedDescription();
            this.tips.clear();
            this.tips.add(new PowerTip(this.name, this.description));
            this.initializeTips();
        }
    }

//    @Override
//    public void onSmith() {
//        super.onSmith();
//        if(this.counter > 1){
//            this.p.heal((int) (this.p.maxHealth * (float)this.counter / 100));
//        }
//    }

    public void onEnterRoom(AbstractRoom room)
    {
        if ((room instanceof RestRoom)) {
            flash();
            if (this.counter > 1) {
                this.p.heal((int) (this.p.maxHealth * (float) this.counter / 100));
            }
        }
    }

    protected  void onRightClick(){
    }

    public String getUpdatedDescription() {
        if(this.counter > 0){
            return (this.DESCRIPTIONS[0] + this.DESCRIPTIONS[1] + this.counter + this.DESCRIPTIONS[2]);
        }
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new RedTeaWithBlood();
    }
}
