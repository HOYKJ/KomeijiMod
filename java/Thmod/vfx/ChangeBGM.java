package Thmod.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class ChangeBGM extends AbstractGameEffect {

    public ChangeBGM(float time){
        this.duration = time;
    }

    public void update()
    {
        this.duration -= Gdx.graphics.getDeltaTime();

        if(this.duration <= 0){
            CardCrawlGame.music.unsilenceBGM();
        }
    }

    public void render(SpriteBatch sb)
    {

    }

    public void dispose(){}
}
