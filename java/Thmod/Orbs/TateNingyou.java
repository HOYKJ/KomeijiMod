package Thmod.Orbs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;

public class TateNingyou extends AbstractOrb {
    public static final String ORB_ID = "TateNingyou";
    private static final OrbStrings orbString;
    public static final String NAME;
    public static final String DESCRIPTION[];
    private static Texture img1;

    public TateNingyou() {
        this.ID = "TateNingyou";
        this.name = TateNingyou.NAME;
        if (TateNingyou.img1 == null) {
            TateNingyou.img1 = ImageMaster.loadImage("images/orbs/TateNingyou.png");
        }
        this.baseEvokeAmount = 0;
        this.evokeAmount = this.baseEvokeAmount;
        this.basePassiveAmount = 3;
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

    public void onEndOfTurn()
    {
        AbstractPlayer p = AbstractDungeon.player;

        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.passiveAmount, true));
    }

    public void onEvoke() {

    }

    public void updateAnimation() {
        super.updateAnimation();
        this.angle += Gdx.graphics.getDeltaTime() * 10.0f;
    }

    public void render(final SpriteBatch sb) {
        sb.setColor(this.c);
        sb.draw(TateNingyou.img1, this.cX - 48.0f + this.bobEffect.y / 8.0f, this.cY - 48.0f - this.bobEffect.y / 8.0f, 48.0f, 48.0f, 96.0f, 96.0f, this.scale, this.scale, 0.0f, 0, 0, 96, 96, false, false);
        this.renderText(sb);
        this.hb.render(sb);
    }

    public void playChannelSFX()
    {
    }

    public AbstractOrb makeCopy()
    {
        return new TateNingyou();
    }

    static {
        orbString = CardCrawlGame.languagePack.getOrbString("TateNingyou");
        NAME = TateNingyou.orbString.NAME;
        DESCRIPTION = TateNingyou.orbString.DESCRIPTION;
    }
}
