package Thmod.Orbs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class YariNingyou extends AbstractOrb {
    public static final String ORB_ID = "YariNingyou";
    private static final OrbStrings orbString;
    public static final String NAME;
    public static final String DESCRIPTION[];
    private static Texture img1;
    private int StrNum;

    public YariNingyou() {
        this.ID = "YariNingyou";
        this.name = YariNingyou.NAME;
        if (YariNingyou.img1 == null) {
            YariNingyou.img1 = ImageMaster.loadImage("images/orbs/YariNingyou.png");
        }
        this.baseEvokeAmount = 1;
        this.evokeAmount = this.baseEvokeAmount;
        this.basePassiveAmount = 1;
        this.passiveAmount = this.basePassiveAmount;
        this.angle = MathUtils.random(360.0f);
        this.cX = AbstractDungeon.player.drawX + AbstractDungeon.player.hb_x;
        this.cY = AbstractDungeon.player.drawY + AbstractDungeon.player.hb_y + AbstractDungeon.player.hb_h / 2.0f;
        this.updateDescription();
        this.StrNum = 0;
//        this.showingEvoke = null;
    }

    public void updateDescription()
    {
        applyFocus();
        this.description = DESCRIPTION[0] + this.passiveAmount + DESCRIPTION[1];
    }

    public void onEndOfTurn()
    {
        AbstractPlayer p = AbstractDungeon.player;
        if(this.StrNum != this.passiveAmount) {
            if(this.StrNum < this.passiveAmount) {
                int addnum = (this.passiveAmount - this.StrNum);
                AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p, p, new StrengthPower(p,addnum),addnum));
            }
            if(this.StrNum > this.passiveAmount) {
                int reducenum = (this.StrNum - this.passiveAmount);
                AbstractDungeon.actionManager.addToTop(new ReducePowerAction(p,p,"Strength",reducenum));
            }
            this.StrNum = this.passiveAmount;
        }
    }

    public void onEvoke() {
        AbstractPlayer p = AbstractDungeon.player;
        AbstractDungeon.actionManager.addToTop(new ReducePowerAction(p,p,"Strength",this.StrNum));
    }

    public void updateAnimation() {
        super.updateAnimation();
        this.angle += Gdx.graphics.getDeltaTime() * 10.0f;
    }

    public void render(final SpriteBatch sb) {
        sb.setColor(this.c);
        sb.draw(YariNingyou.img1, this.cX - 48.0f + this.bobEffect.y / 8.0f, this.cY - 48.0f - this.bobEffect.y / 8.0f, 48.0f, 48.0f, 96.0f, 96.0f, this.scale, this.scale, 0.0f, 0, 0, 96, 96, false, false);
        this.renderText(sb);
        this.hb.render(sb);
    }

    public void playChannelSFX()
    {
    }

    public void applyFocus()
    {
        AbstractPower power = AbstractDungeon.player.getPower("Focus");
        if (power != null)
            this.passiveAmount = Math.max(0, this.basePassiveAmount + power.amount);
    }

    public AbstractOrb makeCopy()
    {
        return new YariNingyou();
    }

    static {
        orbString = CardCrawlGame.languagePack.getOrbString("YariNingyou");
        NAME = YariNingyou.orbString.NAME;
        DESCRIPTION = YariNingyou.orbString.DESCRIPTION;
    }
}
