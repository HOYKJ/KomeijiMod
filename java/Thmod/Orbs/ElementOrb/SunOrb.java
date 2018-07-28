package Thmod.Orbs.ElementOrb;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.StrengthPower;

import Thmod.Cards.ElementCards.AbstractElementCards;
import basemod.DevConsole;

public class SunOrb extends AbstractElementOrb {
    public static final String ORB_ID = "SunOrb";
    private static final OrbStrings orbString;
    public static final String NAME;
    public static final String DESCRIPTION[];
    private static Texture img1;
    private int StrNum;

    public SunOrb() {
        this.ID = "SunOrb";
        this.name = SunOrb.NAME;
        this.elementType = AbstractElementCards.ElementType.Sun;
        if (SunOrb.img1 == null) {
            SunOrb.img1 = ImageMaster.loadImage("images/orbs/SunOrb.png");
        }
        this.baseEvokeAmount = 0;
        this.evokeAmount = this.baseEvokeAmount;
        this.basePassiveAmount = 1;
        this.passiveAmount = this.basePassiveAmount;
        this.angle = MathUtils.random(360.0f);
        this.cX = AbstractDungeon.player.drawX + AbstractDungeon.player.hb_x;
        this.cY = AbstractDungeon.player.drawY + AbstractDungeon.player.hb_y + AbstractDungeon.player.hb_h / 2.0f;
        this.updateDescription();
//        this.showingEvoke = null;
    }

    public void updateDescription()
    {
        applyFocus();
        this.description = DESCRIPTION[0] + this.passiveAmount + DESCRIPTION[1];
    }

    public void onEvoke() {
        AbstractPlayer p = AbstractDungeon.player;
        AbstractDungeon.actionManager.addToTop(new ReducePowerAction(p,p,"Strength",this.StrNum));
    }

    public void render(final SpriteBatch sb) {
        sb.setColor(this.c);
        sb.draw(SunOrb.img1, this.cX - 48.0f + this.bobEffect.y / 8.0f, this.cY - 48.0f - this.bobEffect.y / 8.0f, 48.0f, 48.0f, 96.0f, 96.0f, this.scale, this.scale, 0.0f, 0, 0, 96, 96, false, false);
        this.renderText(sb);
        this.hb.render(sb);
    }

    public void applyFocus()
    {
        super.applyFocus();
        AbstractPlayer p = AbstractDungeon.player;
        if(this.StrNum != this.passiveAmount) {
            if(this.StrNum < this.passiveAmount) {
                int addnum = (this.passiveAmount - this.StrNum);
                AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p, p, new StrengthPower(p,addnum),addnum));
            }
            else if(this.StrNum > this.passiveAmount) {
                int reducenum = (this.StrNum - this.passiveAmount);
                AbstractDungeon.actionManager.addToTop(new ReducePowerAction(p,p,"Strength",reducenum));
            }
            this.StrNum = this.passiveAmount;
            DevConsole.logger.info("StrNum"+this.StrNum);
        }
    }

    public AbstractOrb makeCopy()
    {
        return new SunOrb();
    }

    static {
        orbString = CardCrawlGame.languagePack.getOrbString("SunOrb");
        NAME = SunOrb.orbString.NAME;
        DESCRIPTION = SunOrb.orbString.DESCRIPTION;
    }
}
