package Thmod.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class TenseiAttackWave extends AbstractGameEffect {
    private float x;
    private float y;
    private TextureAtlas.AtlasRegion img;
    private float size;
    private Color setColor;
    private float alpha;
    private float velocity;

    public TenseiAttackWave(Vector2 starter){
        TextureAtlas spellAtlas = new TextureAtlas(Gdx.files.internal("images/vfx/spellWave.atlas"));
        this.img = spellAtlas.findRegion("SpellWave");
        this.x = starter.x  - this.img.packedWidth / 2.0F + 18;
        this.y = starter.y - this.img.packedHeight / 2.0F + 34;

        this.startingDuration = 2.5f;
        this.duration = this.startingDuration;
        this.size = 1;
        this.velocity = 700 * 2 * Settings.scale / this.img.packedHeight;
        this.setColor = Color.PINK.cpy();
    }

    public void update()
    {
        this.duration -= Gdx.graphics.getDeltaTime();

        this.size += this.velocity * Gdx.graphics.getDeltaTime();

        if(this.duration >= 0){
            this.alpha = this.duration / this.startingDuration;
        }
        else {
            this.isDone = true;
        }
    }

    public void render(SpriteBatch sb)
    {
        sb.setBlendFunction(770, 1);

        this.setColor.a = this.alpha;
        sb.setColor(this.setColor);
        sb.draw(this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight,
                this.scale * this.size, this.scale * this.size, this.rotation);

        sb.setBlendFunction(770, 771);
    }

    public void dispose() {}
}
