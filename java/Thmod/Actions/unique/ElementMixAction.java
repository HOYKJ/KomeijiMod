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

    public ElementMixAction(AbstractElementOrb orb){
        this.duration = 0.5f;
        this.orb = orb;
    }

    public void update() {
        if (this.duration == 0.5f) {
            if(SpellCardsRule.orbToMix.size() == 0)
                SpellCardsRule.orbToMix.add(this.orb);
            else {
                AbstractPlayer p = AbstractDungeon.player;
                PointPower pointPower = new PointPower(p, 0);
                boolean earth = false;
                boolean fire = false;
                boolean luna = false;
                boolean metal = false;
                boolean sun = false;
                boolean water = false;
                boolean wood = false;
                switch (SpellCardsRule.orbToMix.get(0).elementType) {
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
                if(((earth) && (wood)) || ((luna) && (water)) || ((luna) && (fire)) || ((luna) && (earth)) || ((sun) && (metal)) || ((sun) && (fire)) || ((sun) && (earth)))
                    AbstractDungeon.actionManager.addToTop(new PlayerTalkAction(p, "融合失败..."));
                else
                    pointPower.elementMix(earth, fire, luna, metal, sun, water, wood, true);
                SpellCardsRule.orbToMix.clear();
            }
        }
        this.tickDuration();
    }
}
