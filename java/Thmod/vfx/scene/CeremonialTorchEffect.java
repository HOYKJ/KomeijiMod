package Thmod.vfx.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.scene.LightFlareLEffect;
import com.megacrit.cardcrawl.vfx.scene.LightFlareMEffect;
import com.megacrit.cardcrawl.vfx.scene.LightFlareSEffect;
import com.megacrit.cardcrawl.vfx.scene.TorchParticleLEffect;
import com.megacrit.cardcrawl.vfx.scene.TorchParticleMEffect;
import com.megacrit.cardcrawl.vfx.scene.TorchParticleSEffect;

import Thmod.vfx.CeremonyAction;
import Thmod.Relics.SpellCardsRule;

public class CeremonialTorchEffect  extends AbstractGameEffect
{
    private float x;
    private float y;
    private Hitbox hb;
    private boolean activated = true;
    private float particleTimer1 = 0.0F;
    private static final float PARTICLE_EMIT_INTERVAL = 0.1F;
    private static TextureAtlas.AtlasRegion img;
    private TorchSize size = TorchSize.M;
    private int num1;
    private int num2;
    private boolean started = false;

    public CeremonialTorchEffect(float x, float y, TorchSize size,int num1,int num2)
    {
        if (img == null) {
            img = ImageMaster.vfxAtlas.findRegion("env/torch");
        }
        this.size = size;
        this.x = x;
        this.y = y;
        this.num1 = num1;
        this.num2 = num2;
        this.hb = new Hitbox(50.0F * Settings.scale, 50.0F * Settings.scale);
        this.hb.move(x, y);
        this.color = new Color(1.0F, 1.0F, 1.0F, 0.4F);
        switch (size)
        {
            case S:
                this.scale = (Settings.scale * 0.6F);
                break;
            case M:
                this.scale = Settings.scale;
                break;
            case L:
                this.scale = (Settings.scale * 1.4F);
                break;
        }
    }

    public static enum TorchSize
    {
        S,  M,  L;

        private TorchSize() {}
    }

    public void update()
    {
        this.hb.update();
        if ((this.hb.hovered) && (InputHelper.justClickedLeft) && (!(this.size == TorchSize.M)))
        {
            this.activated = (!this.activated);
            if (this.activated) {
                if (((num1 == 0) && ((num2 == 2) || (num2 == 6)))
                        || ((num1 == 1) && ((num2 == 2) || (num2 == 3) || (num2 == 5) || (num2 == 6)))
                        || ((num1 == 2) && ((num2 == 2) || (num2 == 3) || (num2 == 4) || (num2 == 5) || (num2 == 6)))
                        || ((num1 == 3) && ((num2 == 1) || (num2 == 2) || (num2 == 6) || (num2 == 7)))
                        || ((num1 == 4) && ((num2 == 0) || (num2 == 2) || (num2 == 6) || (num2 == 8)))
                        || ((num1 == 5) && ((num2 == 1) || (num2 == 2) || (num2 == 6) || (num2 == 7)))
                        || ((num1 == 6) && ((num2 == 2) || (num2 == 3) || (num2 == 4) || (num2 == 5) || (num2 == 6)))
                        || ((num1 == 7) && ((num2 == 2) || (num2 == 3) || (num2 == 5) || (num2 == 6)))
                        || ((num1 == 8) && ((num2 == 2) || (num2 == 6)))){
                    if(this.started) {
//                        CardCrawlGame.sound.playA("ATTACK_FIRE", 0.4F);
                        CardCrawlGame.sound.play("Fire_Remnant2");
                    }
                    else
                        CardCrawlGame.sound.playA("ATTACK_FIRE", 0.4F);
                }
                else
                    CardCrawlGame.sound.playA("ATTACK_FIRE", 0.4F);
            } else {
//                if (((num1 == 0) && ((num2 == 2) || (num2 == 6)))
//                    || ((num1 == 1) && ((num2 == 2) || (num2 == 3) || (num2 == 5) || (num2 == 6)))
//                    || ((num1 == 2) && ((num2 == 2) || (num2 == 3) || (num2 == 4) || (num2 == 5) || (num2 == 6)))
//                    || ((num1 == 3) && ((num2 == 1) || (num2 == 2) || (num2 == 6) || (num2 == 7)))
//                    || ((num1 == 4) && ((num2 == 0) || (num2 == 2) || (num2 == 6) || (num2 == 8)))
//                    || ((num1 == 5) && ((num2 == 1) || (num2 == 2) || (num2 == 6) || (num2 == 7)))
//                    || ((num1 == 6) && ((num2 == 2) || (num2 == 3) || (num2 == 4) || (num2 == 5) || (num2 == 6)))
//                    || ((num1 == 7) && ((num2 == 2) || (num2 == 3) || (num2 == 5) || (num2 == 6)))
//                    || ((num1 == 8) && ((num2 == 2) || (num2 == 6)))){
//                if(this.started) {
////                        CardCrawlGame.sound.playA("ATTACK_FIRE", 0.4F);
//                    CardCrawlGame.sound.play("Fire_Remnant2");
//                }
//                else
//                    CardCrawlGame.sound.playA("ATTACK_FIRE", -0.4F);
//            }
//            else
                CardCrawlGame.sound.playA("ATTACK_FIRE", -0.4F);
            }
            if(this.started) {
                SpellCardsRule.torchLight.set(((this.num1 * 9) + this.num2), (!(SpellCardsRule.torchLight.get(((this.num1 * 9) + this.num2)))));
                AbstractDungeon.effectsQueue.add(new CeremonyAction());
            }
        }
        if (this.activated)
        {
            this.particleTimer1 -= Gdx.graphics.getDeltaTime();
            if (this.particleTimer1 < 0.0F)
            {
                this.particleTimer1 = 0.1F;
                switch (this.size)
                {
                    case S:
                        AbstractDungeon.effectsQueue.add(new TorchParticleSEffect(this.x, this.y - 10.0F * Settings.scale));
                        AbstractDungeon.effectsQueue.add(new LightFlareSEffect(this.x, this.y - 10.0F * Settings.scale));
                        break;
                    case M:
                        AbstractDungeon.effectsQueue.add(new TorchParticleMEffect(this.x, this.y));
                        AbstractDungeon.effectsQueue.add(new LightFlareMEffect(this.x, this.y));
                        break;
                    case L:
                        AbstractDungeon.effectsQueue.add(new TorchParticleLEffect(this.x, this.y + 14.0F * Settings.scale));
                        AbstractDungeon.effectsQueue.add(new LightFlareLEffect(this.x, this.y + 14.0F * Settings.scale));
                        break;
                }
            }
        }
    }

    public void startCeremony(boolean sound){
        this.activated = false;
        this.started = true;
        if(sound)
            CardCrawlGame.sound.playA("ATTACK_FIRE", -0.4F);
        SpellCardsRule.torchLight.add(false);
    }

    public void midUnactivated(){
        this.activated = false;
        CardCrawlGame.sound.playA("ATTACK_FIRE", -0.4F);
    }

    public void render(SpriteBatch sb)
    {
        sb.setColor(this.color);
        sb.draw(img, this.x - img.packedWidth / 2, this.y - img.packedHeight / 2 - 24.0F * Settings.scale, img.packedWidth / 2.0F, img.packedHeight / 2.0F, img.packedWidth, img.packedHeight, this.scale, this.scale, this.rotation);

        this.hb.render(sb);
    }

    public void dispose(){}
}
