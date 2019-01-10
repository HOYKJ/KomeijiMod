package Thmod.Power;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import basemod.DevConsole;

public class MyWorldPower extends AbstractPower {
    public static final String POWER_ID = "MyWorldPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("MyWorldPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private AbstractPlayer p = AbstractDungeon.player;
    private Color timeLock;
    private Color timeLock2;
    private boolean readyToRemove;

    public MyWorldPower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = "MyWorldPower";
        this.owner = owner;
        this.amount = -1;
        updateDescription();
        this.type = AbstractPower.PowerType.BUFF;
        this.img = ImageMaster.loadImage("images/power/32/WocchiPower.png");
        this.timeLock = Color.DARK_GRAY.cpy();
        this.timeLock.a = 0.0F;
        this.timeLock2 = this.timeLock.cpy();
        this.readyToRemove = false;
    }

    public void atEndOfRound()
    {
        this.readyToRemove = true;
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0];
    }

    @Override
    public void renderIcons(SpriteBatch sb, float x, float y, Color c) {
        super.renderIcons(sb, x, y, c);
        if(this.readyToRemove){
            if(this.timeLock.a > 0.0F){
                this.timeLock.a -= Gdx.graphics.getDeltaTime() / 3;
            }
            if(this.timeLock2.a > 0.0F){
                this.timeLock2.a -= Gdx.graphics.getDeltaTime() / 2;
            }
            if(this.timeLock.a <= 0.0F && this.timeLock2.a <= 0.0F){
                AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, this));
            }
        }
        else {
            if (this.timeLock.a < (0.6F)) {
                this.timeLock.a += Gdx.graphics.getDeltaTime() / 3;
            }
            if (this.timeLock2.a < (Color.DARK_GRAY.cpy().a)) {
                this.timeLock2.a += Gdx.graphics.getDeltaTime() / 2;
            }
            if(this.timeLock2.a > 1.0F)
                this.timeLock2.a = 1.0F;
        }

        sb.setColor(this.timeLock);
        sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0.0F, 0.0F, (float) Settings.WIDTH, (float)Settings.HEIGHT);

        sb.setBlendFunction(770, 1);
        sb.setColor(this.timeLock2);
        sb.draw(ImageMaster.BORDER_GLOW_2, 0F, 0F, Settings.WIDTH, Settings.HEIGHT);
        sb.setBlendFunction(770, 771);
    }
}
