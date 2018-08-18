package Thmod.Actions.common;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;

import Thmod.Orbs.Helan;
import Thmod.Orbs.NingyouOrb;
import Thmod.Orbs.Penglai;
import Thmod.Orbs.Shanghai;
import Thmod.Orbs.TateNingyou;
import Thmod.Orbs.YariNingyou;
import Thmod.Orbs.YumiNingyou;

public class ChangeOrbAction extends AbstractGameAction{
    private int orbNum;
    private boolean toEmpty;
    private int setNum;

    public ChangeOrbAction(int orbNum,boolean toEmpty){
        this.orbNum = orbNum;
        this.toEmpty = toEmpty;
        this.setNum = 0;
    }

    public ChangeOrbAction(int orbNum,boolean toEmpty,int setNum){
        this.orbNum = orbNum;
        this.toEmpty = toEmpty;
        this.setNum = setNum;
    }

    public ChangeOrbAction(int orbNum,int setNum){
        this.orbNum = orbNum;
        this.toEmpty = false;
        this.setNum = setNum;
    }

    @Override
    public void update() {
        AbstractPlayer p = AbstractDungeon.player;
        if (this.setNum == 0) {
            p.orbs.get(this.orbNum).onEvoke();
            if (this.toEmpty) {
                AbstractOrb orb = new EmptyOrbSlot();
                p.orbs.set(this.orbNum, orb);
            } else {
                AbstractOrb orb = new NingyouOrb();
                p.orbs.set(this.orbNum, orb);
            }
        }
        else if(this.setNum == 1){
            AbstractOrb orb = new YariNingyou();
            p.orbs.set(this.orbNum, orb);
        }
        else if(this.setNum == 2){
            AbstractOrb orb = new TateNingyou();
            p.orbs.set(this.orbNum, orb);
        }
        else if(this.setNum == 3){
            AbstractOrb orb = new YumiNingyou();
            p.orbs.set(this.orbNum, orb);
        }
        else if(this.setNum == 4){
            AbstractOrb orb = new Shanghai();
            p.orbs.set(this.orbNum, orb);
        }
        else if(this.setNum == 5){
            AbstractOrb orb = new Penglai();
            p.orbs.set(this.orbNum, orb);
        }
        else if(this.setNum == 6){
            AbstractOrb orb = new Helan();
            p.orbs.set(this.orbNum, orb);
        }

        for (int i = 0; i < p.orbs.size(); ++i)
            (p.orbs.get(i)).setSlot(i, p.maxOrbs);
        isDone = true;
    }
}
