package Thmod.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

import Thmod.Relics.SpellCardsRule;
import Thmod.ThMod;
import basemod.DevConsole;

public class CardFeedBack extends AbstractGameEffect {

    public CardFeedBack(float time){
        this.startingDuration = time;
        this.duration = this.startingDuration;
    }

    public void update() {
        this.duration -= Gdx.graphics.getDeltaTime();

        if(this.duration <= 0){
            ThMod.cardFeedback = false;
            this.isDone = true;
        }
        else {
            ThMod.cardFeedback = true;
            for (AbstractCard card : AbstractDungeon.player.hand.group){
                if(card.baseDamage > 0){
                    card.hover();
                }
            }
        }
    }

    public void render(SpriteBatch sb)
    {

    }

    public void dispose(){}
}
