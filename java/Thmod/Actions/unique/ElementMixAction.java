package Thmod.Actions.unique;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import Thmod.Orbs.ElementOrb.AbstractElementOrb;
import Thmod.Power.PointPower;
import Thmod.Relics.SpellCardsRule;

public class ElementMixAction extends AbstractGameAction {
    private static final float startingDuration = 0.5f;
    private AbstractElementOrb orb;
    private AbstractElementOrb orb2;

    public ElementMixAction(AbstractElementOrb orb){
        this.duration = 0.5f;
        this.orb = orb;
        this.orb2 = null;
    }

    public ElementMixAction(){
        this.duration = 0.5f;
        this.orb = null;
        this.orb2 = null;
    }

    public void addOrb(AbstractElementOrb orb){
        if(this.orb == null){
            this.orb = orb;
        }
        else {
            this.orb2 = orb;
        }
    }

    public void update() {
        if (this.duration == 0.5f) {
            if(this.orb2 != null){
                AbstractPlayer p = AbstractDungeon.player;
                PointPower pointPower = new PointPower(p, 0);
                boolean earth = false;
                boolean fire = false;
                boolean luna = false;
                boolean metal = false;
                boolean sun = false;
                boolean water = false;
                boolean wood = false;
                switch (this.orb.elementType) {
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
                switch (this.orb2.elementType) {
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
                if(((earth) && (wood)) || ((luna) && (water)) || ((luna) && (fire)) || ((luna) && (earth)) || ((sun) && (metal)) || ((sun) && (fire)) || ((sun) && (earth)))
                    AbstractDungeon.actionManager.addToTop(new PlayerTalkAction(p, PointPower.DESCRIPTIONS[2]));
                else
                    pointPower.elementMix(earth, fire, luna, metal, sun, water, wood, true);
                SpellCardsRule.orbToMix.clear();
            }
        }
        this.tickDuration();
    }
}
