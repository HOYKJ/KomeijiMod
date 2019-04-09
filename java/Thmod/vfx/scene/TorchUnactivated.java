package Thmod.vfx.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

import java.util.ArrayList;

import Thmod.ThMod;

public class TorchUnactivated extends AbstractGameEffect
{
    private static final float HEAL_AMOUNT = 0.05F;
    private static final float DUR = 2.5F;
    private ArrayList<CeremonialTorchEffect> torchToDone;
    private boolean done1 = false;
    private boolean done2 = false;
    private boolean done3 = false;
    private boolean done4 = false;
    private boolean done5 = false;
    private boolean done6 = false;
    private boolean done7 = false;
    private boolean done8 = false;
    private boolean done9 = false;

    public TorchUnactivated(ArrayList<CeremonialTorchEffect> torchToDone)
    {
        this.startingDuration = 1.0F;
        this.duration = this.startingDuration;
        this.torchToDone = torchToDone;
    }

    public void update()
    {
        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration > (this.startingDuration - 0.1F))
        {
            if(!(done1)) {
                for (int i = 0; i < 9; i++) {
                    int i1 = ((i * 9) + 8);
                    this.torchToDone.get(i1).startCeremony(false);
                }
                done1 = true;
            }
        }
        else if (this.duration > (this.startingDuration - 0.2F))
        {
            if(!(done2)) {
                for (int i = 0; i < 9; i++) {
                    int i1 = ((i * 9) + 7);
                    this.torchToDone.get(i1).startCeremony(false);
                }
                done2 = true;
            }
        }
        else if (this.duration > (this.startingDuration - 0.3F))
        {
            if(!(done3)) {
                for (int i = 0; i < 9; i++) {
                    int i1 = ((i * 9) + 6);
                    this.torchToDone.get(i1).startCeremony(true);
                }
                done3 = true;
            }
        }
        else if (this.duration > (this.startingDuration - 0.4F))
        {
            if(!(done4)) {
                for (int i = 0; i < 9; i++) {
                    int i1 = ((i * 9) + 5);
                    this.torchToDone.get(i1).startCeremony(false);
                }
                done4 = true;
            }
        }
        else if (this.duration > (this.startingDuration - 0.5F))
        {
            if(!(done5)) {
                for (int i = 0; i < 9; i++) {
                    int i1 = ((i * 9) + 4);
                    this.torchToDone.get(i1).startCeremony(false);
                }
                done5 = true;
            }
        }
        else if (this.duration > (this.startingDuration - 0.6F))
        {
            if(!(done6)) {
                for (int i = 0; i < 9; i++) {
                    int i1 = ((i * 9) + 3);
                    this.torchToDone.get(i1).startCeremony(false);
                }
                done6 = true;
            }
        }
        else if (this.duration > (this.startingDuration - 0.7F))
        {
            if(!(done7)) {
                for (int i = 0; i < 9; i++) {
                    int i1 = ((i * 9) + 2);
                    this.torchToDone.get(i1).startCeremony(false);
                }
                done7 = true;
            }
        }
        else if (this.duration > (this.startingDuration - 0.8F))
        {
            if(!(done8)) {
                for (int i = 0; i < 9; i++) {
                    int i1 = ((i * 9) + 1);
                    this.torchToDone.get(i1).startCeremony(false);
                }
                done8 = true;
            }
        }
        else if (this.duration > (this.startingDuration - 0.9F))
        {
            if(!(done9)) {
                for (int i = 0; i < 9; i++) {
                    int i1 = (i * 9);
                    this.torchToDone.get(i1).startCeremony(false);
                }
                done9 = true;
            }
            if(ThMod.blessingOfTime != 3)
                this.isDone = true;
        }
        else if(this.duration > (this.startingDuration - 1.0F)){
            if(ThMod.blessingOfTime == 3){
                for(int i = 0;i < 9;i++) {
                    for (int i1 = 0; i1 < 9; i1++) {
                        if (((i == 0) && ((i1 == 2) || (i1 == 6)))
                                || ((i == 1) && ((i1 == 2) || (i1 == 3) || (i1 == 5) || (i1 == 6)))
                                || ((i == 2) && ((i1 == 2) || (i1 == 3) || (i1 == 4) || (i1 == 5) || (i1 == 6)))
                                || ((i == 3) && ((i1 == 1) || (i1 == 2) || (i1 == 6) || (i1 == 7)))
                                || ((i == 4) && ((i1 == 0) || (i1 == 2) || (i1 == 6) || (i1 == 8)))
                                || ((i == 5) && ((i1 == 1) || (i1 == 2) || (i1 == 6) || (i1 == 7)))
                                || ((i == 6) && ((i1 == 2) || (i1 == 3) || (i1 == 4) || (i1 == 5) || (i1 == 6)))
                                || ((i == 7) && ((i1 == 2) || (i1 == 3) || (i1 == 5) || (i1 == 6)))
                                || ((i == 8) && ((i1 == 2) || (i1 == 6)))) {
                            int i2 = (i * 9 + i1);
                            this.torchToDone.get(i2).setActivated(false);
                        }
                    }
                }
                this.isDone = true;
            }
        }
    }

    public void render(SpriteBatch sb)
    {
    }

    public void dispose(){}
}
