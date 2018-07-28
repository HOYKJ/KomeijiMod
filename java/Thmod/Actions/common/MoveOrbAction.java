package Thmod.Actions.common;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;

import java.util.Collections;

public class MoveOrbAction extends AbstractGameAction {
    private AbstractPlayer p = AbstractDungeon.player;

    @Override
    public void update() {
        boolean hasElement = false;
        label1:
        for(int times =0;times < 99;times++) {
            for (int i = 0; i < p.orbs.size(); i++) {
                hasElement = false;
                if (p.orbs.get(i) instanceof EmptyOrbSlot) {
                    for (int i1 = i; i1 < p.orbs.size(); i1++) {
                        if (!(p.orbs.get(i1) instanceof EmptyOrbSlot)) {
                            hasElement = true;
                            AbstractOrb orbSlot = new EmptyOrbSlot();
                            for (int i2 = i1; i2 < p.orbs.size(); i2++) {
                                Collections.swap(p.orbs, i2, i2 - 1);
                            }
                            p.orbs.set(p.orbs.size() - 1, orbSlot);
                            for (int i2 = 0; i2 < p.orbs.size(); i2++) {
                                (p.orbs.get(i2)).setSlot(i2, p.maxOrbs);
                            }
                            break;
                        }
                    }
                }
                if(!(hasElement))
                    break label1;
            }
        }
        this.isDone = true;
    }
}
