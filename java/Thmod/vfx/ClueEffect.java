package Thmod.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class ClueEffect extends AbstractGameEffect{
    private Texture Img;

    public ClueEffect(int clueNum)
    {
        this.startingDuration = 3.0F;
        this.duration = this.startingDuration;

        switch (clueNum){
            case 1:
                this.Img = ImageMaster.loadImage("images/events/Clue.png");
                break;
            case 2:
                this.Img = ImageMaster.loadImage("images/events/Clue2.png");
                break;
            case 3:
                this.Img = ImageMaster.loadImage("images/events/Clue3.png");
                break;
        }
        float tmp = MathUtils.random(0.8F, 1.0F);
        this.color = new Color();
        this.color.r = tmp;
        this.color.g = (tmp - 0.03F);
        this.color.b = (tmp - 0.07F);
        this.color.a = 0.0F;
    }

    public void update()
    {
        if (this.startingDuration - this.duration < 1.0F) {
            this.color.a = Interpolation.fade.apply(0.0F, 1.0F, this.startingDuration - this.duration);
        } else if (this.duration < 1.0F) {
            this.color.a = Interpolation.fade.apply(1.0F, 0.0F, 1.0F - this.duration);

        }
        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration < 0.0F) {
            this.isDone = true;
        }
    }

    public void render(SpriteBatch sb)
    {
        sb.setColor(this.color);
//        sb.draw(this.Img, 600.0F * Settings.scale - 300.0F, 340.0F * Settings.scale -150.0F, Settings.WIDTH, Settings.HEIGHT);
        sb.draw(this.Img, 900.0F * Settings.scale - 300.0F, 500.0F * Settings.scale -150.0F, 300.0F, 150.0F, 600.0F, 300.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 600, 300, false, false);
    }

    public void dispose(){}
}
