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
import com.megacrit.cardcrawl.powers.DexterityPower;

import Thmod.Cards.ElementCards.AbstractElementCards;
import basemod.DevConsole;

public class LunaOrb extends AbstractElementOrb {
    public static final String ORB_ID = "LunaOrb";
    private static final OrbStrings orbString;
    public static final String NAME;
    public static final String DESCRIPTION[];
    private static Texture img1;
    private int DexNum;

    public LunaOrb() {
        this.ID = "LunaOrb";
        this.name = LunaOrb.NAME;
        this.elementType = AbstractElementCards.ElementType.Luna;
        if (LunaOrb.img1 == null) {
            LunaOrb.img1 = ImageMaster.loadImage("images/orbs/LunaOrb.png");
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
        AbstractDungeon.actionManager.addToTop(new ReducePowerAction(p,p,"Dexterity",this.DexNum));
    }

    public void render(final SpriteBatch sb) {
        sb.setColor(this.c);
        sb.draw(LunaOrb.img1, this.cX - 48.0f + this.bobEffect.y / 8.0f, this.cY - 48.0f - this.bobEffect.y / 8.0f, 48.0f, 48.0f, 96.0f, 96.0f, this.scale, this.scale, 0.0f, 0, 0, 96, 96, false, false);
        this.renderText(sb);
        this.hb.render(sb);
    }

    public void applyFocus()
    {
        super.applyFocus();
        AbstractPlayer p = AbstractDungeon.player;
        if(this.DexNum != this.passiveAmount) {
            if(this.DexNum < this.passiveAmount) {
                int addnum = (this.passiveAmount - this.DexNum);
                AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p, p, new DexterityPower(p,addnum),addnum));
            }
            else if(this.DexNum > this.passiveAmount) {
                int reducenum = (this.DexNum - this.passiveAmount);
                AbstractDungeon.actionManager.addToTop(new ReducePowerAction(p,p,"Dexterity",reducenum));
            }
            this.DexNum = this.passiveAmount;
            DevConsole.logger.info("DexNum"+this.DexNum);
        }
    }

    public AbstractOrb makeCopy()
    {
        return new LunaOrb();
    }

    static {
        orbString = CardCrawlGame.languagePack.getOrbString("LunaOrb");
        NAME = LunaOrb.orbString.NAME;
        DESCRIPTION = LunaOrb.orbString.DESCRIPTION;
    }
}
