package Thmod.Actions.unique;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import Thmod.Orbs.ElementOrb.AbstractElementOrb;
import Thmod.Orbs.Helan;
import Thmod.Orbs.NingyouOrb;
import Thmod.Orbs.Penglai;
import Thmod.Orbs.Shanghai;
import Thmod.Orbs.TateNingyou;
import Thmod.Orbs.YariNingyou;
import Thmod.Orbs.YumiNingyou;
import Thmod.Power.PointPower;
import Thmod.ThMod;
import basemod.DevConsole;

public class isSeedAction extends AbstractGameAction {
    private static final float startingDuration = 0.5f;

    public isSeedAction (){
        this.duration = 0.5f;
    }

    public void update() {
        AbstractPlayer p = AbstractDungeon.player;
        PointPower pointPower = new PointPower(p,0);
        if (this.duration == 0.5f) {
            for (final AbstractCard c : AbstractDungeon.player.hand.group) {
                pointPower.isSeed(c);
            }
            if(ThMod.AliceOpen) {
                int NingyouNum = 0;
                int YariNum = 0;
                int TateNum = 0;
                int YumiNum = 0;
                for (int i = 0; i < p.orbs.size(); i++) {
                    if (p.orbs.get(i) instanceof NingyouOrb)
                        NingyouNum += 1;
                    if ((p.orbs.get(i) instanceof YariNingyou) || (p.orbs.get(i) instanceof Shanghai) || (p.orbs.get(i) instanceof Penglai))
                        YariNum += 1;
                    if ((p.orbs.get(i) instanceof TateNingyou) || (p.orbs.get(i) instanceof Helan))
                        TateNum += 1;
                    if (p.orbs.get(i) instanceof YumiNingyou)
                        YumiNum += 1;
                }
                pointPower.addNingyou(NingyouNum, YariNum, TateNum, YumiNum);
            }
            else {
                boolean earth = false;
                boolean fire = false;
                boolean luna = false;
                boolean metal = false;
                boolean sun = false;
                boolean water = false;
                boolean wood = false;
                for (int i = 0; i < p.orbs.size(); i++) {
                    if(p.orbs.get(i) instanceof AbstractElementOrb) {
                        AbstractElementOrb elementOrb = (AbstractElementOrb) p.orbs.get(i);
                        switch (elementOrb.elementType){
                            case Earth:
                                earth = true;
                                break;
                            case Fire:
                                fire = true;
                                break;
                            case Luna:
                                luna = true;
                                break;
                            case Metal:
                                metal = true;
                                break;
                            case Sun:
                                sun = true;
                                break;
                            case Water:
                                water = true;
                                break;
                            case Wood:
                                wood = true;
                                break;
                        }
                    }
                }
                pointPower.elementMix(earth,fire,luna,metal,sun,water,wood,false);
            }
            pointPower.cardSelect();
        }
        this.tickDuration();
    }
}
