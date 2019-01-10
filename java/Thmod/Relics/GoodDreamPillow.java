package Thmod.Relics;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.util.ArrayList;

import basemod.DevConsole;

public class GoodDreamPillow extends AbstractThRelic {
    public static final String ID = "GoodDreamPillow";
    public static ArrayList<Boolean> dreamCode = new ArrayList<>();

    public GoodDreamPillow()
    {
        super("GoodDreamPillow",  RelicTier.SPECIAL, LandingSound.MAGICAL);
        dreamCode.clear();
        dreamCode.add(MathUtils.randomBoolean());
        dreamCode.add(MathUtils.randomBoolean());
        dreamCode.add(MathUtils.randomBoolean());
        ArrayList<Integer> number = new ArrayList<>();
        number.add((MathUtils.random(9)));
        if(dreamCode.get(0)){
            if(number.get(0) == 9){
                number.add(9);
            }
            else {
                number.add(number.get(0) + MathUtils.random(8 - number.get(0)) + 1);
            }
        }
        else {
            if(number.get(0) == 0){
                number.add(0);
            }
            else {
                number.add(number.get(0) - MathUtils.random(number.get(0) - 1) - 1);
            }
        }
        if(dreamCode.get(1)){
            if(number.get(1) == 9){
                number.add(9);
            }
            else {
                number.add(number.get(1) + MathUtils.random(8 - number.get(1)) + 1);
            }
        }
        else {
            if(number.get(1) == 0){
                number.add(0);
            }
            else {
                number.add(number.get(1) - MathUtils.random(number.get(1) - 1) - 1);
            }
        }
        if(dreamCode.get(2)){
            if(number.get(2) == 9){
                number.add(9);
            }
            else {
                number.add(number.get(2) + MathUtils.random(8 - number.get(2)) + 1);
            }
        }
        else {
            if(number.get(2) == 0){
                number.add(0);
            }
            else {
                number.add(number.get(2) - MathUtils.random(number.get(2) - 1) - 1);
            }
        }
        this.description = (this.DESCRIPTIONS[0] + number.get(0) + number.get(1) + number.get(2) + number.get(3));
    }

    protected  void onRightClick(){
    }

    @Override
    public void onEquip() {
        super.onEquip();
        this.counter = 1;
    }

    public String getUpdatedDescription() {
        return (this.DESCRIPTIONS[0]);
    }

    public AbstractRelic makeCopy() {
        return new GoodDreamPillow();
    }

    @Override
    public void renderCounter(SpriteBatch sb, boolean inTopPanel) {
    }
}
