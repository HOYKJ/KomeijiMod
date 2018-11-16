package Thmod.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;

public class SparkEffect extends AbstractGameEffect {
    private float x;
    private float y;
    private static final float DUR = 1.0F;
    private static TextureAtlas.AtlasRegion img1;
    private static TextureAtlas.AtlasRegion img2;
    private boolean playedSfx = false;
    private boolean playedSfx2 = false;
    private boolean flipHorizontal = false;
    private Color colors[] = new Color[7];
    private Color line;
    private float duration2 = 0.0F;
    private boolean step2 = true;
    private boolean narrow = false;

    public SparkEffect(float x, float y, boolean flipHorizontal, float time, int isMulti) {
        if (img1 == null) {
            img1 = ImageMaster.vfxAtlas.findRegion("combat/laserThin");
        }
        if (img2 == null) {
            if(isMulti >= 1)
                img2 = ImageMaster.vfxAtlas.findRegion("cone9");
            else
                img2 = ImageMaster.vfxAtlas.findRegion("combat/laserThick");
        }

        if(isMulti == -1){
            narrow = true;
        }

        this.flipHorizontal = flipHorizontal;
        this.x = x;
        this.y = y;
        this.color = Color.SKY.cpy();
        this.duration = time;
        this.startingDuration = time;
        if(isMulti == 2)
            this.step2 = false;
        if(!step2)
            this.duration2 = 0.75F;

        this.colors[0] = Color.SCARLET.cpy();
        this.colors[1] = Color.ORANGE.cpy();
        this.colors[2] = Color.YELLOW.cpy();
        this.colors[3] = Color.GREEN.cpy();
        this.colors[4] = Color.SKY.cpy();
        this.colors[5] = Color.BLUE.cpy();
        this.colors[6] = Color.PURPLE.cpy();

        this.line = Color.RED.cpy();
    }

    public void update() {
        if(step2) {
            if (!this.playedSfx) {
                AbstractDungeon.effectsQueue.add(new BorderLongFlashEffect(Color.SKY));
                this.playedSfx = true;
                CardCrawlGame.sound.play("nep00");
                CardCrawlGame.screenShake.rumble(2.0F);
            }
        }
        else {
            if(!this.playedSfx2){
                this.playedSfx2 = true;
                CardCrawlGame.sound.play("power0");
            }
        }

        if(this.duration2 <= 0) {
            this.step2 = true;
            this.duration -= Gdx.graphics.getDeltaTime();
            if (this.duration > this.startingDuration / 2.0F) {
                for (Color color1 : this.colors) {
                    color1.a = Interpolation.pow2In.apply(1.0F, 0.0F, this.duration - 0.5F);
                }
                this.color.a = Interpolation.pow2In.apply(1.0F, 0.0F, this.duration - 0.5F);
            } else {
                for (Color color1 : this.colors) {
                    color1.a = Interpolation.pow2Out.apply(0.0F, 1.0F, this.duration);
                }
                this.color.a = Interpolation.pow2Out.apply(0.0F, 1.0F, this.duration);
            }

            if (this.duration < 0.0F) {
                this.isDone = true;
            }
        }
        else {
            this.duration2 -= Gdx.graphics.getDeltaTime();
            this.line.a = Interpolation.pow2In.apply(1.0F, 0.0F, this.duration2);
        }

    }

    public void render(SpriteBatch sb) {
        sb.setBlendFunction(770, 1);
        sb.setColor(new Color(0.5F, 0.7F, 1.0F, this.color.a));
        if (!this.flipHorizontal) {
            if(step2) {
                sb.draw(img2, this.x, this.y - (float) (img2.packedHeight / 2), 0.0F, (float) img2.packedHeight / 2.0F, (float) img2.packedWidth, (float) img2.packedHeight, this.scale * 2.0F + MathUtils.random(-0.05F, 0.05F), this.scale * 1.5F + MathUtils.random(-0.1F, 0.1F), MathUtils.random(-4.0F, 4.0F));
                sb.draw(img2, this.x, this.y - (float) (img2.packedHeight / 2), 0.0F, (float) img2.packedHeight / 2.0F, (float) img2.packedWidth, (float) img2.packedHeight, this.scale * 2.0F + MathUtils.random(-0.05F, 0.05F), this.scale * 1.5F + MathUtils.random(-0.1F, 0.1F), MathUtils.random(-4.0F, 4.0F));
                sb.setColor(this.color);
                sb.draw(img2, this.x, this.y - (float) (img2.packedHeight / 2), 0.0F, (float) img2.packedHeight / 2.0F, (float) img2.packedWidth, (float) img2.packedHeight, this.scale * 2.0F, this.scale / 2.0F, MathUtils.random(-2.0F, 2.0F));
                sb.draw(img2, this.x, this.y - (float) (img2.packedHeight / 2), 0.0F, (float) img2.packedHeight / 2.0F, (float) img2.packedWidth, (float) img2.packedHeight, this.scale * 2.0F, this.scale / 2.0F, MathUtils.random(-2.0F, 2.0F));
                if(!narrow) {
                    for (Color color1 : colors) {
                        sb.setColor(color1);
                        sb.draw(img2, this.x, this.y - (float) (img2.packedHeight / 2), 0.0F, (float) img2.packedHeight / 2.0F, (float) img2.packedWidth, (float) img2.packedHeight, this.scale * 2.0F, this.scale / 2.0F, MathUtils.random(-2.0F, 2.0F));
                    }
                }
            }
            else {
                sb.setColor(this.line);
                sb.draw(img1, this.x, this.y - (float) (img1.packedHeight / 2), 0.0F, (float) img1.packedHeight / 2.0F, (float) img1.packedWidth, (float) img1.packedHeight, this.scale * 2.0F, this.scale * 0.25F, 0F);
            }
        } else {
            sb.draw(img2, this.x, this.y - (float)(img2.packedHeight / 2), 0.0F, (float)img2.packedHeight / 2.0F, (float)img2.packedWidth, (float)img2.packedHeight, this.scale * 2.0F + MathUtils.random(-0.05F, 0.05F), this.scale * 1.5F + MathUtils.random(-0.1F, 0.1F), MathUtils.random(186.0F, 189.0F));
            sb.draw(img2, this.x, this.y - (float)(img2.packedHeight / 2), 0.0F, (float)img2.packedHeight / 2.0F, (float)img2.packedWidth, (float)img2.packedHeight, this.scale * 2.0F + MathUtils.random(-0.05F, 0.05F), this.scale * 1.5F + MathUtils.random(-0.1F, 0.1F), MathUtils.random(186.0F, 189.0F));
            sb.setColor(this.color);
            sb.draw(img2, this.x, this.y - (float)(img2.packedHeight / 2), 0.0F, (float)img2.packedHeight / 2.0F, (float)img2.packedWidth, (float)img2.packedHeight, this.scale * 2.0F, this.scale / 2.0F, MathUtils.random(187.0F, 188.0F));
            sb.draw(img2, this.x, this.y - (float)(img2.packedHeight / 2), 0.0F, (float)img2.packedHeight / 2.0F, (float)img2.packedWidth, (float)img2.packedHeight, this.scale * 2.0F, this.scale / 2.0F, MathUtils.random(187.0F, 188.0F));

        }

        sb.setBlendFunction(770, 771);
    }
}
