package Thmod.Power.Weather;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import Thmod.vfx.weather.NagiEffect;

public class Nagi extends AbstractPower {
    public static final String POWER_ID = "Nagi";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Nagi");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
//    private Color nagi;
//    private Texture img2;
    private NagiEffect effect;

    public Nagi(AbstractCreature owner) {
        this.name = NAME;
        this.ID = "Nagi";
        this.owner = owner;
        this.amount = 5;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/Weather/Nagi.png");
        this.type = PowerType.BUFF;
//        this.nagi = new Color(1.0F, 1.0F, 0.8F, 0.5F);
//        this.img2 = ImageMaster.loadImage("images/vfx/spotlight.jpg");
        this.effect = new NagiEffect();
        this.effect.initializeData();
        AbstractDungeon.effectList.add(this.effect);
    }

    public void atEndOfTurn(boolean isPlayer) {
        if(isPlayer){
            AbstractDungeon.player.heal(1);
            flash();
        }
    }

    public void atEndOfRound() {
        if (this.amount <= 1)
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "Nagi"));
        else
            this.amount -= 1;
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0];
    }

//    public void renderIcons(SpriteBatch sb, float x, float y, Color c) {
//        super.renderIcons(sb, x, y, c);
//
//        sb.setColor(this.nagi);
//        sb.setBlendFunction(770, 1);
//        sb.draw(this.img2, AbstractDungeon.player.drawX - AbstractDungeon.player.hb_w / 2 - 50, AbstractDungeon.floorY,
//                AbstractDungeon.player.hb_w + 100, Settings.HEIGHT - AbstractDungeon.floorY);
//        sb.setBlendFunction(770, 771);
//    }


    @Override
    public void onRemove() {
        super.onRemove();
        this.effect.remove();
    }
}
