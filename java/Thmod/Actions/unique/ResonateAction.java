package Thmod.Actions.unique;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import Thmod.Actions.common.ChangeOrbAction;
import Thmod.Actions.common.MoveOrbAction;
import Thmod.Orbs.ElementOrb.AbstractElementOrb;

public class ResonateAction extends AbstractGameAction {
    private AbstractElementOrb orb;

    public ResonateAction(AbstractElementOrb orb){
        this.orb = orb;
    }

    public void update(){
        AbstractPlayer p = AbstractDungeon.player;
        int orbNum = 0;
        for(int i = 0;i < p.orbs.size();i++){
            if(p.orbs.get(i) instanceof AbstractElementOrb){
                if(((AbstractElementOrb) p.orbs.get(i)).elementType == this.orb.elementType)
                    orbNum += 1;
            }
        }
        if(orbNum >= 2){
            AbstractDungeon.actionManager.addToTop(new MoveOrbAction());
            switch (this.orb.elementType){
                case Fire:
                    AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p,p,new StrengthPower(p,1),1));
                    break;
                case Water:
                    AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p,p,new DexterityPower(p,1),1));
                    break;
                case Wood:
                    AbstractDungeon.actionManager.addToTop(new DrawCardAction(p,2));
                    break;
                case Earth:
                    AbstractDungeon.actionManager.addToTop(new GainBlockAction(p,p,8));
                    break;
                case Metal:
                    AbstractDungeon.actionManager.addToTop(new DamageAction(AbstractDungeon.getMonsters().getRandomMonster(true),new DamageInfo(p,10, DamageInfo.DamageType.THORNS),AttackEffect.FIRE));
                    break;
            }
            label1:
            for(int i = 0;i < p.orbs.size();i++){
                if(p.orbs.get(i) instanceof AbstractElementOrb){
                    if(((AbstractElementOrb) p.orbs.get(i)).elementType == this.orb.elementType) {
                        AbstractDungeon.actionManager.addToTop(new ChangeOrbAction(i, true));
                        for(int i1 = (i+1);i1 < p.orbs.size();i1++){
                            if(p.orbs.get(i1) instanceof AbstractElementOrb) {
                                if (((AbstractElementOrb) p.orbs.get(i1)).elementType == this.orb.elementType) {
                                    AbstractDungeon.actionManager.addToTop(new ChangeOrbAction(i1, true));
                                    break label1;
                                }
                            }
                        }
                    }
                }
            }

        }
        this.isDone = true;
    }
}
