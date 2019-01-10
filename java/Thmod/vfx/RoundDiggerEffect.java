package Thmod.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.AdditiveSlashImpactEffect;

import Thmod.Actions.Special.NewDamageAction;

public class RoundDiggerEffect extends AbstractGameEffect
{
    private static TextureAtlas.AtlasRegion img;
    private float x;
    private float y;
    private float sX;
    private float sY;
    private float tX;
    private float tY;
    private float targetAngle;
    private float startingAngle;
    private float targetScale;
    private boolean shownSlash = false;
    private boolean isRed;
    private AbstractCreature target;

    public RoundDiggerEffect(float x, float y, boolean isRed, AbstractCreature target)
    {
        if (img == null) {
            img = ImageMaster.vfxAtlas.findRegion("combat/spike");
        }
        float randomAngle = 0.017453292F * MathUtils.random(-50.0F, 230.0F);
        this.x = (MathUtils.cos(randomAngle) * MathUtils.random(200.0F, 600.0F) * Settings.scale + x);
        this.y = (MathUtils.sin(randomAngle) * MathUtils.random(200.0F, 500.0F) * Settings.scale + y);
        this.duration = 1.0F;
        this.scale = 0.01F;
        this.targetScale = 0.5F;

        this.targetAngle = (MathUtils.atan2(y - this.y, x - this.x) * 57.295776F + 90.0F);
        this.startingAngle = MathUtils.random(0.0F, 360.0F);
        this.rotation = this.startingAngle;

        this.x -= img.packedWidth / 2;
        this.y -= img.packedHeight / 2;
        this.sX = this.x;
        this.sY = this.y;
        this.tX = (x - img.packedWidth / 2);
        this.tY = (y - img.packedHeight / 2);

        this.isRed = isRed;
        this.target = target;

        if(isRed)
            this.color = Color.CORAL.cpy();
        else
            this.color = Color.SKY.cpy();
    }

    public void update()
    {
        this.rotation = Interpolation.elasticIn.apply(this.targetAngle + 90.0F, this.startingAngle + 90.0F, this.duration);
        if (this.duration > 0.5F)
        {
//            this.scale = (Interpolation.elasticIn.apply(this.targetScale, this.targetScale * 10.0F, (this.duration - 0.5F) * 2.0F) * Settings.scale);

            this.color.a = Interpolation.fade.apply(1F, 0.0F, (this.duration - 0.5F) * 2.0F);
        }
        else
        {
            this.x = Interpolation.exp10Out.apply(this.tX, this.sX, this.duration * 2.0F);
            this.y = Interpolation.exp10Out.apply(this.tY, this.sY, this.duration * 2.0F);
        }
        if ((this.duration < 0.05F) && (!this.shownSlash)) {
            AbstractDungeon.effectsQueue.add(new AdditiveSlashImpactEffect(this.tX + img.packedWidth / 2.0F, this.tY + img.packedHeight / 2.0F, this.color.cpy()));
            this.shownSlash = true;

            if ((!(target.isDying)) && (target.currentHealth > 0) && (!(target.isEscaping))) {
                AbstractDungeon.actionManager.addToTop(new NewDamageAction(target, new DamageInfo(AbstractDungeon.player, 1, DamageInfo.DamageType.THORNS), true));
            }

        }
        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration < 0.0F)
        {
            this.isDone = true;
            CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, MathUtils.randomBoolean());
            CardCrawlGame.sound.play("ATTACK_FAST", 0.2F);
        }
    }

    public void render(SpriteBatch sb)
    {
        sb.setBlendFunction(770, 1);
        sb.setColor(this.color);
        sb.draw(img, this.x, this.y, img.packedWidth / 2.0F, img.packedHeight / 2.0F, img.packedWidth, img.packedHeight, this.targetScale, this.targetScale, this.rotation);

        sb.draw(img, this.x, this.y, img.packedWidth / 2.0F, img.packedHeight / 2.0F, img.packedWidth, img.packedHeight, this.targetScale, this.targetScale, this.rotation);

        sb.setBlendFunction(770, 771);
    }

    public void dispose(){}
}
