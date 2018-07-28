package Thmod.Orbs.ElementOrb.KenjiaOrb;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;

import Thmod.Cards.ElementCards.AbstractElementCards;

public class KenjiaWater extends AbstractKenjiaOrb {
    public static final String ORB_ID = "KenjiaWater";
    private static final OrbStrings orbString;
    public static final String NAME;
    public static final String DESCRIPTION[];
    private static Texture img1;

    public KenjiaWater() {
        this.ID = "KenjiaWater";
        this.name = KenjiaWater.NAME;
        this.elementType = AbstractElementCards.ElementType.Water;
        if (KenjiaWater.img1 == null) {
            KenjiaWater.img1 = ImageMaster.loadImage("images/orbs/KenjiaWater.png");
        }
        this.baseEvokeAmount = 0;
        this.evokeAmount = this.baseEvokeAmount;
        this.basePassiveAmount = 0;
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
        this.description = DESCRIPTION[0];
    }

    public void render(final SpriteBatch sb) {
        sb.setColor(this.c);
        sb.draw(KenjiaWater.img1, this.cX - 48.0f + this.bobEffect.y / 8.0f, this.cY - 48.0f - this.bobEffect.y / 8.0f, 48.0f, 48.0f, 96.0f, 96.0f, this.scale, this.scale, 0.0f, 0, 0, 96, 96, false, false);
        this.renderText(sb);
        this.hb.render(sb);
    }

    public AbstractOrb makeCopy()
    {
        return new KenjiaWater();
    }

    static {
        orbString = CardCrawlGame.languagePack.getOrbString("KenjiaWater");
        NAME = KenjiaWater.orbString.NAME;
        DESCRIPTION = KenjiaWater.orbString.DESCRIPTION;
    }
}
