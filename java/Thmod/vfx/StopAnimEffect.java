package Thmod.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class StopAnimEffect extends AbstractGameEffect
{
    private AbstractCreature target;
    private float STC;

    public StopAnimEffect(AbstractCreature target, float time)
    {
        this.target = target;
        this.duration = time;
        if(target.state != null){
            STC = target.state.getTimeScale();
            target.state.setTimeScale(0F);
        }
    }

    public void update()
    {
        if (this.duration > 0.0F)
        {
            this.duration -= Gdx.graphics.getDeltaTime();
        }
        else
        {
            if(this.target.state != null){
                this.target.state.setTimeScale(STC);
            }
            this.isDone = true;
        }
    }

    public void render(SpriteBatch sb)
    {
    }

    public void dispose() {}
}
