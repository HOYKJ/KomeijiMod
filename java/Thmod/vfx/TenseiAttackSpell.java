package Thmod.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

import java.util.ArrayList;

import basemod.DevConsole;

import static java.lang.Math.PI;

public class TenseiAttackSpell extends AbstractGameEffect {
    private float x;
    private float y;
    private float ro;
    private Color setColor;
    private Color setColor2;
    private TextureAtlas.AtlasRegion img;
    private TextureAtlas.AtlasRegion img2;
    private Vector2 starter;
    private float alpha;
    private float velocity;
    private float length;

    public TenseiAttackSpell(Vector2 starter, float ro){
        TextureAtlas spellAtlas = new TextureAtlas(Gdx.files.internal("images/vfx/spell.atlas"));
        this.img = spellAtlas.findRegion("Spell");
        this.img2 = spellAtlas.findRegion("SpellBack");
        this.starter = starter;
        this.startingDuration = 0.2f;
        this.duration = this.startingDuration;

        this.length = 0;
        this.velocity = 800 * Settings.scale;
        this.ro = ro;
        this.setColor = Color.WHITE.cpy();
        this.setColor2 = Color.PINK.cpy();
    }

    public void update()
    {
        this.duration -= Gdx.graphics.getDeltaTime();

        if(this.duration >= 0){
            this.alpha = Interpolation.fade.apply(1.0F, 0.0F, this.duration / this.startingDuration);
        }
        else {
            this.alpha = 1;
        }

        this.length += this.velocity * Gdx.graphics.getDeltaTime();

        this.x = this.starter.x + this.length * (float) Math.cos(this.ro);
        this.y = this.starter.y + this.length * (float) Math.sin(this.ro);
        this.rotation = (float) (this.ro / PI * 180) - 90;
    }

    public void render(SpriteBatch sb)
    {
        sb.setBlendFunction(770, 1);

        this.setColor2.a = this.alpha;
        sb.setColor(this.setColor2);
        sb.draw(this.img2, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight,
                this.scale * ((float) this.img2.packedWidth / (float) this.img.packedWidth) , this.scale * ((float) this.img2.packedHeight / (float) this.img.packedHeight) , this.rotation);

        this.setColor.a = this.alpha;
        sb.setColor(this.setColor);
        sb.draw(this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight,
                this.scale, this.scale, this.rotation);

        sb.setBlendFunction(770, 771);
    }

    public void dispose() {}
}
