package Thmod.vfx;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class BloodWakeEffect extends AbstractGameEffect
{
    private TextureAtlas.AtlasRegion img;
    private Color setColor;
    private Vector2 prevPosition;
    private float sca;
    private int max;
    private int i;

    public BloodWakeEffect(Color setColor, Vector2 prevPosition, int max, int i, float sca, boolean renderBehind, Color color)
    {
        this.setColor = setColor;
        this.prevPosition = prevPosition;
        this.img = ImageMaster.STRIKE_BLUR;
        this.max = max;
        this.i = i;
        this.sca = sca;
        this.renderBehind = renderBehind;
        this.color = color;
    }

    public void changeState(Color setColor,int max, int i, float sca, Color color){
        this.setColor = setColor;
        this.max = max;
        this.i = i;
        this.sca = sca;
        this.color = color;
    }

    public void update()
    {

    }

    public void render(SpriteBatch sb) {
        Color tmpColor = this.setColor.cpy();
        tmpColor.a = this.color.a;

        tmpColor.a *= 0.95F;
        sb.setColor(tmpColor);
        sb.draw(this.img, this.prevPosition.x, this.prevPosition.y,
                this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight,
                this.scale * (this.i + 5) / this.max * this.sca / 2.5F, this.scale * (this.i + 5) / this.max * this.sca / 2.5F, this.rotation);
        sb.draw(this.img, this.prevPosition.x, this.prevPosition.y,
                this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight,
                this.scale * (this.i + 5) / this.max * this.sca / 2.5F, this.scale * (this.i + 5) / this.max * this.sca / 2.5F, this.rotation);

    }

    public void powerRemove(){
        this.isDone = true;
    }

    public void dispose() {}
}
