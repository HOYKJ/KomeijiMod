package Thmod.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class FireDamageEffect extends AbstractGameEffect {
    private static int blockSound = 0;
    public TextureAtlas.AtlasRegion img;
    private float x;
    private float y;
    private static final float DURATION = 0.6F;

    public FireDamageEffect(float x, float y, Color color, boolean mute) {
        this.duration = 0.6F;
        this.startingDuration = 0.6F;
        this.img = ImageMaster.ATK_FIRE;
        if (this.img != null) {
            this.x = x - (float)this.img.packedWidth / 2.0F;
            y -= (float)this.img.packedHeight / 2.0F;
        }

        this.color = color.cpy();
        this.scale = Settings.scale;
        if (!mute) {
            CardCrawlGame.sound.play("ATTACK_FIRE");
        }

        this.y = y;
    }

    public FireDamageEffect(float x, float y, Color color) {
        this(x, y, color, false);
    }

    public void update() {
        super.update();

    }

    public void render(SpriteBatch sb) {
        if (this.img != null) {
            sb.setColor(this.color);
            sb.draw(this.img, this.x, this.y, (float)this.img.packedWidth / 2.0F, (float)this.img.packedHeight / 2.0F, (float)this.img.packedWidth, (float)this.img.packedHeight, this.scale, this.scale, this.rotation);
        }

    }

    public void dispose() {
    }
}
