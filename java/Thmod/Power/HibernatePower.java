package Thmod.Power;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.BobEffect;

import java.util.ArrayList;
import java.util.Iterator;

import Thmod.Actions.common.LatterAction;
import Thmod.Actions.unique.TurnEndAction;
import Thmod.vfx.LatterEffect;

public class HibernatePower extends AbstractPower {
    public static final String POWER_ID = "HibernatePower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("HibernatePower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private int healnum;
    private AbstractPlayer p = AbstractDungeon.player;
    private Color intentColor;
    private float intentAlpha;
    private float intentAlphaTarget;
    private float intentSize;
    private float intentSizeTarget;
    private Texture intentImg;

    public HibernatePower(AbstractCreature owner, int Amount) {
        this.name = NAME;
        this.ID = "HibernatePower";
        this.owner = owner;
        this.amount = 3;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/HibernatePower.png");
        this.type = PowerType.BUFF;
        this.healnum = Amount;
        this.intentAlpha = 0.0F;
        this.intentAlphaTarget = 1.0F;
        this.intentSize = 1.0F;
        this.intentSizeTarget = 1.2F;
        this.intentImg = ImageMaster.INTENT_SLEEP;
    }

    public void onInitialApplication() {
        AbstractDungeon.player.state.setAnimation(0,"Close",false);
        AbstractDungeon.effectList.add(new LatterEffect(() ->{AbstractDungeon.player.state.setAnimation(0,"Closed",true);},1.67F));
    }

    public void onRemove() {
        AbstractDungeon.player.state.setAnimation(0,"Normal",true);
    }

    @Override
    public void onVictory() {
        AbstractDungeon.player.state.setAnimation(0,"Normal",true);
    }

    public void atStartOfTurnPostDraw() {
        AbstractDungeon.actionManager.addToBottom(new TurnEndAction());
    }

    public void atEndOfTurn(boolean isPlayer) {
        if(isPlayer){
            if(this.amount > 1){
                p.heal(this.healnum);
                this.amount -= 1;
            }
            else if(this.amount == 1)
                AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner,this.owner,"HibernatePower"));
        }
    }

    @Override
    public void renderIcons(SpriteBatch sb, float x, float y, Color c) {
        super.renderIcons(sb, x, y, c);
        sb.setColor(c);
        this.intentColor = c;

        if (this.intentAlpha != this.intentAlphaTarget && this.intentAlphaTarget == 1.0F) {
            this.intentAlpha += Gdx.graphics.getDeltaTime() / 5;
            if (this.intentAlpha > this.intentAlphaTarget) {
                this.intentAlpha = this.intentAlphaTarget;
                this.intentAlphaTarget = 0.0F;
            }
        }
        else if (this.intentAlpha != this.intentAlphaTarget && this.intentAlphaTarget == 0.0F){
            this.intentAlpha -= Gdx.graphics.getDeltaTime() / 5;
            if (this.intentAlpha < this.intentAlphaTarget) {
                this.intentAlpha = this.intentAlphaTarget;
                this.intentAlphaTarget = 1.0F;
            }
        }

        if (this.intentSize != this.intentSizeTarget && this.intentSizeTarget == 1.2F) {
            this.intentSize += Gdx.graphics.getDeltaTime() / 5;
            if (this.intentSize > this.intentSizeTarget) {
                this.intentSize = this.intentSizeTarget;
                this.intentSizeTarget = 1.0F;
            }
        }
        else if (this.intentSize != this.intentSizeTarget && this.intentSizeTarget == 1.0F){
            this.intentSize -= Gdx.graphics.getDeltaTime() / 8;
            if (this.intentSize < this.intentSizeTarget) {
                this.intentSize = this.intentSizeTarget;
                this.intentSizeTarget = 1.2F;
            }
        }

        this.intentColor.a = this.intentAlpha;
        if (this.intentImg != null) {

            sb.setColor(this.intentColor);
            sb.draw(this.intentImg, this.owner.hb.cX - 80.0F, this.owner.hb.cY + 40.0F, 64.0F, 64.0F, 128.0F, 128.0F, Settings.scale * this.intentSize, Settings.scale * this.intentSize, 0.0F, 0, 0, 128, 128, false, false);
        }

        sb.setColor(c);
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0];
    }
}
