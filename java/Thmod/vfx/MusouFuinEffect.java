package Thmod.vfx;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class MusouFuinEffect extends AbstractGameEffect {
    private float x;
    private float y;
    private static TextureAtlas.AtlasRegion img;
    private Color colors[] = new Color[7];

    public MusouFuinEffect(){
        img = ImageMaster.vfxAtlas.findRegion("combat/empowerCircle2");
        x = AbstractDungeon.player.dialogX;
        y = AbstractDungeon.player.dialogY;

        this.colors[0] = Color.SCARLET.cpy();
        this.colors[1] = Color.ORANGE.cpy();
        this.colors[2] = Color.YELLOW.cpy();
        this.colors[3] = Color.GREEN.cpy();
        this.colors[4] = Color.SKY.cpy();
        this.colors[5] = Color.BLUE.cpy();
        this.colors[6] = Color.PURPLE.cpy();
    }

    public void update() {

    }

    public void render(SpriteBatch sb) {
        sb.setColor(this.colors[0]);
        sb.draw(img, this.x, this.y - (float) (img.packedHeight / 2), 0.0F, (float) img.packedHeight / 2.0F, (float) img.packedWidth, (float) img.packedHeight, this.scale * 4.0F, this.scale * 4.0F, 0.0F);
    }
}
