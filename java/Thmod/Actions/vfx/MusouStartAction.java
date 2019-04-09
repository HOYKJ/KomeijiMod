package Thmod.Actions.vfx;

import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

import Thmod.vfx.MusouTenseiEffect;
import basemod.DevConsole;

public class MusouStartAction extends AbstractGameAction {
    private ArrayList<MusouTenseiEffect> effects;
    private int num;

    public MusouStartAction(ArrayList<MusouTenseiEffect> effects){
        this.effects = effects;
        this.startDuration = 0.399f;
        this.duration = 0.0f;
        this.num = 0;
    }

    @Override
    public void update() {

        if(this.duration <= 0){
            this.duration = this.startDuration;
            AbstractDungeon.effectList.add(effects.get(this.num));
            effects.get(this.num).duration = effects.get(0).duration;
            effects.get(this.num).rotation2 = effects.get(0).rotation2;
            this.num += 1;
        }

        if(this.num == 7){
            this.isDone = true;
        }

        this.duration -= Gdx.graphics.getDeltaTime();
    }
}
