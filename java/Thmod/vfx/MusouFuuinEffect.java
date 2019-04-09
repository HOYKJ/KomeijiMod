package Thmod.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

import java.util.Iterator;

import Thmod.Actions.Special.NewDamageAction;

public class MusouFuuinEffect extends AbstractGameEffect
{
    private float sX;
    private float sY;
    private float tX;
    private float tY;
    private float x;
    private float y;
    private float vY;
    private float vX;
    private TextureAtlas.AtlasRegion img;
    private AbstractCreature targetNew;
    private boolean activated = false;
    private DamageInfo info;
    private AbstractGameAction.AttackEffect attackEffect;
    private boolean souDamage = true;
    private Color setColor;

    public MusouFuuinEffect(float sX, float sY, Color color, DamageInfo info, AbstractGameAction.AttackEffect attackEffect)
    {
        this.img = ImageMaster.GLOW_SPARK_2;

        this.sX = (sX + MathUtils.random(-90.0F, 90.0F) * Settings.scale);
        this.sY = (sY + MathUtils.random(-90.0F, 90.0F) * Settings.scale);

        this.targetNew = AbstractDungeon.getMonsters().getRandomMonster(true);

        if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
            AbstractDungeon.actionManager.clearPostCombatActions();
            this.isDone = true;
            this.souDamage = false;
            return;
        }

        boolean hasAlive = false;
        for (AbstractMonster m:AbstractDungeon.getCurrRoom().monsters.monsters){
            if (!m.halfDead && !m.isDying && !m.isEscaping) {
                hasAlive = true;
            }
        }

        if(!hasAlive) {
            AbstractDungeon.actionManager.clearPostCombatActions();
            this.isDone = true;
            this.souDamage = false;
            return;
        }

        this.tX = (this.targetNew.hb.cX + MathUtils.random(-50.0F, 50.0F) * Settings.scale);
        this.tY = (this.targetNew.hb.cY + MathUtils.random(-50.0F, 50.0F) * Settings.scale);
        this.vX = (this.sX + MathUtils.random(-200.0F, 200.0F) * Settings.scale);
        this.vY = (this.sY + MathUtils.random(-200.0F, 200.0F) * Settings.scale);
        this.x = this.sX;
        this.y = this.sY;

        this.scale = 0.01F;
        this.startingDuration = 1.6F;
        this.duration = this.startingDuration;
//        this.renderBehind = MathUtils.randomBoolean(0.2F);
//        this.color = new Color(1.0F, 0.1F, MathUtils.random(0.2F, 0.5F), 1.0F);
        this.setColor = color;

        this.info = info;
        this.attackEffect = attackEffect;
    }

    public void update()
    {
        if (this.duration > this.startingDuration / 2.0F)
        {
            this.scale = (Interpolation.pow3In.apply(2.5F, 0.01F, (this.duration - this.startingDuration / 2.0F) / (this.startingDuration / 2.0F)) * Settings.scale);

            this.x = Interpolation.swingIn.apply(this.vX, this.sX, (this.duration - this.startingDuration / 2.0F) / (this.startingDuration / 2.0F));
            this.y = Interpolation.swingIn.apply(this.vY, this.sY, (this.duration - this.startingDuration / 2.0F) / (this.startingDuration / 2.0F));
        }
        else
        {
            this.scale = (Interpolation.pow3Out.apply(2.0F, 2.5F, this.duration / (this.startingDuration / 2.0F)) * Settings.scale);

            this.x = Interpolation.swingOut.apply(this.tX, this.vX, this.duration / (this.startingDuration / 2.0F));
            this.y = Interpolation.swingOut.apply(this.tY, this.vY, this.duration / (this.startingDuration / 2.0F));
        }
        this.duration -= Gdx.graphics.getDeltaTime();
//        if ((this.duration < this.startingDuration / 2.0F) && (!this.activated))
//        {
//            this.activated = true;
//            this.sX = this.x;
//            this.sY = this.y;
//        }
        if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
            AbstractDungeon.actionManager.clearPostCombatActions();
            this.isDone = true;
            this.souDamage = false;
            return;
        }

        boolean hasAlive = false;
        for (AbstractMonster m:AbstractDungeon.getCurrRoom().monsters.monsters){
            if (!m.halfDead && !m.isDying && !m.isEscaping) {
                hasAlive = true;
            }
        }

        if(!hasAlive) {
            AbstractDungeon.actionManager.clearPostCombatActions();
            this.isDone = true;
            this.souDamage = false;
            return;
        }

        if((targetNew.isDying) || (targetNew.halfDead) || (targetNew.isEscaping)){
            targetNew = AbstractDungeon.getMonsters().getRandomMonster(true);
            this.tX = (targetNew.hb.cX + MathUtils.random(-50.0F, 50.0F) * Settings.scale);
            this.tY = (targetNew.hb.cY + MathUtils.random(-50.0F, 50.0F) * Settings.scale);
        }

        if (this.duration < 0.0F)
        {
            if(this.souDamage)
                AbstractDungeon.actionManager.addToTop(new NewDamageAction(this.targetNew, this.info, this.attackEffect, true, false, true));
            this.isDone = true;
        }
    }

    public void render(SpriteBatch sb)
    {
        sb.setBlendFunction(770, 1);

        sb.setColor(this.setColor);
        sb.draw(this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale * 3.5F, this.scale * 3.5F, this.rotation);

        Color highLight = Color.WHITE.cpy();
        highLight.a = 0.3F;
        sb.setColor(highLight);
        sb.draw(this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale * 3.5F, this.scale * 3.5F, this.rotation);

        sb.setBlendFunction(770, 771);
    }

    public void dispose(){}
}
